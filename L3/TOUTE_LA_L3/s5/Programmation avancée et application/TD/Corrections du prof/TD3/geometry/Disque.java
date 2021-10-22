package up.mi.jgm.td03.geometry;

import up.mi.jgm.td02.geometrie.Point;
import up.mi.jgm.td02.geometrie.Vecteur;

/**
 * Classe qui represente un disque dans un plan
 * @author Jean-Guy Mailly
 *
 */
public class Disque extends Figure {
	/**
	 * Le centre du disque
	 */
	private Point centre;
	
	/**
	 * Le rayon du disque
	 */
	private double rayon;

	/**
	 * Construit un disque en fonction de son centre et son rayon
	 * @param centre
	 * @param rayon
	 */
	public Disque(Point centre, double rayon) {
		this.centre = centre;
		this.rayon = rayon;
	}

	@Override
	public double perimetre() {
		return 2 * Math.PI * rayon;
	}

	@Override
	public double surface() {
		return Math.PI * rayon * rayon;
	}
	
	@Override
	public Figure translation(Vecteur v) {
		return new Disque(centre.translation(v),rayon);
	}

}
