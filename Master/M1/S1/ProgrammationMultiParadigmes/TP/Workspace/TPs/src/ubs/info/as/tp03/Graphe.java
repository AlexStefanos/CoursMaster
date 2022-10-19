package ubs.info.as.tp03;

import java.util.ArrayList;
import java.util.HashSet;

public class Graphe {
	HashSet<Chemin> chemins;
	
	/**
	 * Constructeur de la classe Graphe
	 */
	public Graphe() {
		this.chemins = new HashSet<Chemin>();
	}
	
	/**
	 * Methode permettant de retourner l'ensemble des chemins de ce graphe
	 * @return l'ensemble des chemins du graphe
	 */
	public HashSet<Chemin> getChemins() {
		return(this.chemins);
	}
	
	/**
	 * Methode permettant d'ajouter un chemin a l'ensemble de chemins du graphe
	 * @param graphe_ : graphe choisi
	 * @param depart_ : station de depart choisie
	 * @param arrivee_ : station d'arrivee choisie
	 * @param ligne_ : ligne choisie
	 */
	public static void ajoutGraphe(Graphe graphe_, Station depart_, Station arrivee_, Ligne ligne_) {
		graphe_.getChemins().add(new Chemin(ligne_, depart_, arrivee_));
	}
	
	/**
	 * Methode permettant de savoir si une station est un sommet
	 * @param graphe_ : graphe choisi
	 * @param sommet_ : sommet choisie
	 * @return true si la station est un sommet, false sinon
	 */
	public static boolean stationEstUnSommet(Graphe graphe_, Station sommet_) {
		return(graphe_.getChemins().stream().anyMatch((t) -> t.getDepart().equals(sommet_)));
	}
	
	/**
	 * Methode permettant de savoir si une station est un voisin d'une autre station
	 * @param graphe_ : graphe choisi
	 * @param station1_ : 1ere station choisie
	 * @param station2_ : 2e station choisie
	 * @return true si la 1ere station est un voisin de la seconde second, false sinon
	 */
	public static boolean estUnVoisinDe(Graphe graphe_, Station station1_, Station station2_) {
		return(graphe_.getChemins().stream().anyMatch((t) -> (t.getDepart().equals(station1_) && t.getArrivee().equals(station2_))
				|| (t.getDepart().equals(station2_) && t.getArrivee().equals(station1_))));
	}
	
	/**
	 * Methode permettant d'ajouter une station a un ensemble de station d'un graphe
	 * @param graphe_ : graphe choisi
	 * @param station_ : station choisie
	 * @return l'ensemble des stations avec une station ajoutee
	 */
	public static HashSet<Station> stationEstSurLaLigne(Graphe graphe_, Station station_) {
		HashSet<Station> stations = new HashSet<Station>();
		graphe_.getChemins().stream().forEach((t) ->  {
			if(station_.equals(t.getDepart()))
				stations.add(t.getArrivee());
			else if(station_.equals(t.getArrivee()))
				stations.add(t.getDepart());
			else {
			}
		});
		return(stations);
	}
	
	/**
	 * Methode permettant d'avoir l'ensemble des voisins d'une station
	 * @param graphe_ : graphe choisi
	 * @param nomDeStation_ : station choisie
	 * @return l'ensemble des voisins d'une station
	 */
	public static HashSet<String> stationsVoisinesDe(Graphe graphe_, String nomDeStation_) {
		HashSet<String> nomStations = new HashSet<String>();
		graphe_.getChemins().stream().forEach((t) -> {
			if(Reseau.correctionNomStation(nomDeStation_).equals(Reseau.correctionNomStation(t.getDepart().getNom())))
				nomStations.add(Reseau.correctionNomStation(t.getArrivee().getNom()));
			else if(Reseau.correctionNomStation(nomDeStation_).equals(Reseau.correctionNomStation(t.getArrivee().getNom())))
				nomStations.add(Reseau.correctionNomStation(t.getDepart().getNom()));
		});
		return(nomStations);
	}
	
	/**
	 * Methode permettant de sauvegarder le chemin entre deux stations
	 * @param graphe_ : graphe choisi
	 * @param station1_ : 1ere station choisie
	 * @param station2_ : 2e station choisie
	 * @param chemin1_ : 1er chemin choisi
	 * @param chemin2_ : 2e chemin choisi
	 * @return la liste des chemins sauvegardee entre les deux stations
	 */
	public static ArrayList<Chemin> cheminDeVersSave(Graphe graphe_, Station station1_, Station station2_, 
			ArrayList<Chemin> chemin1_, ArrayList<Chemin> chemin2_) {
		if(station1_.equals(station2_))
			return(chemin1_);
		ArrayList<Chemin> tmp;
		ArrayList<Chemin> tmpSave;
		ArrayList<Chemin> tmpFinal = new ArrayList<Chemin>();
		for(Chemin chem : graphe_.getChemins()) {
			if(((chemin2_.isEmpty()) || (chemin2_.size() > chemin1_.size()) && (!(chemin1_.contains(chem)) ))) {
				if(station1_.equals(chem.getDepart())) {
					tmp = new ArrayList<Chemin>(chemin1_);
					tmp.add(chem);
					Station arrivee = chem.getArrivee();
					tmpSave = cheminDeVersSave(graphe_, arrivee, station2_, tmp, chemin2_);
					if((!(tmpSave.isEmpty())) && ((tmpSave.size() < chemin2_.size()) || (chemin2_.isEmpty())))
						tmpFinal = new ArrayList<Chemin>(tmpSave);
				}
			}
		}
		return(tmpFinal);
	}
	
	/**
	 * Methode permettant d'avoir la liste des chemin entre deux stations dans un graphe
	 * @param graphe_ : graphe choisi
	 * @param station1_ : 1ere station choisie
	 * @param station2_ : 2e station choisie
	 * @return la liste des chemins entre les deux stations
	 */
	public static ArrayList<Chemin> cheminDeVers(Graphe graphe_, Station station1_, Station station2_) {
		ArrayList<Chemin> chemin1 = new ArrayList<Chemin>();
		ArrayList<Chemin> chemin2 = new ArrayList<Chemin>();
		return(cheminDeVersSave(graphe_, station1_, station2_, chemin1, chemin2));
	}
}
