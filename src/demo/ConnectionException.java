package demo;

public class ConnectionException extends Exception {
    ConnectionException(){
        super("Connection refused");
    }
}
