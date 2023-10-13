package ubs.info.as.tp03v2;

import java.util.HashSet;

/**
 * TP03 Programmation Multi-Paradigmes
 * Instancie une ligne a partir d'un numero de ligne
 */
public class Ligne {
    private int numLigne;
    private HashSet<Station> stations;

    /**
     * Constructeur de la classe Ligne a partir d'un numero de ligne
     * @param numLigne_ : numero de ligne
     */
    public Ligne(int numLigne_) {
        this.numLigne = numLigne_;
        this.stations = new HashSet<Station>();
    }

    /**
     * Getter du numero de ligne de cette instance de Ligne
     * @return numLigne : le numero de cette instance de Ligne
     */
    public int getNumLigne() {
        return(this.numLigne);
    }

    /**
     * Setter du numero de ligne de cette instance de Ligne
     * @param nvNumLigne_ : nouveau numero de ligne pour cette instance de Ligne
     */
    public void setNumLigne(int nvNumLigne_) {
        this.numLigne = nvNumLigne_;
    }

    /**
     * Getter de l'HashSet<Station> de cette instance de Ligne
     * @return stations : l'ensemble de stations de cette instance de Ligne
     */
    public HashSet<Station> getStations() {
        return(stations);
    }

    /**
     * Setter de l'HashSet<Station> de cette instance de Ligne
     * @param nvStation_ : nouvelle ensemble de stations pour cette instance de Ligne
     */
    public void setStations(HashSet<Station> nvStation_) {
        this.stations = nvStation_;
    }
}
