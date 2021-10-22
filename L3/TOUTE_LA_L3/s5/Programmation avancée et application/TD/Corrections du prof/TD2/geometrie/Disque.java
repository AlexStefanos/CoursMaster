package up.mi.jgm.td02.geometrie;

/**
 * Represente un disque dans un plan
 * 
 * @author Jean-Guy Mailly
 *
 */
public class Disque {
	/**
	 * Le centre du cercle
	 */
	private Point centre;

	/**
	 * Le rayon du cercle
	 */
	private double rayon;

	/**
	 * Construit un disque en fonction de son centre et son rayon
	 * 
	 * @param centre le centre du disque
	 * @param rayon  le rayon du disque
	 */
	public Disque(Point centre, double rayon) {
		this.centre = centre;
		this.rayon = rayon;
	}

	/**
	 * Construit un disque de rayon donne, dont le centre est l'origine du repere
	 * dans le plan
	 * 
	 * @param rayon le rayon du disque
	 */
	public Disque(double rayon) {
		this(new Point(), rayon);
	}

	/**
	 * Permet d'obtenir le centre du disque
	 * 
	 * @return le centre du disque
	 */
	public Point getCentre() {
		return centre;
	}

	/**
	 * Permet d'obtenir le rayon du disque
	 * 
	 * @return
	 */
	public double getRayon() {
		return rayon;
	}

	/**
	 * Verifie si un point appartient au disque ou pas
	 * 
	 * @param p le point a verifier
	 * @return true si et seulement si p appartient au disque
	 */
	public boolean contient(Point p) {
		return (this.centre.distance(p) <= rayon);
	}

	/**
	 * Permet de determiner si deux disques sont en intersection
	 * 
	 * @param autre l'autre disque
	 * @return true si et seulement si this et autre sont en intersection
	 */
	public boolean intersection(Disque autre) {
		return (this.centre.distance(autre.centre) <= (this.rayon + autre.rayon));
	}

	/**
	 * Effectue la translation de ce disque par un vecteur
	 * 
	 * @param vec le vecteur de la translation
	 * @return le nouveau disque issu de la translation de this par vec
	 */
	public Disque translation(Vecteur vec) {
		return new Disque(centre.translation(vec), rayon);
	}

	@Override
	public String toString() {
		return "Disque de centre : " + centre.toString() + " ; rayon : " + rayon;
	}
}
