package TP02;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class TestTrinome {
	public static void main(String[] args) {
		private double a,b,c;
		Trinome t1;
		
		System.out.println("Utilisation de la classe Saisie");
		a = Saisie.lireReel("Valeur de a ?");
		b = Saisie.lireReel("Valeur de b ?");
		c = Saisie.lireReel("Valeur de c ?");
		t1 = new Trinome(a,b,c);
		t1.calculRacines();
		t1.afficheRacines();
	}
}