import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public static void usage() {
        System.out.println("marche po");
    }
    public static void main(String[] args) {
        if(args.length == 3) {
            String host = args[0];
            int port = Integer.parseInt(args[1]);
            String objName = args[2];

            ScribbleImpl scribbleImpl = new ScribbleImpl("Alex", "hey");
            try {
                Remote stub = UnicastRemoteObject.exportObject(scribbleImpl, 0);
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
