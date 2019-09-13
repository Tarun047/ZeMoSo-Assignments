package com.tarun.TalkBuddy.daos;

import com.tarun.TalkBuddy.model.Intern;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class InternsDaoImpl implements InternsDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Intern> list() {
        return null;
    }

    @Override
    public Intern findById(Long key) {
        return null;
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
        return null;
    }
}
