package com.mycompany.journal.services.springJpa;

import com.mycompany.journal.db.model.*;
import com.mycompany.journal.db.model.Service;
import com.mycompany.journal.services.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;


@org.springframework.stereotype.Service
public class ServServiceImpl extends GenericServiceImpl<Service> implements ServService {
    public ServServiceImpl() {
        super(Service.class);
    }
}
