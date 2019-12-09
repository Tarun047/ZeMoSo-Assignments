package com.tarun.talkbuddy.service.implementations;

import com.tarun.talkbuddy.daos.interfaces.AssignmentDao;
import com.tarun.talkbuddy.daos.interfaces.TaskDao;
import com.tarun.talkbuddy.model.Task;
import com.tarun.talkbuddy.service.interfaces.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
        return taskDao.remove(task.getId());
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
