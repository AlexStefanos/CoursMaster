package TP;
import java.io.Serializable;
import java.util.ArrayList;

public class Compte {
    private int num;
    private String idClient;
    private Double[] historiqueOperations;
    private double solde;
    private boolean etatOuvert;

    public Compte(int num, String idClient) {
        this.num = num;
        this.idClient = idClient;
        this.etatOuvert = false;
        historiqueOperations = new Double[100];
        solde = 0;
    }

    public double getSolde() {
        return(this.solde);
    }

    public void setSolde(double montant) {
        solde = montant;
    }

    public int getNum() {
        return(num);
    }

    public String getIdClient() {
        return(idClient);
    }

    public boolean getEtatOuvert() {
        return(etatOuvert);
    }

    public void setEtatOuvert(boolean bool) {
        etatOuvert = bool;
    }

    public Double[] getHistoriqueOperations() {
        return(historiqueOperations);
    }
}
