import java.rmi.RemoteException;

public class MessageImpl implements Message {
    private String sender;
    private String message;

    public MessageImpl(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    public void printMessage() throws RemoteException {
        System.out.println(sender + ": " + message);
    }
}
