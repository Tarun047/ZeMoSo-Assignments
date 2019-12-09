package com.tarun.talkbuddy;

import com.tarun.talkbuddy.controller.AssignmentController;
import com.tarun.talkbuddy.controller.InternController;
import com.tarun.talkbuddy.controller.ProfileController;
import com.tarun.talkbuddy.controller.TaskController;
import com.tarun.talkbuddy.model.Assignment;
import com.tarun.talkbuddy.model.Intern;
import com.tarun.talkbuddy.model.Profile;
import com.tarun.talkbuddy.model.Task;
import com.tarun.talkbuddy.model.enums.AssignmentStatus;
import com.tarun.talkbuddy.model.enums.RoleType;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource("classpath:test.properties")
public class TalkBuddyApplicationTests {
	@Autowired
	InternController internController;

	@Autowired
	AssignmentController assignmentController;

	@Autowired
	TaskController taskController;

	@Autowired
	ProfileController profileController;

	@Autowired
	TestRestTemplate restTemplate;

	final private int port=8080;

	final private String url="http://localhost:"+port;


	static Task[] tasks;
	static Intern[] interns;
	static Assignment[] assignments;
	static AtomicInteger currentIndex = new AtomicInteger(0);

	@BeforeClass
	public static void initAll()
	{
		tasks = new Task[100];
		interns = new Intern[100];
		assignments = new Assignment[100];
	}

	@Before
	public void init()
	{
		Intern intern = (Intern)Helper.populate(new Intern(),Intern.class);

		Task task = (Task)Helper.populate(new Task(),Task.class);


		Assignment assignment = new Assignment();
		assignment.setIntern(intern);
		assignment.setTask(task);

		int idx = currentIndex.get();
		interns[idx] = intern;
		tasks[idx] = task;
		assignments[idx] = assignment;
		currentIndex.set((idx+1)%100);
	}

	public Intern getCurrentIntern()
	{
		return interns[currentIndex.get() - 1];
	}

	public Assignment getCurrentAssignment()
	{
		return assignments[currentIndex.get()-1];
	}

	public Task getCurrentTask()
	{
		return tasks[currentIndex.get()-1];
	}



	@Test
	public void contextLoads() {
		assertThat(internController).isNotNull();
		assertThat(assignmentController).isNotNull();
		assertThat(taskController).isNotNull();
		assertThat(profileController).isNotNull();
	}

	@Test
	public void testAdditionEndPoints() {

		String uid = (String)Helper.getRandVal(String.class);
		int internSize = internController.findAll().size();
		int taskSize = taskController.findAll().size();
		int assignmentSize  = assignmentController.findAll().size();

		//Successful Requests
		ResponseEntity<Intern> internResponse = restTemplate.postForEntity(url+ String.format("/api/interns/createintern/%s", uid),getCurrentIntern(),Intern.class);
		assertTrue(internResponse.getStatusCode().is2xxSuccessful());

		ResponseEntity<Task> taskResponse = restTemplate.postForEntity(url+"/api/tasks/add_task",getCurrentTask(),Task.class);
		assertTrue(taskResponse.getStatusCode().is2xxSuccessful());


		ResponseEntity<Intern> assignmentResponse = restTemplate.postForEntity(
				url+String.format("/api/interns/%s/assign_task?taskIds=%s",internResponse.getBody().getId(),
						taskResponse.getBody().getId()),
				null,
				Intern.class
		);

		assertTrue(assignmentResponse.getStatusCode().is2xxSuccessful());

		assertEquals(internSize+1,internController.findAll().size());
		assertEquals(taskSize+1,taskController.findAll().size());
		assertEquals(assignmentSize+1,assignmentController.findAll().size());
	}

	@Test
	public void testDelete()
	{
		long internId = internController.createIntern((String)Helper.getRandVal(String.class),getCurrentIntern()).getId();
		long taskId = taskController.createTask(getCurrentTask()).getId();

		int initialTaskSize = taskController.findAll().size();
		int initalInternSize = internController.findAll().size();
		ResponseEntity<Intern> assignmentResponse = restTemplate.postForEntity(
				url+String.format("/api/interns/%s/assign_task?taskIds=%s",internId,
						 taskId),
				null,
				Intern.class
		);

		int initialAssignmentSize = assignmentController.findAll().size();

		assertTrue(assignmentResponse.getStatusCode().is2xxSuccessful());

		restTemplate.delete(url+String.format("/api/interns/removeintern/%s",internId));
		assertEquals(initalInternSize-1,internController.findAll().size());
		assertEquals(initialAssignmentSize-1,assignmentController.findAll().size());
		restTemplate.delete(url+String.format("/api/tasks/remove/%s",taskId));
		assertEquals(initialTaskSize-1,taskController.findAll().size());



		long secondInternId = internController.createIntern((String)Helper.getRandVal(String.class),getCurrentIntern()).getId();
		long secondTaskId = taskController.createTask(getCurrentTask()).getId();

		restTemplate.postForEntity(
				url+String.format("/api/interns/%s/assign_task?taskIds=%s",secondInternId,
						secondTaskId),
				null,
				Intern.class
		);

		int assignmentsSize = assignmentController.findAll().size();

		restTemplate.delete(url+String.format("/api/interns/%s/remove_task?taskIds=%s",secondInternId,Objects.hash(secondInternId,secondTaskId)));
		assertEquals(assignmentsSize-1,assignmentController.findAll().size());

	}

	@Test
	public void changeAssignmentStatus()
	{
		String uid = (String)Helper.getRandVal(String.class);
		long internId = internController.createIntern(uid,getCurrentIntern()).getId();
		long taskId = taskController.createTask(getCurrentTask()).getId();
		ResponseEntity<Intern> assignmentResponse = restTemplate.postForEntity(
				url+String.format("/api/interns/%s/assign_task?taskIds=%s",internId,
						taskId),
				null,
				Intern.class
		);

		assertTrue(assignmentResponse.getStatusCode().is2xxSuccessful());

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("uid",uid);
		httpHeaders.add("assignmentId",Long.toString(Objects.hash(internId,taskId)));
		HttpEntity<AssignmentStatus> requestEntity = new HttpEntity<>(AssignmentStatus.CLOSED,httpHeaders);
		Assignment assignmentUpdated = restTemplate.postForObject(
				url+"/api/assignments/update",requestEntity,Assignment.class);
		Predicate<Assignment> predicate = assignment -> assignment.getId()==assignmentUpdated.getId();
		assertEquals(assignmentController.findAll().stream().filter(predicate).findAny().get().getStatus(),AssignmentStatus.CLOSED);
	}

	@Test
	public void testGetEndPoints()
	{

		String uid = (String)Helper.getRandVal(String.class);
		long internId = internController.createIntern(uid,getCurrentIntern()).getId();
		long taskId = taskController.createTask(getCurrentTask()).getId();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("uid",uid);
		HttpEntity<Profile> httpEntity = new HttpEntity<>(null,httpHeaders);
		ResponseEntity<Profile> profileResponseEntity = restTemplate.exchange(
				url+"/api/roles/rolelevel",HttpMethod.GET,httpEntity,Profile.class);
		assertTrue(profileResponseEntity.getStatusCode().is2xxSuccessful());
		assertTrue(profileResponseEntity.hasBody());
		assertEquals(profileResponseEntity.getBody().getRole(), RoleType.INTERN);

		ResponseEntity<Intern> internResponseEntity = restTemplate.getForEntity(
				url+String.format("/api/interns/%s",internId),Intern.class);
		assertTrue(internResponseEntity.getStatusCode().is2xxSuccessful());
		assertEquals(internResponseEntity.getBody().getId(),internId);

		ResponseEntity<Task> taskResponseEntity = restTemplate.getForEntity(
				url+String.format("/api/tasks/%s",taskId),
				Task.class
		);
		assertTrue(taskResponseEntity.getStatusCode().is2xxSuccessful());
		assertEquals(taskResponseEntity.getBody().getId(),taskId);


		HttpHeaders headers = new HttpHeaders();
		httpHeaders.add("uid",(String)Helper.getRandVal(String.class));
		HttpEntity<Profile> profileResponse = new HttpEntity<>(null,headers);
		ResponseEntity<Profile> unrecognizedProfile = restTemplate.exchange(
				url+"/api/roles/rolelevel",HttpMethod.GET,httpEntity,Profile.class);

		assertTrue(unrecognizedProfile.getStatusCode().is2xxSuccessful());
		assertTrue(unrecognizedProfile.hasBody());
		assertEquals(RoleType.UNRECOGNIZED_USER,unrecognizedProfile.getBody().getRole());
	}

}
