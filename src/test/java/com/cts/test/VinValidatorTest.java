package com.cts.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.cts.app.data.VIN;

public class VinValidatorTest {
	String validVIN1 = "3N1BC1CP0BL502210";
	String validVIN2 ="3N1CN7AP9CL906872";
	String invalidVIN1 = "3N1CN7AQ9CL906872";
	String invalidVIN2 = "3N1CN7A8888906872";
	
	@Test
    public void validVIN(){
            assertTrue(VIN.isVinValid(validVIN1));
            assertTrue(VIN.isVinValid(validVIN2));
    }
	
	@Test
    public void invalidVIN(){
		assertTrue(!VIN.isVinValid(invalidVIN1));
		assertTrue(!VIN.isVinValid(invalidVIN2));
    }

}
