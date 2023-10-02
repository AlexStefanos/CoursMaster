package ubs.info.as.tp02;

import java.util.HashSet;

public class Station {
    private String nom;
    private HashSet<Ligne> lignes;

    public Station(String nom) {
        this.nom = nom;
        lignes = new HashSet<Ligne>();
    }

    public String getNom() {
        return(this.nom);
    }

    public void setNom(String nouveauNom) {
        this.nom = nouveauNom;
    }

    public HashSet<Ligne> getLignes() {
        return(this.lignes);
    }

    public void setLignes(HashSet<Ligne> nouvellesLignes) {
        this.lignes = nouvellesLignes;
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
    }
}
