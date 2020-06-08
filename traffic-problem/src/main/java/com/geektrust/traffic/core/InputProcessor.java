package com.geektrust.traffic.core;


import java.util.List;

import org.apache.logging.log4j.Logger;
import com.geektrust.traffic.constants.ApplicationConstants;
import com.geektrust.traffic.models.TraverseDetail;
import com.geektrust.traffic.utils.AppLogger;
import com.geektrust.traffic.utils.Utils;

public class InputProcessor {

	private static final Logger logger = AppLogger.getMainLogInstance(InputProcessor.class);
		
	public void processInput(List<String> inputs) {
		logger.info("Calculating optimized travel time for the inputs size :: {}",inputs.size());
		for(String input : inputs) {
			logger.info("*******************************************************************");
			logger.info("Starting processing input {}",input);
			boolean valid = Utils.validateInput(input);
			if(valid) {
				String [] inputDetails = input.split(ApplicationConstants.SPLIT);
				TrafficFinder fd = new TrafficFinder();
				GenerateOutput out = new GenerateOutput();
				fd.setInputDetails(inputDetails);
				fd.updateOrbitsSpeed();
				fd.findPossibleTraversalDetails();
				TraverseDetail result = fd.findOptimumTraverseDetail();
				out.generateOutputMessage(result, input);
				logger.info("Successfully processed input {}",input);
			}else {
				logger.warn("Ignoring invalid input {}",input);
			}
			
		}
	}
	
	
	
	
	
	
	
}
