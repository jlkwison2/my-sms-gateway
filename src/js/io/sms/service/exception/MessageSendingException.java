package js.io.sms.service.exception;

public class MessageSendingException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MessageSendingException(String msg) {
		super(msg);
	}
}
