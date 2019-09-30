package com.tarun.TalkBuddy.controller;

import com.tarun.TalkBuddy.model.Assignment;
import com.tarun.TalkBuddy.model.Profile;
import com.tarun.TalkBuddy.model.enums.AssignmentStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController extends MainController {
    @PostMapping("/update")
    public boolean setAssignmentStatus(@RequestHeader("uid") String uid, @RequestHeader("assignmentId") long id, @Valid @RequestBody AssignmentStatus status) {
        try {
            /*Profile profile = profileRepository.findByUid("uid").orElseThrow(()->new Exception("Invalid uid"));
            if(profile.getIntern().getId()!=id)
                throw new Exception("Unauthorized Request");*/
            assignmentService.updateStatus(id,status);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
