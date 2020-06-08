package com.geektrust.traffic.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.geektrust.traffic.constants.OrbitType;
import com.geektrust.traffic.models.Orbit;
import com.geektrust.traffic.models.TraverseDetail;
import com.geektrust.traffic.models.Vehicle;
import com.geektrust.traffic.models.Weather;
import com.geektrust.traffic.repository.MyDataReposiroty;
import com.geektrust.traffic.utils.AppLogger;

public class TrafficFinder {

	private static final Logger logger = AppLogger.getMainLogInstance(TrafficFinder.class);
	private static final MyDataReposiroty repo = MyDataReposiroty.getInstance();
	
	private Map<String,Vehicle> vehiclesMap = repo.getVehiclesMap();
	private Map<String,Weather> weathersMap = repo.getWeathersMap();
	private Map<String,Orbit> orbitsMap = repo.getOrbitsMap();
	
	private List<TraverseDetail> traverseDetails = new ArrayList<TraverseDetail>();
	private String [] inputDetails;
		
	public List<TraverseDetail> getTraverseDetails() {
		return traverseDetails;
	}
	public void setInputDetails(String [] inputDetails) {
		this.inputDetails = inputDetails;
	}

	public void updateOrbitsSpeed() {
		int orbit1speed = Integer.parseInt(inputDetails[1].trim());
		int orbit2speed = Integer.parseInt(inputDetails[2].trim());
		Orbit orbit1 = orbitsMap.get(OrbitType.ORBIT1.toString());
		if(orbit1 != null) {
			orbit1.updateVelocitySpeed(orbit1speed);
		}
		logger.info("Updated orbit 1 max velocity to {}",orbit1speed);
		Orbit orbit2 = orbitsMap.get(OrbitType.ORBIT2.toString());
		if(orbit2 != null) {
			orbit2.updateVelocitySpeed(orbit2speed);
		}
		logger.info("Updated orbit 2 max velocity to {}",orbit2speed);
		
	}
	
	public void findPossibleTraversalDetails() {
		String weatherType = inputDetails[0].trim();
		logger.info("Calculating optimized Travel time for weather {}",weatherType);
		Weather weather = weathersMap.get(weatherType);
		logger.trace("Weather information {}",weather.toString());
		List<String> vehicleNames = weather.getSuitableVehicleNames();
		logger.trace("Suitable vehicle information {}",vehicleNames.toString());
		vehicleNames.forEach(vehicleType -> {
			Vehicle vehicle = vehiclesMap.get(vehicleType);
			logger.trace("Vehicle information {}",vehicle.toString());
			orbitsMap.forEach((orbitType,orbit) -> {
				int time = vehicle.getOptimizedTravelTime(orbit,weather);
				TraverseDetail traverseDetail = new TraverseDetail();
				traverseDetail.setTraverseTime(time);
				traverseDetail.setOrbit(Arrays.asList(orbit));
				traverseDetail.setVehicle(vehicle);
				logger.trace("Traversal Detail information {}",traverseDetail.toString());
				traverseDetails.add(traverseDetail);
			});
		});
		logger.info("Traversal Details caluclated successfulyl for weather {}",weatherType);
		
	}
	
	public TraverseDetail findOptimumTraverseDetail() {
		TraverseDetail optimumTraverseDetail = null;
		if(traverseDetails != null) {
			Collections.sort(traverseDetails);
			optimumTraverseDetail = traverseDetails.get(0);
		}
		return optimumTraverseDetail;
	}

	
	


}
