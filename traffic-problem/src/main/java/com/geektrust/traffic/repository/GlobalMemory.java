/**
 * Global Memory saves all information in memory and it replicates a Database server.
 * In production we can fetch all information like orbits, vehicles, weathers from file or any database server
 */
package com.geektrust.traffic.repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import com.geektrust.traffic.constants.ErrorMessages;
import com.geektrust.traffic.constants.OrbitType;
import com.geektrust.traffic.constants.VehicleType;
import com.geektrust.traffic.exception.BusinessException;
import com.geektrust.traffic.models.Orbit;
import com.geektrust.traffic.models.Vehicle;
import com.geektrust.traffic.models.Velocity;
import com.geektrust.traffic.models.Weather;
import com.geektrust.traffic.constants.WeatherType;

public enum GlobalMemory {

	instance;
		
	private Map<String,Vehicle> vehiclesMap = new HashMap<String,Vehicle>();
	private Map<String,Weather> weathersMap = new HashMap<String,Weather>();
	private Map<String,Orbit> orbitsMap = new HashMap<String,Orbit>();

	private GlobalMemory() {
		
	}
	
	public void init() throws BusinessException {
		try {
			initAllVehicles();
			initAllWeatherDetails();
			initAllOrbits();
			
		} catch (Exception e) {
			throw new BusinessException(ErrorMessages.INIT_FAILED);
		}
	}
	
	
	/**
	 * This method is responsible for
	 * 	-	Populate all Weather objects with hard coded values from the standard I/O in the problem pdf.
	 * 
	 * Sunny - craters reduce by 10%. Car, bike and tuktuk can be used in this weather.
	 * Rainy - craters increase by 20%. Car and tuktuk can be used in this weather.
	 * Windy - no change to number of craters. All vehicles can be used in this weather.
	 * 
	 * In the list of vehicles, sequence should be maintained. 
	 * As, if there is a tie in which vehicle to choose, use bike, auto/tuktuk, car in that order.
	 * 
	 * @return - List of all possible weathers
	 */
	private void initAllWeatherDetails() {
		weathersMap.put(WeatherType.SUNNY.toString(),new Weather(WeatherType.SUNNY, -10, Arrays.asList(VehicleType.BIKE.toString(), VehicleType.TUKTUK.toString(), VehicleType.CAR.toString())));
		weathersMap.put(WeatherType.RAINY.toString(),new Weather(WeatherType.RAINY, +20, Arrays.asList(VehicleType.TUKTUK.toString(), VehicleType.CAR.toString())));
		weathersMap.put(WeatherType.WINDY.toString(),new Weather(WeatherType.WINDY, +0,  Arrays.asList(VehicleType.BIKE.toString(), VehicleType.CAR.toString())));
	}
	
	/**
	 * This method is responsible for
	 * 	-	Populate all Vehicle objects with hard coded values from the standard I/O in the problem pdf.
	 *  
	 * Bike - 10 megamiles/hour & takes 2 min to cross 1 crater
	 * Tuktuk - 12 mm/hour & takes 1 min to cross 1 crater
	 * Car - 20 mm/hour & takes 3 min to cross 1 crater
	 * 
	 * In the list of vehicles, sequence should be maintained. 
	 * As, if there is a tie in which vehicle to choose, use bike, auto/tuktuk, car in that order.
	 *
	 * @return - List of available vehicles
	 */
	private void initAllVehicles() {

		vehiclesMap.put(VehicleType.BIKE.toString(), new Vehicle(VehicleType.BIKE.toString(), new Velocity(10, "megamiles/hour"), 2));
		vehiclesMap.put(VehicleType.TUKTUK.toString(), new Vehicle(VehicleType.TUKTUK.toString(), new Velocity(12, "megamiles/hour"), 1));
		vehiclesMap.put(VehicleType.CAR.toString(), new Vehicle(VehicleType.CAR.toString(), new Velocity(20, "megamiles/hour"), 3));

	}
	
	/**
	 * This method is responsible for
	 * 	-	Populate all Orbit objects with hard coded values from the standard I/O in the problem pdf.
	 * 
	 * Orbit options: 
	 * Orbit 1 - 18 mega miles & 20 craters to cross
	 * Orbit 2 - 20 mega miles & 10 craters to cross
	 * Orbit 3 - 30 mega miles & 15 craters to cross
	 * Orbit 4 - 15 mega miles & 18 craters to cross
	 * 
	 * Note: 1.) Orbit's speed limit is initialized with -1. Valid value will be set through user's input.
	 * 		 2.) Here source and destination, can't be interchanged. As road could be two ways. 
	 * 		 	 i.e. A to B is not same with B to A.
	 * 
	 * @return - List of routes/orbits along with its corresponding details.
	 */
	private void initAllOrbits() {
		orbitsMap.put(OrbitType.ORBIT1.toString(),new Orbit(OrbitType.ORBIT1.toString(), "Silk Drob", "Hallitharam", 18, 20, new Velocity(-1, "megamiles/hour")));
		orbitsMap.put(OrbitType.ORBIT2.toString(),new Orbit(OrbitType.ORBIT2.toString(), "Silk Drob", "Hallitharam", 20, 10, new Velocity(-1, "megamiles/hour")));
	}
	
	
	Map<String, Vehicle> getVehiclesMap() {
		return vehiclesMap;
	}

	void setVehiclesMap(Map<String, Vehicle> vehiclesMap) {
		this.vehiclesMap = vehiclesMap;
	}

	Map<String, Weather> getWeathersMap() {
		return weathersMap;
	}

	void setWeathersMap(Map<String, Weather> weathersMap) {
		this.weathersMap = weathersMap;
	}

	Map<String, Orbit> getOrbitsMap() {
		return orbitsMap;
	}

	void setOrbitsMap(Map<String, Orbit> orbitsMap) {
		this.orbitsMap = orbitsMap;
	}

	
}
