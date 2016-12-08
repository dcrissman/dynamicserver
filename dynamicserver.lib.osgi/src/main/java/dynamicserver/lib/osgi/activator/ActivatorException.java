package dynamicserver.lib.osgi.activator;

@SuppressWarnings("serial")
public class ActivatorException extends Exception {

	public ActivatorException() {
		super();
	}

	public ActivatorException(String message) {
		super(message);
	}

	public ActivatorException(Throwable cause) {
		super(cause);
	}

	public ActivatorException(String message, Throwable cause) {
		super(message, cause);
	}

}
