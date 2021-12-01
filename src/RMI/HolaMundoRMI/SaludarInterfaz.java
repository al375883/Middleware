package RMI.HolaMundoRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SaludarInterfaz extends Remote {

    public String decirHola() throws RemoteException;
}