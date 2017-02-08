package com.mycompany.journal.services.springData.repositories;

import com.mycompany.journal.db.model.Logpresence;
import com.mycompany.journal.db.model.Manager;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Transactional(readOnly = true)
public interface LogpresenceRepository extends JpaRepository<Logpresence, Long> {

    @Modifying
    @Transactional
    @Query("delete from Logpresence u where u.id = ?1")
    Long deleteById(Long id);

    @Query("from Logpresence u where u.latenessTime = null")
    List<Logpresence> findAllWhoNotLate();

    @Query("from Logpresence u where u.latenessTime = (select max(latenessTime) from Logpresence)")
    List<Logpresence> findWhoMaxLate();

    @Query("from Logpresence u where dateAbsence between ?1 and ?2")
    List<Logpresence> findForPeriod(Date date1, Date date2);

    @Query("select count(dateAbsence) from Logpresence where dateAbsence = ?1")
    long count(LocalDateTime dateAbsence);

    @Query("from Logpresence u where u.manager in ?1 and u.dateAbsence = ?2")
    List<Logpresence> findLogsByManagers(Iterable<Manager> list, LocalDateTime cal);

    @Query(value = "select note, sum(latenesstime) from Logpresence group by note", nativeQuery = true)
    List<Object[]> temp();
}