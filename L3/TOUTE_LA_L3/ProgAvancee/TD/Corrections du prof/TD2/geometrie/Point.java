package up.mi.jgm.td02.geometrie;

/**
 * Represente un point dans le plan
 * 
 * @author Jean-Guy Mailly
 *
 */
public class Point {
	/**
	 * L'abscisse du point
	 */
	private double abscisse;

	/**
	 * L'ordonnee du point
	 */
	private double ordonnee;

	/**
	 * Construit un point en fonction de ses coordonnees
	 * 
	 * @param abscisse l'abscisse du point
	 * @param ordonnee l'ordonnee du point
	 */
	public Point(double abscisse, double ordonnee) {
		this.abscisse = abscisse;
		this.ordonnee = ordonnee;
	}

	/**
	 * Cree un Point correspondant a l'origine du repere dans le plan
	 */
	public Point() {
		this(0, 0);
	}

	/**
	 * Permet d'obtenir l'abscisse du point
	 * 
	 * @return l'abscisse du point
	 */
	public double getAbscisse() {
		return abscisse;
	}

	/**
	 * Permet d'obtenir l'ordonnee du point
	 * 
	 * @return
	 */
	public double getOrdonnee() {
		return ordonnee;
	}

	/**
	 * Calcule la distance entre ce Point et un autre Point
	 * 
	 * @param autre le Point avec lequel on veut determiner la distance
	 * @return la distance entre this et autre
	 */
	public double distance(Point autre) {
		return Math.sqrt((autre.abscisse - this.abscisse) * (autre.abscisse - this.abscisse)
				+ (autre.ordonnee - this.ordonnee) * (autre.ordonnee - this.ordonnee));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(abscisse);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(ordonnee);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (Double.doubleToLongBits(abscisse) != Double.doubleToLongBits(other.abscisse))
			return false;
		if (Double.doubleToLongBits(ordonnee) != Double.doubleToLongBits(other.ordonnee))
			return false;
		return true;
	}

	/**
	 * Effectue la translation d'un point par un vecteur donne
	 * 
	 * @param vec le vecteur de la translation
	 * @return un nouveau point issu de la translation de this par vec
	 */
	public Point translation(Vecteur vec) {
		double nouvelleAbs = this.abscisse + (vec.getCible().getAbscisse() - vec.getOrigine().getAbscisse());
		double nouvelleOrd = this.ordonnee + (vec.getCible().getOrdonnee() - vec.getOrigine().getOrdonnee());
		return new Point(nouvelleAbs, nouvelleOrd);
	}

	@Override
	public String toString() {
		return "(" + abscisse + "," + ordonnee + ")";
	}
}
