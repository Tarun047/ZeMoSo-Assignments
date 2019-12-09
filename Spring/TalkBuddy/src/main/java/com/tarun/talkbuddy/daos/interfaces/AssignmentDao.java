package com.tarun.talkbuddy.daos.interfaces;

import com.tarun.talkbuddy.model.Assignment;

public interface AssignmentDao extends GenericDao<Assignment,Long>{
    Assignment update(Assignment assignment) throws Exception;
}
