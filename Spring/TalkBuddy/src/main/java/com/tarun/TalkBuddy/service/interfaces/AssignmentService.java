package com.tarun.TalkBuddy.service.interfaces;

import com.tarun.TalkBuddy.model.Assignment;
import com.tarun.TalkBuddy.model.enums.AssignmentStatus;

import java.util.List;
import java.util.Optional;

public interface AssignmentService {
    Assignment addAssignment(Assignment assignment);
    boolean removeAssignment(Assignment assignment);
    boolean removeAssignment(long id);
    Optional<Assignment> findAssignment(Long id);
    Assignment updateAssignment(Assignment assignment);
    void updateStatus(long id,AssignmentStatus status) throws Exception;
    List<Assignment> findAll();
}