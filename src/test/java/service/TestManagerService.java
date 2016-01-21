package service;

import com.mycompany.journal.db.model.Manager;
import com.mycompany.journal.services.GenericService;
import com.mycompany.journal.services.ManagerService;
import com.mycompany.journal.services.springData.repositories.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


public class TestManagerService extends TestGenericServiceAbstract<Manager> {


    //private ManagerService managerService;

    @Override
    protected GenericService<Manager> getService() {

        return (ManagerService) appContext.getBean("springDataJpaManagerService");
    }

    @Override
    protected Manager getFirstEntity() {
        Manager m = new Manager();
        m.setId(1L);
        m.setFirstName("John");
        m.setLastName("Smith");

        return m;
    }

    @Override
    protected Manager getSecondEntity() {
        Manager m = new Manager();
        m.setId(2L);
        m.setFirstName("Sam");
        m.setLastName("Brown");

        return m;
    }
}
