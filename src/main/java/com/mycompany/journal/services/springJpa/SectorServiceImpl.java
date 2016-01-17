package com.mycompany.journal.services.springJpa;

import com.mycompany.journal.db.model.*;
import com.mycompany.journal.services.*;

public class SectorServiceImpl extends GenericServiceImpl<Sector> implements SectorService {
    public SectorServiceImpl(Class<Sector> persistentClass) {
        super(persistentClass);
    }
}
