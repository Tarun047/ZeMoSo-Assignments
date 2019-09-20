package com.tarun.TalkBuddy.model;

import ch.qos.logback.classic.pattern.ClassNameOnlyAbbreviator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="interns")
@EntityListeners({AuditingEntityListener.class})
@NamedQuery(name="getAssignmentsForIntern",query = "select assignments from Intern where name=:x")
public class Intern implements Serializable,Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NaturalId
    @NotNull
    private String name;


    private double rating;

    @ManyToOne
    private Mentor mentor;


    @OneToMany(mappedBy = "intern",cascade = {CascadeType.PERSIST,CascadeType.MERGE},orphanRemoval = true)
    Set<Assignment> assignments = new HashSet<>();



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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }


    public Set<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(Set<Assignment> assignments) {
        this.assignments = assignments;
    }

    public Intern copy()
    {
        Intern intern = new Intern();
        intern.setId(this.id);
        intern.setName(this.name);
        intern.setRating(this.rating);
        intern.setAssignments(this.assignments);
        return intern;
    }

}
