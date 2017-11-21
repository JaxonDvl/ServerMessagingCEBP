package demofinal;


import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Client extends Thread {

    private String clientName = null;
    private BufferedReader inputStream = null;
    private PrintStream outputStream = null;

    private Socket clientSocket = null;
    private final ArrayList<Client> clientList;
    private int maxClientsCount;
    private BlockingQueue<PrivateMessage> privateMessage = new ArrayBlockingQueue<PrivateMessage>(5);
    private static List<PublicMessage> publicMessage = new ArrayList<PublicMessage> (10);
    public Client(Socket clientSocket, ArrayList<Client> clientList) {
        this.clientSocket = clientSocket;
        this.clientList = clientList;
        maxClientsCount = clientList.size();
    }
    public synchronized void sendMessage(String sender, String receiver,String message){
        PrivateMessage msg = new PrivateMessage(sender, receiver, MessageType.INFO , message);
        this.privateMessage.offer(msg);
    }
    private void publish(String message) {
        PublicMessage msg = new PublicMessage(MessageType.INFO, message);
        publicMessage.add(msg);
    }
    public void getMessage() {
        PrivateMessage msg = null;
        try {
            msg = this.privateMessage.poll(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(msg != null){
            for (Client client : clientList) {
                if (client != this && client.clientName != null && client.clientName.equals(msg.getReceiver())) {
                    client.outputStream.println("<" + msg.getSender() + "> " + msg.getMessage());
                    break;
                }
            }
        }
    }


    public void run() {
        ArrayList<Client> clientThreads = this.clientList;
        try {
            inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outputStream = new PrintStream(clientSocket.getOutputStream());
            String name;
            while (true) {
                outputStream.println("Name: ");
                name = inputStream.readLine().trim();
                if (name.indexOf('@') == -1) {
                    break;
                } else {
                    outputStream.println("The name should not contain '@' character.");
                }
            }

            outputStream.println("Hy " + name
                    + " \nTo leave enter /exit");

            synchronized (this) {
                for (Client client : clientThreads) {
                    if (client == this) {
                        clientName = "@" + name;
                        break;
                    }
                }
                for (Client client : clientThreads) {
                    if (client != this) {
                        client.outputStream.println("New user in town " + name);

                    }
                }
            }
      /* Start the conversation. */
            while (true) {
                String line = inputStream.readLine();
                if (line.startsWith("/exit")) {
                    break;
                }
        /* If the message is private sent it to the given client. */
                if (line.startsWith("@")) {
                    String[] words = line.split("\\s", 2);
                    if (words.length > 1 && words[1] != null) {
                        words[1] = words[1].trim();
                        if (!words[1].isEmpty()) {
                            sendMessage(this.clientName,words[0],words[1]);
                            getMessage();
                        }
                    }
                } else if(line.startsWith("/publish")) {
          /* The message is public, broadcast it to all other clients. */
                    String[] words = line.split("=", 2);
                    synchronized (this) {
                        publish(words[1]);
                    }
                } else if(line.startsWith("/read")) {
                    System.out.println("called from"+this.clientName);
                    synchronized (this) {
                        for(PublicMessage msg : publicMessage) {
                            this.outputStream.println(msg.getMessage());
                        }
                    }
                }
            }
            synchronized (this) {
                for (Client client : clientThreads) {
                    if (client != this && client.clientName != null) {
                        client.outputStream.println("User  =>" + name
                                + " is offline");

                    }
                }
            }
            outputStream.println("=> Logged out " + name);

      /*
       * Clean up. Set the current thread variable to null so that a new client
       * could be accepted by the server.
       */
            synchronized (this) {
                for (Client client : clientThreads) {
                    if (client == this) {
                        clientThreads.remove(this);
                    }
                }
            }
      /*
       * Close the output stream, close the input stream, close the socket.
       */
            inputStream.close();
            outputStream.close();
            clientSocket.close();
        } catch (IOException e) {
        }
    }

}
