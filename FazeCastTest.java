import com.fazecast.jSerialComm.*;

public class FazeCastTest {
    public static void main(String[] args) {
        SerialPort comPort = SerialPort.getCommPorts()[1];
        SerialPort comPortL = SerialPort.getCommPorts()[2];

        byte[] message = {(byte)0x80, 0x10, (byte)0xF0, 0x08, (byte)0xA8, 0x00, 0x00, 0x00, 0x08, 0x00, 0x00, 0x1C, 0x54};

        comPort.setComPortParameters(4800,8,1,0);
        comPortL.setComPortParameters(4800,8,1,0);
        comPort.openPort();
        comPort.writeBytes(message, message.length);

        try {
            while (true)
            {
                while (comPort.bytesAvailable() == 0)
                    Thread.sleep(20);

                byte[] readBuffer = new byte[comPort.bytesAvailable()];
                int numRead = comPort.readBytes(readBuffer, readBuffer.length);
                System.out.println("Read " + numRead + " bytes.");
            }
        } catch (Exception e) { e.printStackTrace(); }
        comPort.closePort();
    }
}
