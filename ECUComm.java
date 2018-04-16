import com.fazecast.jSerialComm.*;

/**
 *  This class creates an object that enables communication with an ECU utilizing the Fazecast jSerialComm
 *  package.
 *
 * @author Anthony Kottke
 *
 */

public class ECUComm
{
    private final byte[] BUFFER = new byte[250];
    public byte[] mess;
    public SerialPort commPort = null;

    //Test constructor
    public ECUComm()
    {
        System.out.println("TEST");
    }

    /**
     * Constructs an object that allows easy communication with an ECU
     *
     * @param comm The port that is being used to communicate with the ECU
     */
    public ECUComm(SerialPort comm)
    {
        this.commPort = comm;

        if(!commPort.isOpen())
            commPort.openPort();

        System.out.println("ECUComm Created");
    }

    public byte[] CheckSum(byte[] message)
    {
        byte sum = 0;

        for(byte b: message)
        {
         sum = (byte)(sum + b);

        }
        byte[] check = {(byte)(sum & 0xFF)};


        return check;
    }

    /**
     * Writes the message to the ECU, Automatically populates the required 0x80, 0x10 and checksum value.
     *
     * @param message User specified message
     */
    public void Write(byte[] message)
    {
        byte[] writeMess = new byte[3+message.length];
        byte[] req = {(byte)0x80, 0x10};

        System.arraycopy(req,0,writeMess,0,2);
        System.arraycopy(message,0,writeMess,2,message.length);
        System.arraycopy(CheckSum(writeMess),0,writeMess,2+message.length,1);

        int i = commPort.writeBytes(writeMess, writeMess.length);
        System.out.println(i);
        //Closing port for testing purposes

        commPort.closePort();
       Listen();

    }



    ///////////////////////////////////////////////////////////////////////////
    //                    HEAVY CODE CONSTRUCTION.                           //
    ///////////////////////////////////////////////////////////////////////////

    // Only access when something is written
    private void Listen()
    {
        try {
            while (true)
            {

                while (commPort.bytesAvailable() == 0)
                    Thread.sleep(10);

                byte[] readBuffer = new byte[commPort.bytesAvailable()];
                int numRead = commPort.readBytes(readBuffer, readBuffer.length);
                System.out.println("Read " + numRead + " bytes.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("NO RESPONSE");
        }
    }

}
