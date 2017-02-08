package com.mycompany.journal.services;

import com.mycompany.journal.db.model.*;

import java.util.List;
import java.util.Set;

public interface ManagerService extends GenericService<Manager> {

    List<Manager> findOneProperty(String propertyName, String value);

    /**
     *Each username in Login table corresponds manager.
     * @param username of the current user in the system
     * @return Manager suitable for the username
     */
    Manager findByLogin(String username);

    Set<Manager> getSubordinatesList(String username);

}
