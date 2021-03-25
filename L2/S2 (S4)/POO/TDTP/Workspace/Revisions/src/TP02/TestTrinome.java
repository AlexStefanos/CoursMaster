package TP02;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class TestTrinome {
	public static void main (String[] args) {
		int a1,b1,c1,a2,b2,c2,a3,b3,c3;
		//Methode utilisant la classe Saisie
		System.out.println("Utilisation de la classe Saisie :");
		a1 = Saisie.lireEntier("Valeur de a ?");
		b1 = Saisie.lireEntier("Valeur de b ?");
		c1 = Saisie.lireEntier("Valeur de c ?");
		Trinome t1 = new Trinome(a1,b1,c1);
		t1.calculRacines();
		t1.afficheRacines();
		
		//Methode utilisant la classe Scanner de J2SE 5
		System.out.println("");
		System.out.println("Utilisation de la classe Scanner : ");
		Scanner lectureClavier = new Scanner(System.in);
		System.out.println("Valeur de a ? ");
		a2 = lectureClavier.nextInt();
		System.out.println("Valeur de b ? ");
		b2 = lectureClavier.nextInt();
		System.out.println("Valeur de c ? ");
		c2 = lectureClavier.nextInt();
		Trinome t2 = new Trinome(a2,b2,c2);
		t2.calculRacines();
		t2.afficheRacines();
		
		//Methode utilisant la classe JOptionPane de Java
		System.out.println("");
		System.out.println("Utilisation de la classe JOptionPane : ");
		a3 = 0;
		b3 = 0;
		c3 = 0;
		String s1 = JOptionPane.showInputDialog("Valeur de a ?");
		String s2 = JOptionPane.showInputDialog("Valeur de b ?");
		String s3 = JOptionPane.showInputDialog("Valeur de c ?");
		if (s1 != null && s2 != null && s3 != null) {
			a3 = Integer.parseInt(s1);
			b3 = Integer.parseInt(s2);
			c3 = Integer.parseInt(s3);
		}
		Trinome t3 = new Trinome(a3,b3,c3);
		t3.calculRacines();
		t3.afficheRacines();
		t3.afficheRacines2();
	}
}
