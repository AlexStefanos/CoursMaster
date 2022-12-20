package fr.ubs.scribble;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Client extends Remote {
    public void notifyServer() throws RemoteException;
}
