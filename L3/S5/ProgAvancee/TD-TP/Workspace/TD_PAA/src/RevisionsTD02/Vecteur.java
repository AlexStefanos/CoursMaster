package RevisionsTD02;

public class Vecteur {
	private Point A, B;
	
	public Vecteur(Point A, Point B) {
		this.A = A;
		this.B = B;
	}
	
	public boolean equals(Vecteur v2) {
		if((B.x - A.x) == (v2.B.x - v2.A.x) && ((B.y - A.y) == (v2.B.x - v2.A.y)))
			return(true);
		else
			return(false);
	}
	
	
}
