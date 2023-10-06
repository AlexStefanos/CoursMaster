package ubs.info.as.tp02;

import java.util.HashSet;
import java.util.Iterator;

/**
 * TP02 Programmation Multi-Paradigmes
 * Instancie une Station à partir d'un nom de Station
 */
public class Station implements Iterable<Ligne> {
    private String nom;
    private Iterator<Ligne> iterator;
    private HashSet<Ligne> lignes;

    /**
     * Constructeur de la classe Station
     * @param nom_ : nom de la station
     */
    public Station(String nom_) {
        this.nom = nom_;
        this.lignes = new HashSet<Ligne>();
        iterator = lignes.iterator();
    }

    /**
     * Re-définition de la méthode iterator() implémentée de Iterable<Ligne>
     * @return iterator : iterateur de cette instance de Station
     */
    @Override
    public Iterator<Ligne> iterator() {
        return(iterator);
    }

    /**
     * Getter du nom de la station
     * @return nom : nom de cette instance de Station
     */
    public String getNom() {
        return(this.nom);
    }

    /**
     * Setter du nom de la station
     * @param nouvNom_ : nouveau nom pour cette instance de Station
     */
    public void setNom(String nouvNom_) {
        this.nom = nouvNom_;
    }

    /**
     * Getter de l'HashSet<Ligne> de cette instance de Station
     * @return lignes : HashSet<Ligne> de cette instance de Station
     */
    public HashSet<Ligne> getLignes() {
        return(this.lignes);
    }

    /**
     * Setter de l'HashSet<Ligne> de cette instance de Station
     * @param nouvLignes_ : nouveau HashSet<Ligne> de cette instance de Station
     */
    public void setLignes(HashSet<Ligne> nouvLignes_) {
        this.lignes = nouvLignes_;
    }

    /**
     * Ajouter une ligne donne en parametre a l'HashSet<Ligne> lignes de cette instance de Station
     * @param ligne_ : ligne a ajouter
     */
    public void ajouteLigne(Ligne ligne_) {
        int count = 0;
        for(Ligne l : lignes) {
            if(l.getNumLigne() != ligne_.getNumLigne())
                count++;
        }
        if(count == lignes.size())
            this.lignes.add(ligne_);
    }
}
