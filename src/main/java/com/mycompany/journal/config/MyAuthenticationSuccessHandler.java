package com.mycompany.journal.config;

import com.mycompany.journal.db.model.Logpresence;
import com.mycompany.journal.db.model.Manager;
import com.mycompany.journal.services.LoginService;
import com.mycompany.journal.services.LogpresenceService;
import com.mycompany.journal.services.ManagerService;
import com.mysql.jdbc.log.Log;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
            log.setDateAbsence(new GregorianCalendar());
            log.setManager(m);
            logList.add(log);
        }

        logpresenceService.save(logList);
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent authenticationSuccessEvent) {
        String userName = ((UserDetails) authenticationSuccessEvent.getAuthentication().
                getPrincipal()).getUsername();

        loginService.updateLoginDate(userName);

        Long count = logpresenceService.count(new GregorianCalendar());
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