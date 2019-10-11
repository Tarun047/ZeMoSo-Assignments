package com.tarun.TalkBuddy.controller;


import com.tarun.TalkBuddy.repository.ProfileRepository;
import com.tarun.TalkBuddy.service.interfaces.AssignmentService;
import com.tarun.TalkBuddy.service.interfaces.InternService;
import com.tarun.TalkBuddy.service.interfaces.TaskService;
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
    AssignmentService assignmentService;

    @Autowired
    ProfileRepository profileRepository;


    @GetMapping("/code")
    public String test()
    {
        return "hello";
    }


}
