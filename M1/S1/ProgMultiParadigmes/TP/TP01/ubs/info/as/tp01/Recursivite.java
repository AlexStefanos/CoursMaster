package ubs.info.as.tp01;

/**
 * TP01 Programmation Multi-Paradigme
 * @author Alexandre Stefanos
 */
public class Recursivite {
	/**
	 * Méthode récursive qui somme les entiers d'un tableau d'entiers
	 * @param tab : tableau d'entiers
	 * @param pos : itérateur 
	 * @return somme des entiers du tableau
	 */
    public static int sommeRecUtil(int[] tab, int pos) {
        if(pos >= 0)
            return(tab[pos] + sommeRecUtil(tab, pos - 1));    
        else
            return(0);
    }

	/**
	 * Méthode qui somme les entiers d'un tableau d'entiers en appelant sommeRecUtil()
	 * @param tab : tableau de données
	 * @return somme des entiers du tableau
	 */
    public static int sommeRec(int[] tab) {
        return(sommeRecUtil(tab, 16));
    }
    
	/**
	 * Méthode récursive qui cherche un entier dans un tableau d'entiers
	 * @param tab : tableau d'entiers
	 * @param cherche : entier cherché
	 * @param pos : itérateur
	 * @return true si l'entier cherché est trouvé, false sinon
	 */
    public static boolean trouveRecUtil(int[] tab, int cherche, int pos) {
        boolean rep = false;
        if(pos >= 0) {
            if(tab[pos] != cherche) {
                rep = trouveRecUtil(tab, cherche, pos - 1);
                return(rep);
            }
            else
                return(true);
        }
        return(false);
    }

	/**
	 * Méthode qui cherche un entier dans un tableau d'entiers en appelant trouveRecUtil()
	 * @param tab : tableau d'entiers
	 * @param cherche : entier cherché
	 * @return true si l'entier cherché est trouvé, sinon false
	 */
    public static boolean trouveRec(int[] tab, int cherche) {
        return(trouveRecUtil(tab, cherche, 16));
    }

	/**
	 * Méthode récursive qui compte le nombre d'entiers inférieurs à la limite (limiteSup)
	 * @param tab : tableau d'entiers
	 * @param limiteSup : limite donnée
	 * @param pos : itérateur
	 * @return nombre d'entiers inférieurs à la limite (limiteSup)
	 */
    public static int combienInfARecUtil(int[] tab, int limiteSup, int pos) {
        int count = 0;
        if(pos >= 0) {
            if(tab[pos] < limiteSup)
                count += 1;
            count += combienInfARecUtil(tab, limiteSup, pos - 1);
        }
        return(count);
    }

	/**
	 * Méthode qui compte le nombre d'entiers inférieurs à la limite (limiteSup) en appelant combienInfARecUtil()
	 * @param tab : tableau d'entiers
	 * @param limiteSup : limite donnée
	 * @return nombre d'entiers inférieurs à la limite (limiteSup)
	 */
    public static int combienInfARec(int[] tab, int limiteSup) {
        return(combienInfARecUtil(tab, limiteSup, 16));
    }

	/**
	 * Méthode récursive qui sépare les entiers pairs et ceux impairs en deux tableaux
	 * @param tab : tableau d'entiers 
	 * @param tabResult : tableau final à 2 dimensions d'entiers
	 * @param pos : itérable
	 * @param posTabPair : itérable du tableau pair
	 * @param posTabImpair : itérable du tableau impair
	 * @return tableau final à 2 dimensions d'entiers
	 */
    public static int[][] separePairImpairRecUtil(int[] tab, int[][] tabPairImpair, int pos, int posTabPair, int posTabImpair) {
		if(tab.length > pos) {
			if(tab[pos] % 2 == 0) {
				tabPairImpair[0][posTabPair] = tab[pos];
				return(separePairImpairRecUtil(tab, tabPairImpair, (pos+1), (posTabPair+1), posTabImpair));
			}
			else {
				tabPairImpair[1][posTabImpair] = tab[pos];
				return(separePairImpairRecUtil(tab, tabPairImpair, (pos+1), posTabPair, (posTabImpair+1)));
			}
		}
		return(tabPairImpair);
	}

	/**
	 * Méthode qui sépare les entiers paris et ceux impairs en deux tableaux
	 * @param tab : tableau d'entiers
	 * @return tableau final à 2 dimensions d'entiers
	 */
    public static int[][] separePairImpairRec(int[] tab) {
        int pos = 0, posTabPair = 0, posTabImpair = 0;
		int[][] tabPairImpair = new int[2][tab.length];
		
		tabPairImpair = separePairImpairRecUtil(tab, tabPairImpair, pos, posTabPair, posTabImpair);
		return(tabPairImpair);
    }

	/**
	 * Méthode récursive qui renvoie le tableau inversé des valeurs
	 * @param tab : tableau d'entiers
	 * @param pos : itérable
	 * @param posDepuisDernierEntier : itérable commençant par le dernier entier du tableau 
	 * @return tab inversé
	 */
	public static int[] inverseRecUtil(int[] tab, int pos, int posDepuisDernierEntier) {
		int tmp = 0;
		
		if(tab.length > pos) {
			if(pos < (tab.length/2)) {
				if(tab.length > pos) {
					tmp = tab[posDepuisDernierEntier];
					tab[posDepuisDernierEntier] = tab[pos];
					tab[pos] = tmp;
				}
				return(inverseRecUtil(tab, (pos+1), (posDepuisDernierEntier-1)));
			}	
			else
				return(tab);
		}
		else {
			System.out.println("Erreur");
			return(tab);
		}
	}

	/**
	 * Méthode qui renvoie le tableau inversé des valeurs en appelant inverseRecUtil()
	 * @param tab : tableau d'entiers
	 * @return tab inversé
	 */
    public static int[] inverseRec(int[] tab) {
        int pos = 0;
		int[] tabResult = new int[tab.length];
		
		tabResult = inverseRecUtil(tab, pos, (tab.length-1));
		return(tabResult);
    }

	/**
	 * Main de la classe Recursivite
	 * @param args : argument du main de la classe Recursivite
	 */
    public static void main(String[] args) {
        int[] data = {17, 90, 32, 85, 76, 22, 80, 20, 36, 42, 50, 62, 72, 80, 99, 91, 46};

        System.out.println("sommeRec() : " + sommeRec(data));
        System.out.println("trouveRec(42) : " + trouveRec(data, 42));
        System.out.println("combienInfARec(42) : " + combienInfARec(data, 42));
        int[][] tabResult = separePairImpairRec(data);
		System.out.println("separePairImpairRecUtil : ");
		System.out.print("	Chiffres Pairs : ");
		for(int i = 0; i < tabResult[0].length; i++) {
			if(tabResult[0][i] != 0)
				System.out.print(tabResult[0][i] + " ");
		}
		System.out.println("");
		System.out.print("	Chiffres Impairs : ");
		for(int i = 0; i < tabResult[1].length; i++) {
			if(tabResult[1][i] != 0)
				System.out.print(tabResult[1][i] + " ");
		}
		System.out.println("");
		System.out.print("Inverse Rec : ");
		int[] tabResult2 = inverseRec(data);
		for(int i = 0; i < tabResult2.length; i++) {
			System.out.print(tabResult2[i] + " ");
		}
		System.out.println("");
	}
}