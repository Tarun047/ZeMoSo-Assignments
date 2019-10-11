package com.tarun.TalkBuddy.daos.interfaces;

import com.tarun.TalkBuddy.model.Assignment;
import com.tarun.TalkBuddy.model.Intern;

import java.util.Set;

public interface InternDao extends GenericDao<Intern,Long>
{
    Set<Assignment> listAssignments(long id) throws Exception;
}