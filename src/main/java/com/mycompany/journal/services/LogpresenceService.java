package com.mycompany.journal.services;

import com.mycompany.journal.db.model.*;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface LogpresenceService extends GenericService<Logpresence> {

    List<Logpresence> findAllWhoNotLate();

    List<Logpresence> findWhoMaxLate();

    List<Logpresence> findForPeriod(Date date1, Date date2);

    long count(LocalDateTime field);

    List<Logpresence> findLogsByManagers(Iterable<Manager> list);

}
