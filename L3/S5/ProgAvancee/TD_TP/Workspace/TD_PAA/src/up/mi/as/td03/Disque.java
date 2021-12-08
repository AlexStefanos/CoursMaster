package up.mi.as.td03;

import up.mi.as.td02.Vecteur;

public class Disque extends Figure {
	private double rayon;
	
	public Disque(double rayon) {
		this.rayon = rayon;
	}
	
	@Override
	public double perimetre() {
		return(2*rayon*Math.PI);
	}

	@Override
	public double surface() {
		return(Math.PI*rayon*rayon);
	}
	
	@Override
	public Figure translation(Vecteur v) {
		return(null);
	}
}
