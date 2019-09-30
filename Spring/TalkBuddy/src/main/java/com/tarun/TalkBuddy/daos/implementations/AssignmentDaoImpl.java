package com.tarun.TalkBuddy.daos.implementations;

import com.tarun.TalkBuddy.daos.interfaces.AssignmentDao;
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
       Session currentSession = (Session) entityManager.getDelegate();
       Optional<Assignment> assignment = find(key);
       if(assignment.isPresent()) {
           Intern intern = assignment.get().getIntern();
           Task task = assignment.get().getTask();
           currentSession.remove(assignment.get());

           if(currentSession.contains(intern))
           {
               Set<Assignment> assignments = intern.getAssignments();
               assignments.remove(assignment.get());
               intern.setAssignments(assignments);
               currentSession.merge(intern);
           }

           if(currentSession.contains(task))
           {
               Set<Assignment> assignments = task.getAssignments();
               assignments.remove(assignment.get());
               task.setAssignments(assignments);
               currentSession.merge(task);
           }

           return true;
        }
       return false;
    }

    @Override
    public boolean remove(Assignment entry) {
        return remove(entry.getId());
    }

    @Override
    public Assignment save(Assignment entry)
    {
      Intern intern = entry.getIntern();
      Task task = entry.getTask();
      Session session = (Session)entityManager.getDelegate();
      if(!find(entry.getId()).isPresent()) {
          if (intern != null && task != null) {
              Set<Assignment> assignments = intern.getAssignments();
              assignments.add(entry);
              intern.setAssignments(assignments);
              assignments = task.getAssignments();
              assignments.add(entry);
              task.setAssignments(assignments);
              session.save(entry);
          } else
              session.save(entry);
      }
      else
          session.merge(entry);
      return entry;
    }
}
