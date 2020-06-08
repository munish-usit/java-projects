package com.geektrust.traffic.junit.testcases;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.geektrust.traffic.constants.ApplicationConstants;
import com.geektrust.traffic.constants.OrbitType;
import com.geektrust.traffic.constants.VehicleType;
import com.geektrust.traffic.constants.WeatherType;
import com.geektrust.traffic.core.TrafficFinder;
import com.geektrust.traffic.exception.BusinessException;
import com.geektrust.traffic.models.Orbit;
import com.geektrust.traffic.models.Vehicle;
import com.geektrust.traffic.models.Weather;
import com.geektrust.traffic.repository.GlobalMemory;
import com.geektrust.traffic.repository.MyDataReposiroty;

public class VehicleTest {

	private MyDataReposiroty repo = MyDataReposiroty.getInstance();
	private TrafficFinder fd = new TrafficFinder();
	@BeforeClass
	public static void setup() {
		try {
			GlobalMemory.instance.init();

		} catch (BusinessException e) {
			
		}
	}
	
	@Test
	public void testVehicleOptimizedTime() {
		String input = "RAINY 8 15";
		fd.setInputDetails(input.split(ApplicationConstants.SPLIT));
		fd.updateOrbitsSpeed();
		Vehicle vehicle = repo.getVehiclesMap().get(VehicleType.TUKTUK.toString());
		Weather weather = repo.getWeathersMap().get(WeatherType.RAINY.toString());
		Orbit orbit = repo.getOrbitsMap().get(OrbitType.ORBIT1.toString());
		int time = vehicle.getOptimizedTravelTime(orbit, weather);
		assertEquals(159, time);
	}
}
