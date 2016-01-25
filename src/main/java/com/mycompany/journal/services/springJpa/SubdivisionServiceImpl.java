package com.mycompany.journal.services.springJpa;

import com.mycompany.journal.db.model.*;
import com.mycompany.journal.services.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class SubdivisionServiceImpl extends GenericServiceImpl<Subdivision> implements SubdivisionService {
    public SubdivisionServiceImpl() {
        super(Subdivision.class);
    }
}
