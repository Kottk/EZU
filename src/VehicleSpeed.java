package com.ezu;
import com.ezu.*;

/**
 *  Type of sensor that measures vehicle speed. Initializes kmh to 0. Key address for ECUInitializer HashMap is "VS".
 *
 * @author Anthony Kottke
 */

public class VehicleSpeed extends ECUSensor
{
    /**
     * Represents the speed the vehicle is moving in km/h.
     */
    public Integer kmh = 0;
    /**
     * Key for ECUInitializer's HashMap.
     */
    public final String KEY = "VS";


    /**
     * Called by VehicleSpeed constructor, adds this new object to the ECUInitializer's HashMap.
     *
     * @param eI Reference to an ECUInitializer Object.
     */
    @Override
    public void addToMap(ECUInitializer eI)
    {
        eI.map.put(KEY,this);
    }

    /**
     * Constructor should only be called with ECUInitializer Class, calls the addToMap method.
     *
     * @param eI Reference to ECUInitializer object.
     */
    public VehicleSpeed(ECUInitializer eI)
    {
        addToMap(eI);
    }
    /**
     * Getter method for kmh field.
     *
     * @return  Vehicle speed in km/h, initially 0 range(0-255).
     */
    @Override
    public String report() {
        return kmh.toString();
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
     *  This method interprets the response received from the ECU and sets the kmh field.
     *
     * @param resp The full response from the ECU.
     * */

    public void response(byte[] resp)
    {
        //Java reads bytes into integers without the 2's compliment rule, bitwise & function fixes
        int prod = (resp[resp.length-2]&0xff) ;
        kmh =  prod;

    }
}
