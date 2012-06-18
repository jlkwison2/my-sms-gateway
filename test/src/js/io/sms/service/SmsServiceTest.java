package js.io.sms.service;

import javax.comm.SerialPort;

import js.io.SerialPortParam;

public class SmsServiceTest {
	
	public static void testGetStatus() {
		SmsService smsSvc = SmsServiceFactory.getSmsService();
		
		SerialPortParam.Builder serialPortParamBuilder = new SerialPortParam.Builder();
		
		// TODO get params from properties file
		serialPortParamBuilder.setName("test")
							  .setPort("COM3")	
			       			  .setBaudRate(460800)
			       			  .setDataBit(SerialPort.DATABITS_8)
			       			  .setStopBit(SerialPort.STOPBITS_1)
			       			  .setParity(SerialPort.PARITY_NONE);
		
		try {
			smsSvc.init(serialPortParamBuilder.build(), 5000);
			String sts = smsSvc.getStatus();
			if (sts.indexOf("OK") > -1) {
				System.out.println("Modem ready");
			} else {
				System.out.println("Modem not ready");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			smsSvc.destroy();
		}
	}
	
	public static void testSendMsg() {
		SmsService smsSvc = SmsServiceFactory.getSmsService();
		
		SerialPortParam.Builder serialPortParamBuilder = new SerialPortParam.Builder();
		
		// TODO get params from properties file
		serialPortParamBuilder.setName("test")
							  .setPort("COM3")	
			       			  .setBaudRate(460800)
			       			  .setDataBit(SerialPort.DATABITS_8)
			       			  .setStopBit(SerialPort.STOPBITS_1)
			       			  .setParity(SerialPort.PARITY_NONE);
		
		try {
			smsSvc.init(serialPortParamBuilder.build(), 5000);
			smsSvc.sendMsg("09275491668", "This message is from MySmsGateway by JediSoft.ph :)");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			smsSvc.destroy();
		}
	}
	
	// TODO Use JUnit
	public static void main(String[] args) {
		// testGetStatus();
		testSendMsg();
	}
	
}
