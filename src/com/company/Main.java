package com.company;

public class Main {
    static int i;
    static double x;

    public static void main(String[] args) throws InterruptedException
    {

        Client c1 = new Client("client1");
        Client c2 = new Client("client2");
        Client c3 = new Client("client3");
        Client c4 = new Client("client4");
        Client c5 = new Client("client5");
        Client c6 = new Client("client6");
        Client c7 = new Client("client7");
        Client c8 = new Client("client8");
        Server s = new Server();

        ServerThreadDemo st= new ServerThreadDemo(c1, c2, c3, c4, c5, c6, c7, c8);

        c1.start();
        c2.start();
        c3.start();
        c4.start();
        c5.start();
        c6.start();
        c7.start();
        c8.start();
        st.start();
        c1.addTopics("c");
        c2.addTopics("java");
        c3.addTopics("c");
        c5.addTopics("c++");
        c5.addTopics("info");
        c8.addTopics("SystemVerilog");
        s.addClients(c1);
        s.addClients(c2);
        s.addClients(c3);
        s.addClients(c4);
        s.addClients(c5);
        s.addClients(c6);
        s.addClients(c7);
        s.addClients(c8);
        s.deleteExpired();

        try {
            c1.join();
            c2.join();
            c3.join();
            c4.join();
            c5.join();
            c6.join();
            c7.join();
            c8.join();
            st.join();
        }catch( Exception e) {
            System.out.println("Interrupted");
        }
    }
}