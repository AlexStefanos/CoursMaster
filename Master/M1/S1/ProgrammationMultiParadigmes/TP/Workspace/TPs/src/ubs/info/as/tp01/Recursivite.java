package ubs.info.as.tp01;

public class Recursivite {
	public static int sommeRecUtil(int[] tab, int pos) {
		if(tab.length > pos) {
			 return(tab[pos] + sommeRecUtil(tab, (pos+1)));
		}
		else
			return(0);
	}
	
	public static int sommeRec(int[] tab) {
		int pos = 0, somme = 0;
		
		somme += sommeRecUtil(tab, pos);
		return(somme);
	}
	
	public static boolean trouveRecUtil(int[] tab, int cherche, int pos) {
		if(tab.length > pos) {
			if (tab[pos] != cherche) {
				return(trouveRecUtil(tab, cherche, (pos+1)));
			}
			else
				return(true);
		}
		else
			return(false);
	}
	
	public static boolean trouveRec(int[] tab, int cherche) {
		int pos = 0;
		
		return(trouveRecUtil(tab, cherche, pos));
		
	}
	
	public static int combienInfARecUtil(int[] tab, int limiteSup, int pos) {
		if(tab.length > pos) {
			if(tab[pos] < limiteSup)
				return(1 + combienInfARecUtil(tab, limiteSup, (pos+1)));
			else
				return(0 + combienInfARecUtil(tab, limiteSup, (pos+1)));
		}
		else
			return(0);
	}
	
	public static int combienInfARec(int[] tab, int limiteSup) {
		int pos = 0;
		
		return(combienInfARecUtil(tab, limiteSup, pos));
	}
	
	public static int[][] separePairImpairRecUtil(int[] tab, int[][] tabResult, int pos, int posTabPair, int posTabImpair) {
		if(tab.length > pos) {
			if(tab[pos] % 2 == 0) {
				tabResult[0][posTabPair] = tab[pos];
				return(separePairImpairRecUtil(tab, tabResult, (pos+1), (posTabPair+1), posTabImpair));
			}
			else {
				tabResult[1][posTabImpair] = tab[pos];
				return(separePairImpairRecUtil(tab, tabResult, (pos+1), posTabPair, (posTabImpair+1)));
			}
		}
		return(tabResult);
	}
	
	public static int[][] separePairImpairRec(int[] tab) {
		int pos = 0, posTabPair = 0, posTabImpair = 0;
		int[][] tabResult = new int[2][tab.length];
		
		tabResult = separePairImpairRecUtil(tab, tabResult, pos, posTabPair, posTabImpair);
		return(tabResult);
	}
	
	public static void main(String[] args) {
		int[] data = {17, 90, 32, 85, 76, 22, 80, 20, 36, 42, 50, 62, 72, 80, 99, 91, 46};
		
		System.out.println("sommeRec : " + sommeRec(data));
		System.out.println("trouveRec (22) : " + trouveRec(data, 22));
		System.out.println("trouveRec (2) : " + trouveRec(data, 2));
		System.out.println("combienInfARecUtil (42) : " + combienInfARec(data, 42));
		int[][] tabResult = separePairImpairRec(data);
		System.out.println("separePairImpairRecUtil : ");
		System.out.println("Chiffres Pairs : ");
		for(int i = 0; i < tabResult[0].length; i++) {
			if(tabResult[0][i] != 0)
				System.out.print(tabResult[0][i] + " ");
		}
		System.out.println("");
		System.out.println("Chiffres Impairs : ");
		for(int i = 0; i < tabResult[1].length; i++) {
			if(tabResult[1][i] != 0)
				System.out.print(tabResult[1][i] + " ");
		}
	}
}
