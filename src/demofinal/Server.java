package demofinal;


import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static ServerSocket serverSocket = null;
    private static Socket clientSocket = null;

    private static final int maxClientsCount = 10;
    private static final ArrayList<Client> clientList = new ArrayList<Client>(maxClientsCount);

    public static void main(String args[]) {

        int portNumber = 2222;

        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            System.out.println(e);
        }

        while (true) {
            try {
                clientSocket = serverSocket.accept();
                Client client = new Client(clientSocket, clientList);
                client.start();
                clientList.add(client);
                System.out.println("Client added");
                System.out.println("Now size is "+clientList.size());

                if (clientList.size() == maxClientsCount + 1) {
                    PrintStream os = new PrintStream(clientSocket.getOutputStream());
                    os.println("Maximum connection limit reached.");
                    os.close();
                    clientSocket.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
