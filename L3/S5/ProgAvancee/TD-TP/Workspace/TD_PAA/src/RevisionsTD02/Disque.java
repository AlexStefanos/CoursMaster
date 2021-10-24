package RevisionsTD02;

public class Disque {
	private Point pCentre;
	private double rayon;
	
	public Disque(Point pCentre, double rayon) {
		this.pCentre = pCentre;
		this.rayon = rayon;
	}
	
	public boolean appartientDisque(Point p2) {
		if(pCentre.distance(p2) <= rayon)
			return(true);
		else
			return(false);
	}
	
	public boolean intersectionDisques(Disque d2) {
		if(pCentre.distance(d2.pCentre) <= (this.rayon + d2.rayon))
			return(true);
		else
			return(false);
	}
	
	
}
