package com.mycompany.journal.services.springData;

import com.mycompany.journal.config.ManagerParser;
import com.mycompany.journal.db.model.*;
import com.mycompany.journal.services.*;
import com.mycompany.journal.services.springData.repositories.ManagerRepository;
import com.mycompany.journal.services.springData.repositories.PositionRepository;
import com.mycompany.journal.services.springData.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("springDataJpaManagerService")
@Repository
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public Manager findByLogin(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException(
                    "Argument is incorrect !");
        }

        return managerRepository.findManagerByLogin(username);
    }

    @Transactional(readOnly = true)
    @Override
    public Set<Manager> getSubordinatesList(String username) {
        Manager manager = this.findByLogin(username);
        Set<Manager> managerSet = ManagerParser.findSubordinates(manager);

        return Collections.unmodifiableSet(managerSet);
    }

    @Override
    public Manager save(Manager entity) {
        if (entity == null) {
            throw new IllegalArgumentException(
                    "Argument is null !");
        }

        return managerRepository.save(entity);
    }

    @Override
    public Iterable<Manager> saveEntities(Iterable<Manager> entities) {
        return null;
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

//        if (id == null) {
//            throw new IllegalArgumentException(
//                    "Argument is null !");
//        }

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
    public Iterable<Manager> findAll(Collection<Manager> entities) {
        return null;
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
            case "sector": return managerRepository.findBySubdivisionName(value);
            default: return Collections.unmodifiableList(new ArrayList<Manager>());
        }

    }

    public void setManagerRepository(ManagerRepository managerRepository) {
    }

}
