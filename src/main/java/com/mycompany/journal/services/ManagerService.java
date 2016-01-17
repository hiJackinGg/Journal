package com.mycompany.journal.services;

import com.mycompany.journal.db.model.*;

import java.util.List;

public interface ManagerService extends GenericService<Manager> {

    public List<Manager> findOneProperty(String propertyName, String value);

}
