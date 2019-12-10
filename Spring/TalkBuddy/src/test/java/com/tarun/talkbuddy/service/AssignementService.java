package com.tarun.talkbuddy.service;

import com.tarun.talkbuddy.daos.interfaces.AssignmentDao;
import com.tarun.talkbuddy.helpers.GenericDaoMocker;
import com.tarun.talkbuddy.helpers.Helper;
import com.tarun.talkbuddy.model.Assignment;
import com.tarun.talkbuddy.service.implementations.AssignmentServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class AssignementService {

    static AssignmentServiceImpl assignmentService;

    @Mock
    static AssignmentDao assignmentDao;


    private static List<Assignment> testAssignmentList;


    private static boolean initialized = false;


    @Before
    public void setup() {
        if (!initialized){
            MockitoAnnotations.initMocks(this);
            new GenericDaoMocker<Assignment>(assignmentDao);
            specificSetup();
            assignmentService = new AssignmentServiceImpl();
            testAssignmentList = new ArrayList<>();
            assignmentService.setAssignmentDao(assignmentDao);
            populateTestObjects();
            initialized=true;
        }
    }

    private void populateTestObjects()
    {
        for(int i=0;i<10;i++)
        {
            Assignment randomAssignment = new Assignment();
            Helper.populate(randomAssignment,Assignment.class);
            testAssignmentList.add(randomAssignment);
        }
    }

    private  void specificSetup()
    {
        try {
            Mockito.when(assignmentDao.update(any())).thenAnswer(
                    invocation -> {
                        Assignment updatedAssignment = invocation.getArgument(0);
                        assignmentDao.remove(updatedAssignment.getId());
                        assignmentDao.save(updatedAssignment);
                        return updatedAssignment;
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void addAssignmentTest()
    {
        for(int i=0;i<5;i++)
            assignmentService.addAssignment(testAssignmentList.get(i));
       Assert.assertEquals(5,assignmentService.findAll().size());
    }

    @Test
    public void removeAssignmentTest()
    {
        int prevSize = assignmentService.findAll().size();
        for(int i=0;i<5;i++)
            assignmentService.removeAssignment(testAssignmentList.get(i).getId());
        Assert.assertEquals(prevSize-5,assignmentService.findAll().size());
    }

    @Test
    public void updateAssignment()
    {
        Assignment randomAssignment = testAssignmentList.get((new Random()).nextInt(testAssignmentList.size()));
        assignmentService.addAssignment(randomAssignment);
        randomAssignment.setRating(55);
        try {
            assignmentService.updateAssignment(randomAssignment);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Assert.assertEquals(55,assignmentService.findAssignment(randomAssignment.getId()).get().getRating());
    }

    
}
