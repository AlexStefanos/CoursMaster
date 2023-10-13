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
//import fr.ubs.io.MailFile;

public class Server {
    public static void main(String args[]) {
        if(args.length == 0)
            try(ServerSocket serverSocket = new ServerSocket(2222)) {
                Socket socket = serverSocket.accept();
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                int bytes = 0;
                String addr = socket.getInetAddress().getHostAddress();
                LocalTime atm = LocalTime.now();
                DateTimeFormatter format = DateTimeFormatter.ofPattern("HH.mm.ss");
                String formattedAtm = atm.format(format);
                String time = formattedAtm.toString();
                System.out.println(addr + "-" + time + ".txt");
                File file = new File(addr + "-" + time + ".txt");
                file.createNewFile();
                FileOutputStream fis = new FileOutputStream(file);
                long size = dis.readLong();
                byte[] buffer = new byte[4096];
                while(size > 0 && (bytes = dis.read(buffer, 0, buffer.length)) != -1) {
                    fis.write(buffer, 0, bytes);
                    size -= bytes;
                }
                // MailFile mailFile = new MailFile(file);
                // if(addr == mailFile.getMessageId())
                //     mailFile.updateFilename();       Je n'ai toujours pas compris comment utiliser la classe MailFile
                fis.close();
                dis.close();
                dos.close();
                socket.close();
            } catch(IOException e) {
                System.err.println(e.getMessage());
            }
        else if (args[0].equals("-h")) {
            System.out.println("Server.java permet d'ouvrir le serveur, de recevoir le fichier envoyer par le Client et de créer un nouveau fichier en fonction de ce que contient ce fichier et de la date d'envoi.");
        }
        else {
            System.out.println("Server.java permet d'ouvrir le serveur, de recevoir le fichier envoyer par le Client et de créer un nouveau fichier en fonction de ce que contient ce fichier et de la date d'envoi.");
            System.out.println("Aucun argument n'est necessaire.");
        }
    }
}