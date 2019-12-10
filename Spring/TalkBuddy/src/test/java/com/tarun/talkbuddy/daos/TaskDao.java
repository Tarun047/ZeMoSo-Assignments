package com.tarun.talkbuddy.daos;


import com.tarun.talkbuddy.helpers.Helper;
import com.tarun.talkbuddy.daos.implementations.TaskDaoImpl;
import com.tarun.talkbuddy.model.Task;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doAnswer;


@RunWith(MockitoJUnitRunner.class)
public class TaskDao {

    @Mock
    private static EntityManager entityManager;


    @Mock
    private Session session;


    @Mock
    private TaskDaoImpl taskDao;


    @Mock
    private Query<Task> resultsList;


    @Mock
    private Task Task;

    private List<Task> testTasks;

    private Map<Long,Task> localStorage = new HashMap<>();

    @Before
    public void setup() {
        if (testTasks == null) {
            MockitoAnnotations.initMocks(this);
            Mockito.when(entityManager.getDelegate()).thenReturn(session);

            testTasks = new ArrayList<>();
            taskDao = new TaskDaoImpl();
            taskDao.setEntityManager(entityManager);

            Mockito.when(session.save(any())).thenAnswer(invocation -> {
                Task Task = invocation.getArgument(0);
                localStorage.put(Task.getId(),Task);
                return Task;
            });

            doAnswer(invocation -> {
                Task Task = invocation.getArgument(0);
                localStorage.remove(Task.getId());
                return Task;
            }).when(session).remove(any());


            Mockito.when(session.get(eq(Task.class),anyLong())).thenAnswer(
                    invocation -> {
                        long id = invocation.getArgument(1);
                        return localStorage.get(id);
                    }
            );

            for (int i = 0; i < 10; i++) {
                Task Task = new Task();
                Helper.populate(Task, Task.class);
                testTasks.add(Task);
                taskDao.save(Task);
            }


            Mockito.when(session.createQuery("from Task", Task.class)).thenReturn(resultsList);
            Mockito.when(resultsList.getResultList()).thenReturn(testTasks);
        }
    }


    @Test
    public void testList() {
        Assert.assertEquals(taskDao.list(), testTasks);
    }

    @Test
    public void testFind() {

        for (int i = 0; i < 10; i++) {
            Assert.assertEquals(taskDao.find(testTasks.get(i).getId()).get(), testTasks.get(i));
        }
    }

    @Test
    public void testSave() {
        int randomIndex = (new Random()).nextInt(testTasks.size());
        Assert.assertEquals(taskDao.save(testTasks.get(randomIndex)), testTasks.get(randomIndex));
    }


    @Test
    public void testRemove() {
        int randomIndex = (new Random()).nextInt(testTasks.size());
        //Remove valid Task
        Assert.assertTrue(taskDao.remove(testTasks.get(randomIndex)));
        testTasks.remove(randomIndex);
        Assert.assertEquals(testTasks.size(), taskDao.list().size());
        //Remove false Task
        Assert.assertFalse(taskDao.remove(new Task()));
        Assert.assertFalse(taskDao.remove((long)10));
    }
}

