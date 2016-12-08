package dynamicserver.app.osgi.server.extension.exception;

import dynamicserver.lib.osgi.activator.ActivatorException;

@SuppressWarnings("serial")
public class ServiceActivatorException extends ActivatorException {

	public ServiceActivatorException() {
		super();
	}

	public ServiceActivatorException(String message) {
		super(message);
	}

	public ServiceActivatorException(Throwable cause) {
		super(cause);
	}

	public ServiceActivatorException(String message, Throwable cause) {
		super(message, cause);
	}

}
