package dynamicserver.app.osgi.server.extension.exception;

@SuppressWarnings("serial")
public class ServiceManagerException extends Exception {

	public ServiceManagerException() {
		super();
	}

	public ServiceManagerException(String message) {
		super(message);
	}

	public ServiceManagerException(Throwable cause) {
		super(cause);
	}

	public ServiceManagerException(String message, Throwable cause) {
		super(message, cause);
	}

}
