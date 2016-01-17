package com.mycompany.journal.services;

import com.mycompany.journal.db.model.*;

import java.util.Date;
import java.util.List;

public interface LogpresenceService extends GenericService<Logpresence> {

    List<Logpresence> findAllWhoNotLate();

    List<Logpresence> findWhoMaxLate();

    List<Logpresence> findForPeriod(Date date1, Date date2);

}
