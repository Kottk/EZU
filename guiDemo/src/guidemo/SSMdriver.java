/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guidemo;
import com.fazecast.jSerialComm.*;
import java.util.*;
import com.ezu.*;

/**
 *
 * @author Sean
 */
public class SSMdriver {
        static SerialPort comPort = SerialPort.getCommPorts()[1];
        ECUInitializer ecu = new ECUInitializer();  
     
    public SSMdriver(int baudRate, int newDataBits, int stopBits, int pairity){
        comPort.setComPortParameters(baudRate,newDataBits,stopBits,pairity);
    }
    
    public byte[] readPort(SerialPort comPort){
        byte[] test = new byte[0];
      return test;
    }
    
    
    public void openPort(){
        comPort.openPort();
    }
    public void writePort(byte[] message){
        comPort.writeBytes(message, message.length);
    }
    public void closePort(){
        comPort.closePort();
    }
    public int rpmMonitor(){
            
        int rpm = 0;
        
        
        return rpm;
    }
    public int vehicleSpeed(boolean isMetric){
        
        int speed = 0;
        return speed;
    }
    public int boostPressure(){
        return 0;
    }
    public int coolantTemp(){
        return 0;
    }
    public int intakeTemp(){
        return 0;
    }
    public void increaseRPM(){
        
    }
    public void decreaseRPM(){
        
    }
    
    
    
    
}
