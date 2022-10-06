package ubs.info.as.tp03;

import java.util.HashSet;
import java.util.Iterator;

/**
 * TP02 Programmation Multi-Paradigme : Classe Station
 * @author Alexandre Stefanos
 *
 */
public class Station {
	String nom;
	HashSet<Ligne> lignesDisponibles;
	Iterator<Ligne> lignes;
	
	/**
	 * Constructeur de la classe Station
	 * @param nom_ : nom de la station
	 */
	public Station(String nom_) {
		this.nom = nom_;
		lignesDisponibles = new HashSet<Ligne>();
	}
	
	/**
	 * Methode permettant d'ajouter une ligne disponible a une station
	 * @param ligne_ : ligne a ajouter
	 */
	public void ajouteLigne(Ligne ligne_) {
		this.lignesDisponibles.add(ligne_);
	}
}
