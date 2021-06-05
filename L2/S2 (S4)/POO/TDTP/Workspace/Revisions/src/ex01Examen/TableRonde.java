package ex01Examen;

public abstract class TableRonde extends Table{
	double diametre;
	
	public TableRonde (String couleur, int annee, double diametre) {
		super(couleur, annee);
		this.diametre = diametre;
	}
	
	public void afficher() {
		super.afficher();
		System.out.println("Et de diamètre : " + diametre);
	}
	
	public String getType() {
		return "Table Ronde";
	}
	
	public double surface() {
		double a = diametre/2;
		return (Math.PI*a*a);
	}
}
