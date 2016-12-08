package dynamicserver.app.osgi.server.service;

import javax.jws.WebService;

@WebService(name="ServerStatus")
public interface IServerStatus {
	
	public boolean isAliveAndWell();
	
}
