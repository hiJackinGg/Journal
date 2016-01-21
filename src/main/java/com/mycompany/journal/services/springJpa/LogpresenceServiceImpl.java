package com.mycompany.journal.services.springJpa;

import com.mycompany.journal.db.model.*;
import com.mycompany.journal.services.*;
import org.springframework.stereotype.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class LogpresenceServiceImpl extends GenericServiceImpl<Logpresence> implements LogpresenceService {
    public LogpresenceServiceImpl() {
        super(Logpresence.class);
    }

    @Override
    public List<Logpresence> findAllWhoNotLate() {
        return null;
    }

    @Override
    public List<Logpresence> findWhoMaxLate() {
        return null;
    }

    @Override
    public List<Logpresence> findForPeriod(Date date1, Date date2) {
        return null;
    }
}
