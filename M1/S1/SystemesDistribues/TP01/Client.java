import java.net.Socket;
import java.net.SocketAddress;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    public static void main(String[] args) {
        try {
            InetAddress iNetAddress = InetAddress.getLocalHost();
            Socket socket = new Socket(iNetAddress, 22);
            SocketAddress address = new InetSocketAddress(iNetAddress, 22);
            SocketChannel socketChannel = null;
            ByteBuffer buffer = ByteBuffer.allocate(2023);
            File file = new File("/home/alexandre/CoursMaster/M1/S1/SystemesDistribues/TP01/bin");
            FileOutputStream outputStream = new FileOutputStream(file);
            while(true) {
                socket.bind(address);
                socket.connect(address);
                if(socket.isConnected()) {
                    
                }
            }
        } catch(IOException e) {

        } /*finally {
            socket.close();
            socketChannel.close();
            
        }*/
    }
}