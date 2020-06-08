package com.geektrust.traffic.junit.main;

import org.apache.logging.log4j.Logger;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.geektrust.traffic.utils.AppLogger;

public class TestRunner {

	private static final Logger logger = AppLogger.getMainLogInstance(TestRunner.class);
	public static void main(String[] args) {
		
		Result result = JUnitCore.runClasses(TestSuite.class);
		
		for(Failure failure :result.getFailures()) {
			logger.warn("test case failed with reason :: {} ",failure.toString());
		}
		logger.info("total test cases performed :: {}",result.getRunCount());
		logger.info("total test cases failed :: {}",result.getFailureCount());
		logger.info("total test cases ignored :: {}",result.getIgnoreCount());
		logger.info("total test cases passed :: {}",result.getRunCount()-result.getFailureCount()-result.getIgnoreCount());
		logger.info("total case result :: {}",result.wasSuccessful());
		logger.info("test cases time {}",result.getRunTime());
		
	}

}
