import java.util.*;
/**
 * The ECUInitializer builds an ArrayList of ECU objects
 *
 * @author Anthony Kottke
 */
public class ECUInitializer
{
    ArrayList<ECU> sensorList;

    public ECUInitializer()
    {

        sensorList = new ArrayList<ECU>(0);

    }

    public void build()
    {
        sensorList.add(new EngineSpeed());
    }
}
