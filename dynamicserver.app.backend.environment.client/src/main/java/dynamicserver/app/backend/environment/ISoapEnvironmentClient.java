package dynamicserver.app.backend.environment;

import java.net.UnknownHostException;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.xml.ws.WebServiceClient;

@WebServiceClient(name=ISoapEnvironment.SERVICE_NAME,
		targetNamespace=ISoapEnvironment.TARGET_NAMESPACE)
public interface ISoapEnvironmentClient extends ISoapEnvironment{
	
	/**
	 * Returns the environment variable for this machines dns name.
	 */
	@WebMethod(operationName="getEnvironmentByDnsName")
	@WebResult(name="environment")
	public String getEnvironmentByDnsName()
	throws UnknownHostException;
	
}
