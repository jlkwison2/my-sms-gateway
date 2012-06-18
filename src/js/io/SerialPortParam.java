/**
 * JediSoft.ph 2012
 */

package js.io;

/**
 * 
 * @author Jedd Anthony L. Cuison
 *
 */

public class SerialPortParam extends Param {
	private String port;
	private int baudRate;
	private int dataBit;
	private int stopBit;
	private int parity;
	
	public String getPort() {
		return port;
	}
	
	public String getName() {
		return name;
	}
	
	public int getBaudRate() {
		return baudRate; 
	}
	
	public int getDataBit() {
		return dataBit;
	}
	
	public int getStopBit() {
		return stopBit;
	}
	
	public int getParity() {
		return parity;
	}
	
	public static class Builder {
		private String port;
		private String name;
		private int baudRate;
		private int dataBit;
		private int stopBit;
		private int parity;
		
		public Builder setPort(String port) {
			this.port = port;
			return this;
		}
		
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		
		public Builder setBaudRate(int baudRate) {
			this.baudRate = baudRate;
			return this;
		}
		
		public Builder setDataBit(int dataBit) {
			this.dataBit = dataBit;
			return this;
		}
		
		public Builder setStopBit(int stopBit) {
			this.stopBit = stopBit;
			return this;
		}
		
		public Builder setParity(int parity) {
			this.parity = parity;
			return this;
		}
		
		public SerialPortParam build() {
			SerialPortParam serialPort = new SerialPortParam();
			serialPort.port = this.port;
			serialPort.name = this.name;
			serialPort.baudRate = this.baudRate;
			serialPort.dataBit = this.dataBit;
			serialPort.stopBit = this.stopBit;
			serialPort.parity = this.parity;
			return serialPort;
		}
		
	}
}
