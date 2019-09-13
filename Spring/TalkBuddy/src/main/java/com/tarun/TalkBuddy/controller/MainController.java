package com.tarun.TalkBuddy.controller;


import com.tarun.TalkBuddy.repository.AssignmentRepository;
import com.tarun.TalkBuddy.repository.InternRepository;
import com.tarun.TalkBuddy.repository.TaskRepository;
import com.tarun.TalkBuddy.service.InternService;
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
    TaskRepository taskRepository;


    @Autowired
    AssignmentRepository assignmentRepository;


    @GetMapping("/code")
    public String test()
    {
        return "hello";
    }


}
