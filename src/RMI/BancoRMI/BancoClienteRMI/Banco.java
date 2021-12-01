package RMI.BancoRMI.BancoClienteRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Banco extends Remote {

	// public Cliente addCliente(String nombre, int edad, Cliente tutor) throws RemoteException;
	public Cliente addCliente(Cipher nom, int edad, Cliente tutor) throws RemoteException;
	public List<Cliente> getClientes() throws RemoteException;
}
