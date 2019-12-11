package com.tarun.talkbuddy.service;

import com.tarun.talkbuddy.daos.interfaces.TaskDao;
import com.tarun.talkbuddy.helpers.GenericDaoMocker;
import com.tarun.talkbuddy.helpers.Helper;
import com.tarun.talkbuddy.model.Task;
import com.tarun.talkbuddy.service.implementations.TaskServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TaskService {

    static TaskServiceImpl taskService;

    @Mock
    static TaskDao taskDao;

    private static List<Task> testTaskList;


    private static boolean initialized = false;


    @Before
    public void setup() {
        if (!initialized){
            MockitoAnnotations.initMocks(this);
            new GenericDaoMocker<Task>(taskDao);
            taskService = new TaskServiceImpl();
            testTaskList = new ArrayList<>();
            taskService.setTaskDao(taskDao);
            populateTestObjects();
            initialized=true;
        }
    }

    private void populateTestObjects()
    {
        for(int i=0;i<10;i++)
        {
            Task randomTask = new Task();
            Helper.populate(randomTask,Task.class);
            testTaskList.add(randomTask);
        }
    }

    @Test
    public void updateTaskTest()
    {
        Task randomTask = new Task();
        Helper.populate(randomTask,Task.class);
        taskService.addTask(randomTask);
        randomTask.setTaskName("Hello World");
        taskService.updateTask(randomTask);
        Assert.assertEquals("Hello World",taskService.findTask(randomTask.getId()).get().getTaskName());
    }

    @Test
    public void addTaskTest()
    {
        for(int i=0;i<5;i++)
            taskService.addTask(testTaskList.get(i));
        Assert.assertEquals(5, taskService.findAll().size());
    }

    @Test
    public void removeTaskTest()
    {
        //Valid Removal
        int prevSize = taskService.findAll().size();
        for(int i=0;i<5;i++)
            Assert.assertTrue(taskService.removeTask(testTaskList.get(i).getId()));
        Assert.assertEquals(prevSize-5, taskService.findAll().size());
        //Invalid Removal
        for(int i=0;i<5;i++)
            Assert.assertFalse(taskService.removeTask(testTaskList.get(i)));
    }

    @Test
    public void findTask()
    {
        Task randomTask  = testTaskList.get(9);
        Assert.assertEquals(randomTask, taskService.addTask(randomTask));
        Assert.assertTrue(taskService.findTask(randomTask.getId()).isPresent());
        taskService.removeTask(randomTask.getId());
        Assert.assertFalse(taskService.findTask(randomTask.getId()).isPresent());
    }



}
