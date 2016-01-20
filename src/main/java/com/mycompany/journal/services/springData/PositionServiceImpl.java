package com.mycompany.journal.services.springData;

import com.mycompany.journal.db.model.Position;
import com.mycompany.journal.services.PositionService;
import com.mycompany.journal.services.springData.repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionRepository positionRepository;

    @Override
    public Position save(Position entity) {

        if (entity == null) {
            throw new IllegalArgumentException(
                    "Argument is null !");
        }

        return positionRepository.save(entity);
    }

    @Override
    public List<Position> findAll() {

        return positionRepository.findAll();
    }

    @Override
    public void delete(Position entity) {

        if (entity == null) {
            throw new IllegalArgumentException(
                    "Argument is null !");
        }

        positionRepository.delete(entity);
    }

    @Override
    public Position findById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException(
                    "Argument is null !");
        }

        return positionRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {

        if (id == null) {
            throw new IllegalArgumentException(
                    "Argument is null !");
        }

        positionRepository.delete(id);

    }

    @Override
    public long getCount() {
        return positionRepository.count();
    }

    @Override
    public List<Position> findSorted(String propertySortBy, boolean asc) {

        if (propertySortBy == null || propertySortBy.isEmpty()) {
            throw new IllegalArgumentException(
                    "Argument is incorrect !");
        }

        Sort.Direction direction;

        if(asc == true)
            direction = Sort.Direction.ASC;
        else
            direction = Sort.Direction.DESC;

        return positionRepository.findAll(new Sort(direction, propertySortBy));
    }

}
