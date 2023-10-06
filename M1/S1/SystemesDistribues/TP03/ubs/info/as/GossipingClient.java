package TP03.ubs.info.as;

public class GossipingClient {
    private String host, name, baseDirectory;
    private int portTCP, portUDP;

    public GossipingClient(String host, String name, String baseDirectory, int portTCP, int portUDP) {
        this.host = host;
        this.name = name;
        this.baseDirectory = baseDirectory;
        this.portTCP = portTCP;
        this.portUDP = portUDP;
    }

    public void offerMessage() {

    }

    public void requestMessage() {

    }

    public void deleteMessage() {

    }
}