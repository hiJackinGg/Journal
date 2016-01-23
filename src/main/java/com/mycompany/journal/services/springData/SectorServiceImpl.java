package com.mycompany.journal.services.springData;

import com.mycompany.journal.db.model.Sector;
import com.mycompany.journal.services.SectorService;
import com.mycompany.journal.services.springData.repositories.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("springDataJpaSectorService")
public class SectorServiceImpl implements SectorService {
    
    @Autowired
    private SectorRepository sectorRepository;

    @Override
    public Sector save(Sector entity) {
        return sectorRepository.save(entity);
    }

    @Override
    public List<Sector> findAll() {

        return sectorRepository.findAll();
    }

    @Override
    public void delete(Sector entity) {
        sectorRepository.delete(entity);
    }

    @Override
    public Sector findById(Long id) {
        return sectorRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        sectorRepository.delete(id);

    }

    @Override
    public long getCount() {
        return sectorRepository.count();
    }

    @Override
    public List<Sector> findSorted(String propertySortBy, boolean asc) {

        if (propertySortBy == null || propertySortBy.isEmpty()) {
            throw new IllegalArgumentException(
                    "Argument is incorrect !");
        }

        Sort.Direction direction;

        if(asc == true)
            direction = Sort.Direction.ASC;
        else
            direction = Sort.Direction.DESC;

        return sectorRepository.findAll(new Sort(direction, propertySortBy));
    }
}
