package demo;

public abstract class Message {
    private String message;
    private MessageType messageType;

    public Message(MessageType messageType, String message){
        this.messageType = messageType;
        this.message = message;
    }
    public  String getMessage(){
        return  message;
    };
    public MessageType getType() {
        return messageType;
    }

}
