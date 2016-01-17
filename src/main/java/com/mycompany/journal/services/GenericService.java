package com.mycompany.journal.services;

import com.mycompany.journal.db.model.DomainObject;

import java.util.Collection;
import java.util.List;


public interface GenericService<T extends DomainObject> {

    public T save(T entity);

    public List<T> findAll();

    public void delete(T entity);

    public T findById(long id);

    public void delete(Long id);

    public long getCount();

    public List<T> findSorted(String propertySortBy, boolean asc);
}
