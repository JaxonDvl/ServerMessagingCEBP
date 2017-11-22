package demofinal;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientConnection implements Runnable{
    private static Socket clientSocket = null;
    private static PrintStream outputStream = null;
    private static BufferedReader inputStream = null;
    private static BufferedReader inputLine = null;
    private static boolean closed = false;

    public static void main(String[] args) {

        int portNumber = 2222;
        String host = "localhost";

        try {
            clientSocket = new Socket(host, portNumber);
            inputLine = new BufferedReader(new InputStreamReader(System.in));
            outputStream = new PrintStream(clientSocket.getOutputStream());
            inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Host not found " + host);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (clientSocket != null && outputStream != null && inputStream != null) {
            try {

        /* Create a thread to read from the server. */
                new Thread(new ClientConnection()).start();
                while (!closed) {
                    outputStream.println(inputLine.readLine().trim());
                }
        /*
         * Close the output stream, close the input stream, close the socket.
         */
                outputStream.close();
                inputStream.close();
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("IOException:  " + e);
            }
        }
    }
    public void run() {
        String responseLine;
        try {
            while ((responseLine = inputStream.readLine()) != null) {
                System.out.println(responseLine);
                if (responseLine.indexOf("=> Logged out") != -1)
                    break;
            }
            closed = true;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
