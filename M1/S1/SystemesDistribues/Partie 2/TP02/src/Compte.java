import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Compte implements Serializable {
    private int id, solde;
    private String nom;
    private ArrayList<Integer> operationsEnAttente;
    private HashMap<String, Integer> historique;

    public Compte() {
        this.solde = 0;
        this.nom = "";
        Random random = new Random();
        int rdm = random.nextInt();
        while(rdm < 0) {
            rdm = random.nextInt();
        }
        this.id = rdm;
        historique = new HashMap<String, Integer>();
        operationsEnAttente = new ArrayList<Integer>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public HashMap<String, Integer> getHistorique() {
        return historique;
    }

    public void setHistorique(HashMap<String, Integer> historique) {
        this.historique = historique;
    }

    public ArrayList<Integer> getOperationsEnAttente() {
        return operationsEnAttente;
    }

    public void ajouteOperationHistorique(int operation) {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
        String date = dateTime.format(formatter);
        this.historique.put(date, operation);
    }
}