package com.tarun.TalkBuddy.service;

import com.tarun.TalkBuddy.model.Assignment;

import java.util.List;
import java.util.Optional;

public interface AssignmentService {
    Assignment addAssignment(Assignment assignment);
    boolean removeAssignment(Assignment assignment);
    boolean removeAssignment(long id);
    Optional<Assignment> findAssignment(Long id);
    Assignment updateAssignment(Assignment assignment);
    List<Assignment> findAll();
}
