package com.company;

import java.util.ArrayList;
import java.util.LinkedList;

public class Server {
    static public LinkedList<Message> received = new LinkedList<Message>();
    static long life=1300;
    static public ArrayList<Client> clients = new ArrayList<Client>();
    static int max_clients=100;
    static int max_q=2000;
    static private ArrayList<Message> stored_messages = new ArrayList<Message>();
    static int nr_clients=0;

    public void sendMessage(Message m) throws InterruptedException
    {
        if(m.topics!="null")
        {
            System.out.println("Message has topic: "+m.topics+"  ");
            for (int i=0; i<clients.size(); i++)
            {
                for(int j=0; j<clients.get(i).expected_topics.size(); j++)
                {
                    System.out.println("Server says -> sendMessage second for "+(clients.get(i)).expected_topics.get(j));
                    if(((clients.get(i)).expected_topics.get(j)).equals(m.topics))
                    {
                        System.out.println("Server says -> server found the client/clients to send the message");
                        clients.get(i).received_mess.addFirst(m);
                        stored_messages.add(m);
                        System.out.println("----------------");
                        System.out.println("Server says -> sendMessage stored_messages:");
                        for(Message elem : stored_messages)
                        {
                            System.out.println(elem.data +"  ");
                        }
                        System.out.println("----------------");
                    }
                }
            }
        }

        else
        {
            for (int i=0; i<clients.size(); i++)
            {
                if(((clients.get(i)).name).equals(m.recipient))
                {
                    System.out.println("Server says -> server found the client/clients to send the message");
                    clients.get(i).received_mess.addFirst(m);
                    stored_messages.add(m);
                    System.out.println("----------------");
                    System.out.println("Server says -> sendMessage stored_messages:");
                    for(Message elem : stored_messages)
                    {
                        System.out.println(elem.data +"  ");
                    }
                    System.out.println("----------------");
                }
            }
        }
    }

    synchronized void deleteExpired()
    {
        for (int i=0; i<stored_messages.size(); i++)
        {
            long current = System.currentTimeMillis();
            long initial = stored_messages.get(i).server_timer;
            if(current-initial>=life)
            { // delete message
                stored_messages.remove(i);
            }
        }
    }

    synchronized void addClients(Client c)
    {
        if(clients.add(c)==true)
        {
            System.out.println("Server says -> the client was added");
        }
        nr_clients = clients.size();
        System.out.println("Server says -> from addClients, nr clients "+nr_clients);
    }


    synchronized public void addReceive(Message u) throws InterruptedException{
        received.addFirst(u);
        sendMessage(received.element());
    }

    public LinkedList<Message> getReceived() {
        return received;
    }
    public void setReceived(LinkedList<Message> received) {
        this.received = received;
    }

    public ArrayList<Message> getStored_messages() {
        return stored_messages;
    }
    public void setStored_messages(ArrayList<Message> stored_messages) {
        this.stored_messages = stored_messages;
    }

}

