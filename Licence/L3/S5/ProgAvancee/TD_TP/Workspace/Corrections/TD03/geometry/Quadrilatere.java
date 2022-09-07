package up.mi.jgm.td03.geometry;

import up.mi.jgm.td02.geometrie.Point;

/**
 * Classe qui represente un quadrilatere dans le plan
 * @author Jean-Guy Mailly
 *
 */
public abstract class Quadrilatere extends Figure {
	/**
	 * Le premier sommet du quadrilatere
	 */
	protected Point sommet1;
	
	/**
	 * Le deuxieme sommet du quadrilatere
	 */
	protected Point sommet2;
	
	/**
	 * Le troisieme sommet du quadrilatere
	 */
	protected Point sommet3;
	
	/**
	 * Le quatrieme sommet du quadrilatere
	 */
	protected Point sommet4;

	/**
	 * Construit un quadrilatere en fonction de ses quatre sommets
	 * @param sommet1 le premier sommet
	 * @param sommet2 le deuxieme sommet
	 * @param sommet3 le troisieme sommet
	 * @param sommet4 le quatrieme sommet
	 */
	public Quadrilatere(Point sommet1, Point sommet2, Point sommet3, Point sommet4) {
		this.sommet1 = sommet1;
		this.sommet2 = sommet2;
		this.sommet3 = sommet3;
		this.sommet4 = sommet4;
	}

	@Override
	public double perimetre() {
		return sommet1.distance(sommet2) + sommet2.distance(sommet3) + sommet3.distance(sommet4)
				+ sommet4.distance(sommet1);
	}

}
