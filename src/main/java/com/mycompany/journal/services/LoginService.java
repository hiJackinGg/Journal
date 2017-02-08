package com.mycompany.journal.services;


import com.mycompany.journal.db.model.Login;
import com.mycompany.journal.db.model.Logpresence;
import org.joda.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Calendar;

public interface LoginService extends GenericService<Login> {

    void updateLoginDate(String username);

    long count(LocalDateTime field);

    public Page<Login> findAllByPage(Pageable pageable);

}
