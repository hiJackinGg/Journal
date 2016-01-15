package com.mycompany.journal.services;

import com.mycompany.journal.db.model.DomainObject;

import java.util.Collection;
import java.util.List;


public interface GenericService<T extends DomainObject> {

    public void save(T entity);

    public List<T> findAll();

    public void delete(T entity);

    public void update(T entity);

    public T findById(long id);

    public void delete(Long id);

    public void deleteAll(Collection<T> entities);

    public long getCount();

    public List<T> getSorted(String propertySortBy, boolean asc);
}
