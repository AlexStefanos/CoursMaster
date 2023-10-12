package ubs.info.as;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GossipingClient {
    private String[] argsGossiping;
    private List<String> filesHistory;

    public GossipingClient(String[] argsGossiping) {
        this.argsGossiping = argsGossiping;
        filesHistory = new ArrayList<String>();
    }

    public void offerMessage() {
        Client.main(argsGossiping);
        System.out.println("Le message est envoye");
    }

    public void requestMessage() {
        System.out.println("Le message est demande");
    }

    public void deleteMessage() {
        File deleteFile = new File(filesHistory.get(filesHistory.size() - 1));
        deleteFile.delete();
        System.out.println("Le message est supprime");
    }
}