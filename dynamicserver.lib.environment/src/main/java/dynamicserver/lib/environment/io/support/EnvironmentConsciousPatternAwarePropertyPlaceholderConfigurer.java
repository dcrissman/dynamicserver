package dynamicserver.lib.environment.io.support;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import dynamicserver.lib.environment.IEnvironment;
import dynamicserver.lib.spring.io.support.PatternAwarePropertyPlaceholderConfigurer;

/**
 * <p>Acts exactly like PatternAwarePropertyPlaceholderConfigurer, only will
 * automatically add the current environment to the path filter
 * (eg. prod.my.properties).</p>
 * <p>If there is a need to have a property file loaded regardless of environment,
 * then simply create the file with a single period (eg. my.properties). This
 * will be loaded in the absence of a file with the environment extension.</p>
 * Sample:
 * <ul>
 * <li>/properties/prod.my.properties</li>
 * <li>/properties/my.properties</li>
 * <li>/properties/dev.my.properties</li>
 * </ul>
 * <p>In the above example, <i>prod.my.properties</i> would be loaded. If it did not exist
 * then <i>my.properties</i> would be loaded. <i>dev.my.properties</i> will not
 * be loaded because the environment is set to <i>prod</i>.</p>
 * <p>The environment value should be set in the environment.properties file.</p>
 * 
 * @see PatternAwarePropertyPlaceholderConfigurer
 * @author denniscrissman
 */
public class EnvironmentConsciousPatternAwarePropertyPlaceholderConfigurer
		extends PatternAwarePropertyPlaceholderConfigurer {
	
	private static final Log logger = LogFactory.getLog(EnvironmentConsciousPatternAwarePropertyPlaceholderConfigurer.class);
	
	/** Environment to load */
	private static IEnvironment environment = null;
	
	private static final String DIVIDER = ".";
	
	/**
	 * Sets and overrides any environment settings already in effect. 
	 * This will only effect properties loaded from this point forward. It is
	 * best to set this once during any given execution.
	 * @param env
	 */
	public void setEnvironment(IEnvironment env){
		environment = env;
	}
	
	public EnvironmentConsciousPatternAwarePropertyPlaceholderConfigurer(){}
	
	public void setLocationPattern(String locationPattern) throws IOException {
		if(environment == null){
			throw new RuntimeException("The environment property has not been set.");
		}
		
		String env = environment.getEnvironment();
		logger.info("Loading in environment: " + env);
		
		List<String> patternPaths = new ArrayList<String>();
		patternPaths.add(locationPattern);
		
		//Get just the filename of pattern.
		String[] arr = locationPattern.split("/");
		int patternParts = arr[arr.length - 1].split("\\" + DIVIDER).length;
		
		Map<String, Resource> resources = new HashMap<String, Resource>();
		for(Resource resource : preparePatternFiles(patternPaths)){
			String resourceFileName = resource.getFilename();
			String[] arrFileName = resourceFileName.split("\\" + DIVIDER);
			
			String baseFileName = resourceFileName;
			if(arrFileName.length > patternParts){
				List<String> listFileName = new ArrayList<String>(Arrays.asList(arrFileName));
				listFileName.remove(0);
				baseFileName = join(listFileName.toArray(new String[0]), DIVIDER);
			}
			
			if(resourceFileName.startsWith(env + DIVIDER)){
				//Searches for prod.my.properties
				//This condition will always take precedence and will override
				//existing values.
				resources.put(baseFileName, resource);
			}
			else if(arrFileName.length <= patternParts){
				//Searches for my.properties
				if(resources.get(baseFileName) == null){
					resources.put(baseFileName, resource);
				}
			}
		}
		
		if(resources.isEmpty()){
			logger.warn("There were no property files to load.");
			return;
		}
		
		setLocations(resources.values().toArray(new Resource[0]));
	}
	
	/**
	 * Joins a string array into a single string. Each array item can be
	 * seperated by a string value, if undesired, then simply pass in NULL for
	 * that field.
	 */
	private String join(String[] arr, String seperator){
		String rtn = "";
		for(int x = 0; x < arr.length; x++){
			if(arr[x] == null){
				continue;
			}
			rtn += arr[x];
			if((seperator != null) && ((x + 1) < arr.length)){
				rtn += seperator;
			}
		}
		return rtn;
	}
	
	/**
	 * Iterates over the passed in patternLocations to determine which ones
	 * actually exist.
	 * @param patternLocations - possible context locations to test.
	 * @return String[] of pattern locations that exist.
	 */
	protected static List<Resource> preparePatternFiles(List<String> patternLocations){
		List<Resource> matchingResources = new ArrayList<Resource>();
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		/*
		 * Bit of confusing logic here, but no way around it that I could find.
		 * getResources (plural) will throw an exception if the resource 
		 * does not exist, while getResource (singular) will not. 
		 */
		for(String path : patternLocations){
			try {
				if(path.contains("*")){
					matchingResources.addAll(Arrays.asList(resolver.getResources(path)));
				}
				else{
					Resource resource = resolver.getResource(path);
					if(resource.exists()){
						matchingResources.add(resource);
					}
				}
			} catch (Exception e) {
				//Do Nothing
				//This will be caused when the no files or directory does not
				//exist for method getResources (plural).
			}
		}
		return matchingResources;
	}

}
