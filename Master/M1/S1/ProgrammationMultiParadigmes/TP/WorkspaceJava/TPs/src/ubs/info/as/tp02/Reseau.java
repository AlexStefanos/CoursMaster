package ubs.info.as.tp02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * TP02 Programmation Multi-Paradigme : Classe Réseau
 * @author Alexandre Stefanos
 *
 */
public class Reseau {
	ArrayList<ArrayList<Station>> graph;
	ArrayList<Station> stations;
	
	/**
	 * Constructeur de la classe Reseau
	 */
	public Reseau() {
		graph = new ArrayList<ArrayList<Station>>();
		stations = new ArrayList<Station>();
	}
	
	/**
	 * Methode permettant de creer un reseau a partir d'un nom de fichier
	 * @param nomDeFichier_ : nom du fichier a lire
	 * @return le reseau creer
	 * @throws IOException
	 */
	public static Reseau CreeReseauAPartirDuFichier(String nomDeFichier_) throws IOException {
		Reseau reseau = new Reseau();
		
		try(FileReader fReader = new FileReader(nomDeFichier_)) {
			BufferedReader bReader = new BufferedReader(fReader);
			String str = new String();
			while(bReader.ready()) {
				str += bReader.readLine();
				str += '\n';
			}
			System.out.println(str);
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
		
		voisins[0] = stations.get(stations.indexOf(s) - 1).toString();
		voisins[1] = stations.get(stations.indexOf(s) + 1).toString();
		return(voisins);
	}
	
	/**
	 * Methode permettant d'ajouter une ArrayList de stations au graphe
	 * @param stations_ : stations ajoutees
	 */
	public void ajoutGraph(ArrayList<Station> s_) {
		graph.add(s_);
	}
	
	/**
	 * Methode permettant d'ajouter une station a l'ArrayList de stations
	 * @param s_ : station a ajouter
	 */
	public void ajoutStations(Station s_) {
		stations.add(s_);
	}
	
	/**
	 * Methode permettant d'afficher un graphe
	 */
	public void afficheGraph() {
		for(int i = 0; i < graph.size(); i++) {
			System.out.println(graph.indexOf(i));
		}
	}
	
	/**
	 * Methode permettant d'afficher l'ArrayList de stations
	 */
	public void afficheStations() {
		for(int i = 0; i < stations.size(); i++) {
			System.out.println(stations.indexOf(i));
		}
	}
}