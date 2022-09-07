package TP04;

public class TestTriNombres {
	public static void main(String[] args) {
		int[] tab = {35, 6, 2, 66, 100, 36, 11, 1, 6, 14};
		
		/*int[] tab = new int[10];
		 * for(int i=0;i<tab.length;i++) {
		 * 	tab[i] = Saisie.lireEntier("Valeur ?");
		 * }
		 */
		
		TriNombres tn = new TriNombres(tab);
		tn.Trier();
		tn.afficher();
	}
}
