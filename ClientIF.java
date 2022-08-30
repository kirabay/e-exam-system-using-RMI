import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientIF extends Remote {
    void retriveMessage(String message[]) throws RemoteException;

    void retriveDisscution(String message) throws RemoteException;
}
