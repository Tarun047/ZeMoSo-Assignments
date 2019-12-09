package com.tarun.talkbuddy.repository;

import com.tarun.talkbuddy.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment,Long> {
}
