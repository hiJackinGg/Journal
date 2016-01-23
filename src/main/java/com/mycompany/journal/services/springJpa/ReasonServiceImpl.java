package com.mycompany.journal.services.springJpa;

import com.mycompany.journal.db.model.*;
import com.mycompany.journal.services.*;
import org.springframework.stereotype.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ReasonServiceImpl extends GenericServiceImpl<Reason> implements ReasonService {
    public ReasonServiceImpl() {
        super(Reason.class);
    }
}
