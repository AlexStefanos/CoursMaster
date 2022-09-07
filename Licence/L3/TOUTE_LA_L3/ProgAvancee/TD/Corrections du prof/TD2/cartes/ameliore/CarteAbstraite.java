package up.mi.jgm.td02.cartes.ameliore;

import up.mi.jgm.td02.cartes.Couleur;
import up.mi.jgm.td02.cartes.Valeur;

/**
 * Permet de representer une carte de jeu, sans tenir compte du mode de tri
 * (exercice VIII du TD2)
 * 
 * @author Jean-Guy Mailly
 *
 */
public abstract class CarteAbstraite {
	/**
	 * La valeur de la carte
	 */
	private Valeur valeur;

	/**
	 * La couleur de la carte
	 */
	private Couleur couleur;

	/**
	 * Construit une carte en fonction de sa couleur et sa valeur
	 * 
	 * @param c la couleur de la carte
	 * @param v la valeur de la carte
	 */
	public CarteAbstraite(Couleur c, Valeur v) {
		this.valeur = v;
		this.couleur = c;
	}

	/**
	 * Permet d'obtenir la valeur de la carte
	 * 
	 * @return la valeur de la carte
	 */
	public Valeur getValeur() {
		return valeur;
	}

	/**
	 * Permet d'obtenir la couleur de la carte
	 * 
	 * @return la couleur de la carte
	 */
	public Couleur getCouleur() {
		return couleur;
	}
	
	@Override
	public String toString() {
		return valeur + " de " + couleur;
	}
}
