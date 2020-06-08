package com.geektrust.traffic.repository;

import java.util.List;
import java.util.Map;

import com.geektrust.traffic.interfaces.RepositoryInterface;
import com.geektrust.traffic.models.Orbit;
import com.geektrust.traffic.models.Vehicle;
import com.geektrust.traffic.models.Weather;

public class MyDataReposiroty implements RepositoryInterface {

	private MyDataReposiroty() {
		
	}
	private static class MyDataRepositoryHelper {
		private static final MyDataReposiroty instance = new MyDataReposiroty();
	}
	public static MyDataReposiroty getInstance() {
		return MyDataRepositoryHelper.instance;
	}
	
	private Map<String,Vehicle> vehiclesMap = GlobalMemory.instance.getVehiclesMap();
	private Map<String,Weather> weathersMap = GlobalMemory.instance.getWeathersMap();
	private Map<String,Orbit> orbitsMap = GlobalMemory.instance.getOrbitsMap();
		
	public Map<String, Vehicle> getVehiclesMap() {
		return vehiclesMap;
	}
	public Map<String, Weather> getWeathersMap() {
		return weathersMap;
	}
	public Map<String, Orbit> getOrbitsMap() {
		return orbitsMap;
	}
	
	@Override
	public List<Orbit> getAllOrbits() {
		return GlobalMemory.instance.getAllOrbits();
	}
	@Override
	public List<Vehicle> getAllVehicles() {
		return GlobalMemory.instance.getAllVehicles();
	}
	@Override
	public List<Weather> getAllWeatherDetails() {
		return GlobalMemory.instance.getAllWeatherDetails();
	}
}
