package fr.ubs.scribble;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends Remote {
    public void createFigure(Figure figure, int id) throws RemoteException;
    public void deleteFigure(Figure figure, int id) throws RemoteException;
    public void updateFigure(Figure figure, int id) throws RemoteException;
    public int getId() throws RemoteException;
}
