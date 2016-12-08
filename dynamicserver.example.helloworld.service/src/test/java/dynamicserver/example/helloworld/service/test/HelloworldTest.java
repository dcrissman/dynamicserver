package dynamicserver.example.helloworld.service.test;

import dynamicserver.example.helloworld.service.HelloWorldImpl;
import dynamicserver.example.helloworld.service.IHelloWorld;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class HelloworldTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public HelloworldTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( HelloworldTest.class );
    }

    public void testHelloWorld()
    {
    	IHelloWorld helloWorld = new HelloWorldImpl();
    	String name = "John";
    	
    	assertEquals(helloWorld.helloWorld(name), "Hello " + name + "!");
    }
}
