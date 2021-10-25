package up.mi.jgm.td03.operations;

/**
 * Represente un operateur mathematique abstrait
 * 
 * @author Jean-Guy Mailly
 *
 */
public abstract class Operateur {
	/**
	 * L'arite de l'operateur
	 */
	private int arite;

	/**
	 * Construit un operateur en fonction de son arite
	 * 
	 * @param arite l'arite de l'operateur
	 */
	public Operateur(int arite) {
		this.arite = arite;
	}

	/**
	 * Evalue cet operateur sur ses operandes
	 * @return la valeur de cette operateur pour ses operandes
	 */
	public abstract double evaluer();

	/**
	 * Permet d'obtenir l'arite de l'operateur
	 * @return l'arite de l'operateur
	 */
	public int getArite() {
		return arite;
	}
	
	/**
	 * Augmente l'arite de l'operateur
	 */
	protected void incrementeArite() {
		arite++;
	}
}
