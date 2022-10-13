package ubs.info.as.tp03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
	HashMap<Station, ArrayList<Station>> graph;
	
	/**
	 * Constructeur de la classe Reseau
	 */
	public Reseau() {
		stations = new HashSet<Station>();
		listeVoisins = new ArrayList<Station>();
		graph = new HashMap<Station, ArrayList<Station>>();
	}if(i == 0)
//		tmp = str;
//	if(i == 2)
//		tmp2 = str;
//	i++;
//	if(str != tmp && str != tmp2) {
//		listeVoisins.add(new Station(tmp));
//		listeVoisins.add(new Station(tmp2));
//		reseau.graph.put(new Station(str), listeVoisins);
//		listeVoisins.clear();
//	}
//	if(i == 3)
//		i = 0;
	
	/**
	 * Methode permettant de creer un reseau a partir d'un nom de fichier
	 * @param nomDeFichier_ : nom du fichier a lire
	 * @return le reseau cree
	 * @throws IOException
	 */
	public static Reseau CreeReseauAPartirDuFichier(String nomDeFichier_) throws IOException {
		Reseau reseau = new Reseau();
		
		try(FileReader fReader = new FileReader(nomDeFichier_)) {
			BufferedReader bReader = new BufferedReader(fReader);
			String str = new String();
			int i = 0;
			StringTokenizer strTokenizer;
			
			while(bReader.ready()) {
				str = bReader.readLine();
				strTokenizer = new StringTokenizer(str, "\"");
				while(strTokenizer.hasMoreTokens() == true) {
					Ligne ligne = new Ligne(Integer.parseInt(strTokenizer.nextToken()));
					Station depart = new Station(strTokenizer.nextToken());
					System.out.println(strTokenizer.nextToken());
					Station arrivee = new Station(strTokenizer.nextToken());
					depart.ajouteLigne(ligne);
					arrivee.ajouteLigne(ligne);
					reseau.stations.add(depart);
					reseau.stations.add(arrivee);
//					if(i == 0)
//						tmp = str;
//					if(i == 2)
//						tmp2 = str;
//					i++;
//					if(str != tmp && str != tmp2) {
//						listeVoisins.add(new Station(tmp));
//						listeVoisins.add(new Station(tmp2));
//						reseau.graph.put(new Station(str), listeVoisins);
//						listeVoisins.clear();
//					}
//					if(i == 3)
//						i = 0;
				}
			}
		}
		return(reseau);
	}
	
	/**
	 * Methode permettant d'avoir les noms de stations voisines de la station nomDeStation_ 
	 * @param nomDeStation_ : nom de la station dont on cherche les voisins
	 * @return : liste de chaine de caracteres contenant les 2 voisins d'une station
	 */
	public String[] stationsVoisinesDe(String nomDeStation_) {
		Station s = new Station(nomDeStation_);
		String[] voisins = new String[2];
		
		voisins[0] = graph.get(s).get(0).nom;
		voisins[1] = graph.get(s).get(1).nom;
		return(voisins);
	}
	
	/**
	 * Methode permettant d'ajouter une valeur a HashMap<Station, ArrayList<Station>> graph
	 * @param stations_ : stations ajoutees
	 */
	public void ajoutGraph(Station station_, ArrayList<Station> listeVoisins_) {
		if(graph.containsKey(station_) == false)
			graph.put(station_, listeVoisins_);
	}
	
	/**
	 * Methode permettant d'ajouter une cle a HashMap<String, Station> stations
	 * @param s_ : station a ajouter
	 */
	public void ajoutStations(Station station_) {
		
	}
	
	/**
	 * Methode permettant de supprimer une cle de HashMap<Station, ArrayList<Station>> graph
	 * @param station_ a supprimer
	 */
	public void supprGraph(Station station_) {
		
	}
	
	/**
	 * Methode permettant de supprimer une cle de la HashMap<String, Station> stations
	 * @param station_ : station supprimée
	 */
	public void supprStations(Station station_) {
		
	}
	
	/**
	 * Methode permettant d'afficher HashMap<Station, ArrayList<Station>> graph
	 */
	public void afficheGraph() {
		System.out.println(graph.keySet());
	}
	
	/**
	 * Methode permettant d'afficher HashMap<String, Station> stations
	 */
	public void afficheStations() {
		
	}
	
	public HashSet<Station> getStations() {
		return(this.stations);
	}
	
	public HashMap<Station, ArrayList<Station>> getGraph() {
		return(this.graph);
	}
	
	public static void main(String[] args) throws IOException {
		Reseau r1 = new Reseau();
		r1 = Reseau.CreeReseauAPartirDuFichier("metro.txt");
		r1.afficheGraph();
	}
}