package com.tarun.TalkBuddy.daos;

import com.tarun.TalkBuddy.model.Assignment;
import com.tarun.TalkBuddy.model.Assignment;
import com.tarun.TalkBuddy.model.Intern;
import com.tarun.TalkBuddy.model.Task;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class AssignmentDaoImpl implements AssignmentDao {
    @Autowired
    EntityManager entityManager;

    @Override
    public List<Assignment> list() {
        Session currentSession = (Session) entityManager.getDelegate();
        return currentSession.createQuery("from Assignment", Assignment.class).getResultList();
    }

    @Override
    public Optional<Assignment> find(Long key) {

        Session currentSession = (Session) entityManager.getDelegate();
        return Optional.ofNullable(currentSession.get(Assignment.class, key));
    }


    @Override
    public boolean remove(Long key) {
        try {
            Session currentSession = (Session) entityManager.getDelegate();
            if(find(key).isPresent()) {
                currentSession.remove(find(key).get());
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean remove(Assignment entry) {
        try {
            Session currentSession = (Session) entityManager.getDelegate();
            currentSession.remove(currentSession.contains(entry)?entry:currentSession.merge(entry));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Assignment save(Assignment entry)
    {
      Intern intern = entry.getIntern();
      Task task = entry.getTask();
      Set<Assignment> assignments = intern.getAssignments();
      assignments.add(entry);
      intern.setAssignments(assignments);
      assignments = task.getAssignments();
      assignments.add(entry);
      task.setAssignments(assignments);
      entityManager.persist(entry);
      entityManager.merge(intern);
      entityManager.merge(task);
      return entry;
    }
}
