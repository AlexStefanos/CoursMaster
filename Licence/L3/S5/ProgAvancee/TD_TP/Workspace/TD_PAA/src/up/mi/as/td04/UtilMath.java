package up.mi.as.td04;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.concurrent.ThreadLocalRandom; 

public class UtilMath {
	public static int somme3(int a, int b, int c) {
		return (a + b + c);
	}
	
	public static long fact(int n) throws IllegalArgumentException {
		long factorielle = 1;
		if (n == 0)
			return (1);
		else if (n > 0){
			while(n > 0) {
				factorielle *= n;
				n--;
			}
			return factorielle;
		}
		else
			throw new IllegalArgumentException("Une factorielle ne peut être négative");
	}
	
	public static long comb(int n, int p) throws IllegalArgumentException {
		long combinaison = 0;
		if (n >= p) {
			combinaison = fact(n) / (fact(p)*fact(n-p));
			return(combinaison);
		}
		else {
			throw new IllegalArgumentException("Une Combinaison ne peut avoir n < p");
		}
	}
	
	public static long puissance(int n, int m) throws IllegalArgumentException {
		long puis = n;
		if(m < 0)
			throw new IllegalArgumentException("Le cas où la puissance est inférieur à 0 n'est pas implémenté");
		while(m > 1) {
			puis *= n;
			m--;
		}
		return puis;
	}
	
	public static int max2(int a, int b) {
		if (a > b)
			return a;
		else
			return b;
	}
	
	public static int max3v1(int a, int b, int c) {
		if (a >= b && a >= c)
			return a;
		else if (b >= a && b >= c)
			return b;
		else
			return c;
	}
	
	public static int max3v2(int a, int b, int c) {
		if (a > max2(b,c))
			return a;
		else if (b > max2(a,c))
			return b;
		else
			return c;
	}
	
	public static double moyenne(double[] moy) {
		double moyenne = 0;
		for (int i = 0; i<moy.length; i++) {
			moyenne += moy[i];
		}
		return(moyenne/moy.length);
	}
	
	public static double mediane(double[] med) {
		int mid = med.length/2;
		if (med.length % 2 == 0)
			return ((med[mid]+med[mid-1])/2);
		else
			return(med[mid]);
	}
	
	public static double pond(double[] coeff, int[] notes) {
		double[] tab = new double[coeff.length];
		double totalcoeff = 0;
		double total = 0;
		if (coeff.length == notes.length) {
			for (int i = 0; i<coeff.length; i++) {
				tab[i] = coeff[i] * notes[i];
				totalcoeff += coeff[i];
				total += tab[i];
			}
			return(total/totalcoeff);
		}
		else {
			System.out.println("Les tableaux doivent être de la même taille");
			return(1);
		}
			
	}
	
	public static double moyenneMax(double noteCc, double noteExam) {
		if (noteExam > noteCc) {
			return noteExam;
		}
		else
			return ((noteExam + noteCc)/2);
	}
	
	public static double grpMoyenne(double[] grpEtu) {
		double[] moyMax = new double[grpEtu.length/2];
		int j = 0;
		double moyGrp = 0;
		for (int i = 0;i<grpEtu.length;i += 2) {
			moyMax[j] = moyenneMax(grpEtu[i],grpEtu[i+1]);
			j++;
			
		}
		for (int i=0;i<moyMax.length;i++) {
			moyGrp += moyMax[i];
		}
		return (moyGrp/moyMax.length);
	}
	
	public static String formatage(int nb) {
		if (nb < 10000) {
			if (nb < 10)
				return ("    " + nb);
			else if (nb < 100 && nb > 9)
				return("   " + nb);
			else if (nb < 1000 && nb > 99)
				return("  " + nb);
			else
				return(" " + nb);
		}
		else
			return("ERREUR");
	}
	
	public static void interfaceTextuelle() throws InputMismatchException {
		int a, b, c, d;
		Scanner sc = new Scanner(System.in);
		System.out.println("Souhaitez-Vous calculer la somme de trois entiers (tapez 1) ou la factorielle d’un entier (tapez 2) ou"
				+ " la combinaison de deux entiers (tapez 3) ou la puissance m-ème d’un entier (tapez 4).");
			//lireEntierAuClavier(sc, a);
		a = sc.nextInt();
			if (a == 1) {
				System.out.println("Veuillez donner les valeurs des 3 entiers à sommer : ");
				b = sc.nextInt();
				c = sc.nextInt();
				d = sc.nextInt();
				System.out.println("La somme de " + b + " + " + c + " + " + d + " est égale à " + somme3(b,c,d));
				System.out.println("Voulez-vous revenir au menu (tapez 1) ou quitter (tapez un autre entier)");
				a = sc.nextInt();
				if (a == 1)
					interfaceTextuelle();
				else
					System.out.println("Vous avez quitté l'interface Textuelle");
			}
			else if (a == 2) {
				System.out.println("Veuillez donnez la valeur de la factorielle que vous voulez calculer : ");
				b = sc.nextInt();
				System.out.println("La factorielle de " + b + "est égale à " + fact(b));
				System.out.println("Voulez-vous revenir au menu (tapez 1) ou quitter (tapez un autre entier)");
				a = sc.nextInt();
				if (a == 1)
					interfaceTextuelle();
				else
					System.out.println("Vous avez quitté l'interface Textuelle");
			}
			else if (a == 3) {
				System.out.println("Veuillez donner les deux entiers à combiner : ");
				b = sc.nextInt();
				c = sc.nextInt();
				System.out.println("La combinaison de " + b + " et de " + c + " est égale à " + comb(b,c));
				System.out.println("Voulez-vous revenir au menu (tapez 1) ou quitter (tapez un autre entier)");
				a = sc.nextInt();
				if (a == 1)
					interfaceTextuelle();
				else
					System.out.println("Vous avez quitté l'interface Textuelle");
			}
			else if (a == 4) {
				System.out.println("Veuillez donner la valeur à élever à la puissance et l'exposant : ");
				b = sc.nextInt();
				c = sc.nextInt();
				System.out.println("La puissance de " + b + " à la puissance " + c + " est égale à " + puissance(b,c));
				System.out.println("Voulez-vous revenir au menu (tapez 1) ou quitter (tapez un autre entier)");
				a = sc.nextInt();
				if (a == 1)
					interfaceTextuelle();
				else
					System.out.println("Vous avez quitté l'interface Textuelle");
			}
			else
				System.out.println("Vous n'avez pas tapé un numéro valide.");
	}
	
	public static void interfaceTextuelle2() {
		double a,b,c;
		int i = 1;
		int j = 0; 
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez indiquer un nombre d'étudiant :");
		a = sc.nextInt();
		double[] tab = new double[(int) (2*a)];
		while(a > 0) {
			System.out.println("Veuillez indiquer sa note de contrôle continu de l'étudiant n°" + (j+1));
			b = sc.nextDouble();
			System.out.println("Veuillez indiquer sa note d'examen de l'étudiant n°" + (j+1));
			c = sc.nextDouble();
			i++;
			tab[j++] = b;
			tab[j] = c;
			a--;
		}
		System.out.println("Moyenne : " + grpMoyenne(tab));
	}
	
	public static void NbPremier_1a(int n) {
		boolean prem = true;
		for (int k = 2; k < n; k++) {
			if (n % k == 0)
				prem = false;
		}
		if (prem == true)
			System.out.println("NbPremier_1a : n est premier");
		else
			System.out.println("NbPremier_1a : n n'est pas premier");
	}
	
	public static void NbPremier_1b(int n) {
		boolean prem = true;
		if (n % 2 == 0)
			prem = false;
		for (int k = 3; k < n; k += 2) {
			if (n % k == 0)
				prem = false;
		}
		if (prem == true)
			System.out.println("NbPremier_1b : n est premier");
		else
			System.out.println("NbPremier_1b : n n'est pas premier");
	}
	
	public static void NbPremier_1c(int n) {
		boolean prem = true;
		if (n % 2 == 0)
			prem = false;
		for (int k = 3; k < Math.sqrt(n); k += 2) {
			if (n % k == 0)
				prem = false;
		}
		if (prem == true)
			System.out.println("NbPremier_1c : n est premier");
		else
			System.out.println("NbPremier_1c : n n'est pas premier");
	}
	
	public static boolean NbPremier_1(int n) {
		boolean prem = true;
		if (n % 2 == 0 && n != 2)
			prem = false;
		for (int k = 3; k < Math.sqrt(n); k += 2) {
			if (n % k == 0)
				prem = false;
		} 
		return(prem);
	}
	
	public static String NbPremier_2(int n) {
		String liste = "Voici la liste des nombres premiers de 1 à n :";
		if (NbPremier_1(2))
			liste += (" " + 2);
		for (int k = 3; k <= n; k += 2) {
			if (NbPremier_1(k))
				liste += (" " + k);
		}
		return(liste);
	}
	
	public static int NbPremier_3a(int n, int p) {
		int result;
		if (NbPremier_1(p)) { 
			for(result = 0;result < n; result++) {
				if (n % puissance(p, result) != 0) {
					if (result != 0)
						result--;
					return(result);
				}
			}
			return(-1);
		}
		else {
			System.out.println("p n'est pas premier.");
			return(-1);
		}
	}
	
	public static String NbPremier_3b(int n) {
		String liste = "Voici la décomposition en facteurs premiers d'un entier n : ";
		int result;
		if (NbPremier_1(2)) {
			if (NbPremier_3a(n, 2) != - 1) 
				liste += (" " + NbPremier_3a(n, 2));
			else {
				liste += ("ERREUR");
				return (liste);
			}
		}
		for(int p = 1; p < n; p++) {
			if (NbPremier_1(p)) { 
				for(result = 0;result < n; result++) {
					if (n % puissance(p, result) != 0) {
						if (result != 0)
							result--;
						liste += (" " + p + "^" + result);
					}
				}
			}
		}
		return(liste);
	}
	
	public static void JeuNombreSecret(int nb) {
		Character indice;
		int prop, max, min;
		min = 1;
		max = 100;
		Scanner sc = new Scanner(System.in);
		System.out.println("Pensez à un nombre !");
		prop = ThreadLocalRandom.current().nextInt(min,max + 1);
		System.out.println("Ma Proposition est " + prop);
		while (prop != nb) {
			System.out.println("Est-ce plus grand (+), plus petit (-), ou égal (=) à votre nombre ?");
			indice = sc.next().charAt(0);
			if (indice == '+') {
				min = prop;
				prop = ThreadLocalRandom.current().nextInt(min,max + 1);
				System.out.println("Ma Proposition est : " + prop);
			}
			else if (indice == '-') {
				max = prop;
				prop = ThreadLocalRandom.current().nextInt(min,max + 1);
				System.out.println("Ma Proposition est : " + prop);
			}
			else if (indice == '=') {
				System.out.println("J'ai trouvé !");
			}
			else
				System.out.println("ERREUR : Veuillez Réessayer");
		}
		System.out.println("Victoire !");
	}
	
	/*
	 * Prend en entrée un tableau t tel que les valeurs sont triées entre iMin
	 * (inclus) et iMilieu (exclu) d'une part, et entre iMilieu (inclus) et iMax
	 * (exclus) d'autre part. Modifie le tableau pour qu'il soit trié entre iMin
	 * (inclus) et iMax (exclus)
	 * 
	 * @param t 		le tableau dans lequel on fusionne les données
	 * @param iMin		l'indice de début des zones à fusionner
	 * @param iMilieu	l'indice du pivot entre les zones à fusionner
	 * @param iMax		l'indice de la fin des zones à fusionner
	 */
	public static void fusion(int[] t, int iMin, int iMilieu, int iMax) {
		int[] tmpTab = new int[iMax - iMin];
		int i, j, cpt;
		
		for(cpt = 0, i = iMin, j = iMilieu; (i < iMilieu) && (j < iMax);) {
			if(t[i] < t[j]) {
				tmpTab[cpt] = t[i];
				cpt++;
				i++;
			}
			else {
				tmpTab[cpt] = t[j];
				cpt++;
				j++;
			}
		}
		while(i < iMilieu) {
			tmpTab[cpt] = t[i];
			cpt++;
			i++;
		}
		while(j < iMax) {
			tmpTab[cpt] = t[j];
			cpt++;
			j++;
		}
		for(i = 0; i < tmpTab.length; i++) {
			t[iMin + i] = tmpTab[i];
		}
	}
	
	/*
	 * Trie un tableau d'entier par fusion etre les indices iMin (inclus) et iMax
	 * (exclus)
	 * 
	 * @param t		le tableau à trier
	 * @param iMin	l'indice du début de la zone à trier
	 * @param iMax	l'indice de fin de la zone à trier
	 */
	public static void triFusion(int[] t, int iMin, int iMax) {
		if(iMax - iMin > 1) {
			int iMilieu = (iMin + iMax) / 2;
			triFusion(t, iMin, iMilieu);
			triFusion(t, iMilieu, iMax);
			fusion(t, iMin, iMilieu, iMax);
		}
	}
	
	private static int lireEntierAuClavier(Scanner sc, String msg) throws InputMismatchException {
		int result = 0;
		boolean scValide = false;
		
		while(!scValide) {
			try {
				result = sc.nextInt();
				scValide = true;
			} catch(InputMismatchException inp) {
				inp.getMessage();
				scValide = false;
			}
		}
		return(result);
	}
}
