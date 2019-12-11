package com.tarun.talkbuddy.service;

import com.tarun.talkbuddy.daos.interfaces.AssignmentDao;
import com.tarun.talkbuddy.helpers.GenericDaoMocker;
import com.tarun.talkbuddy.helpers.Helper;
import com.tarun.talkbuddy.model.Assignment;
import com.tarun.talkbuddy.model.enums.AssignmentStatus;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
        }
        catch (Exception e)
        {
            e.printStackTrace();;
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
        //Valid Removal
        int prevSize = assignmentService.findAll().size();
        for(int i=0;i<5;i++)
            Assert.assertTrue(assignmentService.removeAssignment(testAssignmentList.get(i).getId()));
        Assert.assertEquals(prevSize-5,assignmentService.findAll().size());
        //Invalid Removal
        for(int i=0;i<5;i++)
            Assert.assertFalse(assignmentService.removeAssignment(testAssignmentList.get(i)));
    }

    @Test
    public void updateAssignment()throws Exception
    {
        Assignment randomAssignment = testAssignmentList.get((new Random()).nextInt(testAssignmentList.size()));
        assignmentService.addAssignment(randomAssignment);
        randomAssignment.setRating(55);
        assignmentService.updateAssignment(randomAssignment);
        Assert.assertEquals(55,assignmentService.findAssignment(randomAssignment.getId()).get().getRating());
    }

    @Test
    public void findAssignment()
    {
        Assignment randomAssignment = testAssignmentList.get(9);
        Assert.assertEquals(randomAssignment,assignmentService.addAssignment(randomAssignment));
        Assert.assertTrue(assignmentService.findAssignment(randomAssignment.getId()).isPresent());
        assignmentService.removeAssignment(randomAssignment.getId());
        Assert.assertFalse(assignmentService.findAssignment(randomAssignment.getId()).isPresent());
    }


    @Test
    public void updateAssignmentStatus()throws Exception
    {
        Assignment randomAssignment = testAssignmentList.get((new Random()).nextInt(testAssignmentList.size()));
        assignmentService.addAssignment(randomAssignment);
        AssignmentStatus newStatus = (AssignmentStatus)Helper.getRandVal(AssignmentStatus.class);
        assignmentService.updateStatus(randomAssignment.getId(),newStatus);
        Assert.assertEquals(newStatus,assignmentService.findAssignment(randomAssignment.getId()).get().getStatus());
    }

}
