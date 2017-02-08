package com.mycompany.journal.services;

import com.mycompany.journal.db.model.DomainObject;

import java.util.Collection;
import java.util.List;


public interface GenericService<T extends DomainObject> {

    T save(T entity);

    Iterable<T> saveEntities(Iterable<T> entities);

    List<T> findAll();

    void delete(T entity);

    T findById(Long id);

    void delete(Long id);

    long getCount();

    List<T> findSorted(String propertySortBy, boolean asc);

    Iterable<T> findAll(Collection<T> entities);
}
