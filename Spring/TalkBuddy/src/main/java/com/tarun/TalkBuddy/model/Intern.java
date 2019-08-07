package com.tarun.TalkBuddy.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="interns")
@EntityListeners(AuditingEntityListener.class)
public class Intern {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;


    private double rating;

    @Nullable
    @ManyToMany(fetch = FetchType.LAZY,
               cascade = {
        CascadeType.PERSIST,
                CascadeType.MERGE,
    })
    @JoinTable(name="intern_tasks",
    joinColumns = {@JoinColumn(name="intern_id")},
    inverseJoinColumns = {@JoinColumn(name="task_id")})
    private Set<Task> tasks = new HashSet<>();

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


    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
