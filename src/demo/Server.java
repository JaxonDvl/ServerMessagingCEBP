package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Server {
    private static Server ourInstance = new Server();
    private List<Client> clientList = new ArrayList<Client>();
    private BlockingQueue<Message> privateQueue = new LinkedBlockingQueue<Message>();
    private List<Message> publicTopics = new ArrayList<Message>();


    public static Server getInstance() {
        return ourInstance;
    }

    private Server() {
    }

    public void start(){
        // TODO should start listening for incoming clients
    }
    public void accept(Client client) throws ConnectionException {
        System.out.println(clientList);
        if(clientList.size()<5){
            clientList.add(client);
        } else {
            throw new ConnectionException();
        }
    }
    public void ejectClient(Client client) throws ClientNotFound{
        if(clientList.contains(client)){
            clientList.remove(client);
        } else {
            throw new ClientNotFound();
        }

    }

    public void enQueue(Message msg) {
        privateQueue.add(msg);
    }
    public void addTopic(Message msg) {
        publicTopics.add(msg);
    }
    public void getAllMessages(){
        for(Message msg: privateQueue){
            System.out.println(msg.getMessage());
        }
        for(Message msg: publicTopics){
            System.out.println(msg.getMessage());
        }



    }
}
