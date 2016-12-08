package dynamicserver.app.osgi.server.extension.util;

import org.springframework.beans.factory.annotation.Required;

import dynamicserver.app.osgi.server.extension.IServiceManager;
import dynamicserver.app.osgi.server.extension.exception.ServiceActivatorException;
import dynamicserver.lib.osgi.activator.IActivator;

/**
 * This is intended as a convenience class to allow for inclusion into the
 * Server instance through the bean configuration. 
 * @author denniscrissman
 */
public class DefaultServiceActivator implements IActivator {
	
	private IServiceManager serviceManager;
	private Object service;
	private String address;
	
	@Required
	public void setServiceManager(IServiceManager serviceManager){
		this.serviceManager = serviceManager;
	}
	
	@Required
	public void setService(Object service){
		this.service = service;
	}
	
	public Object getService(){
		return this.service;
	}
	
	@Required
	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return this.address;
	}
	
	/**
	 * Activates the Service on the current Server instance.
	 * @throws Exception
	 */
	public void activate() throws ServiceActivatorException {
		checkForExceptions();
		try{
			this.serviceManager.addService(getService(), getAddress());
		}
		catch(Throwable throwable){
			throw new ServiceActivatorException(
					"Unable to activate service " + getService().getClass().getName()
					+ " at address " + getAddress(),
					throwable);
		}
	}
	
	/**
	 * Deactivates the Service on the current Server instance.
	 * @throws Exception
	 */
	public void deactivate() throws ServiceActivatorException {
		checkForExceptions();
		try{
			this.serviceManager.removeService(getService());
		}
		catch(Throwable throwable){
			throw new ServiceActivatorException(
					"Unable to deactivate service " + getService().getClass().getName()
					+ " at address " + getAddress(),
					throwable);
		}
	}
	
	/**
	 * Verifies that the required values are actually set. If there is a
	 * problem, a ServiceActivatorException will be thrown, otherwise the method
	 * will simply return.
	 * @throws ServiceActivatorException
	 */
	protected void checkForExceptions() throws ServiceActivatorException{
	    if(this.serviceManager == null){
	    	throw new ServiceActivatorException("Service Manager cannot be null");
	    }
		else if(getService() == null){
			throw new ServiceActivatorException("Service cannot be null");
		}
		else if((getAddress() == null) || (getAddress().equals(""))){
			throw new ServiceActivatorException("Address cannot be null or empty"); 
		}
	}

}
