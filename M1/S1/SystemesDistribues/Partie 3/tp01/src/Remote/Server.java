package Remote;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public static void usage() {
        System.out.println("Il manque des arguments. Il faut 4 arguments : host port sender message");
    }

    public static void main(String[] args) {
        if(args.length == 4) {
            String host = args[0];
            int port = Integer.parseInt(args[1]);
            String sender = args[2];
            String message = args[3];

            Message msg = new MessageImpl(sender, message);
            try {
                Remote stub = UnicastRemoteObject.exportObject(msg, 0);
                Registry registry = LocateRegistry.createRegistry(port);

                registry.rebind(sender, stub);
                System.out.println(host + " bound");
            } catch(RemoteException e) {
                e.printStackTrace();
            }
        } else {
            usage();
        }
    }
}
