package com.geektrust.traffic.models;

import java.io.Serializable;

/**
 * Vehicle Bean.
 * 
 */
public class Vehicle implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String name;
	private Velocity velocity;
	private int timeToCrossCrater;

	public Vehicle() {
		// Default constructor
	}
	
	public Vehicle(String name, Velocity velocity, int timeToCrossCrater) {
		super();
		this.name = name;
		this.velocity = velocity;
		this.timeToCrossCrater = timeToCrossCrater;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Velocity getVelocity() {
		return this.velocity;
	}

	public void setVelocity(Velocity velocity) {
		this.velocity = velocity;
	}

	public int getTimeToCrossCrater() {
		return this.timeToCrossCrater;
	}

	public void setTimeToCrossCrater(int timeToCrossCrater) {
		this.timeToCrossCrater = timeToCrossCrater;
	}
	
	public int getOptimizedTravelTime(Orbit orbit,Weather weather) {
		int orbitDistance = orbit.getDistance();
		int orbitCraters = orbit.getNumberOfCraters();
		int orbitSpeedLimit = orbit.getVelocityLimit().getSpeed();
		int applicableSpeed = orbitSpeedLimit > this.velocity.getSpeed() ? this.getVelocity().getSpeed() : orbitSpeedLimit;
		int actualNumberOfCraters = (int) Math.round(orbitCraters * (100 + weather.getCraterChangeRate()) / 100.00);
		int time = ((orbitDistance * 60) / applicableSpeed) + (actualNumberOfCraters * this.timeToCrossCrater);
		return time;
	}
	
	@Override
	public String toString() {
		StringBuilder vehicle = new StringBuilder("Vehicle");
		vehicle.append(": {")
			.append("name=").append(name)
			.append(", velocity=").append(velocity)
			.append(", timeToCrossCrater=").append(timeToCrossCrater)
			.append("}");
		return vehicle.toString();
	}
}
