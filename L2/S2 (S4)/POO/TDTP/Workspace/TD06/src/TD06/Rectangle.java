package TD06;

public class Rectangle extends Polygone{
	//Constructeurs
	//Deux points opposés
	public Rectangle(Point p1, Point p2) {
		super(new Point[] {p1, new Point(p2.getX(), p1.getY()), p2, new Point(p1.getX(), p1.getY())});
	}
	
	//Un point une longueur et une largeur
	public Rectangle(Point p, float longueur, float largeur) {
		this(p, new Point(p.getX()+longueur, p.getY() + largeur));
	}
	
	//Redéfinition
	protected String nomFigure() {
		return "Rectangle";
	}
	
	/*
	public static void main(String[] args) {
		Point p1 = new Point(0,0);
		float cote = 1;
		Carre c = new Carre(p1, cote);
		c.affiche();
		System.out.println(c.perimetre());
	}	*/
}
