package com.mycompany.journal.services.springJpa;

import com.mycompany.journal.db.model.*;
import com.mycompany.journal.services.*;
import org.springframework.stereotype.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PositionServiceImpl extends GenericServiceImpl<Position> implements PositionService {
    public PositionServiceImpl() {
        super(Position.class);
    }
}
