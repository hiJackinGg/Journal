package com.mycompany.journal.services.springData;

import com.mycompany.journal.db.model.Subdivision;
import com.mycompany.journal.services.SubdivisionService;
import com.mycompany.journal.services.springData.repositories.SubdivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("springDataJpaSubdivisionService")
public class SubdivisionServiceImpl implements SubdivisionService {
    
    @Autowired
    private SubdivisionRepository subdivisionRepository;

    @Override
    public Subdivision save(Subdivision entity) {
        return subdivisionRepository.save(entity);
    }

    @Override
    public List<Subdivision> findAll() {

        return subdivisionRepository.findAll();
    }

    @Override
    public void delete(Subdivision entity) {
        subdivisionRepository.delete(entity);
    }

    @Override
    public Subdivision findById(Long id) {
        return subdivisionRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        subdivisionRepository.delete(id);

    }

    @Override
    public long getCount() {
        return subdivisionRepository.count();
    }

    @Override
    public List<Subdivision> findSorted(String propertySortBy, boolean asc) {

        if (propertySortBy == null || propertySortBy.isEmpty()) {
            throw new IllegalArgumentException(
                    "Argument is incorrect !");
        }

        Sort.Direction direction;

        if(asc == true)
            direction = Sort.Direction.ASC;
        else
            direction = Sort.Direction.DESC;

        return subdivisionRepository.findAll(new Sort(direction, propertySortBy));
    }
}
