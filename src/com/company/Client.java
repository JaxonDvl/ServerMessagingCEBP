package com.company;

import java.util.ArrayList;
import java.util.LinkedList;

public class Client extends Thread
{
    ArrayList<String> expected_topics = new ArrayList<String>();
    LinkedList<Message> created_mess = new LinkedList<Message>();
    int max_queue=100;
    LinkedList<Message> received_mess = new LinkedList<Message>();
    private Thread t;
    private String addtopic;
    private String messTopic;
    private String mess;
    private long time;
    String name;
    Server s=new Server();

    public Client(String name)
    {
        this.name=name;
    }

    void createMessage(String name,String topic,String recipient, String data, int timeout)
    {
        Message m = new Message(name,topic,recipient,data, timeout);
        created_mess.addFirst(m);
        m.start_time=System.currentTimeMillis();
        System.out.println("Client: "+name+" created message: "+data);
    }

    void sendMessage() throws InterruptedException
    {
        System.out.println("Client "+name+" sends the message "+created_mess.element().data+" to server");
        s.addReceive(created_mess.element());
        System.out.println("Client says -> to server message: "+s.getReceived().element().data);
        created_mess.element().server_timer=System.currentTimeMillis();
        System.out.println("Client says -> Message was sent");
    }

    synchronized void receiveMessage()
    {
        received_mess.remove();
    }

    synchronized void deleteExpired()
    {
        for (int i=0; i<received_mess.size(); i++)
        {
            long current = System.currentTimeMillis();
            long initial = received_mess.get(i).start_time;
            if(current-initial>=received_mess.get(i).timeoutH)
            { // delete message
                received_mess.remove(i);
            }
        }
    }

    synchronized void addTopics(String t)
    {
        expected_topics.add(t);
        System.out.println("Client says -> Added topic: "+ t);
    }
    void printReceivedMess()
    {
        for(Message elem : received_mess)
        {
            System.out.println(elem.data +"  ");
        }
        System.out.println(".......................................");
    }

    public void run() {
        synchronized(this) {
        }
    }

    public void start () {
        System.out.println("Starting client thread" );
        if (t == null) {
            t = new Thread (this);
            t.start ();
        }
    }
}

