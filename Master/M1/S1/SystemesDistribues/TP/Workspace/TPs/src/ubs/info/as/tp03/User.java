package ubs.info.as.tp03;

public class User {
    private String nom;
    private String ip;
    private String[] nextMsgToSend;
    private String[] lastMsgReceived;
    private int portUDP;
    private int portTCP;

    public User(String nom, String ip, int portUDP, int portTCP) {
        this.nom = nom;
        this.ip = ip;
        this.portUDP = portUDP;
        this.portTCP = portTCP;
    }

    public void startGossiping(User receivUser) {

    }

    public void stopGossiping(User receivUser) {

    }

    public void offerMessage(User receivUser) {
        
    }

    public void requestMessage(User receivUser) {

    }

    public void deleteMessage() {

    }

    public void answerMessage() {

    }

    public static void main(String[] args) {
        if(args.length > 0 && args[0].equals("-h")) {
            System.out.println("Help message :");
            System.exit(-1);
        }
        if(args.length != 5) {
            System.out.println("Erreur");
            System.exit(-1);
        }

        User user1UDP = new User("alex", "localhost", 5555, 0);
        User user2UDP = new User("alexis", "localhost", 5555, 0);
        User user1TCP = new User("alex", "localhost", 0, 5556);
        User user2TCP = new User("alexis", "localhost", 0, 5556);
    }
}
