package com.tarun.talkbuddy.controller;

import com.tarun.talkbuddy.model.Assignment;
import com.tarun.talkbuddy.model.enums.AssignmentStatus;
import org.springframework.expression.ExpressionException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController extends MainController {



    @GetMapping("/")
    public List<Assignment> findAll()
    {
        return assignmentService.findAll();
    }

    @PostMapping("/update")
    public Assignment setAssignmentStatus(@RequestHeader("uid") String uid, @RequestHeader("assignmentId") long id, @Valid @RequestBody AssignmentStatus status)throws Exception
    {
            /*Profile profile = profileRepository.findByUid("uid").orElseThrow(()->new Exception("Invalid uid"));
            if(profile.getIntern().getId()!=id)
                throw new Exception("Unauthorized Request");*/
            assignmentService.updateStatus(id,status);
            return assignmentService.findAssignment(id).orElseThrow(()->new ExpressionException("No Such Assignment"));

    }
}
