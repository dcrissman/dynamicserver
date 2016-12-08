package dynamicserver.lib.environment.io.support.test;

import java.io.IOException;

import dynamicserver.lib.environment.DefaultEnvironment;
import dynamicserver.lib.environment.io.support.EnvironmentConsciousPatternAwarePropertyPlaceholderConfigurer;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class EnvironmentConsciousPatternAwarePropertyPlaceholderConfigurerTest 
extends	TestCase {
	
	private final static String PROPERTIES_PATH = 
		"classpath:EnvironmentConsciousPatternAwarePropertyPlaceholderConfigurer/META-INF/properties/*.properties";
	
	public EnvironmentConsciousPatternAwarePropertyPlaceholderConfigurerTest(String testName){
		super( testName );
	}
	
	public static Test suite()
    {
        return new TestSuite( 
        		EnvironmentConsciousPatternAwarePropertyPlaceholderConfigurerTest.class );
    }
	
	/**
	 * Test that production properties are loaded, along with properties
	 * that load regardless of environment
	 */
	public void testProd(){
		EnvironmentConsciousPatternAwarePropertyPlaceholderConfigurer config =
			new EnvironmentConsciousPatternAwarePropertyPlaceholderConfigurer();
		
		config.setEnvironment(new DefaultEnvironment("prod"));
		try {
			config.setLocationPattern(PROPERTIES_PATH);
			
			assertEquals("prod", config.getProperty("env"));
			assertEquals("here", config.getProperty("always"));
		} catch (IOException e) {
			throw new RuntimeException("testProd failed", e);
		}
	}
	
	/**
	 * Test that development properties are loaded, along with properties
	 * that load regardless of environment
	 */
	public void testDev(){
		EnvironmentConsciousPatternAwarePropertyPlaceholderConfigurer config =
			new EnvironmentConsciousPatternAwarePropertyPlaceholderConfigurer();
		
		config.setEnvironment(new DefaultEnvironment("dev"));
		try {
			config.setLocationPattern(PROPERTIES_PATH);
			
			assertEquals("dev", config.getProperty("env"));
			assertEquals("here", config.getProperty("always"));
		} catch (IOException e) {
			throw new RuntimeException("testDev failed", e);
		}
	}
	
	/**
	 * Verifies that no properties are loaded if a non-existent envirment is requested.
	 * Properties that load regardless of environment should still load however.
	 */
	public void testNeither(){
		EnvironmentConsciousPatternAwarePropertyPlaceholderConfigurer config =
			new EnvironmentConsciousPatternAwarePropertyPlaceholderConfigurer();
		
		config.setEnvironment(new DefaultEnvironment("neither"));
		try {
			config.setLocationPattern(PROPERTIES_PATH);
			
			assertEquals("none", config.getProperty("env"));
			assertEquals("here", config.getProperty("always"));
		} catch (IOException e) {
			throw new RuntimeException("testNeither failed", e);
		}
	}
	
}
