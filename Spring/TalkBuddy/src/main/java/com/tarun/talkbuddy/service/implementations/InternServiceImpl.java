package com.tarun.talkbuddy.service.implementations;

import com.tarun.talkbuddy.daos.interfaces.AssignmentDao;
import com.tarun.talkbuddy.daos.interfaces.InternDao;
import com.tarun.talkbuddy.model.Assignment;
import com.tarun.talkbuddy.model.Intern;
import com.tarun.talkbuddy.service.interfaces.InternService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class InternServiceImpl implements InternService {
    @Autowired
    InternDao internDao;

    @Autowired
    AssignmentDao assignmentDao;

    Logger logger = LoggerFactory.getLogger(InternServiceImpl.class);


    @Transactional
    @Override
    public Intern addIntern(Intern intern) {
        return internDao.save(intern);
    }

    @Transactional
    @Override
    public boolean removeIntern(Intern intern) {
        return internDao.remove(intern.getId());
    }



    @Transactional
    @Override
    public Optional<Intern> findIntern(Long id) {
        return internDao.find(id);
    }

    @Transactional
    @Override
    public Intern updateIntern(Intern intern) {
        return internDao.save(intern);
    }

    @Transactional
    @Override
    public List<Intern> findAll() {
        return internDao.list();
    }

    @Transactional
    @Override
    public Set<Assignment> getAssignments(long id)
    {

        try {

            return internDao.listAssignments(id);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
            return new HashSet<>();
        }

    }

    @Transactional
    @Override
    public boolean removeIntern(long id)
    {

        return internDao.remove(id);
    }
}
