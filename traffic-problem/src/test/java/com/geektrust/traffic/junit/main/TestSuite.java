package com.geektrust.traffic.junit.main;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.geektrust.traffic.junit.testcases.TrafficFinderTest;
import com.geektrust.traffic.junit.testcases.FileReadTest;
import com.geektrust.traffic.junit.testcases.GlobalMemoryTest;
import com.geektrust.traffic.junit.testcases.UtilsTest;
import com.geektrust.traffic.junit.testcases.VehicleTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	GlobalMemoryTest.class,
	FileReadTest.class,
	UtilsTest.class,
	VehicleTest.class,
	TrafficFinderTest.class
	
})
public class TestSuite {

}
