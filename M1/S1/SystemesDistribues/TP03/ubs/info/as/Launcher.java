package TP03.ubs.info.as;

import java.util.Scanner;

public class Launcher {

    private static void usage() {

    }

    public static void main(String[] args) {
        if((args.length != 5) || (args[0].equals("-h") || (args[0].equals("--help")))) {
            usage();
        } else {
            String host = args[0];
            String name = args[1];
            String baseDirectory = args[2];
            int portTCP = Integer.parseInt(args[3]);
            int portUDP = Integer.parseInt(args[4]);
            GossipingClient gossipingClient = new GossipingClient(host, name, baseDirectory, portTCP, portUDP);
            
            /* Server server = new Server(host, filePath, port);
            Client client = new Client(host, filePath, port); */
            Scanner sc = new Scanner(System.in);
            System.out.println("Veuillez indiquer une valeur parmis les suivantes : ");
            System.out.println("    - 1 : offerMessage()");
            System.out.println("    - 2 : requestMessage()");
            System.out.println("    - 3 : deletMessage()");
            int entry = sc.nextInt();
            while((entry != 1) && (entry != 2) && (entry != 3)) {
                if(entry == 1) {
                    System.out.println("Veuillez indiquer un port TCP pour envoyer le message. Exemple : 5000");
                    gossipingClient.offerMessage();
                } 
                else if(entry == 2) {
                    System.out.println("Veuillez indiquer un port UDP pour envoyer le message. Exemple : 5001");
                    gossipingClient.requestMessage();
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
