import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void usage() {
        System.out.println("marche po");
    }

    public static void main(String[] args) {
        if(args.length == 3) {
            String host = args[0];
            int port = Integer.parseInt(args[1]);
            String objName = args[2];
            
            try {
                Registry registry = LocateRegistry.getRegistry(host, port);
                Scribble scribbleImpl = (Scribble)registry.lookup(objName);

                scribbleImpl.printMessage();
            } catch(RemoteException e) {
                e.printStackTrace();
            } catch(NotBoundException e) {
                e.printStackTrace();
            }
        } else {
            usage();
        }   
    }
}
