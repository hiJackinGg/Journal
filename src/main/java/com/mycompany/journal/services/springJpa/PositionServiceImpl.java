package com.mycompany.journal.services.springJpa;

import com.mycompany.journal.db.model.*;
import com.mycompany.journal.services.*;

public class PositionServiceImpl extends GenericServiceImpl<Position> implements PositionService {
    public PositionServiceImpl(Class<Position> persistentClass) {
        super(persistentClass);
    }
}
