package com.tarun.TalkBuddy.controller;

import com.tarun.TalkBuddy.model.Assignment;
import com.tarun.TalkBuddy.model.Intern;
import com.tarun.TalkBuddy.model.Task;
import org.springframework.expression.ExpressionException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/interns")
public class InternController extends MainController{


    @GetMapping("/")
    public List<Intern> findAll()
    {
        return internService.findAll();
    }

    @PostMapping("/{id}/assign_task")
    public Intern assignTask(@PathVariable(name = "id") long internId,@RequestParam List<String> taskIds)
    {

        Intern intern = internService.findIntern(internId).orElseThrow(()->new ExpressionException("Invalid id"));
        for(String id:taskIds)
        {
            Assignment assignment = new Assignment();
            assignment.setIntern(intern);
            assignment.setTask(taskRepository.findById(Long.parseLong(id)).orElseThrow(()->new ExpressionException("Invalid Task")));
            assignmentRepository.save(assignment);
        }
        return internService.updateIntern(intern);

    }

    @GetMapping("/{id}")
    public Intern getIntern(@PathVariable(name="id") long internId)throws  ExpressionException
    {
        return internService.findIntern(internId).orElseThrow(()->new ExpressionException("No Such Intern Found"));
    }

    @PostMapping("/createintern")
    public Intern createIntern(@Valid @RequestBody Intern intern)
    {
        return internService.addIntern(intern);
    }


    @DeleteMapping("/{id}/remove_task")
    public Intern removeAssignment(@PathVariable(name="id") long internId,@RequestParam List<String> taskIds)throws ExpressionException
    {
        for(String id:taskIds)
        {
            assignmentRepository.deleteById(Long.parseLong(id));
        }
        return internService.findIntern(internId).orElseThrow(()->new ExpressionException("No Such Intern"));
    }


    @DeleteMapping("/removeintern/{id}")
    public Intern removeIntern(@PathVariable(name="id") long id)throws Exception
    {
        Intern intern = internService.findIntern(id).orElseThrow(()->new Exception("No Such Intern"));
        internService.removeIntern(intern);
        return intern;
    }



}
