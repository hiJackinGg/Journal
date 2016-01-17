package com.mycompany.journal.services.springJpa;

import com.mycompany.journal.db.model.*;
import com.mycompany.journal.services.*;

import java.util.List;

public class ManagerServiceImpl extends GenericServiceImpl<Manager> implements ManagerService {
    public ManagerServiceImpl(Class<Manager> persistentClass) {
        super(persistentClass);
    }

    @Override
    public List<Manager> findOneProperty(String propertyName, String value) {
        return null;
    }
}
