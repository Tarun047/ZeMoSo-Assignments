package com.tarun.TalkBuddy.daos;

import com.tarun.TalkBuddy.model.Task;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskDaoImpl implements TaskDao {
    @Autowired
    EntityManager entityManager;

    @Override
    public List<Task> list() {
        Session currentSession = (Session) entityManager.getDelegate();
        return currentSession.createQuery("from Task", Task.class).getResultList();
    }

    @Override
    public Optional<Task> find(Long key) {

        Session currentSession = (Session) entityManager.getDelegate();
        return Optional.ofNullable(currentSession.get(Task.class, key));
    }


    @Override
    public boolean remove(Long key) {
        try {
            Session currentSession = (Session) entityManager.getDelegate();
            currentSession.remove(find(key));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean remove(Task entry) {
        entityManager.remove(entry);
        return false;
    }

    @Override
    public Task save(Task entry)
    {
      Session currentSession = (Session) entityManager.getDelegate();
      currentSession.save(entry);
      return entry;
    }
}
