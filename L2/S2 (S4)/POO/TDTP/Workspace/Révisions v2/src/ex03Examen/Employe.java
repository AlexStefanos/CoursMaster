package ex03Examen;

public class Employe implements Comparable {
	private String nom;
	private double salaire;
	
	public Employe (String nom, double salaire) {
		this.nom = nom;
		this.salaire = salaire;
	}
	
	public String toString() {
		return ("Nom : " + nom + " Salaire : " + salaire);
	}
	
	public void afficher() {
		System.out.println(this);
	}
	
	public boolean isPlusGrand(Object o) {
		return (o instanceof Employe && (this.nom.compareToIgnoreCase(((Employe)o).nom))>0);
	}
}
