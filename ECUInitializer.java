import java.util.*;
/**
 * The ECUInitializer builds an ArrayList of ECU objects
 *
 * @author Anthony Kottke
 */
public class ECUInitializer
{

    Map<String,ECUSensor> map = new HashMap<>(10,(float).75);

    /**
     * Default constructor
     */
    public ECUInitializer()
    {

        System.out.println("ECU Map Initialized...");

    }

    /**
     * All sensors connected to the ECU
     *
     * @return
     */
    public String[] listAddresses()
    {

        String[] addresses = (String[])map.keySet().toArray(new String[0]);

        return addresses;
    }

    public void addToMap(ECUSensor e)
    {
        this.map.put(e.getAddress(),e);
    }
}
