import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class AppServer implements ScribbleServer {
    private Figures figures;
    private int ctId;

    public AppServer() {
        this.figures = new Figures();
        ctId = 0;
    }

    public static void usage() {
        System.out.println("Usage : host port objName");
        System.exit(-1);
    }

    public void createFigure(Figure figure, int id) throws RemoteException {
        System.out.println("Launching createFigure(), id : " + id);
        this.figures.add(figure);
    }

    public void deleteFigure(Figure figure, int id) throws RemoteException {
        System.out.println("Launching deleteFigure(), id : " + id);
        for(int i = 0; i < figures.size(); i++) {
            if(figures.get(i).getId() == figure.getId()) {
                this.figures.remove(figures.get(i));
            }
        }
    }

    public void updateFigure(Figure figure, int id) throws RemoteException {
        System.out.println("Launching updateFigure(), id : " + id);
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

    public static void main(String[] args) {
        if(args.length == 3) {
            String host = args[0];
            int port = Integer.parseInt(args[1]);
            String objName = args[2];

            AppServer appServer = new AppServer();
            try {
                Remote stub = UnicastRemoteObject.exportObject(appServer, 0);
                Registry registry = LocateRegistry.createRegistry(port);
                registry.rebind(objName, stub);
                System.out.println(host + " bound");
            } catch(RemoteException e) {
                e.printStackTrace();
            }
        } else {
            usage();
        }
    }  
}
