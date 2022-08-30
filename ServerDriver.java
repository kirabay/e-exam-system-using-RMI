import static java.rmi.Naming.rebind;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ServerDriver {
    private static Scanner input;
    private static Scanner inputQuestion;
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        input = new Scanner(System.in);
        inputQuestion = new Scanner(System.in);
        System.out.print("Enter the number of questions :");
        int numQ = input.nextInt();
        String questions[] = new String[numQ];
        int answers[] = new int[numQ];
        System.out.println("");
        for (int i = 0; i <questions.length; i++){
            System.out.print("Q. "+(i+1)+" :");
            questions[i] = inputQuestion.nextLine();
            System.out.println("\nAnswer for Q. "+(i+1)+" :");
            answers[i] = input.nextInt();
        }
        rebind("RMIServer", new Server(questions, answers));
        System.out.println("\n Server Started!! ");


    }
}
