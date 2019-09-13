package com.tarun.TalkBuddy.service;

import com.tarun.TalkBuddy.daos.InternsDao;
import com.tarun.TalkBuddy.model.Intern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class InternServiceImpl implements InternService {
    @Autowired
    InternsDao internsDao;

    @Transactional
    @Override
    public boolean addIntern(Intern intern) {
        return internsDao.createOrUpdate(intern);
    }

    @Transactional
    @Override
    public boolean removeIntern(Intern intern) {
        return internsDao.remove(intern);
    }

    @Transactional
    @Override
    public Intern findIntern(Long id) {
        return internsDao.findById(id);
    }

    @Override
    public boolean updateIntern(Intern intern) {
        return internsDao.createOrUpdate(intern);
    }

    @Transactional
    @Override
    public boolean removeIntern(Long id) {
        return internsDao.removeById(id);
    }
}
