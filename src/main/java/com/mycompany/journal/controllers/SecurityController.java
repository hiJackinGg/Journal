package com.mycompany.journal.controllers;

import com.mycompany.journal.db.model.Manager;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.jaas.JaasAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;


@Controller
public class SecurityController {

    private static Logger log = Logger.getLogger(SecurityController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error,
                                      ModelMap model) {
        if(error != null) {
            model.addAttribute("error", "Invalid username or password");
            //return "error403";
        }


        return "login";
    }

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public String accessDenied(Principal user, //let to know the name of authenticated user
                                  ModelMap model) {
        if(user != null) {
            model.addAttribute("errorMsg", user.getName() + ", you do not have permission");
        }
        else{
            model.addAttribute("errorMsg", "You do not have permission !");
        }


        return "error403";
    }

    private void showUserDetails(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();


        log.info(userDetails.getUsername());
        log.info(userDetails.getPassword());

        for(GrantedAuthority ga : userDetails.getAuthorities()){
            log.info(ga.getAuthority());

        }

    }

}
