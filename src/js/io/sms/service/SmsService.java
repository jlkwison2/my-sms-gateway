/**
 * JediSoft.ph 2012
 */

package js.io.sms.service;

import java.io.IOException;

import js.io.Param;

/**
 * 
 * @author Jedd Anthony L. Cuison
 *
 */
public interface SmsService {
	
	/**
	 * Open serial port
	 * @param SMS device parameters 
	 * @param timeout
	 * @return
	 */
	void init(Param param, int timeout);
	
	/**
	 * Get sms service status
	 * @return status
	 */
	String getStatus();
	
	/**
	 * Send a message to the given telephone number
	 * @param telNo telephone number
	 * @param msg
	 * @throws IOException 
	 */
	public void sendMsg(String telNo, String msg) throws IOException ;
	
	/**
	 * Clean up, close port(s) or gateways
	 */
	public void destroy();
	
}
