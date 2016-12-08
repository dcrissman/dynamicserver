package dynamicserver.app.backend.environment;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(name=ISoapEnvironment.SERVICE_NAME)
public interface ISoapEnvironmentServer extends ISoapEnvironment{
	
	/**
	 * Returns the environment variable for the passed in dns name.
	 */
	@WebMethod(operationName="getEnvironmentByDnsName")
	@WebResult(name="environment")
	public String getEnvironmentByDnsName(@WebParam(name="dnsName") String dnsName);
	
}
