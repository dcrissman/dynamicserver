package dynamicserver.lib.environment.test;

import dynamicserver.lib.environment.DefaultEnvironment;
import dynamicserver.lib.environment.IEnvironment;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class DefaultEnvironmentTest 
extends	TestCase{
	
	public DefaultEnvironmentTest(String testName){
		super( testName );
	}
	
	public static Test suite()
    {
        return new TestSuite(DefaultEnvironmentTest.class);
    }
	
	/**
	 * String added in constructor should match the returned environment value.
	 */
	public void testEnvironmentStringMatch(){
		String strEnv = "TEST";
		IEnvironment env = new DefaultEnvironment(strEnv);
		assertEquals(strEnv, env.getEnvironment());
	}
	
}
