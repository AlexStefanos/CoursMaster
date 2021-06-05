package ex01Examen;

public abstract class Table {
	private String couleur;
	private int annee;
	
	public Table (String couleur, int annee) {
		this.couleur = couleur;
		this.annee = annee;
	}
	
	public void afficher() {
		System.out.println("Couleur : " + couleur);
		System.out.println("Annee : " + annee);
	}
	
	public abstract double surface();
	
	public String getType() {
		return("Table");
	}
}
