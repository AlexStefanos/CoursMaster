package up.mi.jgm.td03.operations;

/**
 * Represente une valeur reelle comme un operateur d'arite nulle
 * @author Jean-Guy Mailly
 *
 */
public class Valeur extends Operateur {
	/**
	 * La valeur reelle
	 */
	private double valeur ;
	
	/**
	 * Construit l'operateur valeur
	 * @param val la valeur reelle representee
	 */
	public Valeur(double val) {
		super(0);
		valeur = val ;
	}

	@Override
	public double evaluer() {
		return valeur;
	}
	
	@Override
	public String toString() {
		return "" + valeur ;
	}

}
