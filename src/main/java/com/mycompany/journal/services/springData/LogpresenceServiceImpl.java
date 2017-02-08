package com.mycompany.journal.services.springData;

import com.mycompany.journal.db.model.Logpresence;
import com.mycompany.journal.db.model.Manager;
import com.mycompany.journal.services.LogpresenceService;
import com.mycompany.journal.services.springData.repositories.LogpresenceRepository;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public Iterable<Logpresence> saveEntities(Iterable<Logpresence> entities) {
        return logpresenceRepository.save(entities);
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
    public Iterable<Logpresence> findAll(Collection<Logpresence> entities) {
        Collection<Long> ids = new ArrayList<>(entities.size());
        for(Logpresence log : entities){
            ids.add(log.getId());
        }

        return logpresenceRepository.findAll(ids);
    }

    @Override
    public List<Logpresence> findLogsByManagers(Iterable<Manager> entities) {

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        LocalDateTime date = new LocalDateTime(calendar);

        return logpresenceRepository.findLogsByManagers(entities, date);
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

    @Override
    public long count(LocalDateTime field) {
        return logpresenceRepository.count(field);
    }

    public void setLogpresenceRepository(LogpresenceRepository logpresenceRepository) {
    }
}
