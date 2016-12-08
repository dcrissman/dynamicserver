package dynamicserver.app.osgi.server;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.aegis.databinding.AegisDatabinding;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.frontend.ServerFactoryBean;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.springframework.stereotype.Service;

import dynamicserver.app.osgi.server.extension.IServiceManager;
import dynamicserver.app.osgi.server.extension.exception.ServiceManagerException;

@Service(value="serviceManager")
public class ServiceManager implements IServiceManager{
	
	private final static Log LOGGER = LogFactory.getLog(ServiceManager.class);
	
	private final Map<Object, Server> map = new HashMap<Object, Server>();
	
	public void addService(Object service, String address) throws ServiceManagerException{
		LOGGER.info("Adding Service " + service.getClass().getName() 
				+ " at " + address);
		if(map.containsKey(service)){
			throw new ServiceManagerException(
					"Service is already activated. Please deactivate first.");
		}
		
		ServerFactoryBean factory = new JaxWsServerFactoryBean();
		factory.setDataBinding(new AegisDatabinding());
		factory.setServiceBean(service);
		factory.setAddress(address);
		Server server = factory.create();
		map.put(service, server);
	}
	
	public void removeService(Object service) throws ServiceManagerException{
		LOGGER.info("Removing Service " + service.getClass().getName());
		map.get(service).stop();
		map.remove(service);
	}
	
}
