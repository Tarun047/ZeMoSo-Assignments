package com.tarun.TalkBuddy.service;

import com.tarun.TalkBuddy.model.Intern;

public interface InternService {
    boolean addIntern(Intern intern);
    boolean removeIntern(Intern intern);
    boolean updateIntern(Intern intern);
    boolean removeIntern(Long id);
    Intern findIntern(Long id);

}
