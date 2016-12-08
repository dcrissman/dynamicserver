package dynamicserver.example.helloworld.service;

import javax.jws.WebService;

@WebService(endpointInterface = "dynamicserver.example.helloworld.service.IHelloWorld")
public class HelloWorldImpl implements IHelloWorld {

	public String helloWorld(String name) {
		return "Hello " + name + "!";
	}
	
}