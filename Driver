import java.util.AbstractSet;

public class Driver
{
    public static void main(String[] args)
    {
        ECUInitializer testE = new ECUInitializer();

              

        for(String s: testE.listAddresses())
        {

            System.out.println(s);
        }

        testE.map.forEach((String,ECUSensor) -> System.out.println(ECUSensor.report()));
    }
}
