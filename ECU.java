/**
 *  General class all sensors will inherit from.
 *
 * @author Anthony Kottke
 */
public abstract class ECU
{
    boolean isConnected = false;
    int dataBytes;


    public abstract byte[] query(byte[] mess);
    public abstract void response(byte[] resp);

    protected byte[] parsedResponse(byte[] response)
    {
        byte[] parsed = new byte[response.length - 4];

        for (int i = 0;i < parsed.length-1; i++)
        {
            parsed[i] = response[3+i];
        }

        return parsed;
    }

    protected byte[] checkSum(byte[] builtMess)
    {
        byte sum = 0;

        for(byte b: builtMess)
        {
            sum = (byte)(sum + b);
        }
        byte[] check = {(byte)((byte)sum & 0xFF)};


        return check;
    }
}
