package TP;
import java.util.ArrayList;

public class Compte {
    private int num;
    private String idClient;
    private ArrayList<Double> historiqueOperations;
    private double solde;
    private boolean etatOuvert;

    public Compte(int num, String idClient) {
        this.num = num;
        this.idClient = idClient;
        this.etatOuvert = false;
        historiqueOperations = new ArrayList<Double>();
        solde = 0;
    }

    public void modifSoldes(double modif) {
        this.solde += (modif);
        historiqueOperations.add(modif);
        if(this.etatOuvert == false)
            etatOuvert = true;
    }

    public double getSolde() {
        return(this.solde);
    }
}
