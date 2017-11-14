package com.company;

//import java.util.Timer;
//import javax.swing.Timer;

public class Message {

    String name;
    String topics;
    String recipient;
    String data;
    long timeoutH;
    long start_time;
    long server_timer;
    public Message(String name,String topics,String recipient,String data,int timeoutH){
        this.name=name;
        this.topics=topics;
        this.recipient=recipient;
        this.data=data;
        this.timeoutH=timeoutH;
        long start_time = 0;
        this.server_timer=0;
    }

}
