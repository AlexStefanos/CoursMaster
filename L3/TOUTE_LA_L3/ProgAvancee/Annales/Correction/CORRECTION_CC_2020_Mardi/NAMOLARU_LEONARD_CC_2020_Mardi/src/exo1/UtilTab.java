package exo1;

public class UtilTab {

	/**
	 * Implementez une methode qui prend en entree un nombre entier, et determine
	 * s'il est plus petit que le plus petit element d'un premier tableau, et plus
	 * grand que le plus grand element d'un second tableau.
	 * 
	 * @param n    le nombre entier
	 * @param tab1 le premier tableau
	 * @param tab2 le second tableau
	 * @return true si et seulement si n est plus petit que le min de tab 1, et plus
	 *         grand que le max de tab2
	 */
	public boolean minMax(int n, int[] tab1, int[] tab2) {
		int minTab1 = tab1[0];
		int maxTab2 = tab2[0];
		int i;
		for(i = 0 ; i < tab1.length ; i++) {
			if(tab1[i] < minTab1)
				minTab1 = tab1[i];
		}
		
		for(i = 0 ; i < tab2.length ; i++) {
			if(tab2[i] > maxTab2)
				maxTab2 = tab2[i];
		}
		
		if( ( n < minTab1) && (n > maxTab2) )
			return true;
		return false;
	}
 
	/**
	 * Implementez une methode qui prend en entree un nombre entier et un tableau
	 * d'entiers, et determine si le nombre donne est la mediane des elements du
	 * tableau.
	 * 
	 * @param n   le nombre
	 * @param tab le tableau
	 * @return true si et seulement si n est la mediane des elements de tab
	 */
	public boolean estMediane(int n, int[] tab) {
		int countPlusGrand = 0 , countPlusPetit = 0;
		for(int i = 0 ; i < tab.length ; i++) {
			if(tab[i] > n)
				countPlusGrand++;
			
			if(tab[i] < n)
				countPlusPetit++;				
		}
		
		if(countPlusGrand == countPlusPetit)
			return true;
		return false;
	}

	/**
	 * Implementez une methode qui prend en entree deux tableaux d'entiers, et retourne le resultat de la fusion de ces deux tableaux.
	 * @param tab1 le premier tableau
	 * @param tab2 le second tableau
	 * @return la fusion de tab1 et tab2
	 */
	public int[] fusionTableaux(int[] tab1, int[] tab2) {
		int[] tab = new int[tab1.length + tab2.length];
		int indexTab1 = 0, indexTab2 = 0;
		
		for(int i = 0 ; i < tab.length ; i++) {
			if(i % 2 == 0)
				tab[i] = tab1[indexTab1++];
			else
				tab[i] = tab2[indexTab2++];
		}
		
		return tab;
	}
}
