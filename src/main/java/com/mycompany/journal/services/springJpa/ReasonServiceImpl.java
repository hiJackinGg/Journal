package com.mycompany.journal.services.springJpa;

import com.mycompany.journal.db.model.*;
import com.mycompany.journal.services.*;

public class ReasonServiceImpl extends GenericServiceImpl<Reason> implements ReasonService {
    public ReasonServiceImpl(Class<Reason> persistentClass) {
        super(persistentClass);
    }
}
