package ubs.info.as.tp02;

import java.util.HashSet;

public class Station {
	String nom;
	HashSet<Ligne> lignesDisponibles;
	
	public Station(String nom_) {
		this.nom = nom_;
		lignesDisponibles = new HashSet<Ligne>();
	}
	
	public void ajouteLigne(Ligne ligne_) {
		this.lignesDisponibles.add(ligne_);
	}
	
	public static void main(String[] args) {
		Ligne l = new Ligne(22, "hey", "salut");
		Station s = new Station("blabla");
		s.ajouteLigne(l);
	}
}
