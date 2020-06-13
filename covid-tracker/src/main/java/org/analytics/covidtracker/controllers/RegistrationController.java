package org.analytics.covidtracker.controllers;

import javax.validation.Valid;
import org.analytics.covidtracker.service.UserService;
import org.analytics.covidtracker.utils.AppLogger;
import org.apache.logging.log4j.Logger;
import org.analytics.covidtracker.constants.Messages;
import org.analytics.covidtracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {

	private static final Logger logger = AppLogger.getMainLogInstance(RegistrationController.class);
	@Autowired
	private UserService userService;
	 
	@RequestMapping(value= {"/registration","/register"}, method = RequestMethod.GET)
	public ModelAndView registration(){
		logger.info("Registration request received");
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("register");
		logger.info("Registration request received");
		return modelAndView;
	}

	@RequestMapping(value = {"/registration","/register"}, method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
		logger.info("Create new user request received for user {}",user.getEmail());
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
			.rejectValue("email", "error.user",Messages.USER_ALREADY_EXIST);
			logger.warn("Create new user request for user {} status {}",user.getEmail(),Messages.USER_ALREADY_EXIST);
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("register");
			logger.error("Create new user request for user {} status {}",user.getEmail(),bindingResult.getAllErrors().toString());
			
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", Messages.USER_REGISTRATION_SUCCESS);
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("login");
			logger.info("Create new user request for user {} status {}",user.getEmail(),Messages.USER_REGISTRATION_SUCCESS);

		}
		logger.info("Create new user request processed successfully for user {}",user.getEmail());
		return modelAndView;
	}
}
