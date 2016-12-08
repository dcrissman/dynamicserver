package dynamicserver.app.backend.environment.client;

import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import dynamicserver.app.backend.environment.ISoapEnvironmentClient;
import dynamicserver.app.backend.environment.ISoapEnvironmentServer;

/**
 * Makes a call to an instance of the EnvironmentService to determine what environment
 * should be loaded.
 * @author denniscrissman
 */
public class EnvironmentServiceClient 
extends Service
implements ISoapEnvironmentClient {
	
	public EnvironmentServiceClient(URL wsdlDocumentLocation,
			QName serviceName) {
		super(wsdlDocumentLocation, serviceName);
	}
	
	public String getEnvironment() {
		ISoapEnvironmentServer service = getDefaultPort();
		return service.getEnvironment();
	}

	public String getEnvironmentByDnsName() 
	throws UnknownHostException {
		ISoapEnvironmentServer service = getDefaultPort();
		return service.getEnvironmentByDnsName(
				InetAddress.getLocalHost().getHostName());
	}
	
	private ISoapEnvironmentServer getDefaultPort(){
		return getPort(ISoapEnvironmentServer.class);
	}
	
}
