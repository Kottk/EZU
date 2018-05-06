# EZU
What does it do?

This API intends to make interfacing with a vehicle's ECU easier to do. Once you have established connection with an ECU, building messages and response interpretation is handled by the classes contained in this API. Currently there is only functionality for the following sensors: Engine Speed, Vehicle Speed, Coolant Temp, Intake Temp, and Absolute Manifold Pressure.

**TESTED VEHICLES**
- '99 WRX STI
- '06 WRX STI
- '14 WRX
- '11 Legacy


What doesn't it do?

This API does not contain any tools that allow you to directly interface with an ECU. You will need to have the physical cable or BlueTooth unit to directly interface with your vehicle's ECU. You will also need to find a Serial Port communication API that you are comfortable using. (For our testing process we used fazecast)

What is next?

Adding the code for the rest of the sensors available.
Possibly adding functionality for other programming languages (C++, Python etc.).
Possibly adding functionality for other types of vehicles.

Developers:
Anthony Kottke and Sean Kirwan

**WARNING: MESSING WITH YOUR ECU MAY VOID WARRANTY/BRICK YOUR SYSTEM! PROCEED AT YOUR OWN RISK!**
