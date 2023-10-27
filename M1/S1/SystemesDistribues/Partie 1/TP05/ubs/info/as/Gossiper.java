package ubs.info.as;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.file.Path;

/**
 * TP03 - TP04 Systeme Distribue : UDP multicast - gossiping, UDP multicast - beaconing and discovery
 * @author Alexandre Stefanos
 */
public class Gossiper {
    private String user, host;
    private int portUDP, portTCP;
    private Path baseDirectory;


    /**
     * Constructeur de la classe Gossiper
     * @param user : nom de l'utilisateur
     * @param baseDirectory : chemin du dossier d'origine
     * @param portUDP : port UDP
     * @param portTCP : port TCP
     */
    public Gossiper(String user, Path baseDirectory, String host, int portUDP, int portTCP) {
        this.user = user;
        this.baseDirectory = baseDirectory;
        this.portUDP = portUDP;
        this.portTCP = portTCP;
        this.host = host;
    }

    /**
	 * Methode de gestion de message d'erreur
	 */
    private static void usage() {
        System.out.println("Veuillez indiquer en argument le chemin du fichier que vous souhaitez envoyer au serveur.");
        System.out.println("Veuillez indiquer dans le header du fichier si c'est un mail ou non.");
    }
    
    /**
     * Démarre le gossiper
     */
    public void run() {new Thread(new GossiperHandler(this)).start(); }

    /**
     * Envoie une offre de packet
     */
    public void sendOffer() {
        try(DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName(host);
            byte[] buffer = new byte[521];
            buffer[0] = 1;
            buffer[1 + address.getAddress().length] = (byte)(address.getAddress().length);
            buffer[5] = (byte)(portUDP >> 8);
            buffer[6] = (byte)(portUDP);
            buffer[7] = (byte)user.length();
            buffer[8 + user.length()] = (byte)user.length();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, portUDP);
            socket.send(packet);
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Envoie une offre de packet
     */
    public void sendDelete() {
        try(DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName(host);
            byte[] buffer = new byte[521];
            buffer[0] = 3;
            buffer[1 + address.getAddress().length] = (byte)(address.getAddress().length);
            buffer[5] = (byte)(portUDP >> 8);
            buffer[6] = (byte)(portUDP);
            buffer[7] = (byte)user.length();
            buffer[8 + user.length()] = (byte)user.length();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, portUDP);
            socket.send(packet);
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Envoie une offre de packet
     */
    public void sendRequest() {
        try(DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName(host);
            byte[] buffer = new byte[521];
            buffer[0] = 2;
            buffer[1 + address.getAddress().length] = (byte)(address.getAddress().length);
            buffer[5] = (byte)(portTCP >> 8);
            buffer[6] = (byte)(portTCP);
            buffer[7] = (byte)(user.length());
            buffer[8 + user.length()] = (byte)user.length();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, portTCP);
            socket.send(packet);
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Envoie une offre de packet
     */
    public void sendAnnounce() {
        try(DatagramSocket socket = new DatagramSocket()){
            InetAddress address = InetAddress.getByName(host);
            byte[] buffer = new byte[521];
            buffer[0] = 4;
            buffer[1 + address.getAddress().length] = (byte)(address.getAddress().length);
            buffer[5] = (byte)(portTCP >> 8);
            buffer[6] = (byte)(portTCP);
            buffer[7] = (byte)(user.length());
            buffer[8 + user.length()] = (byte)user.length();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, portTCP);
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
     * Setter du Path baseDirectory
     * @param newBaseDirectory : nouveau chemin
     */
    public void setBaseDirectory(Path newBaseDirectory) {
        this.baseDirectory = newBaseDirectory;
    }

    /**
     * Getter de la String User
     * @return : nom de l'utilisateur
     */
    public String getUser() {
        return(user);
    }

    /**
     * Setter de la String user
     * @param newUser : nouvel utilisateur
     */
    public void setUser(String newUser) {
        this.user = newUser;
    }

    /**
     * Getter du port UDP
     * @return : port UDP
     */
    public int getPortUDP() {
        return(portUDP);
    }

    public void setPortUDP(int newPortUDP) {
        this.portUDP = newPortUDP;
    }

    /**
     * Getter du port TCP
     * @return : port TCP
     */
    public int getPortTCP() {
        return(portTCP);
    }

    /**
     * Setter du port TCP
     * @param newPortTCP
     */
    public void setPortTCP(int newPortTCP) {
        this.portTCP = newPortTCP;
    }

    /**
     * Main de la classe Gossiper
     * @param args : argument du main
     * @throws IOException
     */
    public static void main(String[] args) {
        if((args.length != 5) || (args[0].equals("-h") || (args[0].equals("--help")))) {
            usage();
        } else {
            String user = args[0];
            Path baseDirectory = Path.of(args[1]);
            int portUDP = Integer.parseInt(args[2]);
            int portTCP = Integer.parseInt(args[3]);
            String host = args[4];
            Gossiper gossiper = new Gossiper(user, baseDirectory, host, portUDP, portTCP);
            gossiper.run();
        }
    }
}