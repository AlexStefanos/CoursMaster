package up.mi.jgm.td02.cartes.ameliore;

import up.mi.jgm.td02.cartes.Couleur;
import up.mi.jgm.td02.cartes.Valeur;

/**
 * Represente une carte qui peut etre comparee selon l'ordre "naturel" des
 * cartes
 * 
 * @author Jean-Guy Mailly
 *
 */
public class CarteBasique extends CarteAbstraite implements Comparable<CarteBasique> {

	public CarteBasique(Couleur c, Valeur v) {
		super(c, v);
	}

	@Override
	public int compareTo(CarteBasique carte) {
		if (this.getCouleur().equals(carte.getCouleur())) {
			return this.getValeur().ordinal() - carte.getValeur().ordinal();
		}
		return this.getCouleur().ordinal() - carte.getCouleur().ordinal();
	}

}
