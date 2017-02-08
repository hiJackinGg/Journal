package com.mycompany.journal.services.springJpa;

import com.mycompany.journal.db.model.*;
import com.mycompany.journal.services.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;


@Service
@Transactional
public class ManagerServiceImpl extends GenericServiceImpl<Manager> implements ManagerService {
    public ManagerServiceImpl() {
        super(Manager.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Manager> findOneProperty(String propertyName, String value) {
        return null;
    }

    @Override
    public Manager findByLogin(String username) {
        return null;
    }

    @Override
    public Set<Manager> getSubordinatesList(String username) {
        return null;
    }
}
