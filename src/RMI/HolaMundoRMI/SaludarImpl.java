package RMI.HolaMundoRMI;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SaludarImpl extends UnicastRemoteObject
                         implements SaludarInterfaz, Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6868667861505177483L;

	protected SaludarImpl() throws RemoteException {
        super();
    }

    @Override
    public String decirHola() throws RemoteException {
        return "Hola a todos!";
    }
}