package dynamicserver.lib.spring.io.support;

import java.io.IOException;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class PatternAwarePropertyPlaceholderConfigurer 
extends PropertyPlaceholderConfigurer {

	public void setLocationPattern(String locationPattern) throws IOException {

		setLocations(new PathMatchingResourcePatternResolver()
				.getResources(locationPattern));
	}
	
	public String getProperty(String name) throws IOException{
		return mergeProperties().getProperty(name);
	}

}
