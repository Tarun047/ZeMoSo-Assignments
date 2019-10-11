package com.tarun.TalkBuddy.controller;

import com.tarun.TalkBuddy.model.Profile;
import com.tarun.TalkBuddy.model.enums.RoleType;
import com.tarun.TalkBuddy.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
