package dynamicserver.lib.spring.context.support.test;

import org.springframework.beans.factory.BeanFactory;

import dynamicserver.lib.spring.context.support.DefaultContextLoader;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class DefaultContextLoaderTest
extends	TestCase{
	
	private final static String CONTEXT_PATH
	= "classpath:DefaultContextLoader/META-INF/";
	
	private static final String[] possibleContextLocations = new String[]{
		CONTEXT_PATH + "spring/*.xml",
		CONTEXT_PATH + "context/*.xml",
		CONTEXT_PATH + "applicationContext.xml"
	};

	public DefaultContextLoaderTest(String testName){
		super( testName );
	}
	
	public static Test suite()
	{
	    return new TestSuite(DefaultContextLoaderTest.class);
	}
	
	/**
	 * Verify context files can be loaded and correct objects are returned.
	 */
	public void testLoadingContextFiles(){
		DefaultContextLoader loader = new DefaultContextLoader();
		BeanFactory factory = loader.loadContext(possibleContextLocations, false);
		
		ContainerForTesting contextContainer = (ContainerForTesting) factory.getBean("context-bean");
		ContainerForTesting springContainer = (ContainerForTesting) factory.getBean("spring-bean");
		ContainerForTesting applicationContextContainer = (ContainerForTesting) factory.getBean("applicationContext-bean");
		
		assertNotNull(contextContainer);
		assertNotNull(springContainer);
		assertNotNull(applicationContextContainer);
		
		assertEquals("context-bean-value", contextContainer.getValue());
		assertEquals("spring-bean-value", springContainer.getValue());
		assertEquals("applicationContext-bean-value", applicationContextContainer.getValue());
	}

}
