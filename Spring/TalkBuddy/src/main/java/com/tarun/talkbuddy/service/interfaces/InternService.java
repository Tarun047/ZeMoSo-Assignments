package com.tarun.talkbuddy.service.interfaces;

import com.tarun.talkbuddy.model.Assignment;
import com.tarun.talkbuddy.model.Intern;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface InternService {
    Intern addIntern(Intern intern);
    boolean removeIntern(Intern intern);
    boolean removeIntern(long id);
    Optional<Intern> findIntern(Long id);
    Intern updateIntern(Intern intern);
    List<Intern> findAll();
    Set<Assignment> getAssignments(long id);

}
