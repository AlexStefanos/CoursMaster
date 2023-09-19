import java.net.Socket;
import java.net.SocketAddress;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {
    public static void main(String[] args) {
        try {
            InetAddress iNetAddress = InetAddress.getLocalHost();
            Socket socket = new Socket(iNetAddress, 22);
            System.out.println("hey");
            SocketAddress address = new InetSocketAddress(iNetAddress, 22);
            ServerSocketChannel serverSocket = ServerSocketChannel.open();
            ByteBuffer buffer = ByteBuffer.allocate(2023);
            serverSocket.bind(address);
            while(true) {
                serverSocket.accept();
            }
        } catch(IOException e) {
            
        }System.out.println("hey"); /*finally {
            socketChannel.close();
        }*/
    }
}