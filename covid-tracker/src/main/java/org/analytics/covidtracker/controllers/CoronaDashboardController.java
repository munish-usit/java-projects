package org.analytics.covidtracker.controllers;

import java.util.List;

import org.analytics.covidtracker.model.LocationStats;
import org.analytics.covidtracker.model.User;
import org.analytics.covidtracker.service.CoronaVirusDataService;
import org.analytics.covidtracker.service.UserService;
import org.analytics.covidtracker.utils.AppLogger;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class CoronaDashboardController {

	private static final Logger logger = AppLogger.getMainLogInstance(CoronaDashboardController.class);
	
	@Autowired
	CoronaVirusDataService coronaVirusDataService;
	
	@Autowired
    private UserService userService;


	@RequestMapping(value= {"/admin/dashboard","/admin/index","/admin/home"}, method = RequestMethod.GET)
	public String home(Model model) {
		logger.info("Corona dashboard request received");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		logger.debug("Authenticated user name {} , principal {} , details {}",auth.getName(),auth.getPrincipal(),auth.getDetails());
		model.addAttribute("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		
		List<LocationStats> allStats = coronaVirusDataService.getAllStats();
		int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
		logger.debug("allStats {} ",allStats.size());
		logger.debug("totalReportedCases {} ",+totalReportedCases);
		logger.debug("totalNewCases {} ",totalNewCases);
		
		model.addAttribute("locationStats", allStats);
		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("totalNewCases", totalNewCases);
		logger.info("Corona dashboard request processed successfully");

		return "admin/index";
	}
	
	 
}
