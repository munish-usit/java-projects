package com.geektrust.traffic.junit.main;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.geektrust.traffic.junit.testcases.TrafficFinderTest;
import com.geektrust.traffic.junit.testcases.GlobalMemoryTest;
import com.geektrust.traffic.junit.testcases.UtilsTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	GlobalMemoryTest.class,
	TrafficFinderTest.class,
	UtilsTest.class
})
public class TestSuite {

}
