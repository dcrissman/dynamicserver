package dynamicserver.example.helloworld.service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name="HelloWorld")
public interface IHelloWorld {
	
	public String helloWorld(@WebParam(name="name") String name);
	
}