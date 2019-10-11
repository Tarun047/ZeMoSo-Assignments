package com.tarun.TalkBuddy;


import com.tarun.TalkBuddy.model.Assignment;
import com.tarun.TalkBuddy.model.Intern;
import com.tarun.TalkBuddy.model.Task;
import com.tarun.TalkBuddy.service.interfaces.AssignmentService;
import com.tarun.TalkBuddy.service.interfaces.InternService;
import com.tarun.TalkBuddy.service.interfaces.TaskService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.SerializationUtils;

import javax.validation.ConstraintViolationException;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:test.properties")
@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ServiceTests
{
    private static Task[] tasks;
    private static Intern[] interns;
    private static Assignment[] assignments;
    private static AtomicInteger currentIndex = new AtomicInteger(0);

    @Autowired
    private InternService internService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private AssignmentService assignmentService;

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
        assignmentService.addAssignment(getCurrentAssignment());

        //Get a copy of the original objects
        Intern dupIntern = getCurrentIntern().copy();
        Task dupTask = getCurrentTask().copy();
        Assignment dupAssignment = getCurrentAssignment().copy();
        //Make sure copies aren't same
        assertNotEquals(getCurrentIntern(),dupIntern);
        assertNotEquals(getCurrentTask(),dupTask);


        //Try to add Duplicate Object
        assertThrows(DataIntegrityViolationException.class,()->internService.addIntern(dupIntern));
        assertThrows(DataIntegrityViolationException.class,()->taskService.addTask(dupTask));
        assertThrows(DataIntegrityViolationException.class,()->assignmentService.addAssignment(dupAssignment));
    }

    @Test
    @DisplayName("Test if invalid entries are accepted")
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
        assertThrows(ConstraintViolationException.class,()->taskService.addTask(currentTask));
        //We can't get an ID if intern and task are not set
        assertThrows(JpaSystemException.class,()->assignmentService.addAssignment(currentAssignment));

    }

    @Test
    @DisplayName("Test if deletion is uniform")
    public void testDelete()
    {
        /* Deletion from Task Side */
        //Add test objects
        internService.addIntern(getCurrentIntern());
        taskService.addTask(getCurrentTask());
        assignmentService.addAssignment(getCurrentAssignment());

        //Track the previous size
        int prevSizeOfTask = taskService.findAll().size(),prevSizeOfAssignment = assignmentService.findAll().size();
        int prevSizeOfInternSet = getCurrentIntern().getAssignments().size();
        //Remove the current task
        taskService.removeTask(getCurrentTask());

        //Check if removal is persistant
        assertEquals(prevSizeOfTask-1,taskService.findAll().size());
        assertEquals(prevSizeOfAssignment-1,assignmentService.findAll().size());


        /* Deletion from Intern Side */
        taskService.addTask(getCurrentTask());
        assignmentService.addAssignment(getCurrentAssignment());

        //Track previous size
        int prevSizeOfAssignments = assignmentService.findAll().size(),
                internAssignmentsSize =  internService.getAssignments(getCurrentIntern().getId()).size();

        //Remove the current Intern
        internService.removeIntern(getCurrentIntern());
        assertEquals(prevSizeOfAssignments-internAssignmentsSize,assignmentService.findAll().size());
    }

    @Test
    @DisplayName("Test for assignment deletion side effects")
    public void testIsolation()
    {
        internService.addIntern(getCurrentIntern());
        taskService.addTask(getCurrentTask());
        assignmentService.addAssignment(getCurrentAssignment());

        Intern secondIntern = (Intern)Helper.populate(new Intern(),Intern.class);

        Assignment secondAssignment = new Assignment();
        secondAssignment.setIntern(secondIntern);

        //Adding a common task for two interns
        secondAssignment.setTask(getCurrentTask());

        //Add the secondary entities
        internService.addIntern(secondIntern);
        assignmentService.addAssignment(secondAssignment);

        //Track previous size
        int oldSize = assignmentService.findAll().size();

        //Deletion of assignment 1 must not affect assignment 2
        assignmentService.removeAssignment(getCurrentAssignment());

        assertEquals(oldSize-1,assignmentService.findAll().size());
        assertEquals(1,internService.getAssignments(secondIntern.getId()).size());

        //Deletion of intern 1 must not affect intern 2
        assignmentService.addAssignment(getCurrentAssignment());

        int prevSize = internService.getAssignments(secondIntern.getId()).size(),
            prevAssignmentSize=assignmentService.findAll().size(),
            prevInternSize = internService.findAll().size();

        internService.removeIntern(getCurrentIntern().getId());
        assertEquals(prevInternSize-1,internService.findAll().size());
        assertEquals(prevSize,internService.getAssignments(secondIntern.getId()).size());
        assertEquals(prevAssignmentSize-1,assignmentService.findAll().size());
    }
}
