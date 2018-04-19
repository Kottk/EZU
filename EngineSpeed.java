/**
 *  Sensor that measures Engine speed. Initializes RPM to -1.
 *
 * @author Anthony Kottke
 */
public class EngineSpeed extends ECU
{
    public int rpm = -1;

    /**
     * Constructor should only be called with ECUInitializer Class.
     */
    public EngineSpeed()
    {
        super.isConnected = true;
        super.dataBytes = 2;
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
    public byte[] query(byte[] mess)
    {
        byte[] writeMess = new byte[4 + mess.length];

        writeMess[0] = ((byte)0x80);
        writeMess[1] = ((byte)0x10);
        writeMess[2] = ((byte)0xF0);

        for(int i = 0; i < mess.length;i++)
        {
            writeMess[3+i] = mess[i];

        }

        writeMess[writeMess.length-1] = super.checkSum(writeMess)[0];

        return writeMess;
    }


    /**
     *  This method interprets the response received from the ECU.
     *
     * @param resp The full response from the ECU
     * */
    @Override
    public void response(byte[] resp)
    {
        //Java reads bytes into integers without the 2's compliment rule, bitwise & function fixes
       int prod = (resp[resp.length-2]&0xff) * (resp[resp.length - 3]&0xff) ;
       rpm=  prod/4;
       System.out.println(rpm);
    }
}
