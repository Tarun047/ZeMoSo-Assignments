package com.tarun.TalkBuddy.repository;

import com.tarun.TalkBuddy.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Role,Long>
{
    Optional<Role> findByUid(String uid);
}
