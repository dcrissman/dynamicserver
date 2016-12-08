package dynamicserver.lib.osgi.activator;

/**
 * Activates a remote Service bundle on the Server.
 * @author denniscrissman
 */
public interface IActivator {
	
	/**
	 * Activates the module.
	 * @throws ActivatorException
	 */
	public void activate() throws ActivatorException;
	
	/**
	 * Deactivates the module.
	 * @throws ActivatorException
	 */
	public void deactivate() throws ActivatorException;
	
}
