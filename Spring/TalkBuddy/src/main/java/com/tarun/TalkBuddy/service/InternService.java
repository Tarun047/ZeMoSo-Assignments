package com.tarun.TalkBuddy.service;

import com.tarun.TalkBuddy.model.Intern;

public interface InternService {
    boolean addIntern(Intern intern);
    boolean removeIntern(Intern intern);
    boolean findIntern(Long id);
    boolean updateIntern(Intern intern);

}
