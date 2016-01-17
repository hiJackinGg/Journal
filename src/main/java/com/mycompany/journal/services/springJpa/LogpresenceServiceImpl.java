package com.mycompany.journal.services.springJpa;

import com.mycompany.journal.db.model.*;
import com.mycompany.journal.services.*;

import java.util.Date;
import java.util.List;

public class LogpresenceServiceImpl extends GenericServiceImpl<Logpresence> implements LogpresenceService {
    public LogpresenceServiceImpl(Class<Logpresence> persistentClass) {
        super(persistentClass);
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
