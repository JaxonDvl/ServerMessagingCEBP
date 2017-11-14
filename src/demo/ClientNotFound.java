package demo;

public class ClientNotFound extends Exception {
    ClientNotFound(){
        super("Client not found for disconnection");
    }
}
