package com.tarun.TalkBuddy.controller;

import com.tarun.TalkBuddy.model.Task;
import com.tarun.TalkBuddy.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController extends MainController{



    @GetMapping("/")
    public List<Task> findAll()
    {
        return taskRepository.findAll();
    }

    @PostMapping("/add_task")
    public Task createTask(@Valid @RequestBody Task task)
    {
        return taskRepository.save(task);
    }

    @DeleteMapping("/remove/{id}")
    public void removeTask(@PathVariable(name="id") long taskId)
    {
        taskRepository.delete(taskRepository.findById(taskId).orElseThrow(()->new ExpressionException("No such Task")));
    }
}
