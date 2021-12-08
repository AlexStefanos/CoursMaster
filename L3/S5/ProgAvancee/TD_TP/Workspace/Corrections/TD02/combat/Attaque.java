package up.mi.jgm.td02.combat;

public class Attaque {

	/***
	 * Nom de l'attaque
	 */
	private String nom;
	
	/***
	 * Force de l'attaque
	 */
	private int force;
	
	/***
	 * Nombre d'utilisations encore disponible de l'attaque
	 */
	private int nbUtilisation;
	
	/***
	 * Constructeur de la classe Attaque
	 * @param n Nom de l'attaque
	 * @param f Force de l'attaque
	 * @param nbU Nombre d'utilisations de l'attaque
	 */
	public Attaque(String n, int f, int nbU) {
		this.nom = n;
		this.setForce(f);
		this.setNbUtilisation(nbU);
	}

	/***
	 * Décrémente le nombre d'utilisation possible de l'attaque
	 */
	public void utilise() {
		this.nbUtilisation--;
	}
	
	public int getForce() {
		return force;
	}

	public void setForce(int force) {
		this.force = force;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public int getNbUtilisation() {
		return nbUtilisation;
	}


	public void setNbUtilisation(int nbUtilisation) {
		this.nbUtilisation = nbUtilisation;
	}
	
	@Override
	public String toString() {
		return this.nom + " (F:" + this.force + " & CR:" + this.nbUtilisation + ")";
	}
	
}
