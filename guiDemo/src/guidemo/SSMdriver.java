/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guidemo;
import com.fazecast.jSerialComm.*;
/**
 *
 * @author Sean
 */
public class SSMdriver {
        static SerialPort comPort = SerialPort.getCommPorts()[1];
    
    public SSMdriver(int baudRate, int newDataBits, int stopBits, int pairity){
        comPort.setComPortParameters(4800,8,1,0);
    }
    
    public static byte[] readPort(SerialPort comPort){
        
        
        byte[] response = {};
        
        StringBuilder sb1 = new StringBuilder(); 
        
        try {
            while (true)
            {
                while (comPort.bytesAvailable() == 0)
                    Thread.sleep(20);

                byte[] readBuffer = new byte[comPort.bytesAvailable()];
                
                int numRead = comPort.readBytes(readBuffer, readBuffer.length);
                
                System.out.println("Read " + numRead + " bytes.");
                
                for(byte b:readBuffer){
                  sb1.append(String.format("%02X ", b));
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        
        return response;
    }
    
    
    public static void openPort(){
        comPort.openPort();
    }
    public static void writePort(byte[] message){
        comPort.writeBytes(message, message.length);
    }
    public static void closePort(){
        comPort.closePort();
    }
    public static void rpmMonitor(){
        
        
        byte[] lowByte = {(byte)0x80, 0x10, (byte)0xF0, 0x08, (byte)0xA8, (byte)0x00, 0x00 ,0x00, 0x0E, 0x00, 0x00, 0x0F, 0x4D};
        byte[] highByte = {(byte)0x80, 0x10, (byte)0xF0, 0x08, (byte)0xA8, (byte)0x00, 0x00 ,0x00, 0x0E, 0x00, 0x00, 0x0E, 0x4C};
        
        byte[] lowByteResponse = new byte[2];
        byte[] highByteResponse = new byte[2];
        
        
        
        writePort(lowByte);
        lowByteResponse = readPort(comPort);
        
        
        
        writePort(highByte);
        highByteResponse = readPort(comPort);
        
    }
    
    
    
}
