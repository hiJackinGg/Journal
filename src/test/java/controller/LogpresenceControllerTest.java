package controller;


import com.mycompany.journal.controllers.LogpresenceController;
import com.mycompany.journal.controllers.ManagerController;
import com.mycompany.journal.db.model.Logpresence;
import com.mycompany.journal.db.model.Manager;
import com.mycompany.journal.db.model.Reason;
import com.mycompany.journal.services.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LogpresenceControllerTest extends AbstractControllerTest {

    private final List<Logpresence> logs = new ArrayList<Logpresence>();
    private final List<Manager> managers = new ArrayList<Manager>();
    private final List<Reason> reasons = new ArrayList<Reason>();

    private LogpresenceService logService;


    private ManagerService managerService;


    private ReasonService reasonService;


    @Before
    public void initContacts() {
        Manager manager = new Manager();
        manager.setId(1L);
        manager.setFirstName("John");
        manager.setLastName("Smith");
        managers.add(manager);

        Reason reason = new Reason(1L, "overslept");
        reasons.add(reason);

        Logpresence logpresence = new Logpresence();
        logpresence.setId(1L);
        logpresence.setDateAbsence(new Date());
        logpresence.setLatenessTime(20);
        logpresence.setManager(manager);
        logpresence.setReason(reason);
        logpresence.setNote("some text");

        logs.add(logpresence);
    }

    @Test
    public void testShowLogs() throws Exception {

        logService = mock(LogpresenceService.class);
        when(logService.findAll()).thenReturn(logs);

        LogpresenceController logpresenceController = new LogpresenceController();

        ReflectionTestUtils.setField(logpresenceController, "logService", logService);

        ExtendedModelMap model = new ExtendedModelMap();

        String result = logpresenceController.showLogs(model);

        assertNotNull(result);
        assertEquals(result, "logpresence_table");

        List<Logpresence> modelManagers = (List<Logpresence>) model.get("logList");

        assertEquals(1, modelManagers.size());
    }

    //@Test//hasn't done yet
    public void testCreateLog() {

        logService = mock(LogpresenceService.class);
        managerService = mock(ManagerService.class);
        reasonService = mock(ReasonService.class);

        final Logpresence newLogpresence = new Logpresence();
        newLogpresence.setId(2L);
        newLogpresence.setDateAbsence(new Date());
        newLogpresence.setLatenessTime(15);
        newLogpresence.setNote("some text");

        //

    }

    @Test
    public void testCreateLogStart() throws Exception {

        managerService = mock(ManagerService.class);
        reasonService = mock(ReasonService.class);

        when(managerService.findAll()).thenReturn(managers);
        when(reasonService.findAll()).thenReturn(reasons);

        LogpresenceController logpresencController = new LogpresenceController();

        ReflectionTestUtils.setField(logpresencController, "managerService", managerService);
        ReflectionTestUtils.setField(logpresencController, "reasonService", reasonService);

        ExtendedModelMap model = new ExtendedModelMap();

        String result = logpresencController.createLogStart(model);

        assertNotNull(result);
        assertEquals(result, "logpresence_create");

        List<Manager> modelManagers = (List<Manager>) model.get("managerList");
        List<Reason> modelReasons = (List<Reason>) model.get("reasonList");

        assertEquals(1, modelManagers.size());
        assertEquals(1, modelReasons.size());
    }

    @Test
    public void testDeleteLog() throws Exception {

        logService = mock(LogpresenceService.class);

        LogpresenceController logpresenceController = new LogpresenceController();

        ReflectionTestUtils.setField(logpresenceController, "logService", logService);

        ExtendedModelMap model = new ExtendedModelMap();

        String result = logpresenceController.deleteLog(1L, model);

        assertNotNull(result);
        assertEquals(result, "redirect:/logpresence");
    }

    @Test
    public void testFindLogs1() throws Exception {

        logService = mock(LogpresenceService.class);
        when(logService.findAllWhoNotLate()).thenReturn(logs);

        LogpresenceController logpresenceController = new LogpresenceController();

        ReflectionTestUtils.setField(logpresenceController, "logService", logService);

        ExtendedModelMap model = new ExtendedModelMap();

        String result = logpresenceController.findLogs1(model);

        assertNotNull(result);
        assertEquals(result, "logpresence_table");

        List<Logpresence> modelLogpresence = (List<Logpresence>) model.get("logList");

        assertEquals(1, modelLogpresence.size());
    }

    @Test
    public void testFindLogs2() throws Exception {

        logService = mock(LogpresenceService.class);
        when(logService.findWhoMaxLate()).thenReturn(logs);

        LogpresenceController logpresenceController = new LogpresenceController();

        ReflectionTestUtils.setField(logpresenceController, "logService", logService);

        ExtendedModelMap model = new ExtendedModelMap();

        String result = logpresenceController.findLogs2(model);

        assertNotNull(result);
        assertEquals(result, "logpresence_table");

        List<Logpresence> modelManagers = (List<Logpresence>) model.get("logList");

        assertEquals(1, modelManagers.size());
    }

}
