package com.tarun.talkbuddy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tarun.talkbuddy.model.enums.AssignmentStatus;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="assignments")
@EntityListeners(AuditingEntityListener.class)
public class Assignment implements Serializable,Cloneable,Model
{

    @Id
    @GenericGenerator(name="assignment_generator",strategy = "com.tarun.talkbuddy.model.generators.AssignmentIdGenerator")
    @GeneratedValue(generator = "assignment_generator")
    private long id;


    @NotNull
    @ManyToOne
    @JoinColumn(name="intern_id")
    @JsonIgnore
    private Intern intern;

    @NotNull
    @ManyToOne
    @JoinColumn(name="task_id")
    private Task task;


    public AssignmentStatus getStatus() {
        return status;
    }

    public void setStatus(AssignmentStatus status) {
        this.status = status;
    }

    AssignmentStatus status = AssignmentStatus.OPEN;

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
        assignment.setId(Objects.hash(this.intern.getId(),this.getTask().getId()));
        assignment.setIntern(this.intern);
        assignment.setTask(this.task);
        assignment.setRating(this.rating);
        return assignment;
    }
}
