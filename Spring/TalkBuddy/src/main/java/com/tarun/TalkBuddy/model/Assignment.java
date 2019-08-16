package com.tarun.TalkBuddy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;


    @ManyToOne
    @JoinColumn(name="intern_id")
    @JsonIgnore
    Intern intern;

    @ManyToOne
    @JoinColumn(name="task_id")
    Task task;

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

    @PreRemove
    public void preRemove()
    {
        intern.getAssignments().remove(this);
        task.getAssignments().remove(this);
    }

}
