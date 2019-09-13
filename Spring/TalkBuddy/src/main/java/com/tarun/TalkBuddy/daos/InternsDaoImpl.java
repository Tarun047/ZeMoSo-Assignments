package com.tarun.TalkBuddy.daos;

import com.tarun.TalkBuddy.model.Intern;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class InternsDaoImpl implements InternsDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Intern> list() {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.createQuery("from Intern",Intern.class).getResultList();
    }

    @Override
    public Intern findById(Long key) {
    }

    @Override
    public Intern removeById(Long key) {
        return null;
    }

    @Override
    public Intern remove(Intern entry) {
        return null;
    }

    @Override
    public Intern createOrUpdate(Intern entry) {
        Session curre
    }
}
