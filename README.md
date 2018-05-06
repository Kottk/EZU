# EZU
What does it do?

This API intends to make interfacing with a vehicle's ECU easier to do. Once you have established connection with an ECU, building messages and response interpretation is handled by the classes contained in this API. Currently there is only functionality for the following sensors: Engine Speed, Vehicle Speed, Coolant Temp, Intake Temp, and Absolute Manifold Pressure.

How do I use it?

*WRITING*
Step one: Create an ECUInitializer object. The constructor takes no parameters.
Step two: If you do not know what the addresses are for the sensors once they are mapped, call ECUInitializer getAddresses method.
Step three: For whichever sensor you wish to communicate with, call it through the ECUIntitializer(example: for EngineSpeed the call would be testECUInitializer.map.get("ES").write(**BYTE ARRAY OF DATA YOU WANT TO WRITE TO ECU**))
Step four: Take the returned byte array and send it via your pre built serial port communicator.

*READING*
Step one: Send the byte array recieved from the ECU to the sensor that you are reading from using the response method (example: testECUInitializer.map.get("ES").response(**RECIEVED MESSAGE**))
Step two: The individual sensor built int the ECUInitializer map will interpret the response and alter the field in the class. To read that value out call the sensors report method using the same syntax as the response and write method. The report method returns string values (example: testECUInitializer.map.get("ES").report()) 

**TESTED VEHICLES**
- '99 WRX STI
- '06 WRX STI
- '14 WRX
- '11 Legacy


What doesn't it do?

This API does not contain any tools that allow you to directly interface with an ECU. You will need to have the physical cable or BlueTooth unit to directly interface with your vehicle's ECU. You will also need to find a Serial Port communication API that you are comfortable using. (For our testing process we used fazecast)

What is next?

- Adding the code for the rest of the sensors available.
- Possibly adding functionality for other programming languages (C++, Python etc.).
- Possibly adding functionality for other types of vehicles.

Developers:
Anthony Kottke and Sean Kirwan

**WARNING: MESSING WITH YOUR ECU MAY VOID WARRANTY/BRICK YOUR SYSTEM! PROCEED AT YOUR OWN RISK!**
