package com.mycompany.journal.controllers;

import com.mycompany.journal.db.model.*;
import com.mycompany.journal.services.*;
import com.mycompany.journal.services.springData.repositories.*;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;


@Controller
//@RequestMapping("/manager")
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

//    @RequestMapping( method = RequestMethod.GET)
//    public String showOwnManagers(ModelMap model) {
//        List<Manager> managerList = managerService.findAll();
//        model.addAttribute("managerList", managerList);
////        List<Manager> managerList = new ArrayList<>();
////
////        Manager manager = managerService.findByLogin("admin");
////        managerList.add(manager);
////        model.addAttribute("managerList", managerList);
//        return "manager_table";
//    }

//    @InitBinder
//    public void initSubdivisionBinder(WebDataBinder dataBinder) {
//
//        dataBinder.registerCustomEditor(Subdivision.class, new PropertyEditorSupport(){
//            @Override
//            public void setAsText(String id) {
//
//                Long subdivisionId = null;
//                try {
//                    subdivisionId = Long.valueOf(id);
//                } catch (NumberFormatException ex) {
//                    setValue(null);
//                    return;
//                }
//                setValue(subdivisionService.findById(subdivisionId));
//            }
//        });
//    }
//
//
//    @InitBinder
//    public void initPositionBinder(WebDataBinder dataBinder) {
//
//        dataBinder.registerCustomEditor(Position.class, new PropertyEditorSupport(){
//
//            @Override
//            public void setAsText(String id) {
//
//                Long positionId = null;
//                try {
//                    positionId = Long.valueOf(id);
//                }
//                catch (NumberFormatException ex){
//                    setValue(null);
//                    return;
//                }
//                setValue(managerService.findById(positionId));
//            }
//        });
//    }

    @RequestMapping(value = "managers", method = RequestMethod.GET)
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
        model.addAttribute("manager", new Manager());

        return "manager_create";
    }

    @RequestMapping(value = "updateManager", method = RequestMethod.POST)
    public String updateManager(
            @Valid @ModelAttribute("manager") Manager manager, BindingResult result,
            ModelMap model) {

        if (result.hasErrors()) {
            List<Subdivision> subdivisionList = subdivisionService.findAll();
            List<Position> positionList = positionService.findAll();

            model.addAttribute("subdivisionList", subdivisionList);
            model.addAttribute("positionList", positionList);
            model.addAttribute("manager", manager);
            System.err.println("ERROR");
            System.err.println(result.getAllErrors());
            return "manager_update";
        } else {
            System.out.println(manager.getInitials());
            System.out.println(manager.getPosition());
            System.out.println(manager.getSubdivision().getName());
        }

        //managerService.save(manager);


        return "redirect:/managers";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String updateManagerStart(@PathVariable("id") Long id, ModelMap model) {

        Manager manager = managerService.findById(id);
        model.addAttribute("manager", manager);

        List<Subdivision> subdivisionList = subdivisionService.findAll();
        List<Position> positionList = positionService.findAll();

        model.addAttribute("subdivisionList", subdivisionList);
        model.addAttribute("positionList", positionList);

        return "manager_create";
    }

    @RequestMapping(value = "createManager", method = RequestMethod.POST)
    public String createManager(
            @Valid @ModelAttribute("manager") Manager manager, BindingResult result,
            ModelMap model) {

        if (result.hasErrors()) {
            List<Subdivision> subdivisionList = subdivisionService.findAll();
            List<Position> positionList = positionService.findAll();

            model.addAttribute("subdivisionList", subdivisionList);
            model.addAttribute("positionList", positionList);
            model.addAttribute("manager", manager);
            System.err.println("ERROR");
            System.err.println(result.getAllErrors());
            return "manager_create";
        } else {
            System.out.println(manager.getInitials());
            System.out.println(manager.getPosition());
            System.out.println(manager.getSubdivision().getName());
        }

        //managerService.save(manager);


        return "redirect:/managers";
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
