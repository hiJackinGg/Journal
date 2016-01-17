package com.mycompany.journal.services.springJpa;

import com.mycompany.journal.db.model.*;
import com.mycompany.journal.services.*;

import java.util.List;

/**
 * Created by Denis on 13.01.16.
 */
public class DepartmentServiceImpl extends GenericServiceImpl<Department> implements DepartmentService {
    public DepartmentServiceImpl(Class<Department> persistentClass) {
        super(persistentClass);
    }

}
