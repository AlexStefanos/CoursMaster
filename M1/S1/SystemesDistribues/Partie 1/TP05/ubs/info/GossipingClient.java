package ubs.info;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * TP03 Systemes Distribues : Gossiping
 * Client appele par le Launcher lors du lancement 
 * @author Alexandre Stefanos
 */
public class GossipingClient {
    private String[] argsGossiping;
    private List<String> filesHistory;

    /**
     * Constructeur du GossipingClient
     * @param argsGossiping : arguments donné lors de l'execution du jar
     */
    public GossipingClient(String[] argsGossiping) {
        this.argsGossiping = argsGossiping;
        filesHistory = new ArrayList<String>();
    }

    /**
     * Cree un client qui envoie le fichier donne en argument au serveur
     */
    public void offerMessage() {
        Client.main(argsGossiping);
        System.out.println("Le message est envoye");
    }

    /**
     * 
     */
    public void requestMessage() {
        System.out.println("Le message est demande");
    }

    /**
     * Supprime le fichier donne en argument
     */
    public void deleteMessage() {
        File deleteFile = new File(filesHistory.get(filesHistory.size() - 1));
        deleteFile.delete();
        System.out.println("Le message est supprime");
    }
}