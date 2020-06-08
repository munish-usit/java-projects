package com.geektrust.traffic.junit.testcases;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import com.geektrust.traffic.exception.BusinessException;
import com.geektrust.traffic.utils.Utils;

public class FileReadTest {

	@Rule		
    public ErrorCollector collector = new ErrorCollector();
	
	@Test
	public void readInputFile() throws BusinessException {
		String filename = "D:\\Munish\\Work\\java-projects\\traffic-problem\\src\\test\\resources\\data\\input1.txt";
		List<String> inputs = Utils.readFileData(filename);
		assertEquals(2, inputs.size());
	}
	
	@Test(expected = BusinessException.class)
	public void readInputFileWithException() throws BusinessException {
		String filename = "";
		Utils.readFileData(filename);
		collector.addError(new Throwable("There is an errror in reading file"));							
	}
}
