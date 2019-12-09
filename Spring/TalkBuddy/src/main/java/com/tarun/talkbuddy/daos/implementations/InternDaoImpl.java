package com.tarun.talkbuddy.daos.implementations;

import com.tarun.talkbuddy.daos.interfaces.InternDao;
import com.tarun.talkbuddy.model.Assignment;
import com.tarun.talkbuddy.model.Intern;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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


    //This is a method only for tests it enables evaluation of lazily initialized set
    //This must not be called anywhere else in production code
    @Override
    public Set<Assignment> listAssignments(long id)throws Exception
    {
        Session session = (Session) entityManager.getDelegate();
        Optional<Intern> intern = Optional.ofNullable(session.find(Intern.class,id));
        if(intern.isPresent())
        {
            //Initializing to a variable to avoid lazy initialization exception and call size to force computation
            Set<Assignment> assignments = intern.get().getAssignments();
            assignments.size();
            return assignments;
        }
        else
            throw new Exception("No Such Intern");
    }
}
