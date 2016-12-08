package dynamicserver.app.osgi.server.extension;

import dynamicserver.app.osgi.server.extension.exception.ServiceManagerException;

/**
 * Manages the Services on the Server instance.
 * <br/><b>Note:</b> Interface should only be implemented by the Server. 
 * Services can then use this interface to interact via OSGI with the instance 
 * on the Server.
 * 
 * @author denniscrissman
 */
public interface IServiceManager {
	
	/**
	 * Adds the passed in <code>IService</code> to the Server instance.
	 * @param service - <code>IService</code>
	 * @param address - Address to assign the <code>IService<code> too.
	 * @throws ServiceManagerException
	 */
	public void addService(Object service, String address) throws ServiceManagerException;
	
	/**
	 * Removes the <code>IService</code> from the Server instance.
	 * @param service - <code>IService</code>
	 * @throws ServiceManagerException
	 */
	public void removeService(Object service) throws ServiceManagerException;
	
}
