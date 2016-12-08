package dynamicserver.lib.spring.context.support;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * Responsible for loading the Spring context files required for an application.<br/>
 * By simply calling {@link DefaultContextLoader#loadContext()}, 
 * the following paths will be used if they exist:
 * <ul>
 * 	<li>classpath:META-INF/spring/*.xml</li>
 *	<li>classpath:META-INF/context/*.xml</li>
 * 	<li>classpath:META-INF/applicationContext.xml</li>
 * </ul>
 * 
 * These can be overridden or additional paths can be added by calling 
 * {@link DefaultContextLoader#loadContext(String[], boolean)}.
 * 
 * @author denniscrissman
 */
public class DefaultContextLoader implements IContextLoader 
{
	private static final String[] possibleContextLocations = new String[]{
		"classpath:META-INF/spring/*.xml",
		"classpath:META-INF/context/*.xml",
		"classpath:META-INF/applicationContext.xml"
	};
	
	/**
	 * Loads the context using the default paths if they exist.
	 * @return created <code>BeanFactory</code>.
	 */
	public BeanFactory loadContext()
    {
		return new ClassPathXmlApplicationContext(prepareContextFiles());
    }
	
	/**
	 * Loads the context paths passed in and optionally the default paths as
	 * well.
	 * @param paths - <code>String[]</code> of context paths to load.
	 * @param includeDefaults - true if the defaults should be used, otherwise
	 * false.
	 * @return created <code>BeanFactory</code>.
	 */
	public BeanFactory loadContext(final String[] paths, final boolean includeDefaults){
		if((paths == null) || (paths.length == 0)){
			throw new RuntimeException(
					"Variable 'paths' should never be null or empty, use method"
					+ " loadContext() if only defaults are desired.");
		}
		List<String> contextPaths = Arrays.asList(paths);
		if(includeDefaults){
			contextPaths.addAll(Arrays.asList(prepareContextFiles()));
		}
		return new ClassPathXmlApplicationContext(
				contextPaths.toArray(new String[0]));
	}
	
	/**
	 * Iterates over the possibleContextLocations to determine which ones
	 * actually exist.
	 * @return String[] of context locations that exist.
	 */
	protected static String[] prepareContextFiles(){
		List<String> contextPaths = new ArrayList<String>();
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		/*
		 * Bit of confusing logic here, but no way around it that I could find.
		 * getResources (plural) will throw an exception if the resource 
		 * does not exist, while getResource (singular) will not. 
		 */
		for(String path : possibleContextLocations){
			try {
				if(path.contains("*")){
					resolver.getResources(path);
				}
				else{
					if(!resolver.getResource(path).exists()){
						continue;
					}
				}
				contextPaths.add(path);
			} catch (Exception e) {
				//Do Nothing
				//This will be caused when the no files or directory does not
				//exist for method getResources (plural).
			}
		}
		return contextPaths.toArray(new String[0]);
	}
	
}