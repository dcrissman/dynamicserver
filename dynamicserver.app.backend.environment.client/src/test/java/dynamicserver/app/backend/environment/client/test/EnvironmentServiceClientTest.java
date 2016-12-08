package dynamicserver.app.backend.environment.client.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import javax.xml.namespace.QName;

import dynamicserver.app.backend.environment.ISoapEnvironmentClient;
import dynamicserver.app.backend.environment.client.EnvironmentServiceClient;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class EnvironmentServiceClientTest
extends	TestCase{
	
	private final static boolean BUILD_MODE = true;
	
	private final String urlPath = 
		"http://localhost:8080/dynamicserver.app.osgi.server-1.1/backend/environment?wsdl";
	private final QName name = new QName(
		"http://service.environment.backend.app.dynamicserver/","EnvironmentService");
	
	public EnvironmentServiceClientTest(String testName){
		super( testName );
	}
	
	public static Test suite()
    {
        return new TestSuite(EnvironmentServiceClientTest.class);
    }
	
	public void testGetEnvironment(){
		if(BUILD_MODE){
			return;
		}
		
		String envResponse = getClient().getEnvironment();
		assertEquals("stage", envResponse);
	}
	
	public void testGetEnvironmentByDnsName(){
		if(BUILD_MODE){
			return;
		}
		
		String envResponse = null;
		try {
			envResponse = getClient().getEnvironmentByDnsName();
		} catch (UnknownHostException e) {
			fail(e.getMessage());
		}
		
		assertEquals("stage", envResponse);
	}
	
	private ISoapEnvironmentClient getClient(){
		URL url = null;
		try {
			url = new URL(urlPath);
		} catch (MalformedURLException e) {
			fail(e.getMessage());
		}
		
		return new EnvironmentServiceClient(url, name);
	}

}
