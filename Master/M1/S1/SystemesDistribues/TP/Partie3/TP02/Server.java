package fr.ubs.scribble;

import fr.ubs.scribble.shapes.Shape;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Server extends Remote {
    public void createFigure(Figure figure) throws RemoteException;
    public void deleteFigure(Figure figure) throws RemoteException;
    public void updateFigure(Figure figure) throws RemoteException;
}
