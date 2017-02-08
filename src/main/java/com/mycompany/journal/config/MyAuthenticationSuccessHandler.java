package com.mycompany.journal.config;

import com.mycompany.journal.db.model.Logpresence;
import com.mycompany.journal.db.model.Manager;
import com.mycompany.journal.services.LoginService;
import com.mycompany.journal.services.LogpresenceService;
import com.mycompany.journal.services.ManagerService;
import com.mysql.jdbc.log.Log;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class MyAuthenticationSuccessHandler implements ApplicationListener<AuthenticationSuccessEvent> {

    LoginService loginService;

    ManagerService managerService;

    LogpresenceService logpresenceService;


    /**
     * Inserts default data (manager and date) to logpresence table for new day.
     *
     */
    void insertDefaultData(){
        List<Manager> managerList = managerService.findAll();

        List<Logpresence> logList = new ArrayList<>();

        for(Manager m : managerList) {
            Logpresence log = new Logpresence();
            log.setDateAbsence(new LocalDateTime());
            log.setManager(m);
            logList.add(log);
        }

        logpresenceService.saveEntities(logList);
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent authenticationSuccessEvent) {
        String userName = ((UserDetails) authenticationSuccessEvent.getAuthentication().
                getPrincipal()).getUsername();

        loginService.updateLoginDate(userName);

        Calendar calendar = Calendar.getInstance();

        //calendar.setTime( new Date() );
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        LocalDateTime date = new LocalDateTime(calendar);

        Long count = logpresenceService.count(date);
        if (count == 0)
            this.insertDefaultData(); //insert if there isn't new default inserted data for today

    }

    public LoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    public ManagerService getManagerService() {
        return managerService;
    }

    public void setManagerService(ManagerService managerService) {
        this.managerService = managerService;
    }

    public LogpresenceService getLogpresenceService() {
        return logpresenceService;
    }

    public void setLogpresenceService(LogpresenceService logpresenceService) {
        this.logpresenceService = logpresenceService;
    }
}