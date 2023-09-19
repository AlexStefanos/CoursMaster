package tp01;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * TP01 Systeme Distribue : TCP File Exchange
 * @author Alexandre Stefanos
 * Classe Client
 */
public class Client {
    Socket socket;
    FileOutputStream fOS;
    private String hostName, fileName;
    private int port;
    BufferedOutputStream bOS;
    InputStream iS;

    /**
     * Constructeur de la classe Client
     * @param hostName
     * @param port
     * @param fileName
     */
    public Client(String hostName, int port, String fileName) {
        this.hostName = hostName;
        this.port = port;
        this.fileName = fileName;
    }

    /**
     * Démarre la connexion Client
     */
    public void run() {
        try {
            socket = new Socket(InetAddress.getByName(hostName), port);
            byte[] contents = new byte[10000];
            fOS = new FileOutputStream(fileName);
            bOS = new BufferedOutputStream(fOS);
            iS = socket.getInputStream();
            int bytesRead = 0;
            while((bytesRead = iS.read(contents)) != -1)
                bOS.write(contents, 0, bytesRead);
            bOS.flush();
            socket.close();
        }
        catch(IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Main de la classe Client
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        if(args.length != 3) {
            System.out.println("Error args");
            System.exit(-1);
        }
        if(args[0].equals("-h") || args[0].equals("--help")) {
            System.out.println("Usage : hostName, port, fileName");
            System.exit(-1);
        }
        Client client = new Client(args[0], Integer.parseInt(args[1]), args[2]);
        client.run();
    }
}