package ubs.info.as.tp02;

import java.util.HashSet;
import java.util.Iterator;

public class Station {
    private String nom;
    private Iterator<Ligne> iterator;
    private HashSet<Ligne> lignes;

    public Station(String nom_) {
        this.nom = nom_;
        this.lignes = new HashSet<Ligne>();
        iterator = lignes.iterator();
    }

    public String getNom() {
        return(this.nom);
    }

    public void setNom(String nouvNom_) {
        this.nom = nouvNom_;
    }

    public HashSet<Ligne> getLignes() {
        return(this.lignes);
    }

    public void setLignes(HashSet<Ligne> nouvLignes_) {
        this.lignes = nouvLignes_;
    }

    public void ajouteLigne(Ligne ligne_) {
        this.lignes.add(ligne_);
    }

    public static void main(String[] args) {
        Station st = new Station("hey");
        Ligne ligne = new Ligne(22);
        Ligne ligne2 = new Ligne(22);
        Ligne ligne3 = new Ligne(23);

        st.ajouteLigne(ligne);
        System.out.println(st.getLignes());
        st.ajouteLigne(ligne2);
        System.out.println(st.getLignes());
        st.ajouteLigne(ligne3);
        System.out.println(st.getLignes());
        for(Ligne l : st) {
            System.out.println(l.getNumLigne());
        }
    }
}
