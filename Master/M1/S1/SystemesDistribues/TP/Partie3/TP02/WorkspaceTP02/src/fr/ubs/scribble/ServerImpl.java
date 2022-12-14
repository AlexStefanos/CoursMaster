package fr.ubs.scribble;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl implements Server {
    private Figures figures;

    public void createFigure(Figure figure) throws RemoteException {
        System.out.println("createFigure");
    }

    public void deleteFigure(Figure figure) throws RemoteException {
        System.out.println("deleteFigure");
    }

    public void updateFigure(Figure figure) throws RemoteException {
        System.out.println("updateFigure");
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
            Registry registry  = LocateRegistry.createRegistry(port);
            registry.rebind("Server", stub);
            System.out.println("Server" + " bound");
        } catch(RemoteException e) {
            System.err.println(e.getMessage());
        }
    }
}