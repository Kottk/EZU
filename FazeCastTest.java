import com.fazecast.jSerialComm.*;

public class FazeCastTest {
    public static void main(String[] args) {
        SerialPort comPort = SerialPort.getCommPorts()[1];
        SerialPort comPortL = SerialPort.getCommPorts()[2];

        byte[] message = {(byte)0x80, 0x10, (byte)0xF0, 0x08, (byte)0xA8, 0x00, 0x00, 0x00, 0x08, 0x00, 0x00, 0x1C, 0x54};

        comPort.setComPortParameters(4800,8,1,0);
        comPortL.setComPortParameters(4800,8,1,0);
        comPort.openPort();
        comPortL.openPort();

        comPort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_WRITTEN; }
            @Override
            public void serialEvent(SerialPortEvent event)
            {
                if (event.getEventType() == SerialPort.LISTENING_EVENT_DATA_WRITTEN)
                    System.out.println("All bytes were successfully transmitted!");
            }
        });

        comPortL.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }
            @Override
            public void serialEvent(SerialPortEvent event)
            {
                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
                    return;
                byte[] newData = new byte[comPortL.bytesAvailable()];
                int numRead = comPortL.readBytes(newData, newData.length);
                System.out.println("Read " + numRead + " bytes.");
            }
        });

        //comPortL.readBytes();
        comPort.writeBytes(message, message.length);
        comPortL.closePort();
        comPort.closePort();
    }
}
