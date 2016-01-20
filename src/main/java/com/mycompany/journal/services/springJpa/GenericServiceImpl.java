package com.mycompany.journal.services.springJpa;

import com.mycompany.journal.db.model.*;
import com.mycompany.journal.services.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;

public abstract class GenericServiceImpl<T extends DomainObject> implements GenericService<T> {

    private static final String QUERY_SELECT_ALL = "SELECT x FROM %s x";
    private static final String QUERY_COUNT_ALL = "SELECT COUNT(x) FROM %s x";

    @PersistenceContext
    protected EntityManager em;
    /**
     * Persistent class that this service works with
     */
    protected Class<T> persistentClass;


    public GenericServiceImpl(Class<T> persistentClass) {

        this.persistentClass = persistentClass;
    }

    @Override
    public T save(T entity) {

        return null;
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public void delete(T entity) {

    }

    @Override
    public T findById(Long id) {
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
    public List<T> findSorted(String propertySortBy, boolean asc) {
        return null;
    }
}
