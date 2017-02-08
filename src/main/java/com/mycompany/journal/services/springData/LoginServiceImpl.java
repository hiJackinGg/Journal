package com.mycompany.journal.services.springData;

import com.mycompany.journal.db.model.Login;
import com.mycompany.journal.db.model.Logpresence;
import com.mycompany.journal.services.LoginService;
import com.mycompany.journal.services.springData.repositories.LoginRepository;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

@Service("springDataJpaLoginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public void updateLoginDate(String username) {
        loginRepository.updateLoginDateByUsername(new LocalDateTime(), username);
    }

    @Override
    public long count(LocalDateTime field) {
        return loginRepository.count(new LocalDateTime());
    }

    @Override
    public Login save(Login entity) {
        return null;
    }

    @Override
    public Iterable<Login> saveEntities(Iterable<Login> entities) {
        return null;
    }

    @Override
    public List<Login> findAll() {
        return null;
    }

    @Override
    public void delete(Login entity) {

    }

    @Override
    public Login findById(Long id) {
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
    public List<Login> findSorted(String propertySortBy, boolean asc) {
        return null;
    }

    @Override
    public Iterable<Login> findAll(Collection<Login> entities) {
        return null;
    }

    @Override
    public Page<Login> findAllByPage(Pageable pageable){
        return loginRepository.findAll(pageable);

    }
}
