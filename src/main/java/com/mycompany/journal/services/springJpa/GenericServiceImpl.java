package com.mycompany.journal.services.springJpa;

import com.mycompany.journal.db.model.*;
import com.mycompany.journal.exceptions.GeneralServiceException;
import com.mycompany.journal.services.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//Exception works are executed manually as @Repository is not used yet

public abstract class GenericServiceImpl<T extends DomainObject> implements GenericService<T> {

    //add logging

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
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public T save(T entity) {

        if (entity == null) {
            throw new IllegalArgumentException(
                    "Argument is null !");
        }

        T savedEntity = null;

        try{

            if (entity.getId() == null) {

                em.persist(entity);
                savedEntity = entity;

            } else {

                savedEntity = em.merge(entity);
            }

        }
        catch(Exception e){
            if (entity.getId() == null) {
                throw new GeneralServiceException("Failed to add "
                        + entity.getClass().getSimpleName(), e);
            } else {
                throw new GeneralServiceException("Failed to update "
                        + entity.getClass().getSimpleName() + "with id " + entity.getId(), e);
            }

        }

        return savedEntity;
    }

    @Override
    @Transactional(readOnly=true)
    public List<T> findAll() {

        try{
            List<T> list = em.createQuery(String.format(QUERY_SELECT_ALL,
                    persistentClass.getSimpleName())).getResultList();
            if (list != null)
                return list;
            else
                return new ArrayList<T>();
        }
        catch(Exception e){
            //logging
            return new ArrayList();
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(T entity) {

        if (entity == null) {
            throw new IllegalArgumentException(
                    "Argument is null !");
        }

        try{
            if (!em.contains(entity)) { //however anyway it will not contain
                T attachedEntity = em.merge(entity);
                em.remove(attachedEntity);
            } else {
                em.remove(entity);
            }

        }
        catch(Exception e){
            throw new GeneralServiceException("Failed to delete "
                    + entity.getClass().getSimpleName() + "with id " + entity.getId(), e);

        }

    }

    @Override
    @Transactional(readOnly=true)
    public T findById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException(
                    "Argument is null !");
        }

        try{

            T savedEntity = em.find(persistentClass, id);
            return savedEntity;

        }
        catch(Exception e){
            //logging
            return null;

        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(Long id) {

        if (id == null) {
            throw new IllegalArgumentException(
                    "Argument is null !");
        }

        T savedEntity = null;

        try{

            savedEntity = this.findById(id);

            if (savedEntity != null) {
                this.delete(savedEntity);
            }

        }
        catch(Exception e){
            throw new GeneralServiceException("Failed to delete "
                    + persistentClass.getSimpleName() + "with id " + id, e);

        }

    }

    @Override
    @Transactional(readOnly=true)
    public long getCount() {

        try{

            return (Long) em.createQuery(String.format(QUERY_COUNT_ALL, persistentClass
                    .getSimpleName())).getSingleResult();

        }
        catch(Exception e){
            //logging
            return 0;

        }
    }

    @Override
    @Transactional(readOnly=true)
    public List<T> findSorted(String propertySortBy, boolean asc) {

        if (propertySortBy == null || propertySortBy.isEmpty()) {
            throw new IllegalArgumentException(
                    "Argument is incorrect !");
        }

        try{

            String newQuery = addSortPropertyToQuery(String.format(QUERY_SELECT_ALL,
                    persistentClass.getSimpleName()), propertySortBy, asc);

            return em.createQuery(newQuery).getResultList();

        }
        catch(Exception e){
            //logging
            return new ArrayList<T>();

        }
    }


    /**
     * Adds sort param to query and returning new query
     *
     * @param query
     *            Query to append with sort information
     * @param propertySortBy
     *            Property to sort by
     * @param asc
     *            Specifies sort direction
     * @return new query with added sort
     */
    protected String addSortPropertyToQuery(String query, String propertySortBy,
                                            boolean asc) {
        StringBuilder sb = new StringBuilder();
        sb.append(query);
        sb.append(" ");
        sb.append("order by ");
        sb.append(propertySortBy);

        if (asc) {
            sb.append(" asc");
        } else {
            sb.append(" desc");
        }

        String newQuery = sb.toString();
        return newQuery;
    }

}
