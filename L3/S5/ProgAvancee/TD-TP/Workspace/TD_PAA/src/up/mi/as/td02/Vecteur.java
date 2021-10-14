package up.mi.as.td02;

public class Vecteur {
	private Point p1,p2;
	
	public Vecteur(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public boolean equals(Vecteur vecVerif) {
		if(vecVerif == null)
			return false;
		else if(((p2.getX() - p1.getX()) == (vecVerif.p2.getX() - vecVerif.p1.getX())) && 
				(p2.getY() - p1.getY() == vecVerif.p2.getY() - vecVerif.p1.getY()))
			return true;
		else
			return false;
	}
	
	public int getVectP1X() {
		return(p1.getX());
	}
	
	public int getVectP1Y() {
		return(p1.getY());
	}
	
	public int getVectP2X() {
		return(p2.getX());
	}
	
	public int getVectP2Y() {
		return(p2.getY());
	}
}