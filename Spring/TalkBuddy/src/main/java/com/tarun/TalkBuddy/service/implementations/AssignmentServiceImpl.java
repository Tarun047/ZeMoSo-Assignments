package com.tarun.TalkBuddy.service.implementations;

import com.tarun.TalkBuddy.daos.interfaces.AssignmentDao;
import com.tarun.TalkBuddy.model.Assignment;
import com.tarun.TalkBuddy.model.enums.AssignmentStatus;
import com.tarun.TalkBuddy.service.interfaces.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AssignmentServiceImpl implements AssignmentService {
    @Autowired
    AssignmentDao assignmentDao;

    @Transactional
    @Override
    public Assignment addAssignment(Assignment assignment) {
        return assignmentDao.save(assignment);
    }

    @Transactional
    @Override
    public boolean removeAssignment(Assignment assignment) {
        return assignmentDao.remove(assignment);
    }


    @Transactional
    @Override
    public Optional<Assignment> findAssignment(Long id) {
        return assignmentDao.find(id);
    }

    @Transactional
    @Override
    public Assignment updateAssignment(Assignment assignment) {
        return assignmentDao.save(assignment);
    }

    @Transactional
    @Override
    public void updateStatus(long id,AssignmentStatus status)throws Exception
    {
        Assignment assignment = assignmentDao.find(id).orElseThrow(()->new Exception("No such assignment"));
        assignment.setStatus(status);
        assignmentDao.update(assignment);
    }

    @Transactional
    @Override
    public List<Assignment> findAll() {
        return assignmentDao.list();
    }

    @Transactional
    @Override
    public boolean removeAssignment(long id) {
        return assignmentDao.remove(id);
    }
}
