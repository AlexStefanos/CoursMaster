package ex01Examen;

public abstract class Table {
	private String couleur;
	private int annee;
	
	public Table(String couleur, int annee) {
		this.couleur = couleur;
		this.annee = annee;
	}
	
	public void afficher() {
		System.out.println("La table affichée est  une " + getType() + ", de couleur " + couleur + "fabriquée en " + annee + ".");
	}
	
	public String getType() {
			return "Table";
	}
	
	public abstract double surface();
}
