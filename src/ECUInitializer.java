package com.ezu;

import com.ezu.*;
import java.util.*;

/**
 * The ECUInitializer builds an ArrayList of ECU objects
 *
 * @author Anthony Kottke
 */
public class ECUInitializer
{
    /**
     *  HashMap holds all ECUSensor objects.
     */
    public Map<String, ECUSensor> map = new HashMap<>();

    /**
     * Constructor automatically assigns all available sensors to map with their own unique key.
     */
    public ECUInitializer()
    {
        this.addToMap(new EngineSpeed(this));
        this.addToMap(new VehicleSpeed(this));
        this.addToMap(new CoolantTemp(this));
        System.out.println("ECU Map Initialized...");

    }

    /**
     * All sensors connected to the ECU are given unique keys. **KNOWN ISSUE** When map is instantiated an initial
     * key of 1 is created.
     *
     * @return String array containing all keys paired with
     */
    public String[] listAddresses()
    {

        String[] addresses = (String[])map.keySet().toArray(new String[0]);
        return addresses;
    }

    /**
     * This method can only be accessed by the constructor. This method instantiates all available sensor objects and
     * adds them to the HashMap.
     *
     * @param e Child of ECUSensor
     */
    private void addToMap(ECUSensor e)
    {
        // e.getAddress() returns the unique key assigned to the given sensor object
        this.map.put(e.getAddress(),e);
    }
}
