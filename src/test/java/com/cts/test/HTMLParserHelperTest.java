package com.cts.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.cts.app.data.ExtractDataHtmlHelper;

public class HTMLParserHelperTest {
	
	@Test
    public void extractZip(){
			String zipText = "asdasd 98342 afds afsdf test";
            assertTrue("98342".equals(ExtractDataHtmlHelper.extractZip(zipText)));
    }
	
	@Test
    public void extractColor(){
			String content = "asdasd black afds afsdf test";
            assertTrue("Black".equals(ExtractDataHtmlHelper.extractColor(content)));
    }
	
	@Test
    public void extractNumericString(){
			String s = "$1,123.78";
            assertTrue("1123.78".equals(ExtractDataHtmlHelper.getNumericString(s)));
    }
	
	@Test
    public void extractVin(){
			String vinText = "asdasd 3N1BC1CP0BL502210 afds afsdf test";
            assertTrue("3N1BC1CP0BL502210".equals(ExtractDataHtmlHelper.extractVin(vinText)));
    }
	
	@Test
    public void extractTrans(){
			String text = "this is a manual transmission";
            assertTrue("Manual".equals(ExtractDataHtmlHelper.extractTransmission("", text)));
    }
}
