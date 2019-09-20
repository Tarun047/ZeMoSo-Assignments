package com.tarun.TalkBuddy;


import com.tarun.TalkBuddy.model.Assignment;
import com.tarun.TalkBuddy.model.Intern;
import com.tarun.TalkBuddy.model.Task;
import com.tarun.TalkBuddy.service.AssignmentService;
import com.tarun.TalkBuddy.service.InternService;
import com.tarun.TalkBuddy.service.TaskService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.SerializationUtils;

import javax.validation.ConstraintViolationException;
import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:test.properties")
@SpringBootTest
public class JUnitTest
{
    static Task tasks[];
    static Intern interns[];
    static Assignment assignments[];
    AtomicInteger currentIndex = new AtomicInteger(0);

    @Autowired
    private InternService internService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private AssignmentService assignmentService;

    String getRandomString(int size)
    {
        StringBuilder sb = new StringBuilder(size);
        for(int i=0;i<size;i++)
            sb.append((char)('a'+getRandomInt(25)));
        return sb.toString();
    }

    int getRandomInt(int bound)
    {
        return new Random().nextInt(bound);
    }

    Date getRandomDate()
    {
        return Date.from(Instant.now().plus(Duration.ofDays(1+new Random().nextInt(364))));
    }

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
        Intern intern = new Intern();
        intern.setName(getRandomString(getRandomInt(100)));
        intern.setRating(getRandomInt(10));

        Task task = new Task();
        task.setTaskName(getRandomString(getRandomInt(10)));
        task.setDescription(getRandomString(getRandomInt(100)));
        task.setDeadline(getRandomDate());
        task.setCreatedAt(Date.from(Instant.now()));

        Assignment assignment = new Assignment();
        assignment.setIntern(intern);
        assignment.setTask(task);


        int currentIndex = this.currentIndex.get();
        interns[currentIndex] = intern;
        tasks[currentIndex] = task;
        assignments[currentIndex] = assignment;
        this.currentIndex.set((currentIndex+1)%100);
    }

    public Intern getCurrentIntern()
    {
        return interns[currentIndex.get()-1];
    }

    public Assignment getCurrentAssignment()
    {
        return assignments[currentIndex.get()-1];
    }

    public Task getCurrentTask()
    {
        return tasks[currentIndex.get()-1];
    }

    public Object clone(Serializable originalObject)
    {
        return SerializationUtils.deserialize(SerializationUtils.serialize(originalObject));
    }

    @Test
    /*
    Test method to check if the Unique Key Constraints and Primary Key Constraints are in place or not());
    */
    public void testDuplicates()
    {

        //Add original Object
        internService.addIntern(getCurrentIntern());
        taskService.addTask(getCurrentTask());

        //Get a copy of the original objects
        Intern dupIntern = getCurrentIntern().copy();
        Task dupTask = getCurrentTask().copy();

        //Make sure copies aren't same
        assertNotEquals(getCurrentIntern(),dupIntern);
        assertNotEquals(getCurrentTask(),dupTask);

        //Try to add Duplicate Object
        assertThrows(DataIntegrityViolationException.class,()->internService.addIntern(getCurrentIntern()));
        assertThrows(Exception.class,()->taskService.addTask(getCurrentTask()));


    }

    @Test
    public void testInvalidEntry()
    {
        //Initialize all Not Null attributes to null
        Intern currentIntern = getCurrentIntern();
        currentIntern.setName(null);

        Task currentTask = getCurrentTask();
        currentIntern.setName(null);
        currentTask.setDescription(null);

        Assignment currentAssignment = getCurrentAssignment();
        currentAssignment.setIntern(null);
        currentAssignment.setTask(null);

        //Try to add the invalid objects
        assertThrows(ConstraintViolationException.class,()->internService.addIntern(currentIntern));
        Exception e1 = assertThrows(Exception.class,()->taskService.addTask(currentTask));
        Exception e2 = assertThrows(Exception.class,()->assignmentService.addAssignment(currentAssignment));
        System.out.println(e1.getMessage()+" "+e2.getMessage());

    }

    @Test
    public void testDelete()
    {
        //Add test objects
        internService.addIntern(getCurrentIntern());
        taskService.addTask(getCurrentTask());
        assignmentService.addAssignment(getCurrentAssignment());

        //Track the previous size
        int prevSizeOfTask = taskService.findAll().size(),prevSizeOfAssignment = assignmentService.findAll().size();

        //Remove the current task
        taskService.removeTask(getCurrentTask());

        //Check if removal is persistant
        assertEquals(prevSizeOfTask-1,taskService.findAll().size());
        assertEquals(prevSizeOfAssignment-1,assignmentService.findAll().size());
    }




}
