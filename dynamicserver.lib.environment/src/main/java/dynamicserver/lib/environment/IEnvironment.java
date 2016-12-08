package dynamicserver.lib.environment;

/**
 * Simple interface for containing the environment that the application
 * is being run in.
 * 
 * @author denniscrissman
 */
public interface IEnvironment {
	
	/**
	 * Returns the environment that the application is being run in.
	 * @return <code>String</code> environment that the application is being run in.
	 */
	public String getEnvironment();
	
}
