package service;


import com.mycompany.journal.config.ApplicationConfig;
import com.mycompany.journal.db.model.DomainObject;
import com.mycompany.journal.exceptions.GeneralServiceException;
import com.mycompany.journal.services.GenericService;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public abstract class TestGenericServiceAbstract <T extends DomainObject> {

    /** Spring application context to use in tests */
    protected static ApplicationContext appContext;

    /**
     * Obtaining Spring context for testing
     */
    @BeforeClass
    public static void obtainApplicationContext() {
        appContext = ApplicationConfig
                .getSpringApplicationContext("test_config.xml");
        assertNotNull("Application context was not created", appContext);
    }

    /** Get the service to use for testing
     */
    protected abstract GenericService<T> getService();

    /** Get the first entity for testing */
    protected abstract T getFirstEntity();

    /** Get the first entity for testing */
    protected abstract T getSecondEntity();

    /** Entity that is saved before tests and deleted afterwards */
    protected T entity1;
    /** Entity that is saved before tests and deleted afterwards */
    protected T entity2;


    @Before
    public void saveEntitiesToDatabase(){
        entity1 = getService().save(getFirstEntity());
        entity2 = getService().save(getSecondEntity());

        assertNotNull("Entity1 were not saved successfully", entity1);
        assertNotNull("Entity2 were not saved successfully", entity2);
    }


    @After
    public void deleteEntitiesFromDatabase(){
        if (entity1 != null) {
            getService().delete(entity1);
        }
        if (entity2 != null) {
            getService().delete(entity2);
        }
    }


    @Test
    public void testGetCount(){
        long cnt = getService().getCount();

        getService().delete(entity1);
        entity1 = null;

        long cnt2 = getService().getCount();

        assertEquals(cnt - 1, cnt2);

        entity1 = getService().save(getFirstEntity());

        cnt2 = getService().getCount();

        assertEquals(cnt, cnt2);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testSaveEntityNull(){
        getService().save(null);
    }


    @Test
    public void testDeleteEntityById(){
        long cnt = getService().getCount();

        getService().delete(entity1.getId());

        assertEquals(cnt-1, getService().getCount());

        assertNull(getService().findById(entity1.getId()));

        // Setting entity1 to null, so in @After method it will not be deleted again
        entity1 = null;
    }

    @Test
    public void testDeleteNonexistentEntity(){
        long cnt = getService().getCount();

        //try to delete nonexistent entity
        getService().delete(System.nanoTime());

        //nothing should happen
        assertEquals(cnt, getService().getCount());

    }


    @Test(expected=IllegalArgumentException.class)
    public void testDeleteEntityByIdNull(){
        getService().delete((Long)null);
    }


    @Test(expected=IllegalArgumentException.class)
    public void testDeleteEntityNull(){
        getService().delete((T)null);
    }


    @Test
    public void testFindAll() {
        Collection<T> list = getService().findAll();

        assertNotNull(list);
        assertFalse(list.isEmpty());

        assertTrue(list.contains(entity1));
        assertTrue(list.contains(entity2));
    }


    @Test
    public void testFindById() {
        assertNull(getService().findById(System.nanoTime()));

        T foundEntity = getService().findById(entity1.getId());
        assertNotNull(foundEntity);
        assertEquals(entity1, foundEntity);

        foundEntity = getService().findById(entity2.getId());
        assertNotNull(foundEntity);
        assertEquals(entity2, foundEntity);
    }


    @Test(expected=IllegalArgumentException.class)
    public void testFindByIdNull() {
        getService().findById(null);
    }


    @Test
    public void testFindSorted() {
        List<T> rez = getService().findSorted("id", true);
        assertNotNull(rez);
        assertTrue(rez.contains(entity1));
        assertTrue(rez.contains(entity2));

        int index1 = rez.indexOf(entity1);
        int index2 = rez.indexOf(entity2);

        if (entity1.getId().compareTo(entity2.getId()) < 0) {
            assertTrue(index1 < index2);
        } else {
            assertTrue(index1 > index2);
        }

        rez = getService().findSorted("id", false);
        assertNotNull(rez);
        assertTrue(rez.contains(entity1));
        assertTrue(rez.contains(entity2));

        index1 = rez.indexOf(entity1);
        index2 = rez.indexOf(entity2);

        if (entity1.getId().compareTo(entity2.getId()) < 0) {
            assertTrue(index1 > index2);
        } else {
            assertTrue(index1 < index2);
        }
    }

    @Test(expected=IllegalArgumentException.class)
    public void testFindSortedFieldNull() {
        getService().findSorted(null, true);
    }

}
