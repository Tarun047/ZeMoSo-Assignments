package com.tarun.talkbuddy.repository;

import com.tarun.talkbuddy.model.Intern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InternRepository extends JpaRepository<Intern,Long> {
}
