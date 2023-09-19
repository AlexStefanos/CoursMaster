package fr.ubs.scribble;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ServerImpl implements Server {
    private Figures figures;
    private Client client;
    private int ctId;

    public ServerImpl() {
        this.figures = new Figures();
        ctId = 0;
    }

    public void createFigure(Figure figure, int id) throws RemoteException {
        System.out.println("createFigure(), id : " + id);
        this.figures.add(figure);
    }

    public void deleteFigure(Figure figure, int id) throws RemoteException {
        System.out.println("deleteFigure(), id : " + id);
        for(int i = 0; i < figures.size(); i++) {
            if(figures.get(i).getId() == figure.getId()) {
                this.figures.remove(figures.get(i));
            }
        }
    }

    public void updateFigure(Figure figure, int id) throws RemoteException {
        System.out.println("updateFigure(), id : " + id);
        for(int i = 0; i < figures.size(); i++) {
            if(figures.get(i).getId() == figure.getId()) {
                this.figures.remove(figures.get(i));
                this.figures.add(figure);
            }
        }
    }

    public ArrayList<Figure> sendUpdates() throws RemoteException {
        return(this.figures);
    }

    public int getId() throws RemoteException {
        return(ctId++);
    }

    public static void main(String args[]) throws Exception {
        if(args.length > 1) {
            System.out.println("2 arguments sont attendus : le numéro de port et l'obj permettant de Registry.rebind");
            System.exit(-1);
        }
        try {
            int port = Integer.parseInt(args[0]);
            ServerImpl serverImpl = new ServerImpl();
            Remote stub = UnicastRemoteObject.exportObject(serverImpl, 0);
            Registry registry = LocateRegistry.createRegistry(port);
            registry.rebind("Server", stub);
            System.out.println("Server" + " bound");
        } catch(RemoteException e) {
            System.err.println(e.getMessage());
        }
    }
}