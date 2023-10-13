package ubs.info.as.tp03v2;

import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * TP03 Programmation Multi-Paradigmes
 * Instancie un reseau de metro
 */
public class Reseau {
	private ArrayList<Station> stations;
    private HashSet<Station> graphe;
	
	/**
	 * Constructeur de la classe Reseau
	 */
	public Reseau() {
		stations = new ArrayList<Station>();
        graphe = new HashSet<Station>();
	}
	
	/**
	 * Methode permettant de creer un reseau a partir d'un nom de fichier
	 * @param nomDeFichier_ : nom du fichier a lire
	 * @return le reseau cree
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
	 * Methode permettant d'ajouter une station au reseau
	 * @param reseau_ : reseau choisi
	 * @param station_ : station choisie
	 */
	public static void ajoutStation(Reseau reseau_, Station station_) {
		reseau_.getStations().add(station_);
	}
	
	/**
	 * Methode permettant de creer un ensemble de stations qui ont acces a une ligne
	 * @param ligne_ : ligne donnee
	 * @return l'ensemble de stations cree
	 */
	public HashSet<Station> stationSurLigne(Ligne ligne_) {
		HashSet<Station> tmp = new HashSet<Station>();
		for(Station stati : this.stations) {
			if(stati.getLignesDisponibles().contains(ligne_))
				tmp.add(stati);
		}
		return(tmp);
	}
	
	
	/**
	 * Methode permettant d'avoir les noms de stations voisines de la station nomDeStation_ 
	 * @param nomDeStation_ : nom de la station dont on cherche les voisins
	 * @return : liste de chaine de caracteres contenant les 2 voisins d'une station
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
	
	/**
	 * Methode permettant d'ajouter une station a l'ensemble de stations
	 * @param station_ : station ajoutee a la l'ensemble
	 */
	public void ajoutStation(Station station_) {
		this.stations.add(station_);
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
	 * Methode permettant de retourner l'ensemble des donnees du reseau dans une chaine de caracteres
	 * @return l'ensemble des donnees du reseau
	 */
	public String toString() {
		String tmp = new String("Reseau : \n");
		for(Station stati : this.stations) {
			tmp += stati.toString();
		}
		return(tmp);
	}
	
	/**
	 * Methode permettant de corriger le nom d'une station s'il y a une erreur
	 * @param nom_ : nom de la station
	 * @return nouveau nom de station
	 */
	public static String correctionNomStation(String nom_) {
		return(Normalizer.normalize(nom_, Normalizer.Form.NFD)
			.replaceAll("[^a-zA-Z]", "")
			.toLowerCase());
	}

	public List<String> cheminDeVers(String station1_, String station2_) {
		List<String> result = new ArrayList<String>();
		if(correctionNomStation(station1_).equals(correctionNomStation(station2_))) {
			return(result);
		}
		
		return(result);
	}

	public static void main(String args[]) {
		
	}
}