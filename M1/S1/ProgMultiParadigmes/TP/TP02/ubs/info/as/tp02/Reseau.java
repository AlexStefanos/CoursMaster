package ubs.info.as.tp02;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * TP02 Programmation Multi-Paradigmes
 * Instancie un reseau
 */
public class Reseau {
    private ArrayList<Station> stations;
    private HashSet<Station> graphe;
    /**
     * Constructeur de la Classe Reseau
     * Permet de construire l'ArrayList<Station> stations et l'HashSet<Station> graphe
     */
    public Reseau() {
        stations = new ArrayList<Station>();
        graphe = new HashSet<Station>();
    }

    /**
     * Cree un nouveau reseau a partir d'un fichier donne en parametre
     * @param nomDeFichier_
     * @return reseau : reseau cree a partir du fichier donne en parametre
     * @throws IOException
     */
    public static Reseau CreeReseauAPartirDuFichier(String nomDeFichier_) throws IOException {
        ArrayList<String> listeLireMetro = Metro.lireMetro(nomDeFichier_);
        ArrayList<Station> listeStations = new ArrayList<Station>();
        HashSet<Station> graphe = new HashSet<Station>();
        for(String str : listeLireMetro)
            listeStations.add(new Station(str));
        Reseau reseau = new Reseau();
        reseau.setGraphe(graphe);
        reseau.setStations(listeStations);
        return(reseau);
    }

    /**
     * Getter de l'ArrayList<Station> stations de cette instance du reseau
     * @return stations : la liste de stations de cette instance du reseau
     */
    public ArrayList<Station> getStations() {
        return(this.stations);
    }

    /**
     * Setter de l'ArrayList<Station> stations a partir de nvStations
     * @param nvStations : nouvelle ArrayList<Station> remplacant celle de cette instance du reseau
     */
    public void setStations(ArrayList<Station> nvStations_) {
        this.stations = nvStations_;
    }

    /**
     * Getter de l'HashSet<Station> graphe de cette instance du reseau
     * @return graphe : le graphe des stations de cette instance du reseau
     */
    public HashSet<Station> getGraphe() {
        return(this.graphe);
    }

    /**
     * Setter de l'HashSet<Station> graphe a partir de nvGraphe
     * @param nvGraphe : nouveau HashSet<Station> remplacant celui de cette instance du reseau
     */
    public void setGraphe(HashSet<Station> nvGraphe_) {
        this.graphe = nvGraphe_;
    }

    /**
     * Recupere les voisins d'une station si cette station existe dans l'ArrayList<Station>
     * @param nomDeStation_ : station a chercher
     * @return voisines : les stations voisines de la station donne en parametre
     */
    public String[] stationsVoisinesDe(String nomDeStation_) {
        String[] voisines = new String[2];
        int indice = -1;
        for(int i = 0; i < this.stations.size(); i++) {
            if(this.stations.get(i).getNom().equals(nomDeStation_))
                indice = i;
        }
        if(indice != -1) {
            if(indice != 0)
                voisines[0] = this.stations.get(indice - 1).getNom();
            if(indice != (this.stations.size() - 1))
                voisines[1] = this.stations.get(indice + 1).getNom();
            return(voisines);
        }
        else
            return(voisines);
    }
}
