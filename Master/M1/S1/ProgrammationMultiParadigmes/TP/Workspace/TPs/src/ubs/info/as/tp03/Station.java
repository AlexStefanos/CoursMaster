package ubs.info.as.tp03;

import java.util.HashSet;
import java.util.Iterator;

/**
 * TP02 Programmation Multi-Paradigme : Classe Station
 * @author Alexandre Stefanos
 *
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
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return(false);
		if(obj.getClass() != this.getClass())
			return(false);
		Station station = (Station)obj;
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

	@Override
	public Iterator<Ligne> iterator() {
		return(iterLignesDisponibles);
	}
}
