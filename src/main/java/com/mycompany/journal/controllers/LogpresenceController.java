package com.mycompany.journal.controllers;

import com.mycompany.journal.db.model.Logpresence;
import com.mycompany.journal.db.model.Manager;
import com.mycompany.journal.db.model.Reason;
import com.mycompany.journal.services.*;
import com.mycompany.journal.services.springData.repositories.*;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/")
public class LogpresenceController {

    @Qualifier("springDataJpaLogpresenceService")
    @Autowired
    private LogpresenceService logService;

    @Qualifier("springDataJpaManagerService")
    @Autowired
    private ManagerService managerService;

    @Qualifier("springDataJpaReasonService")
    @Autowired
    private ReasonService reasonService;

    @InitBinder//({"reason"})
    public void initReasonBinder(WebDataBinder dataBinder) {

       // dataBinder.setDisallowedFields("id");
        dataBinder.registerCustomEditor(Reason.class, new PropertyEditorSupport(){
            @Override
            public void setAsText(String id) {

                System.err.println("BINDER1 : id = " + id + ";" + id.length() + "; ");

                Long reasonId = null;
                try {
                    reasonId = Long.valueOf(id);
                } catch (NumberFormatException ex) {
                    setValue((Reason) null);
                    return;
                }
                setValue(reasonService.findById(reasonId));
            }

//                @Override
//                public String getAsText() {
//                    System.err.println("BINDER2 ");
//                    Reason reason = (Reason) this.getValue();
//                    String id = reason.getId().toString();
//                    return id;
//
//            }
        });
    }

    //set manager
    @InitBinder//({"manager"})
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

//            @Override
//            public String getAsText() {
//                System.err.println("BINDER2m ");
//                Manager manager = (Manager) this.getValue();
//                String id = manager.getId().toString();
//                return id;
//
//            }
        });
    }

//    @RequestMapping(method = RequestMethod.GET)
//    public String getListFoToday(ModelMap model, Principal user) {
//
////        Set<Manager> managerSet = managerService.getSubordinatesList(user.getName());
////        List<Logpresence> logList = logService.findLogsByManagers(managerSet);
////        model.addAttribute("logList", logList);
//
//        return "login_update";//"logpresence_table";
//    }

    @RequestMapping(/*value = "logpresenceList", */method = RequestMethod.GET)
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
        model.addAttribute("log", new Logpresence());
        return "logpresence_create";
    }

//    @RequestMapping(value = "createLog", method=RequestMethod.POST)
//    public String createLog(
//            @ModelAttribute Logpresence log,
//            ModelMap model) throws ParseException {
//
//
//
//        logService.save(log);
//
//        return "redirect:/logpresence";
//    }

    @RequestMapping(value = "createLog", method = RequestMethod.POST)
    public String createLog(
           @Valid @ModelAttribute("log") Logpresence log, BindingResult result,
//            @RequestParam(value = "dateAbsence") String dateAbsence,
//            @RequestParam(value = "latenessTime") String latenessTime,
//            @RequestParam(value = "note") String note,
//            @RequestParam(value = "manager") long managerId,
//            @RequestParam(value = "reason") long reasonId,
            ModelMap model) throws ParseException {

        //log.setDateAbsence(new LocalDateTime());
        if (result.hasErrors()) {
            System.err.println("ERROR");
            System.err.println(result.getAllErrors());
            return "logpresence_create";
        } else {
            System.out.println(log.getManager().getInitials());
            System.out.println(log.getReason());
            System.out.println(log.getLatenessTime());
            System.out.println(log.getDateAbsence());
        }



//        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
//        Calendar date = new GregorianCalendar();
//        date.setTime(df.parse(dateAbsence));
//
//        Logpresence log = new Logpresence();
//       // log.setDateAbsence(date);
//        log.setNote(note);
//        if(latenessTime.equals("none") != true)
//            log.setLatenessTime(Integer.valueOf(latenessTime));
//        if(managerId != 0)
//            log.setManager(managerService.findById(managerId));
//        if(reasonId != 0)
//            log.setReason(reasonService.findById(reasonId));
//
//        logService.save(log);

        return "redirect:/logpresenceList";
    }

    @RequestMapping(value = "updateLog", method = RequestMethod.POST)
    public String updateLog(
            @Valid @ModelAttribute("log") Logpresence log, BindingResult result,

            ModelMap model) throws ParseException {

        log.setDateAbsence(new LocalDateTime());
        if (result.hasErrors()) {
            model.addAttribute("log", log);
            List<Manager> managerList = managerService.findAll();
            List<Reason> reasonList = reasonService.findAll();

            model.addAttribute("managerList", managerList);
            model.addAttribute("reasonList", reasonList);
            System.err.println("ERROR");
            System.err.println(result.getAllErrors());
            return "logpresence_create";
        } else {
            System.out.println(log.getManager().getInitials());
            System.out.println(log.getReason());
            System.out.println(log.getLatenessTime());
            System.out.println(log.getDateAbsence());
            System.out.println(log.getNote());
        }


        return "redirect:/logpresenceList";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String updateLogStart(@PathVariable("id") Long id, ModelMap model) {

        Logpresence log = logService.findById(id);
        model.addAttribute("log", log);

        List<Manager> managerList = managerService.findAll();
        List<Reason> reasonList = reasonService.findAll();

        model.addAttribute("managerList", managerList);
        model.addAttribute("reasonList", reasonList);

        return "logpresence_create";
    }

    @RequestMapping(value = "deleteLog/{id}", method = RequestMethod.GET)
    public String deleteLog(
            @PathVariable long id, ModelMap model) {
        logService.delete(id);

        return "redirect:/logpresenceList";
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
