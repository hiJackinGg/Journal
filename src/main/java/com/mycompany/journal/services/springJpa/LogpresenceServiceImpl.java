package com.mycompany.journal.services.springJpa;

import com.mycompany.journal.db.model.*;
import com.mycompany.journal.services.*;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class LogpresenceServiceImpl extends GenericServiceImpl<Logpresence> implements LogpresenceService {
    public LogpresenceServiceImpl() {
        super(Logpresence.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Logpresence> findAllWhoNotLate() {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Logpresence> findWhoMaxLate() {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Logpresence> findForPeriod(Date date1, Date date2) {
        return null;
    }

    @Override
    public long count(LocalDateTime field) {
        return 0;
    }

    @Override
    public List<Logpresence> findLogsByManagers(Iterable<Manager> list) {
        return null;
    }
}
