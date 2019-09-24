package com.tarun.TalkBuddy.daos.implementations;

import com.tarun.TalkBuddy.daos.interfaces.InternDao;
import com.tarun.TalkBuddy.model.Intern;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class InternDaoImpl implements InternDao {

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
        Session currentSession = (Session) entityManager.getDelegate();
        Optional<Intern> currIntern = find(key);
        if(currIntern.isPresent()) {
            currentSession.remove(currIntern.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Intern entry)
    {
        return remove(entry.getId());
    }

    @Override
    public Intern save(Intern entry)
    {
            Session currentSession = (Session) entityManager.getDelegate();
            if(currentSession.contains(entry))
                currentSession.merge(entry);
            else
                currentSession.save(entry);
            return entry;

    }
}
