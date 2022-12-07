package TP01;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    public static void main(String args[]) {
        if(args.length != 2 || args[0] == "-h") {
            System.out.println("2 arguments sont attendus : le numéro de port et l'obj permettant de Registry.rebind");
            System.exit(-1);
        }
        try {
            int port = Integer.parseInt(args[0]);
            String box = args[1];
            MessageBox msgBox = new MessageBoxImpl("hey", "Alex");
            Remote stub = UnicastRemoteObject.exportObject(msgBox, 0);
            Registry registry  = LocateRegistry.createRegistry(port);
            registry.rebind(box, stub);
            System.out.println(box + " bound");
        } catch(RemoteException e) {
            System.err.println(e.getMessage());
        }
    }
}
