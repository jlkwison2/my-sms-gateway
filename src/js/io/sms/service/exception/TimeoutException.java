package js.io.sms.service.exception;

public class TimeoutException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TimeoutException(long timeout) {
		super("Timeout duration of " + timeout + " ms has elapsed!");
	}
}
