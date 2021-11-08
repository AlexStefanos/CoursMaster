package up.mi.jgm.td02.cartes.simple;

import up.mi.jgm.td02.cartes.Couleur;
import up.mi.jgm.td02.cartes.Valeur;

/**
 * Represente une carte simple (exercice VII du TD2)
 * 
 * @author Jean-Guy Mailly
 *
 */
public class Carte implements Comparable<Carte> {
	/**
	 * La couleur de la carte
	 */
	private Couleur couleur;
	
	/**
	 * La valeur de la carte
	 */
	private Valeur valeur;

	/**
	 * Construit une carte en fonction de sa couleur et sa valeur
	 * 
	 * @param c la couleur de la carte
	 * @param v la valeur de la carte
	 */
	public Carte(Couleur c, Valeur v) {
		couleur = c;
		valeur = v;
	}

	@Override
	public int compareTo(Carte carte) {
		if (this.couleur.equals(carte.couleur)) {
			return this.valeur.ordinal() - carte.valeur.ordinal();
		}
		return this.couleur.ordinal() - carte.couleur.ordinal();
	}

	@Override
	public String toString() {
		return valeur + " de " + couleur;
	}
}
