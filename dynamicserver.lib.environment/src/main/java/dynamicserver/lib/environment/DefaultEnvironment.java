package dynamicserver.lib.environment;

import org.springframework.beans.factory.annotation.Required;

public class DefaultEnvironment implements IEnvironment {
	
	private String environment;
	
	public String getEnvironment() {
		return this.environment;
	}
	
	@Required
	public void setEnvironment(String environment){
		this.environment = environment;
	}
	
	public DefaultEnvironment(){};
	
	public DefaultEnvironment(String environment){
		this();
		setEnvironment(environment);
	}

}
