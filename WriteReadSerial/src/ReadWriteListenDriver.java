import jssc.*;

public class ReadWriteListenDriver {
	
	SerialPort serialPort = new SerialPort("COM1");
	SerialPortReader read = new SerialPortReader(serialPort);
	SerialPortWriter write = new SerialPortWriter(serialPort);
	SerialEventListener listen = new SerialEventListener(serialPort);
	
	public static void main(String[] args){
		
	}
	
	public void initPort(){
		try {
	        serialPort.openPort();//Open serial port
	        serialPort.setParams(4800, 8, 1, 0);//Set params.
	    }
	    catch (SerialPortException ex) {
	        System.out.println(ex);
	    }
	}
}
