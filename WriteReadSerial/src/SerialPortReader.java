import jssc.SerialPort;
import jssc.SerialPortException;

public class SerialPortReader {
	
	private String response;

	/**
	 * 
	 * 
	 * @param serialPort
	 */
	
	public SerialPortReader(SerialPort serialPort){
	
	}
	
	public SerialPortReader(){
		
	}
	
	/**
	 * 
	 * 
	 * @param serialPort
	 * @return
	 */
	
	public void readPort(SerialPort serialPort){
		
	    try {
	        byte[] buffer = serialPort.readBytes(250);//Read 10 bytes from serial port
	        response = buffer.toString();
	        
	    }
	    catch (SerialPortException ex) {
	        System.out.println(ex);
	    }
	}
	
	public String getResponse(){
		return response;
	}
	
}
