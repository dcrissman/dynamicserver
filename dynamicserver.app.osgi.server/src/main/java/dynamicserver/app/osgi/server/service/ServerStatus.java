package dynamicserver.app.osgi.server.service;

import javax.jws.WebService;

@WebService(endpointInterface = "dynamicserver.app.osgi.server.service.IServerStatus")
public class ServerStatus implements IServerStatus {

	public boolean isAliveAndWell() {
		return true;
	}

}
