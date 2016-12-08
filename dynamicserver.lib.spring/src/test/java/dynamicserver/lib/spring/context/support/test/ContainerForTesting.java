package dynamicserver.lib.spring.context.support.test;

public class ContainerForTesting {
	
	private String value;
	
	public void setValue(String value){
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
	
}
