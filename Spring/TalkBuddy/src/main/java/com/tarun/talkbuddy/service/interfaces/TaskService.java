package com.tarun.talkbuddy.service.interfaces;

import com.tarun.talkbuddy.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task addTask(Task task);
    boolean removeTask(Task task);
    boolean removeTask(long id);
    Optional<Task> findTask(Long id);
    Task updateTask(Task task);
    List<Task> findAll();
}
