package RMI.RMICallback;
// BEGIN main
import java.rmi.*;

/** Client -- the interface for the client callback */
public interface ClientCallback extends Remote {
	public void alert(String mesg) throws RemoteException;
}
// END main
