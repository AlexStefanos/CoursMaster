package tp01;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Date;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Server {
    ServerSocket serverSocket;

    public Server() throws IOException {
        serverSocket = new ServerSocket(5555);
        System.out.println(serverSocket.isBound());
    }

    public void acceptClientSocket() {
        try {
            serverSocket.accept();
        } catch(IOException e) {
            System.err.println(e);
        }
    }

    public void close() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void readData() throws IOException {
        FileInputStream inputStream = new FileInputStream("binData");

        inputStream.readAllBytes();
        inputStream.transferTo(new FileOutputStream("binData"));
        inputStream.close();
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0L;

        server.readData();
        System.out.println(server.serverSocket.getReceiveBufferSize());
        while(elapsedTime < 2*60) {
            server.acceptClientSocket();
            elapsedTime = (new Date()).getTime() - startTime;
        }
        server.close();
    }
}