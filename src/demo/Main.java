package demo;

public class Main {
    public static void main(String[] args){
        Server server = Server.getInstance();
        Client c1 = new Client("1");
        Client c2 = new Client("2");
//        Client c3 = new Client("3");
//        Client c4 = new Client("4");
//        Client c5 = new Client("5");
//        Client c6 = new Client("6");
//        c1.connect();
//        c2.connect();
//        c3.connect();
//        c4.connect();
//        c5.connect();
//        c4.disconnect();
//        c4.disconnect();

        c1.sendMessage(c2,MessageType.INFO, "messaj");
        c1.publishTopic(MessageType.DEBUG, "public messaj");
        server.getAllMessages();
//        while(true){
//            c1.connect(server);
//            c1.disconnect();
//        }
    }
}
