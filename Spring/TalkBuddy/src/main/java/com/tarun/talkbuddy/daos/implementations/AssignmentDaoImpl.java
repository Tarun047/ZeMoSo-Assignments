package com.tarun.talkbuddy.daos.implementations;

import com.tarun.talkbuddy.daos.interfaces.AssignmentDao;
import com.tarun.talkbuddy.model.Assignment;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

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
           currentSession.remove(assignment.get());
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
      Session session = (Session)entityManager.getDelegate();
      session.save(entry);
      return entry;
    }

    @Override
    public Assignment update(Assignment assignment)throws Exception
    {
        Session session = (Session)entityManager.getDelegate();
        Optional<Assignment> currentAssignment = find(assignment.getId());
        if(currentAssignment.isPresent())
        {
            session.merge(assignment);
        }
        else {
            throw new Exception("No such Assignment");
        }
        return assignment;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
