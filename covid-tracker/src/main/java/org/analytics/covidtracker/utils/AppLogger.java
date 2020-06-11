package org.analytics.covidtracker.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppLogger {
	
	public static Logger getMainLogInstance(Class<?> className) {
		return LogManager.getLogger(className);
	}

}
