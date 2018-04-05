import jssc.*;
// TEST WITH VIRTUAL PORT
public class SerialPortTest
{
    public static void main(String[] args) throws SerialPortException
    {

        byte[] message = {(byte)0x80, (byte) 0xAA};
        SerialPortList testlist = new SerialPortList();

        String[] nameTest = testlist.getPortNames();
        for (String e : nameTest)

        {
            System.out.println(e);
        }

        SerialPort test = new SerialPort("COM2");

        try {
            test.openPort();
        } catch(Exception e)
        {
            System.out.println(e.getStackTrace());
        }


        //test.setParams(4800, 8, 1,0);
        //try
       // {
       //     test.writeByte((byte) 0x0A);
      // } catch(Exception e)
       // {
       //     System.out.println(e.getStackTrace());
      //  }
       // test.closePort();

    }
}
