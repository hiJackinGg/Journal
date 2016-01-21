package com.mycompany.journal.services.springJpa;

import com.mycompany.journal.db.model.*;
import com.mycompany.journal.services.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;


@org.springframework.stereotype.Service
public class SectorServiceImpl extends GenericServiceImpl<Sector> implements SectorService {
    public SectorServiceImpl() {
        super(Sector.class);
    }
}
