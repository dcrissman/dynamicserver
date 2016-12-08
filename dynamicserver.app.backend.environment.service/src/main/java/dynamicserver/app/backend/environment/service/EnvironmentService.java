package dynamicserver.app.backend.environment.service;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

import dynamicserver.app.backend.environment.ISoapEnvironment;
import dynamicserver.app.backend.environment.ISoapEnvironmentServer;


@WebService(
		serviceName=ISoapEnvironment.SERVICE_NAME,
		endpointInterface = "dynamicserver.app.backend.environment.ISoapEnvironmentServer")
public class EnvironmentService implements ISoapEnvironmentServer{
	
	public String getEnvironment() {
		Message message = PhaseInterceptorChain.getCurrentMessage();
		HttpServletRequest request = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);
		
		//TODO Add actual look up logic
		String ipAddress = null;
		if(request != null){
			ipAddress = request.getRemoteAddr();
		}
		else{
			return null;
		}
		
		return "stage";
	}

	public String getEnvironmentByDnsName(String dnsName) {
		//TODO Add actual look up logic
		return "stage";
	}
	
}
