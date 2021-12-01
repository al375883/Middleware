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
public class SensorServerImpl
	extends UnicastRemoteObject
	implements SensorServer, Runnable
{
	private static final long serialVersionUID = -464196277362659008L;
	List<ClientCallback> list = new ArrayList<ClientCallback>();

	/** Construct the object that implements the remote server.
	 * Called from main, after it has the SecurityManager in place.
	 */
	public SensorServerImpl() throws RemoteException {
		super();	// sets up networking
	}

	/** Start background thread to track stocks :-) and alert users. */
	public void start() {
		new Thread(this).start();
	}

	/** The remote method that "does all the work". This won't get
	 * called until the client starts up.
	 */
	public void connect(ClientCallback da) throws RemoteException {
		System.out.println("Adding client " + da);
		list.add(da);
	}

	boolean done = false;
	Random rand = new Random();

	public void run() {
		while (!done) {
			try {
				Thread.sleep(5 * 1000);
				System.out.println("Tick");
			} catch (InterruptedException unexpected) {
				System.out.println("Interrupted!");
				done = true;
			}
			Iterator it = list.iterator();
			while (it.hasNext()){
				String mesg = ("The temperature went " +
					(rand.nextFloat() > 0.5 ? "up" : "down") + "!");
				// Send the alert to the given user.
				// If this fails, remove them from the list
				try {
					((ClientCallback)it.next()).alert(mesg);
				} catch (RemoteException re) {
                    System.out.println(
						"Exception alerting client, removing it.");
					System.out.println(re);
					it.remove();
				}
			}
		}
	}


}
// END main
