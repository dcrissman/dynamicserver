package dynamicserver.lib.spring.io.support.test;

import java.io.IOException;

import dynamicserver.lib.spring.io.support.PatternAwarePropertyPlaceholderConfigurer;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class PatternAwarePropertyPlaceholderConfigurerTest
extends	TestCase{
	
	public final static String PROPERTIES_PATH
		= "classpath:PatternAwarePropertyPlaceholderConfigurer/META-INF/properties/*.properties";
	
	public PatternAwarePropertyPlaceholderConfigurerTest(String testName){
		super( testName );
	}
	
	public static Test suite()
    {
        return new TestSuite(PatternAwarePropertyPlaceholderConfigurerTest.class);
    }
	
	/**
	 * Verifies that when a property is requested the correct value is returned.
	 */
	public void testProperties(){
		PatternAwarePropertyPlaceholderConfigurer config = new PatternAwarePropertyPlaceholderConfigurer();
		try {
			config.setLocationPattern(PROPERTIES_PATH);
		} catch (IOException e) {
			fail("IOException thrown for path: " + PROPERTIES_PATH + "\n" + e.getMessage());
		}
		String actualValue = null;
		try {
			actualValue = config.getProperty("mykey");
		} catch (IOException e) {
			fail("Unable to retrieve property: mykey");
		}
		
		assertEquals("myvalue", actualValue);
	}
	
}
