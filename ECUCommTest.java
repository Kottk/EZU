import com.fazecast.jSerialComm.*;

public class ECUCommTest
{
    public static void main(String[] args)
    {
     SerialPort serTest = SerialPort.getCommPort("COM3");
     SerialPort serListenTest = SerialPort.getCommPort("COM4");
     serListenTest.openPort();

     ECUComm test = new ECUComm(serTest);
     byte[] mess = {(byte)0xF0, 0x08, (byte)0xA8, 0x00, 0x00, 0x00, 0x08, 0x00, 0x00, 0x1C};

     test.Write(mess);
     serListenTest.closePort();
    }
}
