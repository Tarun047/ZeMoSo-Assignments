package com.tarun.TalkBuddy.daos;

import com.tarun.TalkBuddy.model.Intern;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class InternsDaoImpl implements InternsDao {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Intern> list() {
        Session currentSession = (Session) entityManager.getDelegate();
        return currentSession.createQuery("from Intern",Intern.class).getResultList();
    }

    @Override
    public Optional<Intern> find(Long key) {

        Session currentSession = (Session) entityManager.getDelegate();
        return Optional.ofNullable(currentSession.get(Intern.class,key));
    }



    @Override
    public boolean remove(Long key) {
        try {
            Session currentSession = (Session) entityManager.getDelegate();
            currentSession.remove(find(key));
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean remove(Intern entry) {
        try {
            Session currentSession = (Session) entityManager.getDelegate();
            currentSession.remove(entry);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Intern save(Intern entry) {
        try {
            Session currentSession = (Session) entityManager.getDelegate();
            currentSession.save(entry);
            return entry;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
