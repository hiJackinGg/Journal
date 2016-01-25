package com.mycompany.journal.controllers;

import com.mycompany.journal.db.model.*;
import com.mycompany.journal.services.*;
import com.mycompany.journal.services.springData.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Qualifier("springDataJpaManagerService")
    @Autowired
    private ManagerService managerService;

    @Qualifier("springDataJpaSubdivisionService")
    @Autowired
    private SubdivisionService subdivisionService;

    @Qualifier("springDataJpaPositionService")
    @Autowired
    private PositionService positionService;

    @RequestMapping(method = RequestMethod.GET)
    public String showManagers(ModelMap model) {
        List<Manager> managerList = managerService.findAll();
        model.addAttribute("managerList", managerList);

        return "manager_table";
    }

    @RequestMapping(value = "createManagerStart", method = RequestMethod.GET)
    public String createManagerStart(ModelMap model) {
        List<Subdivision> subdivisionList = subdivisionService.findAll();
        List<Position> positionList = positionService.findAll();

        model.addAttribute("subdivisionList", subdivisionList);
        model.addAttribute("positionList", positionList);

        return "manager_create";
    }

    @RequestMapping(value = "createManager", method = RequestMethod.GET)
    public String createManager(
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName,
            @RequestParam(value = "middleName") String middleName,
            @RequestParam(value = "personnel") String personnel,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "sec") long subdivisionId,
            @RequestParam(value = "pos") long positionId,
            ModelMap model) {

        Manager manager = new Manager();
        manager.setFirstName(firstName);
        manager.setLastName(lastName);
        manager.setMiddleName(middleName);
        manager.setPersonnel(personnel);
        manager.setEmail(email);
        if(subdivisionId != 0)
        manager.setSector(subdivisionService.findById(subdivisionId));
        if(positionId != 0)
        manager.setPosition(positionService.findById(positionId));

        managerService.save(manager);


        return "redirect:/manager";
    }

    @RequestMapping(value = "sortManagers", method = RequestMethod.GET)
    public String sortManagers(
            @RequestParam(value = "sortOpt") String sortOpt,
            ModelMap model) {

        List<Manager> managerList = managerService.findSorted(sortOpt, true);
        model.addAttribute("managerList", managerList);

        return "manager_table";
    }

    @RequestMapping(value = "findManagers", method = RequestMethod.GET)
    public String findManagers(
            @RequestParam(value = "findOpt") String findOpt,
            @RequestParam(value = "title") String title,
            ModelMap model) {

        List<Manager> managerList = managerService.findOneProperty(findOpt, title);

        model.addAttribute("managerList", managerList);

        return "manager_table";
}

    @RequestMapping(value = "deleteManager/{id}", method = RequestMethod.GET)
    public String deleteManager(
            @PathVariable long id, ModelMap model) {
        managerService.delete(id);

        return "redirect:/manager";
    }

    @RequestMapping(value = "findAllManager",method = RequestMethod.GET)
    public String findAllManager(ModelMap model) {
        List<Manager> managerList = managerService.findAll();
        model.addAttribute("managerList", managerList);

        return "manager_table";
    }
}
