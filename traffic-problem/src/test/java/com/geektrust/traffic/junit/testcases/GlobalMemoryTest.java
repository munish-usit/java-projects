package com.geektrust.traffic.junit.testcases;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.geektrust.traffic.repository.GlobalMemory;
import com.geektrust.traffic.exception.BusinessException;
import com.geektrust.traffic.repository.MyDataReposiroty;

public class GlobalMemoryTest {

	private MyDataReposiroty instance = MyDataReposiroty.getInstance();
	
	@BeforeClass
	public static void setup() {
		try {
			GlobalMemory.instance.init();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void getAllOrbits() {
		int size = instance.getOrbitsMap().size();
		assertEquals(2, size);
	}
	@Test
	public void getAllVehicles() {
		int size = instance.getVehiclesMap().size();
		assertEquals(3, size);
	}
	@Test
	public void getAllWeatherTypes() {
		int size = instance.getWeathersMap().size();
		assertEquals(3, size);
	}
	
}
