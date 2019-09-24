package com.tarun.TalkBuddy.service.implementations;

import com.tarun.TalkBuddy.daos.interfaces.AssignmentDao;
import com.tarun.TalkBuddy.daos.interfaces.TaskDao;
import com.tarun.TalkBuddy.model.Assignment;
import com.tarun.TalkBuddy.model.Task;
import com.tarun.TalkBuddy.service.interfaces.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskDao taskDao;

    @Autowired
    AssignmentDao assignmentDao;

    @Transactional
    @Override
    public Task addTask(Task task) {
        return taskDao.save(task);
    }

    @Transactional
    @Override
    public boolean removeTask(Task task) {
        boolean retVal = taskDao.remove(task.getId());
        if(retVal)
        {
            Set<Assignment> assignments = task.getAssignments();
            for (Assignment assignment : assignments) {
                assignmentDao.remove(assignment);
            }
        }
        return retVal;
    }


    @Transactional
    @Override
    public Optional<Task> findTask(Long id) {
        return taskDao.find(id);
    }

    @Transactional
    @Override
    public Task updateTask(Task task) {
        return taskDao.save(task);
    }

    @Transactional
    @Override
    public List<Task> findAll() {
        return taskDao.list();
    }

    @Transactional
    @Override
    public boolean removeTask(long id)
    {

        return taskDao.remove(id);
    }
}
