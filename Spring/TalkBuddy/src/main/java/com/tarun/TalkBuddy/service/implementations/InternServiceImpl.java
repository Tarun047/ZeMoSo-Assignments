package com.tarun.TalkBuddy.service.implementations;

import com.tarun.TalkBuddy.daos.interfaces.AssignmentDao;
import com.tarun.TalkBuddy.daos.interfaces.InternDao;
import com.tarun.TalkBuddy.model.Assignment;
import com.tarun.TalkBuddy.model.Intern;
import com.tarun.TalkBuddy.service.interfaces.InternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class InternServiceImpl implements InternService {
    @Autowired
    InternDao internDao;

    @Autowired
    AssignmentDao assignmentDao;

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
    public boolean removeIntern(long id)
    {

        return internDao.remove(id);
    }
}
