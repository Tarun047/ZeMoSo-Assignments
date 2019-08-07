package com.tarun.TalkBuddy.repository;

import com.tarun.TalkBuddy.model.Intern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InternRepository extends JpaRepository<Intern,Long> {
}
