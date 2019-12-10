package com.tarun.talkbuddy.daos;


import com.tarun.talkbuddy.Helper;
import com.tarun.talkbuddy.daos.implementations.InternDaoImpl;
import com.tarun.talkbuddy.model.Assignment;
import com.tarun.talkbuddy.model.Intern;
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
public class InternsDao {

    @Mock
    private static EntityManager entityManager;


    @Mock
    private Session session;


    @Mock
    private InternDaoImpl internDao;


    @Mock
    private Query<Intern> resultsList;


    @Mock
    private Intern intern;

    private List<Intern> testInterns;

    private Map<Long,Intern> localStorage = new HashMap<>();

    @Before
    public void setup() {
        if (testInterns == null) {
            MockitoAnnotations.initMocks(this);
            Mockito.when(entityManager.getDelegate()).thenReturn(session);

            testInterns = new ArrayList<>();
            internDao = new InternDaoImpl();
            internDao.setEntityManager(entityManager);

            Mockito.when(session.save(any())).thenAnswer(invocation -> {
                Intern intern = invocation.getArgument(0);
                localStorage.put(intern.getId(),intern);
                return intern;
            });

            doAnswer(invocation -> {
                Intern intern = invocation.getArgument(0);
                localStorage.remove(intern.getId());
                return intern;
            }).when(session).remove(any());


            Mockito.when(session.get(eq(Intern.class),anyLong())).thenAnswer(
                    invocation -> {
                        long id = invocation.getArgument(1);
                        return localStorage.get(id);
                    }
            );

            for (int i = 0; i < 10; i++) {
                Intern intern = new Intern();
                Helper.populate(intern, Intern.class);
                testInterns.add(intern);
                internDao.save(intern);
            }


            Mockito.when(session.createQuery("from Intern", Intern.class)).thenReturn(resultsList);
            Mockito.when(resultsList.getResultList()).thenReturn(testInterns);
        }
    }


    @Test
    public void testList() {
        Assert.assertEquals(internDao.list(), testInterns);
    }

    @Test
    public void testFind() {

        for (int i = 0; i < 10; i++) {
            Assert.assertEquals(internDao.find(testInterns.get(i).getId()).get(), testInterns.get(i));
        }
    }

    @Test
    public void testSave() {
        int randomIndex = (new Random()).nextInt(testInterns.size());
        Assert.assertEquals(internDao.save(testInterns.get(randomIndex)), testInterns.get(randomIndex));
    }


    @Test
    public void testRemove() {
        int randomIndex = (new Random()).nextInt(testInterns.size());
        //Remove valid intern
        Assert.assertTrue(internDao.remove(testInterns.get(randomIndex)));
        testInterns.remove(randomIndex);
        Assert.assertEquals(testInterns.size(), internDao.list().size());
        //Remove false intern
        Assert.assertFalse(internDao.remove(new Intern()));
        Assert.assertFalse(internDao.remove((long)10));
    }
}

