package RMI.BancoRMI.BancoServidorRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Cipher extends Remote {
	
	public String getName() throws RemoteException;

}
