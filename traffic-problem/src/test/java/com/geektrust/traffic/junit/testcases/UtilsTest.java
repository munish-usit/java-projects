package com.geektrust.traffic.junit.testcases;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.geektrust.traffic.exception.BusinessException;
import com.geektrust.traffic.repository.GlobalMemory;
import com.geektrust.traffic.utils.Utils;

@RunWith(Parameterized.class)
public class UtilsTest {

	private String input;
	private boolean result;
	
	public UtilsTest(String input,boolean result) {
		this.input = input;
		this.result = result;
	}
	
	@BeforeClass
	public static void setup() {
		try {
			GlobalMemory.instance.init();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		
	@SuppressWarnings("rawtypes")
	@Parameterized.Parameters
	   public static Collection inputList() {
	      return Arrays.asList(new Object[][] {
	         { null, false },
	         { "", false },
	         { "RAINY 10", false },
	         { " 10 20", false },
	         { "MUNISH 10 20", false },
	         { "RAINY RAIN 10", false },
	         { "RAINY 20 40", true }
	         
	      });
	   }
	
	@Test
	public void validateInput() {
		assertEquals(result, Utils.validateInput(input));
	}
	
	
	
	@AfterClass
	public static void tear() {
		
	}
	
	
}
