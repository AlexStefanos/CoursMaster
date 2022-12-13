package fr.ubs.scribble;

import fr.ubs.scribble.shapes.Shape;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Client extends Remote {
    public void sendModiFigure(Figure figure, int operation) throws RemoteException;
    public void sendModiFigures(Figure figures) throws RemoteException;
    public void receiveModiFigure(Figure figure) throws RemoteException;
}
