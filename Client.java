import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Client extends UnicastRemoteObject implements ClientIF, Runnable { // since we execute instance of this
                                                                                // class with mul threads
    private ServerIF server;
    private String name = null;
    static Scanner input;
    static int lengthOfQuestions;

    protected Client(String name, ServerIF server) throws RemoteException {
        this.name = name;
        this.server = server;
        server.registerClient(this);
    }

    public synchronized void retriveMessage(String message[]) throws RuntimeException {
        System.out.println("Studnet Name : " + this.name);
        int qNum = 1;
        System.out.println("Note: Say 1 if it's true or 0 if it's false");
        for (int i=0; i < message.length; i++) {
            System.out.println(qNum + ", " + message[i]);
            ++qNum;
        }
        Client.lengthOfQuestions = message.length;
    }

    public synchronized void retriveDisscution(String message) throws RemoteException {
        System.out.println(message);
    }

    public void run() {
        input = new Scanner(System.in);
        System.out.println("=================Answer Sheet=================\n");
        int answers[]= new int[Client.lengthOfQuestions], numQuestions = 1, ans = 2;
        while (numQuestions <= Client.lengthOfQuestions) {

            boolean inputValid = false;
            while (!inputValid) {
                // String val = input.next();
                // ans = 0;

                try {
                    System.out.print(numQuestions + " :");
                    ans = input.nextInt();
                    inputValid = true;
                    break;
                    // break;
                } catch (InputMismatchException msg) {
                    System.out.println(" \n Please Enter 1 for True and 0 for False ");
                    // msg.printStackTrace();
                    input.next();
                    inputValid = false;
                }
            }
            // // ans = a;
            // System.out.println("You've entered: "+ans);
            while (ans != 0 & ans != 1) {
                System.err.println("Please Enter 1 for True and 0 for False!");
                ans = input.nextInt();
            }
            answers[numQuestions - 1] = ans;
            ++numQuestions;
        }
        try {
            System.out.println("========================================================");
            System.out.println("|\tResult :" + server.sendAnswer(this, answers) + "         |");
            System.out.println("========================================================");
            // Thread Synchronization needed like barrier here
            // for(Thread thread : )
            // thread.join(); getback to here
            System.out.println("\n\nYou can Disscuss with your classmate in the chatboot!\n\n");

            while (true) {

                String disscutionMsg = input.nextLine();
                if (!disscutionMsg.isEmpty())
                    server.broadcastDisscussion(name + " : " + disscutionMsg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
