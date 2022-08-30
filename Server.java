import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server extends UnicastRemoteObject implements ServerIF {
    private static final long serialVersionUID = 1L;
    private ArrayList<ClientIF> clients;
    private String []questions;
    private int[] answers;

    protected Server(String questions[], int answers[]) throws RemoteException {
        this.questions = questions;
        this.answers = answers;
        clients = new ArrayList<ClientIF>();
    }

    public synchronized void registerClient(ClientIF clientIntf) throws RemoteException {
        this.clients.add(clientIntf);
        this.broadcastMessage(questions, clientIntf);
    }

    public synchronized void broadcastMessage(String [] message, ClientIF client) throws RemoteException {
        client.retriveMessage(message);
    }

    public synchronized void broadcastDisscussion(String message) throws RemoteException {
        int i = 0;
        while (i < clients.size()) {
            clients.get(i++).retriveDisscution(message);
        }
    }

    public synchronized String sendAnswer(ClientIF clientIntf, int[] answer) throws RemoteException {
        int total = 0;
        String response = null;
        for (int i = 0; i < answer.length; i++) {
            if (answers[i] == answer[i])
                ++total;
        }
        if (total >= (int)(answer.length * 0.75))
            response = "Congratulations, You got " + total + " out of "+answer.length;
        else
            response = "You got " + total + " out of "+ answer.length;
        return response;
    }

}