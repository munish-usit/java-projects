/**
 * Utility class with utility functions like readData or validateInput
 */
package com.geektrust.traffic.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;

import com.geektrust.traffic.constants.ApplicationConstants;
import com.geektrust.traffic.constants.ErrorMessages;
import com.geektrust.traffic.exception.BusinessException;
import com.geektrust.traffic.repository.MyDataReposiroty;


public class Utils {

	private static final Logger logger = AppLogger.getMainLogInstance(Utils.class);
	public static List<String> readFileData(String fileName) throws BusinessException {
		logger.info("Reading input file {} ",fileName);
		List<String> inputs = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = br.readLine()) != null) {
                inputs.add(line);
            }
		} catch (Exception e) {
			throw new BusinessException(ErrorMessages.INVALID_FILE);
		
		}
		logger.info("Input file read successfully with input size :: {}",inputs.size());
		return inputs;
	}
	
	public static boolean validateInput(String input) {
		if(null == input) {
			logger.warn("Invalid input {}, Error code {}, message {}",input,ErrorMessages.EMPTY_INPUT.getErrorCode(),ErrorMessages.EMPTY_INPUT.getErrorMessage());;
			return false;
		}
		if(input.isEmpty()) {
			logger.warn("Invalid input {}, Error code {}, message {}",input,ErrorMessages.EMPTY_INPUT.getErrorCode(),ErrorMessages.EMPTY_INPUT.getErrorMessage());;
			return false;
		}
		String [] temp = input.split(ApplicationConstants.SPLIT);
		if(temp.length < 3) {
			logger.warn("Invalid input {}, Error code {}, message {}",input,ErrorMessages.INVALID_INPUT.getErrorCode(),ErrorMessages.INVALID_INPUT.getErrorMessage());;
			return false;
		}
		String weather = temp[0].trim();
		if(weather.isEmpty()) {
			logger.warn("Invalid input {}, Error code {}, message {}",input,ErrorMessages.INVALID_WEATHER.getErrorCode(),ErrorMessages.INVALID_WEATHER.getErrorMessage());;
			return false;
		}
		if(!MyDataReposiroty.getInstance().getWeathersMap().containsKey(weather)) {
			logger.warn("Invalid input {}, Error code {}, message {}",input,ErrorMessages.INVALID_WEATHER.getErrorCode(),ErrorMessages.INVALID_WEATHER.getErrorMessage());;
			return false;
		}
		
		try {
			Integer.parseInt(temp[1].trim());
		}catch(NumberFormatException e) {
			logger.warn("Invalid input {}, Error code {}, message {}",input,ErrorMessages.INVALID_ORBITSPEED.getErrorCode(),ErrorMessages.INVALID_ORBITSPEED.getErrorMessage());;
			return false;
		}
		
		try {
			Integer.parseInt(temp[2].trim());
		}catch(NumberFormatException e) {
			logger.warn("Invalid input {}, Error code {}, message {}",input,ErrorMessages.INVALID_ORBITSPEED.getErrorCode(),ErrorMessages.INVALID_ORBITSPEED.getErrorMessage());;
			return false;
		}
						
		return true;
	}
}
