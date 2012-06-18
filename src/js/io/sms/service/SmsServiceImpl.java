/**
 * JediSoft.ph 2012
 */

package js.io.sms.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TooManyListenersException;

import javax.comm.CommPortIdentifier;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;
import javax.comm.UnsupportedCommOperationException;

import js.io.Param;
import js.io.SerialPortParam;
import js.io.sms.service.exception.MessageSendingException;
import js.io.sms.service.exception.TimeoutException;

class SmsServiceImpl implements SmsService, SerialPortEventListener {
	private CommPortIdentifier portId;
	private SerialPort serialPort;
	private InputStream in;
	private OutputStream out;
	private String resp;
	private boolean notified;
	private static final int RESP_BUFF_SIZE = 50;
	private static final long TIMEOUT = 5000;
	
	static final private char CTRL_Z = (char) 26;
	private static final String AT_CMGF = "AT+CMGF";
	private static final String AT_CMGS = "AT+CMGS";
	private static final String AT = "AT";
	private static final String RET = "\r";
	private static final String OK = "OK";
	private static final String ERROR = "ERROR";
	private static final String GREATER_THAN = ">";
	
	private byte [] getBytes(String cmd) {
		return cmd.getBytes();
	}
	
	private void resetNotifiedFlag() {
		notified = false;
	}
	
	/**
	 * Send a command to the serial port and return a response
	 * @param cmd AT modem commands
	 * @timeout time to wait for a response in milliseconds 
	 * @return
	 * @throws IOException 
	 */
	private String sendCmd(String cmd, long timeout) throws IOException, TimeoutException {
		synchronized (this) {
			try {
				resetNotifiedFlag();
				System.out.println(cmd); // TODO Replace with log4j
				out.write(cmd.getBytes());
				out.flush();
				
				this.wait(timeout);
				if (notified) {
					return resp;
				} else {
					throw new TimeoutException(timeout);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public String getStatus() {
		String sts = null;
		try {
			sts = sendCmd(AT + RET, TIMEOUT);
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sts;
	}
	
	/**
	 * Sets gsm modem's mode
	 * @param mode
	 * @throws IOException
	 */
	// TODO Verify if the command is correct and if this is
	// really needed.
	private void setModemMode(int mode) throws IOException {
		String resp = sendCmd(AT_CMGF + "=" + mode + "\r", TIMEOUT);
		if (!isFound(resp, OK)) {
			throw new RuntimeException("Error settin modem to mode " + mode);
		}
	}
	
	/**
	 * Checks if the expected status is in the reponse
	 * @param resp response
	 * @param expected expected status
	 * @return
	 */
	private boolean isFound(String resp, String expected) {
		if (resp != null) {
			if (resp.indexOf(expected) > -1) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void sendMsg(String telNo, String msg) throws IOException {
		String resp;
		String cmdStr = AT_CMGS + "=\"" + telNo + "\"\r";
		resp = sendCmd(cmdStr, TIMEOUT);
		if (isFound(resp, GREATER_THAN)) {
			cmdStr = msg + CTRL_Z;
			resp = sendCmd(cmdStr, TIMEOUT);
		} else {
			throw new MessageSendingException(cmdStr + " response = " + resp);
		}
	}

	@Override
	public void destroy() {
		serialPort.removeEventListener();
		System.out.println("Event listener removed");
		serialPort.close();
		System.out.println("Serial port closed");
	}

	@Override
	public void init(Param param, int timeout) {
		try {
			SerialPortParam portParam = (SerialPortParam) param;
			portId = CommPortIdentifier.getPortIdentifier(portParam.getPort());
			serialPort = (SerialPort) portId.open(portParam.getName(), timeout);
			serialPort.setSerialPortParams(portParam.getBaudRate(),  
										   portParam.getDataBit(),
										   portParam.getStopBit(),
										   portParam.getParity());
			// serialPort.setInputBufferSize(RESP_BUFF_SIZE);
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
			in = serialPort.getInputStream();
			out = serialPort.getOutputStream();
			// setModemMode(1);
			System.out.println("Initialization successful!");
		} catch (NoSuchPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PortInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedCommOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TooManyListenersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		System.out.println("Serial event triggered...");
		switch(event.getEventType()) {
    	case SerialPortEvent.BI:
    	case SerialPortEvent.OE:
    	case SerialPortEvent.FE:
    	case SerialPortEvent.PE:
    	case SerialPortEvent.CD:
    	case SerialPortEvent.CTS:
    	case SerialPortEvent.DSR:
    	case SerialPortEvent.RI:
    	case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
    		break;
    	case SerialPortEvent.DATA_AVAILABLE:
    		System.out.println("Data available...");
    		StringBuilder sb = new StringBuilder();
    		try {
    			while (in.available() > 0) {
    				byte[] readBuffer = new byte[RESP_BUFF_SIZE];
    				int size = in.read(readBuffer);
    				System.out.println("Response size = " + in.available());
    				sb.append(new String(readBuffer).substring(0, size - 1));
    			}
    			
    			synchronized (this) {
    				notified = true;
    				this.notify();
				}
    			
    			resp = sb.toString();
    			System.out.println(resp);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        	break;
		}
		
	}
	
}
