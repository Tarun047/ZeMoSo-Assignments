package com.tarun.TalkBuddy.controller;


import com.tarun.TalkBuddy.repository.AssignmentRepository;
import com.tarun.TalkBuddy.repository.InternRepository;
import com.tarun.TalkBuddy.repository.TaskRepository;
import com.tarun.TalkBuddy.service.InternService;
import com.tarun.TalkBuddy.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class MainController {
    @Autowired
    InternService internService;

    @Autowired
    TaskService taskService;


    @Autowired
    AssignmentRepository assignmentRepository;


    @GetMapping("/code")
    public String test()
    {
        return "hello";
    }


}
