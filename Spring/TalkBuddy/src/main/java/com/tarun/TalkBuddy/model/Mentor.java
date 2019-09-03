package com.tarun.TalkBuddy.model;

import org.hibernate.annotations.NaturalId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="mentors")
@EntityListeners({AuditingEntityListener.class})

public class Mentor
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NaturalId
    String name;

    @OneToMany(mappedBy = "mentor",cascade = {CascadeType.ALL})
    Set<Intern> interns = new HashSet<>();


}
