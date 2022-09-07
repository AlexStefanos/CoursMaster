package up.mi.jgm.td06.compteurclics;

public class Compteur {
	private int valeur;

	public Compteur() {
		valeur = 0;
	}

	public void incrementer() {
		valeur++;
	}

	public int getValeur() {
		return valeur;
	}

}
