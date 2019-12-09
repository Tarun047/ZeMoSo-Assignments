package com.tarun.talkbuddy.model;

import com.tarun.talkbuddy.model.enums.RoleType;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "profiles")
public class Profile implements Serializable
{


    @Id
    private String uid;

    @NotNull
    private RoleType role;

    public Intern getIntern() {
        return intern;
    }

    public void setIntern(Intern intern) {
        this.intern = intern;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    @OneToOne
    @JoinColumn(name = "intern_id")
    private Intern intern;

    @OneToOne
    @JoinColumn(name="mentor_id")
    private Mentor mentor;



    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }
}
