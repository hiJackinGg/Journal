package com.mycompany.journal.services.springJpa;

import com.mycompany.journal.db.model.*;
import com.mycompany.journal.services.*;

public class ServServiceImpl extends GenericServiceImpl<Service> implements ServService {
    public ServServiceImpl(Class<Service> persistentClass) {
        super(persistentClass);
    }
}
