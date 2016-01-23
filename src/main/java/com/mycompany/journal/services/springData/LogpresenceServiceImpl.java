package com.mycompany.journal.services.springData;

import com.mycompany.journal.db.model.Logpresence;
import com.mycompany.journal.services.LogpresenceService;
import com.mycompany.journal.services.springData.repositories.LogpresenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("springDataJpaLogpresenceService")
public class LogpresenceServiceImpl implements LogpresenceService {

    @Autowired
    private LogpresenceRepository logpresenceRepository;

    @Override
    public Logpresence save(Logpresence entity) {

        if (entity == null) {
            throw new IllegalArgumentException(
                    "Argument is null !");
        }

        return logpresenceRepository.save(entity);
    }

    @Override
    public List<Logpresence> findAll() {

        return logpresenceRepository.findAll();
    }

    @Override
    public void delete(Logpresence entity) {

        if (entity == null) {
            throw new IllegalArgumentException(
                    "Argument is null !");
        }

        logpresenceRepository.delete(entity);
    }

    @Override
    public Logpresence findById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException(
                    "Argument is null !");
        }

        return logpresenceRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {

        if (id == null) {
            throw new IllegalArgumentException(
                    "Argument is null !");
        }

        logpresenceRepository.delete(id);

    }

    @Override
    public long getCount() {
        return logpresenceRepository.count();
    }

    @Override
    public List<Logpresence> findSorted(String propertySortBy, boolean asc) {

        if (propertySortBy == null || propertySortBy.isEmpty()) {
            throw new IllegalArgumentException(
                    "Argument is incorrect !");
        }

        Sort.Direction direction;

        if(asc == true)
            direction = Sort.Direction.ASC;
        else
            direction = Sort.Direction.DESC;

        return logpresenceRepository.findAll(new Sort(direction, propertySortBy));
    }

    @Override
    public List<Logpresence> findAllWhoNotLate() {
        return logpresenceRepository.findAllWhoNotLate();
    }

    @Override
    public List<Logpresence> findWhoMaxLate() {
        return logpresenceRepository.findWhoMaxLate();
    }

    @Override
    public List<Logpresence> findForPeriod(Date date1, Date date2) {
        return logpresenceRepository.findForPeriod(date1, date2);
    }

    public void setLogpresenceRepository(LogpresenceRepository logpresenceRepository) {
    }
}
