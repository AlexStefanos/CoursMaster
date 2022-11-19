package tp04;

import java.io.File;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Enumeration;

public class Server {
    ServerSocketChannel serverSocket;
    SocketChannel socket;

    public void run() {
        try {
            serverSocket = ServerSocketChannel.open();
            serverSocket.bind(new InetSocketAddress(port));
            serverSocket.configureBlocking(false);
            socket = new DatagramSocket(5556, InetAddress.getByName("localhost"));
            socket.setBroadcast(true);
            Enumeration interfaces = NetworkInterface.
        }
    }
}