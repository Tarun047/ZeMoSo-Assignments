package com.tarun.TalkBuddy.service.interfaces;

import com.tarun.TalkBuddy.model.Intern;

import java.util.List;
import java.util.Optional;

public interface InternService {
    Intern addIntern(Intern intern);
    boolean removeIntern(Intern intern);
    boolean removeIntern(long id);
    Optional<Intern> findIntern(Long id);
    Intern updateIntern(Intern intern);
    List<Intern> findAll();

}
