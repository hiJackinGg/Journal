package com.mycompany.journal.services.springData;

import com.mycompany.journal.db.model.Reason;

import com.mycompany.journal.services.ReasonService;

import com.mycompany.journal.services.springData.repositories.ReasonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReasonServiceImpl implements ReasonService {
    
    @Autowired
    private ReasonRepository reasonRepository;

    @Override
    public Reason save(Reason entity) {

        if (entity == null) {
            throw new IllegalArgumentException(
                    "Argument is null !");
        }

        return reasonRepository.save(entity);
    }

    @Override
    public List<Reason> findAll() {

        return reasonRepository.findAll();
    }

    @Override
    public void delete(Reason entity) {

        if (entity == null) {
            throw new IllegalArgumentException(
                    "Argument is null !");
        }

        reasonRepository.delete(entity);
    }

    @Override
    public Reason findById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException(
                    "Argument is null !");
        }

        return reasonRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {

        if (id == null) {
            throw new IllegalArgumentException(
                    "Argument is null !");
        }

        reasonRepository.delete(id);

    }

    @Override
    public long getCount() {
        return reasonRepository.count();
    }

    @Override
    public List<Reason> findSorted(String propertySortBy, boolean asc) {

        if (propertySortBy == null || propertySortBy.isEmpty()) {
            throw new IllegalArgumentException(
                    "Argument is incorrect !");
        }

        Sort.Direction direction;

        if(asc == true)
            direction = Sort.Direction.ASC;
        else
            direction = Sort.Direction.DESC;

        return reasonRepository.findAll(new Sort(direction, propertySortBy));
    }
}
