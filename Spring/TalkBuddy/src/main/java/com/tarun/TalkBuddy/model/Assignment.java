package com.tarun.TalkBuddy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="assignments")
@EntityListeners(AuditingEntityListener.class)
public class Assignment implements Serializable,Cloneable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;



    @ManyToOne
    @JoinColumn(name="intern_id")
    @JsonIgnore
    private Intern intern;

    @ManyToOne
    @JoinColumn(name="task_id")
    private Task task;

    int rating;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Intern getIntern() {
        return intern;
    }

    public void setIntern(Intern intern) {
        this.intern = intern;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    /*
    On Removing a task the change signals removal of all Assignments referring to that task as well
    Now this change wouldn't be propagated to Intern Entity as it is not on the owning side (as it has mappedBy)
    For this removal to go smoothly we need to propagate the change to Intern Entity as well that is
    remove the assignment from intern side as well
     */

    public Assignment copy()
    {
        Assignment assignment = new Assignment();
        assignment.setId(this.id);
        assignment.setIntern(this.intern);
        assignment.setTask(this.task);
        assignment.setRating(this.rating);
        return assignment;
    }

    @PreRemove
    public void preRemove()
    {
        intern.getAssignments().remove(this);
        task.getAssignments().remove(this);
    }
}
