package up.mi.as.td04;

public class TestUtilMath {
	public static void main(String[] args) {
		System.out.println("Factorielle : " + UtilMath.fact(2));
		System.out.println("Combinaison : " + UtilMath.comb(3, 2));
		System.out.println("Puissance : " + UtilMath.puissance(2, 4));
		System.out.println("Max2 : " + UtilMath.max2(2, 6));
		System.out.println("Max3v1 : " + UtilMath.max3v1(3, 2, 6));
		System.out.println("Max3v2 : " + UtilMath.max3v2(3, 3, 4));
		double[] tab1 = {15, 13, 12};
		System.out.println("Moyenne : " + UtilMath.moyenne(tab1));
		double[] tab2 = {12, 13, 14, 15};
		double[] tab3 = {12, 13, 14, 15, 16};
		System.out.println("Médiane Paire : " + UtilMath.mediane(tab2));
		System.out.println("Médiane Impaire :" + UtilMath.mediane(tab3));
		int[] tab4 = {12, 13, 14, 2};
		double[] tab5 = {0.2, 0.4, 0.1, 2};
		System.out.println("Moyenne pondérée : " + UtilMath.pond(tab5, tab4));
		System.out.println("Moyenne MaxExam : " + UtilMath.moyenneMax(12, 16));
		double[] tab6 = {40, 10, 26, 0};
		System.out.println("Groupe Etudiant : " + UtilMath.grpMoyenne(tab6));
		UtilMath.interfaceTextuelle();
		//UtilMath.interfaceTextuelle2();
		UtilMath.NbPremier_1a(15);
		UtilMath.NbPremier_1b(23);
		UtilMath.NbPremier_1c(36);
		System.out.println("NbPremier_1 : " + UtilMath.NbPremier_1(7));
		System.out.println("NbPremier_2 : " + UtilMath.NbPremier_2(41));
		System.out.println("NbPremier_3a : " + UtilMath.NbPremier_3a(360, 7));
		//System.out.println("NbPremier_3b : " + NbPremier_3b(360));
		//UtilMath.JeuNombreSecret(22);
	}
}
