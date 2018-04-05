import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class SerialEventListener {
	
	private String response;

	public SerialEventListener(SerialPort serialPort){
		
	}
	
	public void createEventListener(SerialPort serialPort){ 
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
	public void serialEvent(SerialPortEvent event, SerialPort serialPort){
		
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
	
	public String getResponse(){
		return response;
	}
}
