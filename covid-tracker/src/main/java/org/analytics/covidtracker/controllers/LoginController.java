package org.analytics.covidtracker.controllers;

import org.analytics.covidtracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.analytics.covidtracker.service.UserService;
import org.analytics.covidtracker.utils.AppLogger;
import org.apache.logging.log4j.Logger;

@Controller
public class LoginController {

	private static final Logger logger = AppLogger.getMainLogInstance(LoginController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
    	logger.info("Login request received");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        logger.info("Login request processed successfully");
        return modelAndView;
    }
  

    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView home(){
    	logger.info("Admin home request received");
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        logger.debug("Authenticated user name {} , principal {} , details {}",auth.getName(),auth.getPrincipal(),auth.getDetails());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        logger.info("Admin home request processed successfully");
        return modelAndView;
    }


}
