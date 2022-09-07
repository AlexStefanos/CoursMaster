package TDTP9;

public abstract class Employe {
	private String nom;
	private String prenom;
	private int age;
	
	public Employe (String nom, String prenom, int age) {
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
	}
	
	abstract double calculerSalaire();
	
	public String getTitre() {
		return "L'employé";
	}
	
	public String toString() {
		String str = (getTitre() + prenom + " " + nom + " " + age);
		return str;
	}
}
