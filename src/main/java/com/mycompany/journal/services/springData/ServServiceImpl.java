package com.mycompany.journal.services.springData;

import com.mycompany.journal.db.model.Service;
import com.mycompany.journal.services.ServService;


import java.util.List;

@org.springframework.stereotype.Service("springDataJpaServService")
public class ServServiceImpl implements ServService {
    @Override
    public Service save(Service entity) {
        return null;
    }

    @Override
    public List<Service> findAll() {
        return null;
    }

    @Override
    public void delete(Service entity) {

    }

    @Override
    public Service findById(Long id) {
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
    public List<Service> findSorted(String propertySortBy, boolean asc) {
        return null;
    }
}
