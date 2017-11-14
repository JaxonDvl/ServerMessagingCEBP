package com.company;

import java.util.concurrent.Semaphore;

public class ServerThreadDemo extends Thread{
    private Semaphore semaphore = new Semaphore(5);
    //Client c;
    private Server s;
    private Thread t;
    private Client t1, t2, t3, t4, t5, t6, t7, t8;

    ServerThreadDemo(Client ta, Client tb, Client tc, Client td, Client te, Client tf, Client tg, Client th)
    {
        t1=ta;
        t2=tb;
        t3=tc;
        t4=td;
        t5=te;
        t6=tf;
        t7=tg;
        t8=th;
    }

    public void run() {
        try {
            semaphore.acquire();
            System.out.println("????? "+ t1.getName()+" starts");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ServerThreadDemo -> inside semaphore");
        t1.createMessage("client1","java","null", "ana are mere", 1000);
        try {
            t1.sendMessage();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("!!!!!!!!!!!!!!!!!Message queue is FULL");
        }
        t1.createMessage("client1","null","client4", "how are you?", 800);
        try {
            t1.sendMessage();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("!!!!!!!!!!!!!!!!!Message queue is FULL");
        }
        t1.printReceivedMess();
        try {
        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("!!!!!!!!!!!!!!!!!Message queue is empty");
        }
        System.out.println("????? "+ t1.getName()+ " ends");
        t1.deleteExpired();
        semaphore.release();
        try {
            semaphore.acquire();
            System.out.println("///// "+ t2.getName()+" starts");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.createMessage("client2","c","null", "no", 1200);
        try {
            t2.sendMessage();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("!!!!!!!!!!!!!!!!!Message queue is FULL");
        }
        t2.createMessage("client2","SystemVerilog","null", "Lab in B513", 1212);
        try {
            t2.sendMessage();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("!!!!!!!!!!!!!!!!!Message queue is FULL");
        }
        t2.printReceivedMess();
        System.out.println("////// "+ t2.getName()+ " ends");
        t1.deleteExpired();
        t2.deleteExpired();
        semaphore.release();
        try {
            semaphore.acquire();
            System.out.println("^^^^^^^ "+ t3.getName()+" starts");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t3.createMessage("client3","null","client1", "hi", 1400);
        try {
            t3.sendMessage();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("!!!!!!!!!!!!!!!!!Message queue is FULL");
        }
        t3.createMessage("client3","java","null", "good morning!", 600);
        try {
            t3.sendMessage();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("!!!!!!!!!!!!!!!!!Message queue is FULL");
        }
        t3.printReceivedMess();
        System.out.println("^^^^^^^ "+ t3.getName()+ " ends");
        t1.deleteExpired();
        t2.deleteExpired();
        t3.deleteExpired();
        semaphore.release();
        try {
            semaphore.acquire();
            System.out.println("&&&&&&& "+ t4.getName()+" starts");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        t4.createMessage("client4","null","client3", "hello from client4", 900);
        try {
            t4.sendMessage();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("!!!!!!!!!!!!!!!!!Message queue is FULL");
        }
        t4.createMessage("client4","null","client6", "Call me back when you can.", 1400);
        try {
            t4.sendMessage();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("!!!!!!!!!!!!!!!!!Message queue is FULL");
        }
        t4.printReceivedMess();
        System.out.println("&&&&&&& "+ t4.getName()+ " ends");
        t1.deleteExpired();
        t2.deleteExpired();
        t3.deleteExpired();
        t4.deleteExpired();
        semaphore.release();
        try {
            semaphore.acquire();
            System.out.println("####### "+ t5.getName()+" starts");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t5.createMessage("client5","c","null", "email about c course", 700);
        try {
            t5.sendMessage();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("!!!!!!!!!!!!!!!!!Message queue is FULL");
        }
        t5.createMessage("client5","null","client8", "Are you in town?", 1200);
        try {
            t5.sendMessage();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("!!!!!!!!!!!!!!!!!Message queue is FULL");
        }
        t5.printReceivedMess();
        System.out.println("####### "+ t5.getName()+ " ends");
        t1.deleteExpired();
        t2.deleteExpired();
        t3.deleteExpired();
        t4.deleteExpired();
        t5.deleteExpired();
        semaphore.release();
        try {
            semaphore.acquire();
            System.out.println("```````` "+ t6.getName()+" starts");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t6.createMessage("client6","c++","null", "info about c++", 1700);
        try {
            t6.sendMessage();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("!!!!!!!!!!!!!!!!!Message queue is FULL");
        }
        t6.createMessage("client6","java","null", "info about java", 1750);
        try {
            t6.sendMessage();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("!!!!!!!!!!!!!!!!!Message queue is FULL");
        }
        t6.createMessage("client6","null","client6", "reminder: meeting at 2 PM", 1750);
        try {
            t6.sendMessage();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("!!!!!!!!!!!!!!!!!Message queue is FULL");
        }
        t6.printReceivedMess();
        System.out.println("```````` "+ t6.getName()+ " ends");
        t1.deleteExpired();
        t2.deleteExpired();
        t3.deleteExpired();
        t4.deleteExpired();
        t5.deleteExpired();
        t6.deleteExpired();
        semaphore.release();
        try {
            semaphore.acquire();
            System.out.println("%%%%%% "+ t7.getName()+" starts");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        t7.createMessage("client7","info","null", "info course starts at 4 pm", 720);
        try {
            t7.sendMessage();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("!!!!!!!!!!!!!!!!!Message queue is FULL");
        }
        t7.createMessage("client7","c","null", "delete emails today ", 720);
        try {
            t7.sendMessage();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("!!!!!!!!!!!!!!!!!Message queue is FULL");
        }
        t7.printReceivedMess();
        System.out.println("%%%%%% "+ t7.getName()+ " ends");
        t1.deleteExpired();
        t2.deleteExpired();
        t3.deleteExpired();
        t4.deleteExpired();
        t5.deleteExpired();
        t6.deleteExpired();
        t7.deleteExpired();
        semaphore.release();
        try {
            semaphore.acquire();
            System.out.println("@@@@@ "+ t8.getName()+" starts");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        t8.createMessage("client8","null","client7", "cancel skype call", 780);
        try {
            t8.sendMessage();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("!!!!!!!!!!!!!!!!!Message queue is FULL");
        }
        t8.printReceivedMess();
        System.out.println("@@@@@ "+ t8.getName()+ " ends");
        t1.deleteExpired();
        t2.deleteExpired();
        t3.deleteExpired();
        t4.deleteExpired();
        t5.deleteExpired();
        t6.deleteExpired();
        t7.deleteExpired();
        t8.deleteExpired();
        semaphore.release();

        System.out.println(t1.name+ " received the following messages: ");
        t1.printReceivedMess();
        System.out.println(t2.name+ " received the following messages: ");
        t2.printReceivedMess();
        System.out.println(t3.name+ " received the following messages: ");
        t3.printReceivedMess();
        System.out.println(t4.name+ " received the following messages: ");
        t4.printReceivedMess();
        System.out.println(t5.name+ " received the following messages: ");
        t5.printReceivedMess();
        System.out.println(t6.name+ " received the following messages: ");
        t6.printReceivedMess();
        System.out.println(t7.name+ " received the following messages: ");
        t7.printReceivedMess();
        System.out.println(t8.name+ " received the following messages: ");
        t8.printReceivedMess();
    }

    public void start () {
        System.out.println("ServerThreadDemo -> Starting server thread" );
        if (t == null) {
            t = new Thread(this);
            t.start ();
        }
    }
}
