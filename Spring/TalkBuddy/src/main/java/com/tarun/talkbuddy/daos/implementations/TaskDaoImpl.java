package com.tarun.talkbuddy.daos.implementations;

import com.tarun.talkbuddy.daos.interfaces.TaskDao;
import com.tarun.talkbuddy.model.Task;
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
        Session currentSession = (Session) entityManager.getDelegate();
        Optional<Task> currTask = find(key);
        if(currTask.isPresent()) {
            currentSession.remove(currTask.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Task entry) {
        return remove(entry.getId());
    }

    @Override
    public Task save(Task entry) {
        Session currentSession = (Session) entityManager.getDelegate();
        if(currentSession.contains(entry))
            currentSession.merge(entry);
        else
            currentSession.save(entry);
        return entry;
    }
}
