package ubs.info.as;

import java.net.Socket;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;

public class Client {
    private static void usage() {
        System.out.println("Le jar Client permet de se connecter et d'envoyer un fichier au Server");
        System.out.println("Veuillez indiquer en argument l'adresse de l'hôte, le port et le chemin du fichier que vous souhaitez envoyer au serveur");
        System.out.println("Exemple : java -jar Client.jar localhost 2222 ./input.txt");
        System.out.println("Veuillez indiquer dans le header du fichier si c'est un mail ou non.");
    }

    public static void main(String[] args) {
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        String filePath = args[2];
        Socket socket;
        FileInputStream fis;
        DataOutputStream dos;
        if((args.length != 3) || (args[0].equals("-h")))
            usage();
        else {
            try {
                InetAddress address = InetAddress.getByName(host);
                socket = new Socket(address.getHostName(), port);
                File file = new File(filePath);
                System.out.println("Envoi du contenu du fichier : " + filePath);
                fis = new FileInputStream(file);
                dos = new DataOutputStream(socket.getOutputStream());
                dos.writeLong(file.length());
                byte[] buffer = new byte[4096];
                int bytes = 0;
                while((bytes = fis.read(buffer)) != -1) {
                    dos.write(buffer, 0, bytes);
                    dos.flush();
                }
                fis.close();
                socket.close();
                dos.close();
            } catch(IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}