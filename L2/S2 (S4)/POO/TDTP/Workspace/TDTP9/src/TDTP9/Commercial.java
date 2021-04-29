package TDTP9;

public abstract class Commercial extends Employe{
	private double chiffreA;
	
	public Commercial(double chiffreA, String nom, String prenom, int age) {
		super(prenom, nom, age);
		this.chiffreA = chiffreA;
	}
	
	public double getChiffreAffaire() {
		return chiffreA;
	}
}
