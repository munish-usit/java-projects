package org.analytics.covidtracker.controllers;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.analytics.covidtracker.utils.AppLogger;
import org.apache.logging.log4j.Logger;

@Controller
public class LoginController {

	private static final Logger logger = AppLogger.getMainLogInstance(LoginController.class);
   
    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
    	logger.info("Login request received");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        logger.info("Login request processed successfully");
        return modelAndView;
    }
}