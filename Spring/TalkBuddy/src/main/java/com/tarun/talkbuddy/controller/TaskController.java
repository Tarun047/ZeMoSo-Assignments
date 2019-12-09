package com.tarun.talkbuddy.controller;

import com.tarun.talkbuddy.model.Task;
import org.springframework.expression.ExpressionException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController extends MainController{



    @GetMapping("/")
    public List<Task> findAll()
    {
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable(name = "id") long internId) throws ExpressionException {
        return taskService.findTask(internId).orElseThrow(() -> new ExpressionException("No Such Intern Found"));
    }

    @PostMapping("/add_task")
    public Task createTask(@RequestBody Task task)
    {
        return taskService.addTask(task);
    }

    @DeleteMapping("/remove/{id}")
    public void removeTask(@PathVariable(name="id") long taskId)
    {
        taskService.removeTask(taskId);
    }
}
