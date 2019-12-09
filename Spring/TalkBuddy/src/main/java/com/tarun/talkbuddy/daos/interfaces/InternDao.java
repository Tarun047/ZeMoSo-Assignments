package com.tarun.talkbuddy.daos.interfaces;

import com.tarun.talkbuddy.model.Assignment;
import com.tarun.talkbuddy.model.Intern;

import java.util.Set;

public interface InternDao extends GenericDao<Intern,Long>
{
    Set<Assignment> listAssignments(long id) throws Exception;
}