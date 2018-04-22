/**
 *  Type of sensor that measures Engine speed. Initializes RPM to -1.
 *
 * @author Anthony Kottke
 */
public class EngineSpeed extends ECUSensor
{
    public Integer rpm = -1;
    public final String KEY = "ES";

    @Override
    public void addToMap(ECUInitializer eI)
    {
       eI.map.put("1",this);
    }

    /**
     * Constructor should only be called with ECUInitializer Class.
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

    @Override
    public String report() {
        return rpm.toString();
    }

    /**
     * All sensors are created and placed into a HashMap, accessible only through the ECUInitializer. Keys are String
     * objects unique to all sensors.
     *
     * @return String key used to access correct object in ECUInitializer map
     */
    @Override
    public String getAddress()
    {
        return KEY;
    }

    /**
     *  This method interprets the response received from the ECU.
     *
     * @param resp The full response from the ECU
     * */

    public void response(byte[] resp)
    {
        //Java reads bytes into integers without the 2's compliment rule, bitwise & function fixes
        int prod = (resp[resp.length-2]&0xff) * (resp[resp.length - 3]&0xff) ;
        rpm =  prod/4;

    }
}
