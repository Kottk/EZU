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
        byte[] check = {(byte)((byte)sum & 0xFF)};


        return check;
    }

    /**
     * Writes the message to the ECU, Automatically populates the required 0x80, 0x10 and checksum value.
     *
     * @param message User specified message
     */
    public void Write(byte[] message)
    {
        byte[] writeMess = new byte[4 + message.length];

        writeMess[0] = ((byte)0x80);
        writeMess[1] = ((byte)0x10);
        writeMess[2] = ((byte)0xF0);
            
         for(int i = 0; i < message.length;i++)
        {
         writeMess[3+i] = message[i];
       
        }
         writeMess[writeMess.length-1] = CheckSum(writeMess)[0];
         System.out.println();
         

        int i = commPort.writeBytes(writeMess, writeMess.length);
        System.out.println(i);
        //Closing port for testing purposes

        //commPort.closePort();
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
                    Thread.sleep(20);

                byte[] readBuffer = new byte[commPort.bytesAvailable()];
                int numRead = commPort.readBytes(readBuffer, readBuffer.length);
                System.out.println("Read " + numRead + " bytes.");
                for(byte b:readBuffer)
                {
                System.out.print(b + " ");
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            commPort.closePort();
            System.out.println("NO RESPONSE");
        }
    }

}
