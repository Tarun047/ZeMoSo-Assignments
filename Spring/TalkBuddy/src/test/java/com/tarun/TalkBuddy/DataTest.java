package com.tarun.TalkBuddy;

import com.tarun.TalkBuddy.model.Assignment;
import com.tarun.TalkBuddy.model.Intern;
import com.tarun.TalkBuddy.model.Task;
import com.tarun.TalkBuddy.repository.AssignmentRepository;
import com.tarun.TalkBuddy.repository.InternRepository;
import com.tarun.TalkBuddy.repository.TaskRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.expression.ExpressionException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DataTest {

    @Autowired
    AssignmentRepository aRepo;

    @Autowired
    InternRepository iRepo;

    @Autowired
    TaskRepository tRepo;

    @Autowired
    EntityManager entityManager;

    Logger logger = LoggerFactory.getLogger(DataTest.class);

    List<Task> fetchInternAssignments(Session session,String name)
    {
        Query query = session.createNamedQuery("getAssignmentsForIntern");
        query.setParameter("x",name);
        return query.list();
    }

    void addAssignment(Intern i,Task t)
    {
        Assignment assignment = new Assignment();
        assignment.setTask(t);
        assignment.setIntern(i);
        i.getAssignments().add(assignment);
        t.getAssignments().add(assignment);
        aRepo.save(assignment);
    }

    @Test
    public void testPersistance()
    {
        Intern intern = new Intern();
        intern.setId(1);
        intern.setName("Tarun");
        intern.setRating(92);
        iRepo.save(intern);

        try {
            Task mTask = new Task();
            mTask.setTaskName("Yat");
            mTask.setDescription("Yet another task");
            tRepo.save(mTask);


            Task mTask2 = new Task();
            mTask2.setTaskName("TWA");
            mTask2.setDescription("This Work is Awesome");
            tRepo.save(mTask2);

            addAssignment(intern, mTask);
            addAssignment(intern, mTask2);
            iRepo.save(intern);

            Session session = (Session) entityManager.getDelegate();
            List<Task> taskList = fetchInternAssignments(session, "Tarun");
            assert taskList.size() == 2;
            System.out.println(taskList);
            tRepo.delete(mTask);
            intern = iRepo.findById(intern.getId()).orElseThrow(() -> new ExpressionException("No such Intern"));

            assert intern.getAssignments().size() == 1;
        }
        catch(Exception e){}

    }
}
