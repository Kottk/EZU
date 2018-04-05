import jssc.*; 

public class ReadWriteSerial {
	
	String defaultPort = "";
	String message = "80 10 F0 08 A8 00 00 00 08 00 00 1C 54";
	String response = "";
	SerialPort serialPort = new SerialPort("COM1");
	
	public static void main(String[] args){
		
	}
	
	/**
	 * 
	 * 
	 */
	
	public void initPort(){
		try {
	        serialPort.openPort();//Open serial port
	        serialPort.setParams(4800, 8, 1, 0);//Set params.
	    }
	    catch (SerialPortException ex) {
	        System.out.println(ex);
	    }
	}
	
	/**
	 * 
	 * 
	 */
	
	public void closePort(){
		try {
			serialPort.closePort();
		} catch (SerialPortException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 
	 * 
	 * 
	 */
	
	public void readPort(){
	    try {
	        byte[] buffer = serialPort.readBytes(250);//Read 10 bytes from serial port
	        response = buffer.toString();
	    }
	    catch (SerialPortException ex) {
	        System.out.println(ex);
	    }
	}
	
	/**
	 * 
	 * 
	 */
	
	public void createEventListener(){ 
	    try {
	        int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;//Prepare mask
	        serialPort.setEventsMask(mask);//Set mask
	        serialPort.addEventListener((SerialPortEventListener) new SerialPortReader(serialPort));//Add SerialPortEventListener
	    }
	    catch (SerialPortException ex) {
	        System.out.println(ex);
	    }
	}
	
	/**
	 * 
	 * 
	 * @param event
	 */
	public void serialEvent(SerialPortEvent event){
		
		if(event.isRXCHAR()){//If data is available
            if(event.getEventValue() == 250){//Check bytes count in the input buffer
                //Read data, if 10 bytes available 
                try {
                    byte buffer[] = serialPort.readBytes(250);
                    response = buffer.toString();
                }
                catch (SerialPortException ex) {
                    System.out.println(ex);
                }
            }
        }
	}
	
	/**
	 * 
	 * 
	 */
	
	public void writeToPort(){
		try {
	        serialPort.writeBytes(message.getBytes());//Write data to port
	    }
	    catch (SerialPortException ex) {
	        System.out.println(ex);
	    }
	}
	
}
