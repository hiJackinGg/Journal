package com.mycompany.journal.services.springData.repositories;

import com.mycompany.journal.db.model.Login;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

public interface LoginRepository extends JpaRepository<Login, Long> {

    @Transactional
    @Modifying
    @Query("update Login l set l.loginDate = ?1 where l.username = ?2")
    void updateLoginDateByUsername(LocalDateTime dateTime, String username);

    @Query("select count(loginDate) from Login where loginDate = ?1")
    long count(LocalDateTime field);

}