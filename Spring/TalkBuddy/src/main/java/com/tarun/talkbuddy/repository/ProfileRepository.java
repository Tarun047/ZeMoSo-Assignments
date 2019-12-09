package com.tarun.talkbuddy.repository;

import com.tarun.talkbuddy.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile,Long>
{
    Optional<Profile> findByUid(String uid);
}
