/**
 * Utility class with utility functions like readData or validateInput
 */
package org.analytics.covidtracker.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;



public class Utils {

	private static final Logger logger = AppLogger.getMainLogInstance(Utils.class);
	public static List<String> readFileData(String fileName) {
		logger.info("Reading input file {} ",fileName);
		List<String> inputs = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = br.readLine()) != null) {
                inputs.add(line);
            }
		} catch (Exception e) {
			logger.error("Input file read error due to reason {} ",e.getMessage());
		}
		logger.info("Input file read successfully with input size :: {}",inputs.size());
		return inputs;
	}
	
	
}
