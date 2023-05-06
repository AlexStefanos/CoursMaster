package tp01;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * TP01 Systeme Distribue : TCP File Exchange
 * @author Alexandre Stefanos
 * Classe Server
 */
public class Server {
    ServerSocket serverSocket;
    Socket socket;
    int port;
    String directoryName;
    Path path;
    InetAddress address;
    File file;
    FileInputStream fIS;
    BufferedInputStream bIS;
    OutputStream oS;

    /**
     * Constructeur de la classe Server
     * @param port : port du serveur
     * @param directoryName : nom du dossier de données
     */
    public Server(int port, String directoryName) {
        this.port = port;
        this.directoryName = directoryName;
    }

    /**
     * Démarre le serveur
     */
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            address = InetAddress.getByName("localhost");
            path = Paths.get("./" + directoryName);
            Files.createDirectory(path);
            file = new File("./" + directoryName + File.separator +  "addr-time");
            file.createNewFile();
            fIS = new FileInputStream(file);
            bIS = new BufferedInputStream(fIS);
            oS = socket.getOutputStream();
            byte[] contents;
            long fileLength = file.length();
            long current = 0;
            long start = System.nanoTime();
            while(current != fileLength) {
                int size = 100000;
                if(fileLength - current >= size)
                    current += size;
                else {
                    size = (int)(fileLength - current);
                    current = fileLength;
                }
                contents = new byte[size];
                bIS.read(contents, 0, size);
                oS.write(contents);
            }
            oS.flush();
            socket.close();
            serverSocket.close();
        }
        catch(IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Main de la classe Server
     * @param args : arguments du main
     */
    public static void main(String[] args) {
        if(args.length != 2) {
            System.out.println("Error args");
            System.exit(-1);
        }
        if(args[0].equals("-h") || args[0].equals("--help")) {
            System.out.println("Usage : port, directoryName");
            System.exit(-1);
        }
        Server server = new Server(Integer.parseInt(args[0]), args[1]);
        while(true) {
            server.run();
        }
    }
}