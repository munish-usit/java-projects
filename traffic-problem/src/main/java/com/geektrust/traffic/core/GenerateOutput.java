package com.geektrust.traffic.core;

import org.apache.logging.log4j.Logger;

import com.geektrust.traffic.models.TraverseDetail;
import com.geektrust.traffic.utils.AppLogger;

public class GenerateOutput {

	private static final Logger logger = AppLogger.getMainLogInstance(GenerateOutput.class);
	public void generateOutputMessage(TraverseDetail result,String input) {
		String output = "";
		if (null == result) {
			output = "System Error: Unable to findout shortest possible time";
			logger.error("Input {}, Output {}",input,output);
		} else {
			output = result.getVehicle().getName().toUpperCase()+" "+result.getOrbits().get(0).getOrbitName().toUpperCase();
			logger.info("Input {}, Output {}",input,output);
		}
		System.out.println(output);
	}
}
