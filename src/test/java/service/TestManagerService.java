package service;

import com.mycompany.journal.db.model.Manager;
import com.mycompany.journal.services.GenericService;
import com.mycompany.journal.services.ManagerService;
import com.mycompany.journal.services.springData.repositories.ManagerRepository;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;


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

    @Test
    public void testFindByLogin(){
        Manager m =((ManagerService) getService()).findByLogin("user");

        System.out.println(m);
    }

//    @Test
//    public void testFi(){
//
//
//
//        Calendar calendar = Calendar.getInstance();
//
//        //calendar.setTime( new Date() );
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//
//       LocalDateTime d = new LocalDateTime("1994-02-10");
//        //String dd = org.joda.time.format.DateTimeFormat.forPattern("yyyy-MM-dd").print(d);
//        System.out.println(d);
//    }



    private Set<Manager> findSubordinates(Manager manager){
        boolean isUser = true;
        Set<Manager> set = new TreeSet<>();

        findSubordinates(manager, isUser, set);

        return set;
    }

    private void findSubordinates(Manager manager, boolean isUser, Set<Manager> set) {
        System.out.println(manager.getId());
        System.out.println(manager.getDelegatedFrom());
        System.out.println(manager.getSubordinates());
        if(!isUser) {
            if (!set.add(manager))
                return;
        }

        for(Manager m : manager.getDelegatedFrom()){
            findSubordinates(m, false, set);
        }

        for(Manager m : manager.getSubordinates()){
            findSubordinates(m, false, set);
        }

    }

    @Test
    public void testFindSubordinates(){

        Manager manager = new Manager(1);

        Manager m11 = new Manager(11);
        Manager m12 = new Manager(12);
        manager.addSubordinate(m11);
        manager.addSubordinate(m12);
        Manager m111 = new Manager(111);
        Manager m112 = new Manager(112);
        m11.addSubordinate(m111);
        m11.addSubordinate(m112);
        m11.addManagerDelegatedFrom(manager);
        Manager m121 = new Manager(121);
        m12.addSubordinate(m121);


        Manager m011 = new Manager(-11);
        Manager m012 = new Manager(-12);
        manager.addSubordinate(m011);
        manager.addSubordinate(m012);
        Manager m0111 = new Manager(-111);
        Manager m0112 = new Manager(-112);
        m011.addSubordinate(m0111);
        m011.addSubordinate(m0112);
        Manager m0121 = new Manager(-121);
        m012.addSubordinate(m0121);


        //findSubordinates(manager);

        System.out.println(findSubordinates(m11));
    }

}
