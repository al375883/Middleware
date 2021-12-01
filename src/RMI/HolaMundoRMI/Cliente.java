package RMI.HolaMundoRMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {

    public static void main(String[] args) {
        Registry registro;
        try{
            registro = LocateRegistry.getRegistry();
            SaludarInterfaz servicio = (SaludarInterfaz)registro.lookup("Saludar");
            System.out.println(servicio.decirHola());
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}