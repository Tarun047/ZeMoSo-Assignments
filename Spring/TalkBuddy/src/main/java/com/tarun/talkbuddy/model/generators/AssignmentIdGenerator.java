package com.tarun.talkbuddy.model.generators;

import com.tarun.talkbuddy.model.Assignment;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Objects;

public class AssignmentIdGenerator implements IdentifierGenerator
{

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        if(object instanceof Assignment)
        {
            Assignment assignment = (Assignment)object;
            if(assignment.getIntern()!=null && assignment.getTask()!=null)
                return (long)Objects.hash(assignment.getIntern().getId(),assignment.getTask().getId());
            else {

                throw new HibernateException("Intern and task is compulsory for creating an assignment");
            }
        }
        throw new HibernateException("The object assigned is not an instance of the Assignment");
    }
}
