package RMI.RMICallback;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

// BEGIN main
/** This is the main class of the server */
public class Server
{

    /** This is the main program, just to get things started. */
    public static void main(String[] argv) {

	Registry registro;
	try{
	    SensorServerImpl serverImpl = new SensorServerImpl();
	    registro = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
	    registro.rebind(SensorServer.LOOKUP_NAME, serverImpl);

	    serverImpl.start();
	    serverImpl.run();
	    
	} catch (RemoteException e){
	    e.printStackTrace();
	}

    }

}
