package up.mi.jgm.td02.geometrie;

public class TestGeometrie {
	public static void main(String[] args) {
		Point p = new Point(3,4);
		Vecteur v = new Vecteur(new Point(1,1), new Point(4,5));
		Disque d = new Disque(p, 4.5);
		System.out.println("Disque : " + d);
		System.out.println("Translation : " + d.translation(v));
	}
}
