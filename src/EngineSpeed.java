package com.ezu;

/**
 *  Type of sensor that measures Engine speed. Initializes RPM to -1. Key address for ECUInitializer HashMap is "ES".
 *
 * @author Anthony Kottke
 */
public class EngineSpeed extends ECUSensor
{
    /**
     * Represents the engine speed in rpm.
     */
    public Integer rpm = -1;
    /**
     * Key for ECUInitializer's HashMap.
     */
    public final String KEY = "ES";

    /**
     * Called by EngineSpeed constructor, adds this new object to the ECUInitializer's HashMap.
     *
     * @param eI Reference to an ECUInitalizer Object.
     */
    @Override
    public void addToMap(ECUInitializer eI)
    {
        eI.map.put(KEY,this);
    }

    /**
     * Constructor should only be called with ECUInitializer Class, calls the addToMap method.
     *
     * @param eI Reference to an ECUInitializer Object.
     */
    public EngineSpeed(ECUInitializer eI)
    {
        addToMap(eI);
    }


    /**
     *  Builds and returns a message that can be used to communicate with the ECU.
     *
     * @param mess The information to be sent to the ECU. This data should contain only the data size byte, the command
     *             byte, and the actual data.
     * @return Returns a byte array containing the information specified by the user WITH the required source, destination,
     *         0x80, and checksum bytes.
     */
    @Override
    public byte[] write(byte[] mess)
    {
        return super.write(mess);
    }

    /**
     * Getter method for the rpm field.
     *
     * @return  Measured engine speed in rpm, initially -1 range(0-16,384).
     */
    @Override
    public String report() {
        return rpm.toString();
    }

    /**
     * All sensors are created and placed into a HashMap, accessible only through the ECUInitializer. Keys are String
     * objects unique to all sensors.
     *
     * @return String key used to access correct object in ECUInitializer map.
     */
    @Override
    public String getAddress()
    {
        return KEY;
    }

    /**
     *  This method interprets the response received from the ECU and sets rpm field.
     *
     * @param resp The full response from the ECU.
     * */

    public void response(byte[] resp)
    {
        //Java reads bytes into integers without the 2's complement rule, bitwise & function fixes
        int prod = ((resp[resp.length-3]<<8) | (resp[resp.length - 2]&0xff)) ;
        rpm =  prod/4;

    }
}
