package ubs.info.as.tp03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * TP03 Programmation Multi-Paradigme : Classe Réseau
 * @author Alexandre Stefanos
 *
 */
public class Reseau {
	HashSet<Station> stations;
	static ArrayList<Station> listeVoisins;
	HashMap<Station, ArrayList<Station>> oldGraphe;
	Graphe graphe;
	
	/**
	 * Constructeur de la classe Reseau
	 */
	public Reseau() {
		stations = new HashSet<Station>();
		listeVoisins = new ArrayList<Station>();
		oldGraphe = new HashMap<Station, ArrayList<Station>>();
		this.graphe = new Graphe();
	}
	
	/**
	 * Methode permettant de creer un reseau a partir d'un nom de fichier
	 * @param nomDeFichier_ : nom du fichier a lire
	 * @return le reseau cree
	 * @throws IOException
	 */
	public static Reseau CreeReseauAPartirDuFichier(String nomDeFichier_) throws IOException {
		Reseau reseau = new Reseau();
		try(BufferedReader bR = new BufferedReader(new FileReader(nomDeFichier_))) {
			String ligne;
			while((ligne = bR.readLine()) != null) {
				StringTokenizer strTokenizer = new StringTokenizer(ligne, "\"");
				while(strTokenizer.hasMoreTokens()) {
					Ligne l = new Ligne(Integer.parseInt(strTokenizer.nextToken()));
					Station depart = new Station(strTokenizer.nextToken());
					strTokenizer.nextToken();
					Station arrivee = new Station(strTokenizer.nextToken());
					depart.ajouteLigne(l);
					arrivee.ajouteLigne(l);
					ajoutStation(reseau, depart);
					ajoutStation(reseau, arrivee);
					ajoutStationGraphe(reseau, depart, arrivee, l);
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
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
	 * Methode permettant d'ajouter une station au graphe
	 * @param reseau_ : reseau choisi
	 * @param depart_ : station de depart
	 * @param arrivee_ : arrivee de depart
	 * @param ligne_ : ligne choisie
	 */
	public static void ajoutStationGraphe(Reseau reseau_, Station depart_, Station arrivee_, Ligne ligne_) {
		Graphe.ajoutGraphe(reseau_.getGraphe(), depart_, arrivee_, ligne_);
	}
	
	/**
	 * Methode permettant de savoir si une station est dans un graphe
	 * @param reseau_ : reseau choisi
	 * @param station_ : station choisie
	 * @return true si la station est un sommet, false sinon
	 */
	public static boolean stationDansGraphe(Reseau reseau_, Station station_) {
		return(Graphe.stationEstUnSommet(reseau_.getGraphe(), station_));
	}
	
	/**
	 * Methode permettant de savoir si une station est un voisin d'un sommet
	 * @param reseau_ : reseau choisi
	 * @param sommet_ : sommet choisi
	 * @param station_ : station choisie
	 * @return true si la station est voisine est un sommet, false sinon
	 */
	public static boolean stationEstUnVoisinDe(Reseau reseau_, Station sommet_, Station station_) {
		return(Graphe.estUnVoisinDe(reseau_.getGraphe(), sommet_, station_));
	}
	
	/**
	 * Methode permettant d'ajouter une station a l'ensemble de stations
	 * @param reseau_ : reseau choisi
	 * @param station_ : station choisie
	 * @return l'ensemble de stations avec la station ajoutee
	 */
	public HashSet<Station> stationSurLigne(Reseau reseau_, Station station_) {
		return(Graphe.stationEstSurLaLigne(reseau_.getGraphe(), station_));
	}
	
	/**
	 * Methode permettant d'avoir les stations voisines d'une station d'un reseau dans une chaine de caracteres
	 * @param reseau_ : reseau choisi
	 * @param nomDeStation_ : nom de la station choisie
	 * @return 
	 */
	public static String[] stationsVoisinesDe(Reseau reseau_, String nomDeStation_) {
		HashSet<String> stations = Graphe.stationsVoisinesDe(reseau_.getGraphe(), nomDeStation_);
		if(stations.isEmpty())
			return(null);
		String[] tmp = new String[stations.size()];
		int x = 0;
		for(String str : stations) {
			tmp[x] = str;
			x += 1;
		}
		return(tmp);
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
		Station s = new Station(nomDeStation_);
		String[] voisins = new String[2];
		
		voisins[0] = oldGraphe.get(s).get(0).nom;
		voisins[1] = oldGraphe.get(s).get(1).nom;
		return(voisins);
	}
	
	/**
	 * Methode permettant d'ajouter une station a l'ensemble de stations
	 * @param station_ : station ajoutee a la l'ensemble
	 */
	public void ajoutStation(Station station_) {
		this.stations.add(station_);
	}
	
	/**
	 * Methode permettant de retourner l'ensemble stations
	 * @return l'ensemble stations
	 */
	public HashSet<Station> getStations() {
		return(this.stations);
	}
	
	/**
	 * Methode permettant de retourner l'HashMap graphe
	 * @return l'HashMap graphe
	 */
	public Graphe getGraphe() {
		return(this.graphe);
	}
	
	/**
	 * Methode permettant de changer l'ensemble de stations
	 * @param stations_ : nouvel ensemble de ce reseau
	 */
	public void setStation(HashSet<Station> stations_) {
		this.stations = stations_;
	}
	
	/**
	 * Methode permettant de changer l'HashMap graphe
	 * @param graphe_ : nouveau graphe de ce reseau
	 */
	public void setGraphe(Graphe graphe_) {
		this.graphe = graphe_;
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
		return(Normalizer.normalize(nom_, Normalizer.Form.NFD).replaceAll("_", "-").replaceAll("[^]\\p", "")
				.replaceAll(" ", "").toLowerCase().replaceAll("[â-z0-9-]", ""));
	}
	
	/**
	 * Methode permettant de retourner les chemins d'une station a une autre dans un reseau
	 * @param reseau_ : reseau choisi
	 * @param station1_ : 1ere station choisie
	 * @param station2_ : 2e station choisie
	 * @return liste des chemins
	 */
	public static ArrayList<Chemin> cheminDeVers(Reseau reseau_, Station station1_, Station station2_) {
		return(Graphe.cheminDeVers(reseau_.getGraphe(), station1_, station2_));
	}
}