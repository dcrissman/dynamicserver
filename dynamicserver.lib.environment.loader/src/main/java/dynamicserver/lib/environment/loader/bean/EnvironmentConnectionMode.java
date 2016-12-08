package dynamicserver.lib.environment.loader.bean;

/**
 * Enum to indicate how the server determines the environment.
 * @author denniscrissman
 */
public enum EnvironmentConnectionMode {
	
	/** 
	 * Forces the Server to determine the client ip address
	 * and make a decision based on that information.
	 */
	SERVER_DETERMINES,
	/**
	 * Client provides it's own DNS Name and then the server
	 * makes its decision.
	 */
	CLIENT_PROVIDES
	
}
