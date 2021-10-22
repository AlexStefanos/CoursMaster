package up.mi.jgm.td02.geometrie;

/**
 * Represente un vecteur du plan
 * 
 * @author Jean-Guy Mailly
 *
 */
public class Vecteur {
	/**
	 * L'origine du vecteur
	 */
	private Point origine;

	/**
	 * La cible du vecteur
	 */
	private Point cible;

	/**
	 * Construit un vecteur en fonction des deux points qui le definissent
	 * 
	 * @param origine l'origine du vecteur
	 * @param cible   la cible du vecteur
	 */
	public Vecteur(Point origine, Point cible) {
		this.origine = origine;
		this.cible = cible;
	}

	/**
	 * Construit le vecteur null
	 */
	public Vecteur() {
		this(new Point(), new Point());
	}

	/**
	 * Permet d'obtenir l'origine du vecteur
	 * 
	 * @return l'origine du vecteur
	 */
	public Point getOrigine() {
		return origine;
	}

	/**
	 * Permet d'obtenir la cible du vecteur
	 * 
	 * @return la cible du vecteur
	 */
	public Point getCible() {
		return cible;
	}

	@Override
	public boolean equals(Object o) {
		if ((o == null) || (!(o instanceof Vecteur)))
			return false;
		if (o == this)
			return true;
		
		Vecteur vec = (Vecteur) o;
		double abs = vec.getCible().getAbscisse() - vec.getOrigine().getAbscisse();
		double ord = vec.getCible().getOrdonnee() - vec.getOrigine().getOrdonnee();
		
		return ((this.cible.getAbscisse() - this.origine.getAbscisse()) == abs)
				&& ((this.cible.getOrdonnee() - this.origine.getOrdonnee()) == ord);
	}
}
