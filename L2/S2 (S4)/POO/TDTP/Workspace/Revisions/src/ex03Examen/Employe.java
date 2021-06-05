package ex03Examen;

public class Employe implements Comparable {
	private String nom;
	private double salaire;
	
	public Employe(String nom, double salaire) {
		this.nom = nom;
		this.salaire = salaire;
	}
	
	public String toString() {
		String result = "L'Employé nommé " + nom + "a un salaire de " + salaire + ".";
		return result;
	}
	
	public void afficher() {
		System.out.println(this);
	}
	
	public boolean isPlusGrand(Object o) {
		return (o instanceof Employe && (this.nom.compareToIgnoreCase(((Employe)o).nom))>0);
	}
}
