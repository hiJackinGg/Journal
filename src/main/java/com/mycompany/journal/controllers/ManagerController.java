package com.mycompany.journal.controllers;

import com.mycompany.journal.db.model.*;
import com.mycompany.journal.services.springData.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/")
public class ManagerController {

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private SectorsRepository sectorRepository;

    @Autowired
    private PositionRepository positionRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String showManagers(ModelMap model) {
        List<Manager> managerList = managerRepository.findAll();
        model.addAttribute("managerList", managerList);

        return "manager_table";
    }

    @RequestMapping(value = "createManagerStart", method = RequestMethod.GET)
    public String createManagerStart(ModelMap model) {
        List<Sector> sectorList = sectorRepository.findAll();
        List<Position> positionList = positionRepository.findAll();

        model.addAttribute("sectorList", sectorList);
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
            @RequestParam(value = "sec") long sectorId,
            @RequestParam(value = "pos") long positionId,
            ModelMap model) {

        Manager manager = new Manager();
        manager.setFirstName(firstName);
        manager.setLastName(lastName);
        manager.setMiddleName(middleName);
        manager.setPersonnel(personnel);
        manager.setEmail(email);
        if(sectorId != 0)
        manager.setSector(sectorRepository.findOne(sectorId));
        if(positionId != 0)
        manager.setPosition(positionRepository.findOne(positionId));

        managerRepository.saveAndFlush(manager);


        return "redirect:/";
    }

    @RequestMapping(value = "sortManagers", method = RequestMethod.GET)
    public String sortManagers(
            @RequestParam(value = "sortOpt") String sortOpt,
            ModelMap model) {

        List<Manager> managerList = managerRepository.findAll(new Sort(Sort.Direction.ASC, sortOpt));
        model.addAttribute("managerList", managerList);

        return "manager_table";
    }

    @RequestMapping(value = "findManagers", method = RequestMethod.GET)
    public String findManagers(
            @RequestParam(value = "findOpt") String findOpt,
            @RequestParam(value = "title") String title,
            ModelMap model) {
        List<Manager> managerList = new ArrayList<>();

        switch(findOpt){
            case "all": return "redirect:/";
            case "firstName": managerList = managerRepository.findByFirstName(title);break;
            case "lastName": managerList =  managerRepository.findByLastName(title);break;
            case "middleName": managerList =  managerRepository.findByMiddleName(title);break;
            case "personnel": managerList =  managerRepository.findByPersonnel(title);break;
            case "email": managerList =  managerRepository.findByEmail(title);break;
            case "position": managerList =  managerRepository.findByPositionName(title);break;
            case "sector": managerList =  managerRepository.findBySectorName(title);break;

        }
    //List<Manager> managerList = managerRepository.findAll(findOpt));
    model.addAttribute("managerList", managerList);

    return "manager_table";
}

    @RequestMapping(value = "deleteManager/{id}", method = RequestMethod.GET)
    public String deleteManager(
            @PathVariable long id, ModelMap model) {
        managerRepository.deleteById(id);

        return "redirect:/";
    }

    @RequestMapping(value = "findAllManager",method = RequestMethod.GET)
    public String findAllManager(ModelMap model) {
        List<Manager> managerList = managerRepository.findAll();
        model.addAttribute("managerList", managerList);

        return "manager_table";
    }
}
