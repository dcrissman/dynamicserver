package dynamicserver.lib.spring.context.support;

import org.springframework.beans.factory.BeanFactory;

/**
 * Responsible for loading the context files.
 * @author denniscrissman
 */
public interface IContextLoader {
	
	/**
	 * Load context files using default configuration values.
	 * @return Fully loaded <code>BeanFactory</code> parsed from the 
	 * context files.
	 */
	public BeanFactory loadContext();
	
}
