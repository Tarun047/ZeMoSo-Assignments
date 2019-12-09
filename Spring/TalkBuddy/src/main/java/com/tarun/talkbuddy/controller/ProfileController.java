package com.tarun.talkbuddy.controller;

import com.tarun.talkbuddy.model.Profile;
import com.tarun.talkbuddy.model.enums.RoleType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/roles")
public class ProfileController extends MainController
{

    @GetMapping("/rolelevel")
    public Profile getRole(@RequestHeader("uid") String uid)throws Exception
    {
        Optional<Profile> role = profileRepository.findByUid(uid);
        if(role.isPresent())
            return role.get();
        else
        {
            Profile profile = new Profile();
            profile.setRole(RoleType.UNRECOGNIZED_USER);
            return profile;
        }
    }

}
