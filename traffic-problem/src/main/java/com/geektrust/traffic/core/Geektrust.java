/**
 * DESCRIPTION - This class is responsible to test Lengaburu Traffic problem.
 * 
 * Problem 1: Lengaburu Traffic
 * King Shan wants to visit the suburb of Hallitharam, and has 2 possible orbits and 3 possible vehicles to chose from.
 * Your coding challenge is to determine which orbit and vehicle King Shan should take to reach Hallitharam the fastest.
 * 
 * This class will take input from the user, and will give the output accordingly.
 * Inputs: 	Weather, Orbit1 Traffic Speed, Orbit2 Traffic Speed;
 * Output: 	Shortest possible time to reach to destination from the source;
 * 		    Output contains vehicle and orbit/route details.   
  */
package com.geektrust.traffic.core;

import java.util.List;

import org.apache.logging.log4j.Logger;

import com.geektrust.traffic.constants.ErrorMessages;
import com.geektrust.traffic.exception.BusinessException;
import com.geektrust.traffic.repository.GlobalMemory;
import com.geektrust.traffic.utils.AppLogger;
import com.geektrust.traffic.utils.Utils;

public class Geektrust {

	private static final Logger logger = AppLogger.getMainLogInstance(Geektrust.class);
	public static void main(String[] args) {

		try {
			logger.info("Traffic Problem started");
			// Expecting only input file name. Inputs will be read from the file.
			if(args.length <=0) {
				logger.error("Error code {} , message {}",ErrorMessages.INVALID_ARGS.getErrorCode(),ErrorMessages.INVALID_ARGS.getErrorMessage());
				return;
			}
			String inputFile = args[0];
			List<String> inputs = Utils.readFileData(inputFile);
			if(inputs.size() ==0) {
				logger.error("Error code {} , message {}",ErrorMessages.EMPTY_INPUT_FILE.getErrorCode(),ErrorMessages.EMPTY_INPUT_FILE.getErrorMessage());
				return;
			}
			GlobalMemory.instance.init();
			logger.info("Global memory initialized successfully");
			InputProcessor inputProcessor = new InputProcessor();
			inputProcessor.processInput(inputs);
						
		} catch (BusinessException e) {
			logger.error("exiting due to exception code {} , message {}",e.getErrorCode(),e.toString());
			e.printStackTrace();
		}

	}

}
