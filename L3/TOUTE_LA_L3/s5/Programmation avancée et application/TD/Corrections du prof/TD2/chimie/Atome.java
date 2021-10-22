package up.mi.jgm.td02.chimie;

public class Atome {
	private int nbNeutrons;
	private int nbProtons;
	private String nom;
	private String symbole;

	public Atome(int nbNeutrons, int nbProtons, String nom, String symbole) {
		this.nbNeutrons = nbNeutrons;
		this.nbProtons = nbProtons;
		this.nom = nom;
		this.symbole = symbole;
	}

	public int getNbNeutrons() {
		return nbNeutrons;
	}

	public int getNbProtons() {
		return nbProtons;
	}

	public String getNom() {
		return nom;
	}

	public String getSymbole() {
		return symbole;
	}

	@Override
	public String toString() {
		return nom + " (" + symbole + ")" + " Z = " + nbProtons + " ; N = " + nbNeutrons;
	}
}
