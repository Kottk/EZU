import jssc.*;

public class SerialPortWriter {
	
	private String message = "80 10 F0 08 A8 00 00 00 08 00 00 1C 54";
	
	/**
	 * 
	 * 
	 */
	public SerialPortWriter(SerialPort serialPort){
	
	}
	
	
	/**
	 * 
	 * 
	 */
	
	public void writeToPort(SerialPort serialPort){
		try {
	        serialPort.writeBytes(message.getBytes());//Write data to port
	    }
	    catch (SerialPortException ex) {
	        System.out.println(ex);
	    }
	}
}
