package TP01;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        if(args.length != 3 || args[0] == "-h") {
            System.out.println("3 arguments sont attendus : l'host, le numéro de port et l'obj permettant de Registry.rebind");
            System.exit(-1);
        }
        String host = args[0];
        try {
            int port = Integer.parseInt(args[1]);
            String box = args[2];
            Registry registry = LocateRegistry.getRegistry(host, port);
            MessageBox msgBox = (MessageBox)registry.lookup(box);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Voulez-vous envoyer un message (tapez 1) ou recevoir le dernier message (2) ?");
            int tmp = scanner.nextInt();
            while((tmp != 0) && (tmp != 1))
                tmp = scanner.nextInt();
            if(tmp == 0)
                msgBox.sendMessage();
            else
                msgBox.getMessage();
            msgBox.printMessage();
        } catch(RemoteException e) {
            System.err.println(e.getMessage());
        } catch(NotBoundException e) {
            System.err.println(e.getMessage());
        }
    }
}