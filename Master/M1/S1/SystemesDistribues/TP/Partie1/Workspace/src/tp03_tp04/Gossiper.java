package tp03_tp04;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.file.Path;

/**
 * TP03 et TP04 Systeme Distribue : UDP - gossiping, UDP multicast - beaconing and discovery
 * @author Alexandre Stefanos
 */
public class Gossiper {
    private String user;
    private int udpPort, tcpPort;
    private Path baseDirectory;

    /**
     * Constructeur de la classe Gossiper
     * @param user : nom de l'utilisateur
     * @param baseDirectory : chemin du dossier d'origine
     * @param udpPort : port UDP
     * @param tcpPort : port TCP
     */
    public Gossiper(String user, Path baseDirectory, int udpPort, int tcpPort) {
        this.user = user;
        this.baseDirectory = baseDirectory;
        this.udpPort = udpPort;
        this.tcpPort = tcpPort;
    }

    /**
     * Démarre le gossiper
     */
    public void run() {new Thread(new GossiperHandler(this)).start(); }

    /**
     * Envoie une offre de packet
     * @param ip : ip de l'utilisateur
     * @param udpPort : port UDP
     * @param user : nom de l'utilisateur
     */
    public void sendOffer(InetAddress ip, int udpPort, String user) {
        try {
            DatagramSocket socket = new DatagramSocket();
            byte[] buffer = new byte[521];
            buffer[0] = 1;
            buffer[1 + ip.getAddress().length] = (byte)(ip.getAddress().length);
            buffer[5] = (byte)(udpPort >> 8);
            buffer[6] = (byte)(udpPort);
            buffer[7] = (byte)user.length();
            buffer[8 + user.length()] = (byte)user.length();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, ip, udpPort);
            socket.send(packet);
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Envoie une offre de packet
     * @param ip : ip de l'utilisateur
     * @param udpPort : port UDP
     * @param user : nom de l'utilisateur
     */
    public void sendDelete(InetAddress ip, int udpPort, String user) {
        try {
            DatagramSocket socket = new DatagramSocket();
            byte[] buffer = new byte[521];
            buffer[0] = 3;
            buffer[1 + ip.getAddress().length] = (byte)(ip.getAddress().length);
            buffer[5] = (byte)(udpPort >> 8);
            buffer[6] = (byte)(udpPort);
            buffer[7] = (byte)user.length();
            buffer[8 + user.length()] = (byte)user.length();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, ip, udpPort);
            socket.send(packet);
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Envoie une offre de packet
     * @param ip : ip de l'utilisateur
     * @param tcpPort : port TCP
     * @param user : nom de l'utilisateur
     */
    public void sendRequest(InetAddress ip, int tcpPort, String user) {
        try {
            DatagramSocket socket = new DatagramSocket();
            byte[] buffer = new byte[521];
            buffer[0] = 2;
            buffer[1 + ip.getAddress().length] = (byte)(ip.getAddress().length);
            buffer[5] = (byte)(tcpPort >> 8);
            buffer[6] = (byte)(tcpPort);
            buffer[7] = (byte)(user.length());
            buffer[8 + user.length()] = (byte)user.length();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, ip, tcpPort);
            socket.send(packet);
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Envoie une offre de packet
     * @param ip : ip de l'utilisateur
     * @param tcpPort : port TCP
     * @param user : nom de l'utilisateur
     */
    public void sendAnnounce(InetAddress ip, int tcpPort, String user) {
        try {
            DatagramSocket socket = new DatagramSocket();
            byte[] buffer = new byte[521];
            buffer[0] = 4;
            buffer[1 + ip.getAddress().length] = (byte)(ip.getAddress().length);
            buffer[5] = (byte)(tcpPort >> 8);
            buffer[6] = (byte)(tcpPort);
            buffer[7] = (byte)(user.length());
            buffer[8 + user.length()] = (byte)user.length();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, ip, tcpPort);
            socket.send(packet);
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Getter du Path baseDirectory
     * @return : chemin du dossier d'origine
     */
    public Path getBaseDirectory() {
        return(baseDirectory);
    }

    /**
     * Getter de la String User
     * @return : nom de l'utilisateur
     */
    public String getUser() {
        return(user);
    }

    /**
     * Getter du port UDP
     * @return : port UDP
     */
    public int getUdpPort() {
        return(udpPort);
    }

    /**
     * Getter du port TCP
     * @return : port TCP
     */
    public int getTcpPort() {
        return(tcpPort);
    }

    /**
     * Main de la classe Gossiper
     * @param args : argument du main
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String user = args[0];
        Path baseDirectory = Path.of(args[1]);
        int udpPort = Integer.parseInt(args[2]);
        int tcpPort = Integer.parseInt(args[3]);
        String ip = args[4];
        Gossiper gossiper = new Gossiper(user, baseDirectory, udpPort, tcpPort);
        gossiper.run();
    }
}