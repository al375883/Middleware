package RMI.RMICallback;

import java.io.*;
import java.rmi.*;
import java.rmi.server.*;

/** This class tries to be all things to all people:
 *	- main program for client to run.
 *	- "server" program for remote to use Client of
 */
// BEGIN main
public class Client extends UnicastRemoteObject implements ClientCallback
{
	protected final static String host = "localhost";

	/** No-argument constructor required as we are a Remote Object */
	public Client() throws RemoteException {
	    super();
	}

	/** This is the main program, just to get things started. */
	public static void main(String[] argv) throws IOException, NotBoundException {
		new Client().do_the_work();
	}

	/** This is the server program part */
	private void do_the_work() throws IOException, NotBoundException {

		System.out.println("Client starting");

		// First, register us with the RMI registry
		Naming.rebind("Client", this);

		// Now, find the server, and register with it
		System.out.println("Finding server");
		SensorServer server =
			(SensorServer)Naming.lookup("rmi://" + host + "/" +
			SensorServer.LOOKUP_NAME);

		// This should cause the server to call us back.
		System.out.println("Connecting to sensor server");
		server.connect(this);

		System.out.println("Client program ready.");
	}

	/** This is the client callback */
	public void alert(String message) throws RemoteException {
		System.out.println(message);
	}
}
// END main
