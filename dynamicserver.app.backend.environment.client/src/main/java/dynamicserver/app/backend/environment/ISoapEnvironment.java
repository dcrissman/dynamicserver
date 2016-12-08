package dynamicserver.app.backend.environment;

import javax.jws.WebMethod;
import javax.jws.WebResult;

import dynamicserver.lib.environment.IEnvironment;

public interface ISoapEnvironment extends IEnvironment {
	
	/** Name of the Environment Soap Service */
	public static final String SERVICE_NAME = 
		"EnvironmentService";
	/** Target namespace of the Environment Soap Service */
	public static final String TARGET_NAMESPACE = 
		"http://service.environment.backend.app.dynamicserver/";
	
	@WebMethod(operationName="getEnvironment")
	@WebResult(name="environment")
	public String getEnvironment();
	
}
