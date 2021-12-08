package up.mi.as.td02;

public class Disque {
	private Point centreDisque, pt;
	private double rayon;
	
	public Disque(Point centreDisque, Point pt) {
		centreDisque = this.centreDisque;
		this.pt = pt;
		this.rayon = centreDisque.distance(pt);
	}
	
	public boolean estDansDisque(Point ptVerif) {
		if(ptVerif == null)
			return false;
		else if(ptVerif.distance(centreDisque) <= rayon)
			return true;
		else
			return false;
	}
	
	public boolean estIntersecte(Disque dqVerif) {
		if(dqVerif == null)
			return false;
		else if(centreDisque.distance(dqVerif.centreDisque) < (dqVerif.rayon + rayon))
			return true;
		else
			return false;
	}
}
