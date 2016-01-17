package com.mycompany.journal.controllers;

import com.mycompany.journal.db.model.Logpresence;
import com.mycompany.journal.db.model.Manager;
import com.mycompany.journal.db.model.Reason;
import com.mycompany.journal.services.*;
import com.mycompany.journal.services.springData.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/logpresence")
public class LogpresenceController {

    @Autowired
    private LogpresenceService logService;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private ReasonService reasonService;

    @RequestMapping(method = RequestMethod.GET)
    public String showLogs(ModelMap model) {
        List<Logpresence> logList = logService.findAll();
        model.addAttribute("logList", logList);

        return "logpresence_table";
    }

    @RequestMapping(value = "createLogStart", method = RequestMethod.GET)
    public String createLogStart(ModelMap model) {

        List<Manager> managerList = managerService.findAll();
        List<Reason> reasonList = reasonService.findAll();

        model.addAttribute("managerList", managerList);
        model.addAttribute("reasonList", reasonList);

        return "logpresence_create";
    }

    @RequestMapping(value = "createLog", method = RequestMethod.GET)
    public String createLog(
            @RequestParam(value = "dateAbsence") String dateAbsence,
            @RequestParam(value = "latenessTime") String latenessTime,
            @RequestParam(value = "note") String note,
            @RequestParam(value = "man") long managerId,
            @RequestParam(value = "res") long reasonId,
            ModelMap model) {

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

        Date date = null;

        try {
            date = df.parse(dateAbsence);
        } catch (ParseException ex) {
        }

        Logpresence log = new Logpresence();
        log.setDateAbsence(date);
        log.setNote(note);
        if(latenessTime.equals("none") != true)
            log.setLatenessTime(Integer.valueOf(latenessTime));
        if(managerId != 0)
            log.setManager(managerService.findById(managerId));
        if(reasonId != 0)
            log.setReason(reasonService.findById(reasonId));

        logService.save(log);

        return "redirect:/logpresence";
    }

    @RequestMapping(value = "deleteLog/{id}", method = RequestMethod.GET)
    public String deleteLog(
            @PathVariable long id, ModelMap model) {
        logService.delete(id);

        return "redirect:/logpresence";
    }

    @RequestMapping(value = "findLogs1", method = RequestMethod.GET)
    public String findLogs1(
            ModelMap model) {

        List<Logpresence> logList = logService.findAllWhoNotLate();
        model.addAttribute("logList", logList);

        return "logpresence_table";
    }

    @RequestMapping(value = "findLogs2", method = RequestMethod.GET)
    public String findLogs2(
            ModelMap model) {

        List<Logpresence> logList = logService.findWhoMaxLate();
        model.addAttribute("logList", logList);

        return "logpresence_table";
    }

    @RequestMapping(value = "findLogs3", method = RequestMethod.GET)
    public String findLogs3(
            @RequestParam(value = "date1") String date1,
            @RequestParam(value = "date2") String date2,
            ModelMap model) {


        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

        Date datef = null;
        Date datet = null;

        try {
            datef = df.parse(date1);
            datet = df.parse(date2);
        } catch (ParseException ex) {
        }

        List<Logpresence> logList = logService.findForPeriod(datef,datet);
        model.addAttribute("logList", logList);

        return "logpresence_table";
    }
}
