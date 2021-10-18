package up.mi.jgm.td01;

/**
 * Classe creee pour tester les fonctionnalites implementees dans UtilTab
 * 
 * @author Jean-Guy Mailly
 *
 */
public class TestUtilTab {
	public static void main(String[] args) {
		double[] tab = { 18.2, 2.3, 5, 42, 23.7 };
		UtilTab.affichageTableau(tab, false);
		System.out.println("appartient(0, tab) = " + UtilTab.appartient(0, tab));
		System.out.println("appartient(2.3, tab) = " + UtilTab.appartient(2.3, tab));
		System.out.println("max(tab) = " + UtilTab.max(tab));
		System.out.println("min(tab) = " + UtilTab.min(tab));
		System.out.println("somme(tab) = " + UtilTab.somme(tab));
		UtilTab.triParSelection(tab);
		UtilTab.affichageTableau(tab, true);
		System.out.println("moyenne(tab) = " + UtilTab.moyenne(tab));
		System.out.println("mediane(tab) = " + UtilTab.mediane(tab));

		double[] tab2 = { 18.2, 2.3, 5, 42, 23.7, 12 };
		System.out.println("mediane(tab2) = " + UtilTab.mediane(tab2));

		int[] tab3 = { 18, 2, 5, 42, 23, 12 };
		UtilTab.triFusion(tab3, 0, tab2.length);
		System.out.print("triFusion(tab3) = " );
		for(int v : tab3)
			System.out.print(v + " ");
		System.out.println();
			
		System.out.println("moyennePonderee(tab2, tab3) = " + UtilTab.moyennePonderee(tab2, tab3));
	}
}
