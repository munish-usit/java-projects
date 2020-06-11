package org.analytics.covidtracker.controllers;

import java.util.List;

import org.analytics.covidtracker.model.LocationStats;
import org.analytics.covidtracker.service.CoronaVirusDataService;
import org.analytics.covidtracker.utils.AppLogger;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class CoronaDashboardController {

	private static final Logger logger = AppLogger.getMainLogInstance(CoronaDashboardController.class);
	
	@Autowired
	CoronaVirusDataService coronaVirusDataService;

	@GetMapping("/admin/dashboard")
	public String home(Model model) {
		logger.info("Corona dashboard request received");
		List<LocationStats> allStats = coronaVirusDataService.getAllStats();
		int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
		model.addAttribute("locationStats", allStats);
		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("totalNewCases", totalNewCases);
		logger.info("Corona dashboard request processed successfully");

		return "admin/dashboard";
	}
}
