package tp03_tp04;

import tp03_tp04.Gossiper;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.file.Path;

/**
 * TP03 et TP04 Systeme Distribue : UDP - gossiping, UDP multicast - beaconing and discovery
 * @author Alexandre Stefanos
 */
public class GossiperHandler extends Thread {
    private Gossiper gossiper;

    /**
     * Constructeur de la classe GossiperHandler
     * @param gossiper : Instance de la classe Gossiper
     */
    public GossiperHandler(Gossiper gossiper) {this.gossiper = gossiper; }

    /**
     * Démarre le GossiperHandler
     */
    public void run() {
        try {
            DatagramSocket socket = new DatagramSocket(gossiper.getUdpPort());
            byte[] buffer = new byte[521];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            InetAddress ip = InetAddress.getByAddress(new byte[]{
                    buffer[1],
                    buffer[2],
                    buffer[3],
                    buffer[4]});
            int port = (buffer[5] << 8) + buffer[6];
            String user = gossiper.getUser();
            Path path = gossiper.getBaseDirectory();
            if(buffer[0] == 1) {
                if(!path.toFile().exists())
                    gossiper.sendOffer(ip, port, gossiper.getUser());
                else
                    gossiper.sendDelete(ip, port, gossiper.getUser());
            }
            else if(buffer[0] == 2)
                gossiper.sendRequest(ip, port, gossiper.getUser());
            else if(buffer[0] == 3)
                gossiper.sendDelete(ip, port, gossiper.getUser());
            else if(buffer[0] == 4)
                gossiper.sendAnnounce(ip, port, gossiper.getUser());
            else
                System.exit(0);
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
