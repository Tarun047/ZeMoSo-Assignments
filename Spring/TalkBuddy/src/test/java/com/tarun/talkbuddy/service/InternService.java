package com.tarun.talkbuddy.service;

import com.tarun.talkbuddy.daos.interfaces.InternDao;
import com.tarun.talkbuddy.helpers.GenericDaoMocker;
import com.tarun.talkbuddy.helpers.Helper;
import com.tarun.talkbuddy.model.Assignment;
import com.tarun.talkbuddy.model.Intern;
import com.tarun.talkbuddy.service.implementations.InternServiceImpl;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;


@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InternService {

    static InternServiceImpl internService;
    
    @Mock
    static InternDao internDao;

    private static List<Intern> testInternList;


    private static boolean initialized = false;


    @Before
    public void setup()throws Exception
    {
        if (!initialized){
            MockitoAnnotations.initMocks(this);
            new GenericDaoMocker<Intern>(internDao);
            specificSetup();
            internService = new InternServiceImpl();
            testInternList = new ArrayList<>();
            internService.setInternDao(internDao);
            populateTestObjects();
            initialized=true;
        }
    }

    public void specificSetup()throws Exception
    {
        Mockito.when(internDao.listAssignments(anyLong())).thenAnswer(invocation -> {
        Intern intern = internService.findIntern(invocation.getArgument(0)).get();
            return intern.getAssignments();
        });
    }

    private void populateTestObjects()
    {
        for(int i=0;i<10;i++)
        {
            Intern randomIntern = new Intern();
            Helper.populate(randomIntern,Intern.class);
            testInternList.add(randomIntern);
        }
    }




    @Test
    public void addInternTest()
    {
        for(int i=0;i<5;i++)
            internService.addIntern(testInternList.get(i));
        Assert.assertEquals(5,internService.findAll().size());
    }

    @Test
    public void removeInternTest()
    {
        //Valid Removal
        int prevSize = internService.findAll().size();
        for(int i=0;i<5;i++)
            Assert.assertTrue(internService.removeIntern(testInternList.get(i).getId()));
        Assert.assertEquals(prevSize-5,internService.findAll().size());
        //Invalid Removal
        for(int i=0;i<5;i++)
            Assert.assertFalse(internService.removeIntern(testInternList.get(i)));
    }

    @Test
    public void findIntern()
    {
        Intern randomIntern  = testInternList.get(9);
        Assert.assertEquals(randomIntern,internService.addIntern(randomIntern));
        Assert.assertTrue(internService.findIntern(randomIntern.getId()).isPresent());
        internService.removeIntern(randomIntern.getId());
        Assert.assertFalse(internService.findIntern(randomIntern.getId()).isPresent());
    }

    @Test
    public void listAssignments()
    {
        Set<Assignment> assignments = new HashSet<>();
        for(int i=0;i<10;i++)
        {
            Assignment randomAssignment = new Assignment();
            Helper.populate(randomAssignment,Assignment.class);
            assignments.add(randomAssignment);
        }

        Intern randomIntern  = testInternList.get(5);
        randomIntern.setAssignments(assignments);
        internService.updateIntern(randomIntern);
        Assert.assertEquals(assignments,internService.getAssignments(randomIntern.getId()));

        Assert.assertEquals(0,internService.getAssignments((int)Helper.getRandVal(Long.TYPE)).size());
    }
}
