package tp01;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Hello {
    public Server() {
        
    }

    public String sayHello() {
        return("Hello World!");
    }

    public static void main(String args[]) {
        try {
            Server obj = new Server();
            Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 3035);
            Registry registry  = LocateRegistry.getRegistry();
            registry.bind("Hello", stub);
            System.out.println("Server Ready");
        } catch(RemoteException e) {
            System.err.println(e.getMessage());
        } catch(AlreadyBoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
