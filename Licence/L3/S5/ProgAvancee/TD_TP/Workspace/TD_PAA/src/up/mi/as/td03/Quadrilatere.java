package up.mi.as.td03;

import up.mi.as.td02.Point;

public abstract class Quadrilatere extends Figure {
	private Point s1,s2,s3,s4;
	
	public Quadrilatere(Point s1, Point s2, Point s3, Point s4) {
		this.s1 = s1;
		this.s2 = s2;
		this.s3 = s3;
		this.s4 = s4;
	}
	
	@Override
	public double perimetre() {
		return(s1.distance(s2) + s2.distance(s3) + s3.distance(s4) + s4.distance(s1));
	}
}
