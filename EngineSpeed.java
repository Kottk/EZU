/**
 * Created by akottke9849 on 4/17/2018.
 */
public class EngineSpeed extends ECUController
{
    public EngineSpeed()
    {
        super.isConnected = true;
        super.dataBytes = 2;
    }


    @Override
    public byte[] query(byte[] mess)
    {

        return new byte[0];
    }

    @Override
    public byte[] response(byte[] resp)
    {
        return new byte[0];
    }
}
