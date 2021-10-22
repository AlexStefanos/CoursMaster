package up.mi.jgm.td02.cartes.ameliore;

import up.mi.jgm.td02.cartes.Couleur;
import up.mi.jgm.td02.cartes.Valeur;

/**
 * Represente une carte qui peut etre comparee aux autres selon les regles de la
 * manille
 * 
 * @author Jean-Guy Mailly
 *
 */
public class CarteManille extends CarteAbstraite implements Comparable<CarteManille>{

	public CarteManille(Couleur c, Valeur v) {
		super(c, v);
	}

	@Override
	public int compareTo(CarteManille carte) {
		if (!this.getCouleur().equals(carte.getCouleur())) {
			return this.getCouleur().ordinal() - carte.getCouleur().ordinal();
		}
		if (this.getValeur().equals(Valeur.DIX))
			return 1;
		if (this.getValeur().equals(Valeur.AS)) {
			if (carte.getValeur().equals(Valeur.DIX))
				return -1;
			if (carte.getValeur().equals(Valeur.AS))
				return 0;
			return 1;
		}
		if (carte.getValeur().equals(Valeur.DIX) || carte.getValeur().equals(Valeur.AS))
			return -1;
		return this.getValeur().ordinal() - carte.getValeur().ordinal();
	}

}
