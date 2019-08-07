package com.tarun.TalkBuddy.repository;

import com.tarun.TalkBuddy.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
}
