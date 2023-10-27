package ubs.info.as;

import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import fr.ubs.io.MailFile;

/**
 * TP01 Systemes Distribues : TCP File Exchange
 * @author Alexandre Stefanos
 */
public class Server {
    /**
	 * Methode de gestion de message d'erreur
	 */
    private static void usage() {
        System.out.println("Le jar Server permet d'ouvrir un serveur en localhost, de recevoir le fichier envoyer par le jar Client et de créer un nouveau fichier en fonction de ce que contient le fichier envoye et de la date d'envoi.");
        System.out.println("Le port doit être indiqué en argument.");
    }

    public static void main(String args[]) {
        FileOutputStream fis;
        int port = Integer.parseInt(args[0]);
        if(args.length == 1 && (!args[0].equals("-h")) && (!args[0].equals("--help")))
            try(ServerSocket serverSocket = new ServerSocket(port);Socket socket = serverSocket.accept();
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());) {
                int bytes = 0;
                String addr = socket.getInetAddress().getHostAddress();
                LocalTime atm = LocalTime.now();
                DateTimeFormatter format = DateTimeFormatter.ofPattern("HH.mm.ss");
                String formattedAtm = atm.format(format);
                String time = formattedAtm.toString();
                System.out.println(addr + "-" + time + ".txt");
                File file = new File(addr + "-" + time + ".txt");
                file.createNewFile();
                fis = new FileOutputStream(file);
                long size = dis.readLong();
                byte[] buffer = new byte[4096];
                while(size > 0 && (bytes = dis.read(buffer, 0, buffer.length)) != -1) {
                    fis.write(buffer, 0, bytes);
                    size -= bytes;
                }
                MailFile mailFile = new MailFile(file);
                mailFile.updateFilename();
            } catch(IOException e) {
                System.err.println(e.getMessage());
                usage();
            }
            finally {
                try {
                    fis = new FileOutputStream("close");
                    fis.close();
                } catch(IOException e) {
                    System.err.println(e.getMessage());
                    System.err.println(e.getMessage());
                    usage();
                }
            }
        else {
            usage();
        }
    }
}