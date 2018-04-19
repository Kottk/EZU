
public abstract class ECU
{
    boolean isConnected = false;
    int dataBytes;

    public abstract byte[] query(byte[] mess);
    public abstract byte[] response(byte[] resp);

    protected byte[] parsedResponse(byte[] response)
    {
        byte[] parsed = new byte[response.length - 4];

        for (int i = 0;i < parsed.length; i++)
        {
            parsed[i] = response[3+i];
        }

        return parsed;
    }
}
