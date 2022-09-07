package up.mi.jgm.td03.television;

/**
 * Represente une emission de divertissement
 * 
 * @author Jean-Guy Mailly
 *
 */
public class Divertissement extends Programme {
	/**
	 * Le nom du presentateur du programme
	 */
	private String presentateur;

	/**
	 * Le nom de l'emission
	 */
	private String nom;

	/**
	 * Cree une emission de divertissement en fonction de son heure de debut, son
	 * presentateur et son nom
	 * 
	 * @param hD l'heure de debut de l'emission 
	 * @param presentateur le nom du presentateur
	 * @param nom le nom de l'emission
	 */
	public Divertissement(int hD, String presentateur, String nom) {
		super(hD, hD + 2);
		this.presentateur = presentateur;
		this.nom = nom;
	}

	@Override
	public String toString() {
		return nom + ", presente par " + presentateur + " de " + getHeureDebut() + "h00 à " + getHeureFin() + "h00.";
	}

}
