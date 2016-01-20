package service;

import com.mycompany.journal.db.model.Logpresence;
import com.mycompany.journal.db.model.Manager;
import com.mycompany.journal.services.GenericService;
import com.mycompany.journal.services.LogpresenceService;
import com.mycompany.journal.services.ManagerService;

import java.util.Date;


public class TestLogpresenceService extends TestGenericServiceAbstract<Logpresence> {
    @Override
    protected GenericService<Logpresence> getService() {
        return (LogpresenceService) appContext.getBean("logpresenceService");
    }

    @Override
    protected Logpresence getFirstEntity() {
        Logpresence log = new Logpresence();
        log.setDateAbsence(new Date());
        log.setLatenessTime(20);
        log.setNote("some text");

        return log;
    }

    @Override
    protected Logpresence getSecondEntity() {
        Logpresence log = new Logpresence();
        log.setDateAbsence(new Date());
        log.setLatenessTime(15);
        log.setNote("some text2");

        return log;
    }
}
