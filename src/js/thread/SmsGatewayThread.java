/**
 * JediSoft.ph 2012
 */

package js.thread;

import java.io.IOException;
import java.io.InputStream;

import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;

import js.io.sms.service.SmsService;
import js.io.sms.service.SmsServiceFactory;

public class SmsGatewayThread {
//	private static final long SLEEP_DURATION = 60000; 
//	private static final int DEFAULT_TIMEOUT = 3000;
//	private static final int SMS_TEXT_MODE = 1;
//	private SmsService smsSvc;
//	private js.io.SerialPort portParamWrapper;
//	private js.io.SerialPort.Builder portParamWrapperBuilder;
//	private SerialPort serialPort;
//	private InputStream in;
//	
//	private void initPortParamWrapper() {
//		portParamWrapperBuilder = new js.io.SerialPort.Builder();
//		
//		// TODO get params from properties file
//		portParamWrapperBuilder.setName(this.getClass().getName())
//			       			   .setBaudRate(460800)
//			       			   .setDataBit(SerialPort.DATABITS_8)
//			       			   .setStopBit(SerialPort.STOPBITS_1)
//			       			   .setParity(SerialPort.PARITY_NONE);
//		
//		portParamWrapper = portParamWrapperBuilder.build();
//	}
//	
//	@Override
//	public void run() {
//		try {
//			initPortParamWrapper();
//			smsSvc = SmsServiceFactory.getSmsService();
//			serialPort = smsSvc.openPort(portParamWrapper, DEFAULT_TIMEOUT);
//			smsSvc.setModemMode(serialPort, SMS_TEXT_MODE);
//			while (true) {
//				Thread.sleep(SLEEP_DURATION);
//			}
//		} catch (InterruptedException e) {
//			System.out.println(e.getMessage());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void serialEvent(SerialPortEvent event) {
//		switch(event.getEventType()) {
//        	case SerialPortEvent.BI:
//        	case SerialPortEvent.OE:
//        	case SerialPortEvent.FE:
//        	case SerialPortEvent.PE:
//        	case SerialPortEvent.CD:
//        	case SerialPortEvent.CTS:
//        	case SerialPortEvent.DSR:
//        	case SerialPortEvent.RI:
//        	case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
//        		break;
//        	case SerialPortEvent.DATA_AVAILABLE:
//        		byte[] readBuffer = new byte[20];
//
//        		try {
//        			while (in.available() > 0) {
//        				int numBytes = in.read(readBuffer);
//        			}
//        			System.out.print(new String(readBuffer));
//        		} catch (IOException e) {
//        			System.out.println(e);
//        		}
//            	break;
//        }
//	}
}
