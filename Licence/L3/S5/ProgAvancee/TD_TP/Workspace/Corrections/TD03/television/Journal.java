package up.mi.jgm.td03.television;

/**
 * Represente un journal televise
 * @author Jean-Guy Mailly
 *
 */
public class Journal extends Programme {
	
	/**
	 * Le nom du presentateur du journal
	 */
	private String presentateur;

	/**
	 * Construit un journal
	 * @param hD l'heure de debut
	 * @param hF l'heure de fin
	 * @param presentateur le nom du presentateur
	 */
	public Journal(int hD, int hF, String presentateur) {
		super(hD, hF);
		this.presentateur = presentateur;
	}

	@Override
	public String toString() {
		return "Journal presente par " + presentateur + " de " + getHeureDebut() + "h00 a " + getHeureFin() + "h00.";
	}

}
