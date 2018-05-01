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
        SerialPort comPort = SerialPort.getCommPort("COM3");
        ECUInitializer ecu = new ECUInitializer();  
     
    public SSMdriver(int baudRate, int newDataBits, int stopBits, int pairity){
        comPort.setComPortParameters(baudRate,newDataBits,stopBits,pairity);
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


    public String rpmMonitor() {
        byte[] engineSpeed = {0x08, (byte) 0xA8, 0x00, 0x00, 0x00, 0x0E, 0x00, 0x00, 0x0F};
        byte[] writeMess = ecu.map.get("ES").write(engineSpeed);


        comPort.writeBytes(writeMess,writeMess.length);

        try {
                while (comPort.bytesAvailable() == 0)
                    Thread.sleep(20);
                {
                    byte[] readBuffer = new byte[comPort.bytesAvailable()];

                    int numRead = comPort.readBytes(readBuffer, readBuffer.length);

                    System.out.println("Read " + numRead + " bytes.");
                    ecu.map.get("ES").response(readBuffer);

                }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return ecu.map.get("ES").report();
    }
    public String vehicleSpeed(boolean isMetric){
            byte[] vSpeed = {0x05, (byte) 0xA8, 0x00, 0x00, 0x00, 0x10};
            comPort.writeBytes(ecu.map.get("VS").write(vSpeed), ecu.map.get("VS").write(vSpeed).length);
            if (isMetric) {
            }
            try {
                    while (comPort.bytesAvailable() == 0)
                        Thread.sleep(20);

                    byte[] readBuffer = new byte[comPort.bytesAvailable()];

                    int numRead = comPort.readBytes(readBuffer, readBuffer.length);

                    System.out.println("Read " + numRead + " bytes.");

                    ecu.map.get("VS").response(readBuffer);

            } catch (Exception e) {
                e.printStackTrace();

            }
            return ecu.map.get("VS").report();
        }
    public String boostPressure() {
        byte[] boost = {0x05, (byte) 0xA8, 0x00, 0x00, 0x00, 0x22};
        comPort.writeBytes(ecu.map.get("MP").write(boost), ecu.map.get("MP").write(boost).length);

        try {
                while (comPort.bytesAvailable() == 0)
                    Thread.sleep(20);

                byte[] readBuffer = new byte[comPort.bytesAvailable()];

                int numRead = comPort.readBytes(readBuffer, readBuffer.length);

                System.out.println("Read " + numRead + " bytes.");

                ecu.map.get("MP").response(readBuffer);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return ecu.map.get("MP").report();
    }
    public String coolantTemp() {
        byte[] coolant = {0x05, (byte) 0xA8, 0x00, 0x00, 0x00, 0x08};
        comPort.writeBytes(ecu.map.get("CT").write(coolant), ecu.map.get("CT").write(coolant).length);

        while(true) {
            try {
                {
                    while (comPort.bytesAvailable() == 0)
                        Thread.sleep(20);

                    byte[] readBuffer = new byte[comPort.bytesAvailable()];

                    int numRead = comPort.readBytes(readBuffer, readBuffer.length);

                    System.out.println("Read " + numRead + " bytes.");

                    ecu.map.get("CT").response(readBuffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ecu.map.get("CT").report();
        }
    }
    public String intakeTemp() {
        byte[] intake = {0x05, (byte) 0xA8, 0x00, 0x00, 0x00, 0x12};
        comPort.writeBytes(ecu.map.get("IT").write(intake), ecu.map.get("IT").write(intake).length);

        try {
            {
                while (comPort.bytesAvailable() == 0)
                    Thread.sleep(20);

                byte[] readBuffer = new byte[comPort.bytesAvailable()];

                int numRead = comPort.readBytes(readBuffer, readBuffer.length);

                System.out.println("Read " + numRead + " bytes.");

                ecu.map.get("IT").response(readBuffer);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return ecu.map.get("IT").report();

    }
    public void increaseRPM(){

    }
    public void decreaseRPM(){
        
    }
    
    
    
    
}
