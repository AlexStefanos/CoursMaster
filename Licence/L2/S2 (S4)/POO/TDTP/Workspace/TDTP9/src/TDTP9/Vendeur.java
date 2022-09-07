package TDTP9;

public class Vendeur extends Commercial {
	private static double POURCENTAGE_VENDEUR = 0.2;
	
	public Vendeur(String prenom, String nom, int age, double chiffreA) {
		super(chiffreA, prenom, nom, age);
	}
	
	public double calculerSalaire() {
		return (POURCENTAGE_VENDEUR * getChiffreAffaire());
	}
	
	public String getTitre() {
		return "Le vendeur :";
	}
}
