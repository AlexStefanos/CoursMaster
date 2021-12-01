package up.mi.jgm.td03.geometry;

import up.mi.jgm.td02.geometrie.Point;
import up.mi.jgm.td02.geometrie.Vecteur;

/**
 * Clase qui represente un losange dans le plan
 * @author Jean-Guy Mailly
 *
 */
public class Losange extends Quadrilatere {

	/**
	 * Construit un losange en fonction de ses sommets
	 * @param sommet1 le premier sommet	
	 * @param sommet2 le deuxieme sommet
	 * @param sommet3 le troisieme sommet
	 * @param sommet4 le quatrieme sommet
	 */
	public Losange(Point sommet1, Point sommet2, Point sommet3, Point sommet4) {
		super(sommet1, sommet2, sommet3, sommet4);
		if (!verificationPoints(sommet1, sommet2, sommet3, sommet4)) {
			throw new IllegalArgumentException("Ces points ne forment pas un losange.");
		}
	}

	@Override
	public double surface() {
		double d1 = sommet1.distance(sommet3);
		double d2 = sommet2.distance(sommet4);
		return (d1 * d2) / 2;
	}

	/**
	 * Verifie si quatre points forment un losange
	 * @param sommet1 le premier point
	 * @param sommet2 le deuxieme point
	 * @param sommet3 le troisieme point
	 * @param sommet4 le quatrieme point
	 * @return true si et seulement si les distances entre les points sont egales
	 */
	private static boolean verificationPoints(Point sommet1, Point sommet2, Point sommet3, Point sommet4) {
		double d1 = sommet1.distance(sommet2);
		double d2 = sommet2.distance(sommet3);
		double d3 = sommet3.distance(sommet4);
		double d4 = sommet4.distance(sommet1);
		return (d1 == d2) && (d2 == d3) && (d3 == d4);
	}

	@Override
	public Figure translation(Vecteur v) {
		return new Losange(sommet1.translation(v), sommet2.translation(v), sommet3.translation(v),
				sommet4.translation(v));
	}
}
