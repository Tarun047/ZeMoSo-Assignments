package com.tarun.TalkBuddy.controller;

import com.tarun.TalkBuddy.model.Role;
import com.tarun.TalkBuddy.model.RoleType;
import com.tarun.TalkBuddy.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import javax.validation.Valid;
import java.util.IllegalFormatException;
import java.util.Map;

@RestController
@RequestMapping("api/roles")
public class RoleController
{
    @Autowired
    RolesRepository rolesRepository;

    @PostMapping("/addrole")
    Role putRole(@Valid @RequestBody Role role)
    {
        return rolesRepository.save(role);
    }

    @GetMapping("/rolelevel")
    RoleType getRole(@RequestHeader("uid") String uid)throws Exception
    {
        return rolesRepository.findByUid(uid)
                    .orElseThrow(()->new ResourceAccessException("No Such User"))
                    .getRole();
    }

}
