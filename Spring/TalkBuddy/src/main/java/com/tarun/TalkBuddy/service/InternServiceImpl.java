package com.tarun.TalkBuddy.service;

import com.tarun.TalkBuddy.daos.InternsDao;
import com.tarun.TalkBuddy.model.Intern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class InternServiceImpl implements InternService {
    @Autowired
    InternsDao internsDao;

    @Transactional
    @Override
    public Intern addIntern(Intern intern) {
        return internsDao.save(intern);
    }

    @Transactional
    @Override
    public boolean removeIntern(Intern intern) {
        return internsDao.remove(intern);
    }


    @Transactional
    @Override
    public Optional<Intern> findIntern(Long id) {
        return internsDao.find(id);
    }

    @Transactional
    @Override
    public Intern updateIntern(Intern intern) {
        return internsDao.save(intern);
    }

    @Transactional
    @Override
    public List<Intern> findAll() {
        return internsDao.list();
    }

    @Transactional
    @Override
    public boolean removeIntern(long id) {
        return internsDao.remove(id);
    }
}
