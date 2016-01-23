package com.mycompany.journal.services.springData;

import com.mycompany.journal.db.model.*;
import com.mycompany.journal.services.*;
import com.mycompany.journal.services.springData.repositories.ManagerRepository;
import com.mycompany.journal.services.springData.repositories.PositionRepository;
import com.mycompany.journal.services.springData.repositories.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import java.util.*;

@Service("springDataJpaManagerService")
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public Manager save(Manager entity) {
        if (entity == null) {
            throw new IllegalArgumentException(
                    "Argument is null !");
        }

        return managerRepository.save(entity);
    }

    @Override
    public List<Manager> findAll() {

        return managerRepository.findAll();
    }

    @Override
    public void delete(Manager entity) {

        if (entity == null) {
            throw new IllegalArgumentException(
                    "Argument is null !");
        }

        managerRepository.delete(entity);
    }

    @Override
    public Manager findById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException(
                    "Argument is null !");
        }

        return managerRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {

        if (id == null) {
            throw new IllegalArgumentException(
                    "Argument is null !");
        }

        managerRepository.delete(id);

    }

    @Override
    public long getCount() {
        return managerRepository.count();
    }

    @Override
    public List<Manager> findSorted(String propertySortBy, boolean asc) {

        if (propertySortBy == null || propertySortBy.isEmpty()) {
            throw new IllegalArgumentException(
                    "Argument is incorrect !");
        }

        Direction direction;

        if(asc == true)
            direction = Direction.ASC;
        else
            direction = Direction.DESC;

        return managerRepository.findAll(new Sort(direction, propertySortBy));
    }

    @Override
    public List<Manager> findOneProperty(String propertyName, String value){

        if (propertyName == null || propertyName.isEmpty()) {
            throw new IllegalArgumentException(
                    "Argument is incorrect !");
        }

        switch(propertyName){
            case "all": return managerRepository.findAll();
            case "firstName": return managerRepository.findByFirstName(value);
            case "lastName": return managerRepository.findByLastName(value);
            case "middleName": return managerRepository.findByMiddleName(value);
            case "personnel": return managerRepository.findByPersonnel(value);
            case "email": return managerRepository.findByEmail(value);
            case "position": return managerRepository.findByPositionName(value);
            case "sector": return managerRepository.findBySectorName(value);
            default: return Collections.unmodifiableList(new ArrayList<Manager>());
        }

    }

    public void setManagerRepository(ManagerRepository managerRepository) {
    }

}
