package ubs.info.as.tp02;

/**
 * TP02 Programmation Multi-Paradigme : Classe Ligne
 * @author Alexandre Stefanos
 *
 */
public class Ligne {
	int numero;
	String stationDepart;
	String stationArrivee;
	
	/**
	 * Constructeur de la classe Ligne
	 * @param numero_ : numero de la ligne
	 * @param stationDepart_ : station de depart de la ligne
	 * @param stationArrivee_ : station d'arrivee de la ligne
	 */
	public Ligne(int numero_, String stationDepart_, String stationArrivee_) {
		this.numero = numero_;
		this.stationDepart = stationDepart_;
		this.stationArrivee = stationArrivee_;
	}
}
