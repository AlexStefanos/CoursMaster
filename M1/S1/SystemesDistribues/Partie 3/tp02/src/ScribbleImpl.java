import java.rmi.RemoteException;

public class ScribbleImpl implements Scribble {
    private String sender;
    private String message;

    public ScribbleImpl(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    public void printMessage() throws RemoteException {
        System.out.println(sender + ": " + message);
    }
}
