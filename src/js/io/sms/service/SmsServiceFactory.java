package js.io.sms.service;

public class SmsServiceFactory {

	public static SmsService getSmsService() {
		return new SmsServiceImpl();
	}
	
}
