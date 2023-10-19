package ubs.info.as;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.file.Path;

/**
 * TP04 Systeme Distribue : UDP multicast - beaconing and discovery
 * @author Alexandre Stefanos
 */
public class GossiperHandler extends Thread {
    private Gossiper gossiper;

    /**
     * Constructeur de la classe GossiperHandler
     * @param gossiper : Instance de la classe Gossiper
     */
    public GossiperHandler(Gossiper gossiper) {this.gossiper = gossiper;}

    /**
     * Démarre le GossiperHandler
     */
    public void run() {
        try {
            DatagramSocket socket = new DatagramSocket(gossiper.getPortUDP());
            byte[] buffer = new byte[521];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            int portUDP = (buffer[5] << 8) + buffer[6];
            gossiper.setPortUDP(portUDP);
            Path path = gossiper.getBaseDirectory();
            if(buffer[0] == 1) {
                if(!path.toFile().exists())
                    gossiper.sendOffer();
                else
                    gossiper.sendDelete();
            }
            else if(buffer[0] == 2)
                gossiper.sendRequest();
            else if(buffer[0] == 3)
                gossiper.sendDelete();
            else if(buffer[0] == 4)
                gossiper.sendAnnounce();
            else
                System.exit(0);
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
