package tp04;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class User {
    SocketChannel socket;

    public void run() {
        try {
            socket = new DatagramSocket(5556, InetAddress.getByName("localhost"));
            socket.setBroadcast(true);
            while(true) {
                byte[] receiveBuffer = new byte[10000];
                DatagramPacket receiver = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receiver);
                String message = new String(packet.getData()).trim();
            }
        } catch(IOException e) {
            System.err.println("Erreur run() User.java");
        }
    }

    public static void main(String args[]) {
        System.out.println("Exécution du run()");
        run();
    }
}