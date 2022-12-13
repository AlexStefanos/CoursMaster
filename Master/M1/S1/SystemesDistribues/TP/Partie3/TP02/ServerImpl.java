package fr.ubs.scribble;

import fr.ubs.scribble.shapes.Shape;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl implements Server {
    private Figures figures;

    public void createFigure(Figure figure) throws RemoteException {
        if(figure != null && !figure.isEmpty())
            figures.add(figure);
        figure = null;
    }

    public void deleteFigure(Figure figure) throws RemoteException {
        for(Figure tmp : figures) {
            if(tmp.getId() == figure.getId())
                figures.remove(tmp);
        }
    }

    public void updateFigure(Figure figure) throws RemoteException {
        for(Figure tmp : figures) {
            if(tmp.getId() == figure.getId())
                tmp = figure;
        }
    }

    public static void main(String args[]) throws Exception {
        if(args.length != 2 || args[0] == "-h") {
            System.out.println("2 arguments sont attendus : le numéro de port et l'obj permettant de Registry.rebind");
            System.exit(-1);
        }
        try {
            int port = Integer.parseInt(args[0]);
            String str = args[1];
            FiguresCanvas figureCanvas = new FiguresCanvas();
            Remote stub = UnicastRemoteObject.exportObject(figureCanvas, 0);
            Registry registry  = LocateRegistry.createRegistry(port);
            registry.rebind(figureCanvas, stub);
            System.out.println(str + " bound");
            //on recoit ici lorsque le client sendModiFigure(...), ou sendModiFigures(...), ou receiveModiFigure(...);
        } catch(RemoteException e) {
            System.err.println(e.getMessage());
        }
    }
}