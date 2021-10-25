package up.mi.jgm.td03.geometry;

import up.mi.jgm.td02.geometrie.Vecteur;

/**
 * Cette classe represente une notion abstraite de figure geometrique
 * 
 * @author Jean-Guy Mailly
 *
 */
public abstract class Figure {

	/**
	 * Calcule le perimetre de la figure
	 * 
	 * @return le perimetre de la figure
	 */
	public abstract double perimetre();

	/**
	 * Calcule la surface de la figure
	 * 
	 * @return la surface de la figure
	 */
	public abstract double surface();

	/**
	 * Effectue la translation de la figure par un vecteur donne
	 * @param v le vecteur de la translation
	 * @return une nouvelle figure qui correspond a la translation de cette figure par v
	 */
	public abstract Figure translation(Vecteur v);

	@Override
	public String toString() {
		StringBuilder build = new StringBuilder(this.getClass().getName());
		build.append(" : perimetre = ");
		build.append(perimetre());
		build.append(" ; surface = ");
		build.append(surface());
		return build.toString();

	}
}
