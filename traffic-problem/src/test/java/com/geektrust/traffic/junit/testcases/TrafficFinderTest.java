package com.geektrust.traffic.junit.testcases;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.geektrust.traffic.repository.GlobalMemory;
import com.geektrust.traffic.repository.MyDataReposiroty;
import com.geektrust.traffic.constants.ApplicationConstants;
import com.geektrust.traffic.constants.OrbitType;
import com.geektrust.traffic.core.GenerateOutput;
import com.geektrust.traffic.core.TrafficFinder;
import com.geektrust.traffic.exception.BusinessException;
import com.geektrust.traffic.models.TraverseDetail;


public class TrafficFinderTest {

	private TrafficFinder fd = new TrafficFinder();
	private MyDataReposiroty repo = MyDataReposiroty.getInstance();
	@BeforeClass
	public static void setup() {
		try {
			GlobalMemory.instance.init();

		} catch (BusinessException e) {
			
		}
	}
	
	@Test
	public void updateOrbitSpeedTest() {
		String input = "RAINY 40 25";
		fd.setInputDetails(input.split(ApplicationConstants.SPLIT));
		fd.updateOrbitsSpeed();
		int speed1 = repo.getOrbitsMap().get(OrbitType.ORBIT1.toString()).getVelocityLimit().getSpeed();
		int speed2 = repo.getOrbitsMap().get(OrbitType.ORBIT2.toString()).getVelocityLimit().getSpeed();
		assertTrue(String.valueOf(speed1).equals("40") && String.valueOf(speed2).equals("25"));
	}
	
	@Test
	public void optimalTraversalDetailTest(){
		String input = "SUNNY 12 10";
		fd.setInputDetails(input.split(ApplicationConstants.SPLIT));
		fd.updateOrbitsSpeed();
		fd.findPossibleTraversalDetails();
		TraverseDetail result = fd.findOptimumTraverseDetail();
		GenerateOutput out = new GenerateOutput();
		out.generateOutputMessage(result, input);
		assertTrue(result.getVehicle().getName().equalsIgnoreCase("TUKTUK")
				&& result.getOrbits().get(0).getOrbitName().equalsIgnoreCase("ORBIT1"));
		
	}
	
		
}
