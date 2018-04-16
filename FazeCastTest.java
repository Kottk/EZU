import com.fazecast.jSerialComm.*;
import java.util.*;
public class FazeCastTest {
    public static void main(String[] args) {
        SerialPort comPort = SerialPort.getCommPorts()[0];
        
        byte[] message = {(byte)0x80, 0x10, (byte)0xF0, 0x08, (byte)0xA8, (byte)0x00, 0x00 ,0x00, 0x0E, 0x00, 0x00, 0x0F, 0x4D};
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();  
        
        comPort.setComPortParameters(4800,8,1,0);
        comPort.openPort();
        
        for(byte b: message){
            sb1.append(String.format("%02X ", b));
        }
        System.out.println(sb1.toString());
        comPort.writeBytes(message, message.length);

        try {
            while (true)
            {
                while (comPort.bytesAvailable() == 0)
                    Thread.sleep(20);

                byte[] readBuffer = new byte[comPort.bytesAvailable()];
                
                int numRead = comPort.readBytes(readBuffer, readBuffer.length);
                
                System.out.println("Read " + numRead + " bytes.");
                
                for(byte b:readBuffer){
                  sb2.append(String.format("%02X ", b));
                }
                System.out.println(sb2.substring(sb1.length(), sb2.length()).toString());
            }
        } catch (Exception e) { e.printStackTrace(); }
        comPort.closePort();
    }
}