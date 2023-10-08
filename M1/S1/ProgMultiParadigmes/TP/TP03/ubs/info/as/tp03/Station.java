package ubs.info.as.tp03;

import java.util.HashSet;
import java.util.Iterator;

/**
 * TP03 Programmation Multi-Paradigme
 * Instancie une station de metro
 */
public class Station implements Iterable<Ligne> {
	String nom;
	HashSet<Ligne> lignesDisponibles;
	Iterator<Ligne> iterLignesDisponibles;
	
	/**
	 * Constructeur de la classe Station
	 * @param nom_ : nom de la station
	 */
	public Station(String nom_) {
		this.nom = nom_;
		lignesDisponibles = new HashSet<Ligne>();
		iterLignesDisponibles = lignesDisponibles.iterator();
	}
	
	/**
	 * Methode permettant de savoir a l'aide d'un booleen si deux stations sont egales ou non
	 * @return true si les deux stations sont egales, false sinon
	 */
	@Override
	public boolean equals(Object obj_) {
		if(obj_ == null)
			return(false);
		if(obj_.getClass() != this.getClass())
			return(false);
		Station station = (Station)obj_;
		if(station.nom != this.nom)
			return(false);
		if(station.lignesDisponibles.size() != this.lignesDisponibles.size())
			return(false);
		while(station.iterLignesDisponibles.hasNext()) {
			if(this.lignesDisponibles.contains(station.iterLignesDisponibles.next()) == false)
				return(false);
		}
		return(true);
	}
	
	/**
	 * Methode permettant de retourner l'hashcode de cette station
	 */
	@Override
	public int hashCode() {
		return(this.nom.length());
	}

	/**
	 * Methode permettant d'ajouter une ligne disponible a une station
	 * @param ligne_ : ligne a ajouter
	 */
	public void ajouteLigne(Ligne ligne_) {
		this.lignesDisponibles.add(ligne_);
	}
	
	/**
	 * Methode permettant de retourner le nom de la station
	 * @return le nom de la station
	 */
	public String getNom() {
		return(this.nom);
	}
	
	/**
	 * Methode permettant de changer le nom de la station
	 * @param nom_ : nouveau nom de la station
	 */
	public void setNom(String nom_) {
		this.nom = nom_;
	}
	
	/**
	 * Methode permettant de retourner l'ensemble de lignes disponibles de la station
	 * @return l'ensemble de lignes disponibles de la station
	 */
	public HashSet<Ligne> getLignesDisponibles() {
		return(this.lignesDisponibles);
	}
	
	/**
	 * Methode permettant de changer l'ensemble de lignes disponibles de la station
	 * @param lignesDisponibles_ : le nouvel ensemble de lignes disponibles de la station
	 */
	public void setLignesDisponibles(HashSet<Ligne> lignesDisponibles_) {
		this.lignesDisponibles = lignesDisponibles_;
	}

	/**
	 * Methode permettant de retourner l'ensemble des donnees de la station dans une chaine de caracteres
	 * @return chaine de caracteres de l'ensemble des donnes de la station
	 */
	public String toString() {
		String tmp = new String("Station : " + this.nom + "\n");
		for(Ligne ligne : this.lignesDisponibles)
			tmp += ligne.toString();
		return(tmp);
	}
	
	/**
	 * Methode permettant de retourner l'iterateur de l'ensemble lignesDisponibles
	 * @return iterateur de l'ensemble lignesDisponibles
	 */
	@Override
	public Iterator<Ligne> iterator() {
		return(iterLignesDisponibles);
	}
}
