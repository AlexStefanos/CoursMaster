package up.mi.jgm.td02;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UtilListes {
	/**
	 * Determine le plus grand element d'une liste d'entiers
	 * 
	 * @param liste la liste d'entiers
	 * @return le plus grand element de liste, ou null si la liste est vide
	 */
	public static Integer maxListeInteger(List<Integer> liste) {
		if (liste.isEmpty())
			return null;
		Integer max = liste.get(0);
		for (int i = 1; i < liste.size(); i++) {
			if (liste.get(i) > max)
				max = liste.get(i);
		}
		return max;
	}

	/**
	 * Determine la moyenne d'une liste d'entiers
	 * 
	 * @param liste la liste d'entiers
	 * @return la moyenne des elements de liste, ou -1 si la liste est vide
	 */
	public static double moyenneListeInteger(List<Integer> liste) {
		if (liste.isEmpty())
			return -1;
		double somme = 0;
		for (Integer i : liste) {
			somme += i;
		}
		return somme / liste.size();
	}

	/**
	 * Trie une liste d'entiers
	 * 
	 * @param liste la liste a trier
	 * @return une copie triee de la liste
	 */
	public static List<Integer> triListeInteger(List<Integer> liste) {
		List<Integer> tmpListe = new LinkedList<Integer>(liste);
		for (int i = 0; i < tmpListe.size() - 1; i++) {
			int indiceMin = rechercheIndiceMinInteger(tmpListe, i);
			if (indiceMin != i) {
				echangerInteger(tmpListe, i, indiceMin);
			}
		}
		return tmpListe;
	}

	/**
	 * Recherche l'indice du plus petit element d'une liste d'Integer a partir d'une
	 * position donnee
	 * 
	 * @param liste     la liste ou rechercher le plus petit element
	 * @param indiceMin la premiere position ou chercher le plus petit element
	 * @return le plus petit element de liste a partir de la position indiceMin
	 */
	private static int rechercheIndiceMinInteger(List<Integer> liste, int indiceMin) {
		for (int i = indiceMin + 1; i < liste.size(); i++) {
			if (liste.get(i) < liste.get(indiceMin)) {
				indiceMin = i;
			}
		}
		return indiceMin;
	}

	/**
	 * Echange deux elements dans une liste d'Integer
	 * 
	 * @param liste la liste ou effectuer l'echange
	 * @param i     l'indice du premier element a echanger
	 * @param j     l'indice du second element a echanger
	 */
	private static void echangerInteger(List<Integer> liste, int i, int j) {
		Integer tmp = liste.get(i);
		liste.set(i, liste.get(j));
		liste.set(j, tmp);
	}

	/**
	 * Determine le plus grand element d'une liste d'objets Comparable
	 * 
	 * @param liste la liste
	 * @return le plus grand element de liste au sens de compareTo(), ou null si la
	 *         liste est vide
	 */
	public static <T extends Comparable<T>> T max(List<T> liste) {
		if (liste.isEmpty())
			return null;
		T max = liste.get(0);
		for (int i = 1; i < liste.size(); i++) {
			if (liste.get(i).compareTo(max) > 0)
				max = liste.get(i);
		}
		return max;
	}

	/**
	 * Trie une lsite d'elements comparables
	 * 
	 * @param liste la liste a trier
	 * @return une copie de la liste triee grace a compareTo()
	 */
	public static <T extends Comparable<T>> List<T> tri(List<T> liste) {
		List<T> tmpListe = new LinkedList<T>(liste);
		for (int i = 0; i < tmpListe.size() - 1; i++) {
			int indiceMin = rechercheIndiceMin(tmpListe, i);
			if (indiceMin != i) {
				echanger(tmpListe, i, indiceMin);
			}
		}
		return tmpListe;
	}

	/**
	 * Echange deux elements d'une liste
	 * 
	 * @param liste la liste ou echanger deux elements
	 * @param i     l'indice du premier element a echanger
	 * @param j     l'indice du second element a echanger
	 */
	private static <T extends Comparable<T>> void echanger(List<T> liste, int i, int j) {
		T tmp = liste.get(i);
		liste.set(i, liste.get(j));
		liste.set(j, tmp);
	}

	/**
	 * Recherche l'indice du plus petit element d'une liste de Comparable a partir
	 * d'une position donnee
	 * 
	 * @param liste     la liste ou rechercher le plus petit element
	 * @param indiceMin la premiere position ou chercher le plus petit element
	 * @return le plus petit element, selon compareTo(), de liste a partir de la
	 *         position indiceMin
	 * 
	 */
	private static <T extends Comparable<T>> int rechercheIndiceMin(List<T> liste, int indiceMin) {
		for (int i = indiceMin + 1; i < liste.size(); i++) {
			if (liste.get(i).compareTo(liste.get(indiceMin)) < 0) {
				indiceMin = i;
			}
		}
		return indiceMin;
	}

	public static void main(String[] args) {
		List<Integer> lInt = new ArrayList<Integer>();
		lInt.add(3);
		lInt.add(2);
		lInt.add(7);
		lInt.add(1);
		lInt.add(4);
		System.out.println("max(lInt) = " + maxListeInteger(lInt));
		System.out.println("moyenne(lInt) = " + moyenneListeInteger(lInt));
		System.out.println("tri(lInt) = " + triListeInteger(lInt));

		List<String> lString = new ArrayList<String>();
		lString.add("bonjour");
		lString.add("toto");
		lString.add("au revoir");
		lString.add("paris");
		lString.add("informatique");
		System.out.println("max(lString) = " + max(lString));
		System.out.println("tri(lString) = " + tri(lString));
	}
}
