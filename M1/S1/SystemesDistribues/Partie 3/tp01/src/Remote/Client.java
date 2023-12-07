package Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void usage() {
        System.out.println("Il manque des arguments. Il faut 3 arguments : host port sender");
    }

    public static void main(String[] args) throws Exception {
        if(args.length == 3) {
            String host = args[0];
            int port = Integer.parseInt(args[1]);
            String sender = args[2];
            
            Registry registry = LocateRegistry.getRegistry(host, port);
            Message msg = (Message)registry.lookup(sender);

            msg.printMessage();
        } else {
            usage();
        }
    }
}
