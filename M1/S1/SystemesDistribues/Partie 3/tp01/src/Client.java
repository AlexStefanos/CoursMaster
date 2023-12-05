import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void usage() {
        System.out.println("marche po");
    }

    public static void main(String[] args) throws Exception {
        if(args.length == 3) {
            String host = args[0];
            int port = Integer.parseInt(args[1]);
            String objName = args[2];
            
            Registry registry = LocateRegistry.getRegistry(host, port);
            Message msg = (Message)registry.lookup(objName);

            msg.printMessage();
        } else {
            usage();
        }
    }
}
