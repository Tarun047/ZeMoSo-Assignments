package com.tarun.talkbuddy.daos;

import com.tarun.talkbuddy.Helper;
import com.tarun.talkbuddy.daos.implementations.AssignmentDaoImpl;
import com.tarun.talkbuddy.model.Assignment;
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
import static org.mockito.Mockito.doNothing;


@RunWith(MockitoJUnitRunner.class)
public class AssignmentDao {

    @Mock
    private static EntityManager entityManager;


    @Mock
    private Session session;


    @Mock
    private AssignmentDaoImpl assignmentDao;


    @Mock
    private Query<Assignment> resultsList;


    @Mock
    private Assignment assignment;

    private List<Assignment> testAssignments;

    Map<Long,Assignment> localStorage = new HashMap<>();

    @Before
    public void setup() {
        if (testAssignments == null) {
            MockitoAnnotations.initMocks(this);
            Mockito.when(entityManager.getDelegate()).thenReturn(session);

            testAssignments = new ArrayList<>();
            assignmentDao = new AssignmentDaoImpl();
            assignmentDao.setEntityManager(entityManager);

            Mockito.when(session.save(any())).thenAnswer(invocation -> {
                Assignment assignment = invocation.getArgument(0);
                localStorage.put(assignment.getId(),assignment);
                return assignment;
            });

            doAnswer(invocation -> {
                Assignment assignment = invocation.getArgument(0);
                localStorage.remove(assignment.getId());
                return assignment;
            }).when(session).remove(any());


            Mockito.when(session.get(eq(Assignment.class),anyLong())).thenAnswer(
                    invocation -> {
                        long id = invocation.getArgument(1);
                        return localStorage.get(id);
                    }
            );

            for (int i = 0; i < 10; i++) {
                Assignment assignment = new Assignment();
                Helper.populate(assignment, Assignment.class);
                testAssignments.add(assignment);
                assignmentDao.save(assignment);
            }


            Mockito.when(session.createQuery("from Assignment", Assignment.class)).thenReturn(resultsList);
            Mockito.when(resultsList.getResultList()).thenReturn(testAssignments);
        }
    }


    @Test
    public void testList() {
        Assert.assertEquals(assignmentDao.list(), testAssignments);
    }

    @Test
    public void testFind() {

        for (int i = 0; i < 10; i++) {
            Assert.assertEquals(assignmentDao.find(testAssignments.get(i).getId()).get(), testAssignments.get(i));
        }
    }

    @Test
    public void testSave() {
        int randomIndex = (new Random()).nextInt(testAssignments.size());
        Assert.assertEquals(assignmentDao.save(testAssignments.get(randomIndex)), testAssignments.get(randomIndex));
    }


    @Test
    public void testRemove() {
        int randomIndex = (new Random()).nextInt(testAssignments.size());
        //Remove valid assignment
        Assert.assertTrue(assignmentDao.remove(testAssignments.get(randomIndex)));
        testAssignments.remove(randomIndex);
        Assert.assertEquals(testAssignments.size(),assignmentDao.list().size());
        //Remove false assignment
        Assert.assertFalse(assignmentDao.remove(new Assignment()));
        Assert.assertFalse(assignmentDao.remove((long)10));
    }
}
