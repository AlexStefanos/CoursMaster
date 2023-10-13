package ubs.info.as;

import java.util.Scanner;

public class Launcher {

    private static void usage() {
        System.out.println("Veuillez indiquer en argument le chemin du fichier que vous souhaitez envoyer au serveur.");
        System.out.println("Veuillez indiquer dans le header du fichier si c'est un mail ou non.");
    }

    public static void main(String[] args) {
        if((args.length != 5) || (args[0].equals("-h") || (args[0].equals("--help")))) {
            usage();
        } else {
            GossipingClient gossipingClient = new GossipingClient(args);
            String[] serverArgs = new String[1];
            serverArgs[0] = args[3];
            Server.main(serverArgs);
            Scanner sc = new Scanner(System.in);
            System.out.println("Veuillez indiquer une valeur parmis les suivantes : ");
            System.out.println("    - 1 : offerMessage()");
            System.out.println("    - 2 : requestMessage()");
            System.out.println("    - 3 : deletMessage()");
            int entry = sc.nextInt();
            while((entry != 1) && (entry != 2) && (entry != 3)) {
                if(entry == 1) {
                    gossipingClient.offerMessage();
                } 
                else if(entry == 2) {
                    gossipingClient.requestMessage();
                    Client.main(serverArgs);
                }
                else if(entry == 3) {
                    System.out.println("Suppression du message");
                    gossipingClient.deleteMessage();
                }
                else {
                    System.out.println("Veuillez indiquer une valeur parmis les suivantes : ");
                    System.out.println("    - 1 : offerMessage()");
                    System.out.println("    - 2 : requestMessage()");
                    System.out.println("    - 3 : deletMessage()");
                    entry = sc.nextInt();
                }
            }
            sc.close();
        }
    }
}
