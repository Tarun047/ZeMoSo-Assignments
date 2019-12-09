package com.tarun.talkbuddy.model;

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

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "mentor")
    private Profile profile;


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Intern> getInterns() {
        return interns;
    }

    public void setInterns(Set<Intern> interns) {
        this.interns = interns;
    }
}
