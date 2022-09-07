package ex01Examen;

public class TableRonde extends Table {
	private double diametre;
	
	public TableRonde(String couleur, int annee, double diametre) {
		super(couleur,annee);
		this.diametre = diametre;
	}
	
	public void afficher() {
		super.afficher();
		System.out.println("Diametre : " + diametre);
	}
	
	public double surface() {
		double rayon = diametre/2; //attention, il faut avoir un double
		return (Math.PI*rayon*rayon);
	}
	
	public String getType() {
		return ("Table Ronde");
	}
}
