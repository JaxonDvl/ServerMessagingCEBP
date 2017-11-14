package demo;

public class PrivateMessage extends Message {
    private Client destination;

    public PrivateMessage(Client destination, MessageType messageType, String message) {
        super(messageType, message);
        this.destination = destination;
    }
}
