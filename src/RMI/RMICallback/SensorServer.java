package RMI.RMICallback;

import java.rmi.Remote;
import java.rmi.RemoteException;

// BEGIN main
public interface SensorServer extends Remote {
	public static final String LOOKUP_NAME = "Sensor_Service";
	public void connect(ClientCallback d) throws RemoteException;
}
// END main
