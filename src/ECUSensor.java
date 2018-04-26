package com.ezu;
import com.ezu.*;
/**
 *  General class all sensors will inherit from.
 *
 * @author Anthony Kottke
 */
public abstract class ECUSensor
{

    /**
     * All child classes will have an addToMap method that is called in child class constructors. This child class
     * constructor must be instantiated with a reference to an ECUInitializer.
     *
     * @param eI Reference to an ECUInitializer Object
     */
    public abstract void addToMap(ECUInitializer eI);

    /**
     * Generic getter method
     *
     * @return  Value the sensor is responsible for monitoring (ie. rpm for EngineSpeed).
     */
    public abstract String report();

    /**
     * Generates a message that can be sent to the ECU. The message sent to the write method should only contain in the
     * following order the Data Size, Command/Response, and the Data bytes.
     *
     * @param mess Byte array containing the message to be written to the ECU
     * @return Formatted message to be sent to the ECU
     */
    public byte[] write( byte[] mess) {
        byte[] writeMess = new byte[4 + mess.length];

        writeMess[0] = ((byte) 0x80);
        writeMess[1] = ((byte) 0x10);
        writeMess[2] = ((byte) 0xF0);

        for (int i = 0; i < mess.length; i++) {
            writeMess[3 + i] = mess[i];

        }

        byte sum = 0;

        for (byte b : writeMess) {
            sum = (byte) (sum + b);
        }
        writeMess[writeMess.length - 1] = (byte) ((byte) sum & 0xFF);

        return writeMess;
    }

    /**
     * All sensors are created and placed into a HashMap, accessible only through the ECUInitializer. Keys are String
     * objects unique to all sensors.
     *
     * @return  String key used to access correct object in ECUInitializer map
     */
    public abstract String getAddress();

    /**
     * Returns a formatted response that removes duplicate information leaving only data.
     *
     * @param response Full response given by ECU
     * @return Response without the initial, source, target and checksum bytes
     */
    protected byte[] parsedResponse(byte[] response)
    {
        byte[] parsed = new byte[response.length - 4];

        for (int i = 0;i < parsed.length-1; i++)
        {
            parsed[i] = response[3+i];
        }

        return parsed;
    }


}
