package com.mycompany.journal.services.springData;

import com.mycompany.journal.db.model.Department;
import com.mycompany.journal.services.DepartmentService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("springDataJpaDepartmentService")
public class DepartmentServiceImpl implements DepartmentService {
    @Override
    public Department save(Department entity) {
        return null;
    }

    @Override
    public List<Department> findAll() {
        return null;
    }

    @Override
    public void delete(Department entity) {

    }

    @Override
    public Department findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public long getCount() {
        return 0;
    }

    @Override
    public List<Department> findSorted(String propertySortBy, boolean asc) {
        return null;
    }
}
