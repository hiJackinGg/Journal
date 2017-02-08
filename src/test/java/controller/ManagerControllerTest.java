package controller;

import com.mycompany.journal.controllers.ManagerController;
import com.mycompany.journal.db.model.*;
import com.mycompany.journal.services.*;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.context.MessageSource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ManagerControllerTest extends AbstractControllerTest {

    private final List<Manager> managers = new ArrayList<Manager>();
    private final List<Subdivision> subdivisions = new ArrayList<Subdivision>();
    private final List<Position> positions = new ArrayList<Position>();

    private ManagerService managerService;

    private SubdivisionService sectorService;

    private PositionService positionService;

    @Before
    public void initData() {

        Manager manager = new Manager();
        manager.setId(1L);
        manager.setFirstName("John");
        manager.setLastName("Smith");
        managers.add(manager);

        subdivisions.add(new Subdivision(1L, "QA"));
        positions.add(new Position(1L, "Middle java developer"));
    }

    @Test
    public void testShowManagers() throws Exception {

        managerService = mock(ManagerService.class);
        when(managerService.findAll()).thenReturn(managers);

        ManagerController managerController = new ManagerController();

        ReflectionTestUtils.setField(managerController, "managerService", managerService);

        ExtendedModelMap model = new ExtendedModelMap();

        String result = managerController.showManagers(model);

        assertNotNull(result);
        assertEquals(result, "manager_table");

        List<Manager> modelManagers = (List<Manager>) model.get("managerList");

        assertEquals(1, modelManagers.size());
    }

//    @Test//the test works incorrectly
//    public void testCreate() {
//
//        final Manager newManager = new Manager();
//        newManager.setId(2L);
//        newManager.setFirstName("John");
//        newManager.setLastName("Smith");
//
//        sectorService = mock(SubdivisionService.class);
//        positionService = mock(PositionService.class);
//
//        managerService = mock(ManagerService.class);
//        when(managerService.save(newManager)).thenAnswer( new Answer<Manager>() {
//            public Manager answer(InvocationOnMock invocation) throws Throwable {
//                managers.add(newManager);
//                return newManager;
//            }
//        });
//
//        ManagerController managerController = new ManagerController();
//        ReflectionTestUtils.setField(managerController, "sectorService", sectorService);
//        ReflectionTestUtils.setField(managerController, "positionService", positionService);
//
//        ExtendedModelMap model = new ExtendedModelMap();
//
//        String result = managerController.createManager(
//                newManager.getFirstName(),
//                newManager.getLastName(),
//                null,
//                null,
//                null,
//                0,
//                0,
//                model
//
//        );
//
//        assertNotNull(result);
//        assertEquals("redirect:/", result);
//        assertEquals(2, managers.size());
//    }

    @Test
    public void testCreateManagerStart() throws Exception {

        sectorService = mock(SubdivisionService.class);
        positionService = mock(PositionService.class);

        when(sectorService.findAll()).thenReturn(subdivisions);
        when(positionService.findAll()).thenReturn(positions);

        ManagerController managerController = new ManagerController();

        ReflectionTestUtils.setField(managerController, "sectorService", sectorService);
        ReflectionTestUtils.setField(managerController, "positionService", positionService);

        ExtendedModelMap model = new ExtendedModelMap();

        String result = managerController.createManagerStart(model);

        assertNotNull(result);
        assertEquals(result, "manager_create");

        List<Subdivision> modelSubdivisions = (List<Subdivision>) model.get("sectorList");
        List<Position> modelPositions = (List<Position>) model.get("positionList");


        assertEquals(1, modelSubdivisions.size());
        assertEquals(1, modelPositions.size());
    }

    @Test
    public void testSortManagers() throws Exception {

        String property = "lastname";

        managerService = mock(ManagerService.class);
        when(managerService.findSorted(property, true)).thenReturn(managers);

        ManagerController managerController = new ManagerController();

        ReflectionTestUtils.setField(managerController, "managerService", managerService);

        ExtendedModelMap model = new ExtendedModelMap();

        String result = managerController.sortManagers(property, model);

        assertNotNull(result);
        assertEquals(result, "manager_table");

        List<Manager> modelManagers = (List<Manager>) model.get("managerList");

        assertEquals(1, modelManagers.size());
    }

    @Test
    public void testFindManagers() throws Exception {

        final String property = "firstname";
        final String value = "John";
        final ArrayList<Manager> resultList = new ArrayList<>();

        for(Manager m : managers)
            if(m.getFirstName().equals(value))
                resultList.add(m);

        managerService = mock(ManagerService.class);
        when(managerService.findOneProperty(property, value)).thenReturn(resultList);

        ManagerController managerController = new ManagerController();

        ReflectionTestUtils.setField(managerController, "managerService", managerService);

        ExtendedModelMap model = new ExtendedModelMap();

        String result = managerController.findManagers(property, value, model);

        assertNotNull(result);
        assertEquals(result, "manager_table");

        List<Manager> modelManagers = (List<Manager>) model.get("managerList");

        assertEquals(1, modelManagers.size());
    }

    @Test
    public void testDeleteManager() throws Exception {

        managerService = mock(ManagerService.class);

        ManagerController managerController = new ManagerController();

        ReflectionTestUtils.setField(managerController, "managerService", managerService);

        ExtendedModelMap model = new ExtendedModelMap();

        String result = managerController.deleteManager(1L, model);

        assertNotNull(result);
        assertEquals(result, "redirect:/");

    }
}
