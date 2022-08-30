import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ClientDriver {
    static Scanner sc;
    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        String firstName = null, lastName = null;
        System.out.println("---------------------------- WELCOME TO eEXAM ----------------------------\n\n");

        System.out.print("First Name: ");
        sc = new Scanner(System.in);
        firstName = sc.nextLine();
        System.out.print("Last Name: ");
        lastName = sc.nextLine();
        String val="none";


       while(!val.isEmpty()){
        try {
            String serverURL = "rmi://localhost/RMIServer";
            ServerIF server = (ServerIF) Naming.lookup(serverURL);
            new Thread(new Client(firstName+' ' + lastName, server)).start();
            val = "";
        } catch (Exception e) {
            System.err.println("\n\n\n *****    Error Server is not Started !!    ***** \n");
            System.out.print("[ To reconnect type: \"Connect\"  ] \n:");
            val = sc.nextLine();

        }
    }

    }
}
