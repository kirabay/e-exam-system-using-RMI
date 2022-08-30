import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerIF extends Remote{
     void registerClient(ClientIF clientIntf) throws RemoteException;  
     void broadcastMessage(String message[], ClientIF client) throws RemoteException; 
     String sendAnswer(ClientIF clientIntf, int[] answer) throws RemoteException;
     void broadcastDisscussion(String message) throws RemoteException;

}
