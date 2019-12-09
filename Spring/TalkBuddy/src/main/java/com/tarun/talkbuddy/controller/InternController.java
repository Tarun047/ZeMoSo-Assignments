package com.tarun.talkbuddy.controller;

import com.tarun.talkbuddy.model.Assignment;
import com.tarun.talkbuddy.model.Intern;
import com.tarun.talkbuddy.model.Profile;
import com.tarun.talkbuddy.model.enums.RoleType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.ExpressionException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/interns")
public class InternController extends MainController {
    Logger logger = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/")
    public List<Intern> findAll() {
        return internService.findAll();
    }

    @PostMapping("/{id}/assign_task")
    public Intern assignTask(@PathVariable(name = "id") long internId, @RequestParam List<String> taskIds) {
        Intern intern = internService.findIntern(internId).orElseThrow(() -> new ExpressionException("Invalid id"));
        try {
            for (String id : taskIds) {
                Assignment assignment = new Assignment();
                assignment.setIntern(intern);
                assignment.setTask(taskService.findTask(Long.parseLong(id)).orElseThrow(() -> new ExpressionException("Invalid Task")));
                assignmentService.addAssignment(assignment);
            }
            return internService.updateIntern(intern);
        }
        catch (Exception e)
        {
           logger.debug(e.getMessage());
        }
        return intern;
    }

    @GetMapping("/{id}")
    public Intern getIntern(@PathVariable(name = "id") long internId) throws ExpressionException {
        return internService.findIntern(internId).orElseThrow(() -> new ExpressionException("No Such Intern Found"));
    }

    @PostMapping("/createintern/{uid}")
    public Intern createIntern(@PathVariable(name="uid")String uid,@Valid @RequestBody Intern intern) {
        Profile profile = new Profile();
        profile.setUid(uid);
        profile.setIntern(intern);
        profile.setRole(RoleType.INTERN);
        intern = internService.addIntern(intern);
        profileRepository.save(profile);
        return intern;

    }


    @DeleteMapping("/{id}/remove_task")
    public Intern removeAssignment(@PathVariable(name = "id") long internId, @RequestParam List<String> taskIds) throws ExpressionException {
        System.out.println(internId);
        for (String id : taskIds) {
            assignmentService.removeAssignment(Long.parseLong(id));
        }
        return internService.findIntern(internId).orElseThrow(() -> new ExpressionException("No Such Intern"));
    }


    @DeleteMapping("/removeintern/{id}")
    public Intern removeIntern(@PathVariable(name = "id") long id) throws Exception {
        Intern intern = internService.findIntern(id).orElseThrow(() -> new Exception("No Such Intern"));
        internService.removeIntern(intern);
        return intern;
    }


}
