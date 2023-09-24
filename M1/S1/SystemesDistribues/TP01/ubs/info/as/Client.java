package ubs.info.as;

import java.net.Socket;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
//import fr.ubs.io.MailFile;

public class Client {
    public static void main(String[] args) {
        if(args.length > 0 && !args[0].equals("-h")) {
            String filePath = args[0];
            try {
                InetAddress host = InetAddress.getLocalHost();
                Socket socket = new Socket(host.getHostName(), 2222);
                File file = new File(filePath);
                System.out.println("Envoi du contenu du fichier : " + filePath);
                FileInputStream fis = new FileInputStream(file);
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
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
        else if(args[0].equals("-h")) {
            System.out.println("Veuillez indiquer en argument le chemin du fichier que vous souhaitez envoyer au serveur.");
            System.out.println("Veuillez indiquer dans le header du fichier si c'est un mail ou non.");
        }
        else {
            System.out.println("Veuillez indiquer en argument le chemin du fichier que vous souhaitez envoyer au serveur.");
            System.out.println("Veuillez indiquer dans le header du fichier si c'est un mail ou non.");
        }
    }
}