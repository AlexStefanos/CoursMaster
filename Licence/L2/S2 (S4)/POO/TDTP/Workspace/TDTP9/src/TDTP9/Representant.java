package TDTP9;

public class Representant {
	private static final double POURCENTAGE_VENDEUR = 0.25;
	
	public Representant(double chiffreA, int age, String nom, String prenom) {
		super(nom, prenom, age, chiffreA);
	}
	
	private double calculerSalaire() {
		return (POURCENTAGE_VENDEUR * super.getChiffreAffaire());
	}
	
	public String getTitre() {
		return ("Le représentant : ");
	}
}
