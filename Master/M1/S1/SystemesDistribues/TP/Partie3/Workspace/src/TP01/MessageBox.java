package TP01;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MessageBox extends Remote {
    String getMessage() throws RemoteException;
    String sendMessage() throws RemoteException;
    void printMessage() throws RemoteException;
}
