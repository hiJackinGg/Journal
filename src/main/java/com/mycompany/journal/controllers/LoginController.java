package com.mycompany.journal.controllers;

import com.mycompany.journal.db.model.Login;
import com.mycompany.journal.db.model.Logpresence;
import com.mycompany.journal.db.model.Manager;
import com.mycompany.journal.db.model.Reason;
import com.mycompany.journal.services.LoginService;
import com.mycompany.journal.services.ManagerService;
import com.mycompany.journal.services.ReasonService;
import com.mycompany.journal.services.springData.repositories.LoginRepository;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Qualifier("springDataJpaLoginService")
    @Autowired
    private LoginService loginService;

    @Qualifier("springDataJpaManagerService")
    @Autowired
    private ManagerService managerService;

    @InitBinder
    public void initManagerBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(Manager.class, new PropertyEditorSupport(){

            @Override
            public void setAsText(String id) {
                System.err.println( "BINDER1m : id = "+ id);
                Long managerId = null;
                try {
                    managerId = Long.valueOf(id);
                }
                catch (NumberFormatException ex){
                    System.err.println( "!!!!EXCEPTION!!!");
                    setValue(null);
                    return;
                }
                setValue(managerService.findById(managerId));
            }
        });
    }

    @RequestMapping(value = "loginList", method = RequestMethod.GET)
    public String showLogs(ModelMap model) {
        List<Login> loginList = loginService.findAll();
        model.addAttribute("loginList", loginList);

        return "logpresence_table";
    }

    @RequestMapping(value = "createLoginStart", method = RequestMethod.GET)
    public String createLogStart(ModelMap model) {

        List<Manager> managerList = managerService.findAll();

        model.addAttribute("managerList", managerList);
        model.addAttribute("login", new Login());
        return "login_create";
    }

    @RequestMapping(value = "createLog", method = RequestMethod.POST)
    public String createLog(
            @Valid @ModelAttribute("login") Login login,
            BindingResult result,
            ModelMap model) throws ParseException {

        if (result.hasErrors()) {
            List<Manager> managerList = managerService.findAll();
            model.addAttribute("login", login);
            model.addAttribute("managerList", managerList);
            return "logpresence_create";

        } else {

            loginService.save(login);
        }

        return "redirect:/loginList";
    }

    @RequestMapping(value = "login/{id}", method = RequestMethod.GET)
    public String updateLogStart(@PathVariable("id") Long id, ModelMap model) {

        Login login = loginService.findById(id);
        model.addAttribute("login", login);

        List<Manager> managerList = managerService.findAll();

        model.addAttribute("managerList", managerList);


        return "login_update";
    }

    @RequestMapping(value = "updateLogin", method = RequestMethod.POST)
    public String updateLog(
            @Valid @ModelAttribute("login") Login login,
            BindingResult result,
            ModelMap model) {

        if (result.hasErrors()) {
            List<Manager> managerList = managerService.findAll();
            model.addAttribute("login", login);
            model.addAttribute("managerList", managerList);

            return "login_create";

        } else {

            loginService.save(login);
        }


        return "redirect:/loginList";
    }

}
