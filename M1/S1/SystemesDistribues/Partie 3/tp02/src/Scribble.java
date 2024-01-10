import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Scribble extends Remote {
    public void notifyServer() throws RemoteException;
}