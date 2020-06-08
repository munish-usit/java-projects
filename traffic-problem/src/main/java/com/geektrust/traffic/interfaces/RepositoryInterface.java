package com.geektrust.traffic.interfaces;

import java.util.List;

import com.geektrust.traffic.models.Orbit;
import com.geektrust.traffic.models.Vehicle;
import com.geektrust.traffic.models.Weather;

public interface RepositoryInterface {

	public List<Orbit>	getAllOrbits();
	public List<Vehicle> getAllVehicles();
	public List<Weather> getAllWeatherDetails(); 
	
}
