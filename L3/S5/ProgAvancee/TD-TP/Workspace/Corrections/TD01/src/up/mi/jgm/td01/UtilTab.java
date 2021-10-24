package up.mi.jgm.td01;

/**
 * Classe utilitaire qui permet la manipulation de tableaux
 * 
 * @author Jean-Guy Mailly
 *
 */
public class UtilTab {

	/**
	 * Permet l'affichage des elements d'un tableau, en ligne ou en colonne
	 * 
	 * @param tab     le tableau a afficher
	 * @param enLigne booleen qui vaut true si et seulement si le tableau doit etre
	 *                affiche en ligne
	 */
	public static void affichageTableau(double[] tab, boolean enLigne) {
		for (int i = 0; i < tab.length; i++) {
			System.out.print(tab[i]);
			if (enLigne) {
				System.out.print(" ");
			} else {
				System.out.println();
			}
		}
		System.out.println();
	}

	/**
	 * Methode qui permet de determine si une valeur appartient au tableau
	 * 
	 * @param val la valeur dont on teste l'appartenance
	 * @param tab le tableau
	 * @return true si et seulement si val appartient a tab
	 */
	public static boolean appartient(double val, double[] tab) {
		for (int i = 0; i < tab.length; i++) {
			if (tab[i] == val) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Methode qui determine l'element minimal d'un tableau
	 * 
	 * @param tab le tableau dont on cherche le minimum
	 * @return le minimum de tab
	 */
	public static double min(double[] tab) {
		double tmpMin = tab[0];
		for (int i = 1; i < tab.length; i++) {
			if (tab[i] < tmpMin) {
				tmpMin = tab[i];
			}
		}
		return tmpMin;
	}

	/**
	 * Methode qui determine l'element maximal d'un tableau
	 * 
	 * @param tab le tableau dont on cherche le maximum
	 * @return le maximum de tab
	 */
	public static double max(double[] tab) {
		double tmpMax = tab[0];
		for (int i = 1; i < tab.length; i++) {
			if (tab[i] > tmpMax) {
				tmpMax = tab[i];
			}
		}
		return tmpMax;
	}

	/**
	 * Methode qui calcule la somme des elements d'un tableau
	 * 
	 * @param tab le tableau dont on calcule la somme
	 * @return la somme des elements de tab
	 */
	public static double somme(double[] tab) {
		double tmpSomme = 0;
		for (int i = 0; i < tab.length; i++) {
			tmpSomme += tab[i];
		}
		return tmpSomme;
	}

	/**
	 * Methode qui trie un tableau par selection. Le tri par selection consiste a
	 * chercher l'element le plus petit du tableau, et a l'echanger avec le premier
	 * element. Le processus est reitere pour le deuxieme plus petit qui est echange
	 * avec le deuxieme element du tableau, etc, jusqu'a ce que le tableau soit
	 * trie.
	 * 
	 * @param tab le tableau a trier.
	 */
	public static void triParSelection(double[] tab) {
		for (int i = 0; i < tab.length - 1; i++) {
			int indiceMin = rechercheIndicePlusPetit(tab, i);
			if (indiceMin != i) {
				echanger(tab, i, indiceMin);
			}
		}
	}

	/**
	 * Methode qui permet d'obtenir l'indice du plus petit element d'un tableau a
	 * partir d'une position donnee
	 * 
	 * @param tab       le tableau dont on cherche le plus petit element
	 * @param indiceMin la position a partir de laquelle on recherche le plus petit
	 *                  element
	 * @return l'indice du plus petit element de tab situe apres la position
	 *         indiceMin
	 */
	private static int rechercheIndicePlusPetit(double[] tab, int indiceMin) {
		for (int j = indiceMin + 1; j < tab.length; j++) {
			if (tab[j] < tab[indiceMin]) {
				indiceMin = j;
			}
		}
		return indiceMin;
	}

	/**
	 * Methode qui echange deux elements d'un tableau, donnes par leur position
	 * 
	 * @param tab le tableau dans lequel on echange deux elements
	 * @param i   l'indice du premier element a echanger
	 * @param j   l'indice du second element a echanger
	 */
	private static void echanger(double[] tab, int i, int j) {
		double tmpVal = tab[i];
		tab[i] = tab[j];
		tab[j] = tmpVal;
	}

	/**
	 * Determine la moyenne des elements d'un tableau de nombres decimaux
	 * 
	 * @param tab le tableau dont on veut la moyenne
	 * @return la moyenne des elements de tab
	 */
	public static double moyenne(double[] tab) {
		return somme(tab) / tab.length;
	}

	/**
	 * Determine la mediane des elements d'un tableau de nombres decimaux
	 * 
	 * @param tab le tableau dont on veut la mediane
	 * @return la mediane des elements de tab
	 */
	public static double mediane(double[] tab) {
		double[] copie = copieTab(tab);
		triParSelection(copie);
		int milieu = copie.length / 2;
		if ((copie.length % 2) == 0) {
			return (copie[milieu - 1] + copie[milieu]) / 2;
		} else {
			return copie[milieu];
		}
	}

	/**
	 * Cree une copie d'un tableau de double
	 * 
	 * @param tab le tableau a copier
	 * @return un nouvel objet equivalent a tab
	 */
	private static double[] copieTab(double[] tab) {
		double[] copie = new double[tab.length];
		for (int i = 0; i < tab.length; i++)
			copie[i] = tab[i];
		return copie;
	}

	/**
	 * Determine une moyenne ponderee
	 * 
	 * @param valeurs les valeurs dont on veut la moyenne
	 * @param coefs   les coefficients associes aux valeurs
	 * @return la moyenne des valeurs ponderees par les coefs
	 */
	public static double moyennePonderee(double[] valeurs, int[] coefs) {
		double sommeValeursPonderees = 0;
		int sommeCoefs = 0;

		for (int i = 0; i < valeurs.length; i++) {
			sommeValeursPonderees += valeurs[i] * coefs[i];
			sommeCoefs += coefs[i];
		}
		return sommeValeursPonderees / sommeCoefs;
	}

	/**
	 * Prend en entree un tableau t tel que les valeurs sont triees entre iMin
	 * (inclus) et iMilieu (exclu) d'une part, et entre iMilieu (inclus) et iMax
	 * (exclus) d'autre part. Modifie le tableau pour qu'il soit trie entre iMin
	 * (inclus) et iMax (exclus).
	 * 
	 * @param t       le tableau dans lequel on fusionne les donnees
	 * @param iMin    l'indice de debut des zones a fusionner
	 * @param iMilieu l'indice du pivot entre les zones a fusionner
	 * @param iMax    l'indice de la fin des zones a fusionner
	 */
	public static void fusion(int[] t, int iMin, int iMilieu, int iMax) {
		int[] tmpTab = new int[iMax - iMin];

		int i, j, cpt;
		for (cpt = 0, i = iMin, j = iMilieu; (i < iMilieu) && (j < iMax);) {
			if (t[i] < t[j]) {
				tmpTab[cpt] = t[i];
				cpt++;
				i++;
			} else {
				tmpTab[cpt] = t[j];
				cpt++;
				j++;
			}
		}

		while (i < iMilieu) {
			tmpTab[cpt] = t[i];
			cpt++;
			i++;
		}

		while (j < iMax) {
			tmpTab[cpt] = t[j];
			cpt++;
			j++;
		}

		for (i = 0; i < tmpTab.length; i++) {
			t[iMin + i] = tmpTab[i];
		}
	}

	/**
	 * Trie un tableau d'entier par fusion entre les indices iMin (inclus) et iMax
	 * (exclus)
	 * 
	 * @param t    le tableau a trier
	 * @param iMin l'indice de debut de la zone a trier
	 * @param iMax l'indice de fin de la zone a trier
	 */
	public static void triFusion(int[] t, int iMin, int iMax) {
		if (iMax - iMin > 1) {
			int iMilieu = (iMin + iMax) / 2;
			triFusion(t, iMin, iMilieu);
			triFusion(t, iMilieu, iMax);
			fusion(t, iMin, iMilieu, iMax);
		}
	}
}