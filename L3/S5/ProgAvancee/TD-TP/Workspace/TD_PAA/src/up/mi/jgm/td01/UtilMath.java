package up.mi.jgm.td01;

import java.util.Scanner;

public class UtilMath {
	public static void main(String[] args) {
		System.out.println("Factorielle : " + fact(3));
		System.out.println("Combinaison : " + comb(3, 2));
		System.out.println("Puissance : " + puissance(2, 4));
		System.out.println("Max2 : " + max2(2, 6));
		System.out.println("Max3v1 : " + max3v1(3, 2, 6));
		System.out.println("Max3v2 : " + max3v2(3, 3, 4));
		double[] tab1 = {15, 13, 12};
		System.out.println("Moyenne : " + moyenne(tab1));
		double[] tab2 = {12, 13, 14, 15};
		double[] tab3 = {12, 13, 14, 15, 16};
		System.out.println("Médiane Paire : " + mediane(tab2));
		System.out.println("Médiane Impaire :" + mediane(tab3));
		int[] tab4 = {12, 13, 14, 2};
		double[] tab5 = {0.2, 0.4, 0.1, 2};
		System.out.println("Moyenne pondérée : " + pond(tab5, tab4));
		System.out.println("Moyenne MaxExam : " + moyenneMax(12, 16));
		double[] tab6 = {40, 10, 26, 0};
		System.out.println("Groupe Etudiant : " + grpMoyenne(tab6));
		interfaceTextuelle();
		interfaceTextuelle2();
}
	
	public static int somme3(int a, int b, int c) {
		return (a + b + c);
	}
	
	public static long fact(int n) {
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
		else {
			System.out.println("Une factorielle ne peut être négative");
			return(1);
		}
	}
	
	public static long comb(int n, int p) {
		long combinaison = 0;
		if (n >= p && p != 0) {
			combinaison = fact(n) / (fact(p)*fact(n-p));
			return(combinaison);
		}
		else {
			System.out.println("Une Combinaison ne peut avoir n < p");
			return(1);
		}
	}
	
	public static long puissance(int n, int m) {
		long puis = n;
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
	
	public static void interfaceTextuelle() {
		int a, b, c, d;
		Scanner sc = new Scanner(System.in);
		System.out.println("Souhaitez-Vous calculer la somme de trois entiers (tapez 1) ou la factorielle d’un entier (tapez 2) ou"
				+ " la combinaison de deux entiers (tapez 3) ou la puissance m-ème d’un entier (tapez 4).");
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
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez indiquer un nombre d'étudiant :");
		a = sc.nextInt();
		double[] tab = new double[(int) (2*a)];
		while(a > 0) {
			int i = 1;
			int j = 0;
			System.out.println("Veuillez indiquer sa note de contrôle continu de l'étudiant n°" + i);
			b = sc.nextDouble();
			System.out.println("Veuillez indiquer sa note d'examen de l'étudiant n°" + i);
			c = sc.nextDouble();
			i++;
			tab[j++] = b;
			tab[j] = c;
			a--;
		}
		grpMoyenne(tab);
	}
}
