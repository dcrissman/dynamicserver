package dynamicserver.lib.environment.loader.bean;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import javax.xml.namespace.QName;

import org.springframework.beans.factory.annotation.Required;

import dynamicserver.app.backend.environment.ISoapEnvironmentClient;
import dynamicserver.app.backend.environment.client.EnvironmentServiceClient;
import dynamicserver.lib.environment.IEnvironment;

/**
 * Wrapper class to bean-ify EnvironmentServiceClient. Also provides ability to
 * configure what EnvironmentConnectionMode should be used.
 * 
 * @see EnvironmentServiceClient
 * @author denniscrissman
 */
public class EnvironmentRemoteDeterminer implements IEnvironment {
	
	private String urlPath;
	private String namespace;
	private String name;
	private EnvironmentConnectionMode mode = EnvironmentConnectionMode.CLIENT_PROVIDES;
	private ISoapEnvironmentClient client;
	private boolean isInitialized = false;
	
	/**
	 * Sets the url to the wsdl.
	 */
	@Required
	public void setUrl(String url){
		this.urlPath = url;
	}
	
	/**
	 * Sets the namespace for the wsdl being used.
	 */
	@Required
	public void setNamespace(String namespace){
		this.namespace = namespace;
	}
	
	/**
	 * Sets the web service name that is being used.
	 */
	@Required
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * Sets the mode for determining environment on server side.<br>
	 * Default is CLIENT_PROVIDES
	 */
	public void setMode(EnvironmentConnectionMode mode){
		this.mode = mode;
	}
	
	public EnvironmentRemoteDeterminer(){}
	
	public EnvironmentRemoteDeterminer(String url, String namespace, String name){
		setUrl(url);
		setNamespace(namespace);
		setName(name);
	}
	
	/**
	 * Initializing method that actually establishes the client connection. This method
	 * should only be called once after the desired parameters have been set.<br>
	 * Generally called from "init-method" during bean instantiation.
	 * @throws MalformedURLException
	 */
	public void init() throws MalformedURLException{
		QName qname = new QName(this.namespace, this.name);
		URL url = new URL(this.urlPath);
		this.client = new EnvironmentServiceClient(url, qname);
		this.isInitialized = true;
	}
	
	/**
	 * Validates if the init() method has been called on this instance or not.
	 * @return true if init() has been called, otherwise false.
	 */
	public boolean isInitialized(){
		return this.isInitialized;
	}
	
	public String getEnvironment() {
		String environment;
		
		try{
			switch(mode){
				case SERVER_DETERMINES:
					environment = client.getEnvironment();
				default:
					environment = client.getEnvironmentByDnsName();
			}
		}
		catch(UnknownHostException e){
			throw new RuntimeException("Local DNS Name could not be determined.", e);
		}
		
		return environment;
	}

}
