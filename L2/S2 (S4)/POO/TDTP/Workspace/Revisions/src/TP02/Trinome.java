package TP02;
import javax.swing.JOptionPane;

public class Trinome {
	//Attributs
	private double a,b,c;
	private double delta;
	private double r1, r2;
	
	//Constructeurs
	public Trinome(double c1, double c2, double c3) {
		this.a = c1;
		this.b = c2;
		this.c = c3;
		delta = 0;
		r1 = 0;
		r2 = 0;
	}
	
	//Methodes
	public void calculRacines() {
		delta = b*b - 4*a*c;
		if (delta > 0) {
			r1 = (-b - Math.sqrt(delta))/ 2*a;
			r2 = (-b + Math.sqrt(delta))/ 2*a;
		}
		if (delta == 0) {
			r1 = -b / 2*a;
		}
	}
	
	//Affichage en ligne de commande
	public void afficheRacines() {
		if (delta > 0) {
			System.out.println("Les Racines du Trinome donné sont : r1 = " + r1 + " et r2 = " + r2 + ".");
		}
		if (delta == 0) {
			System.out.println("L'unique Racine du Trinome est : r1 = " + r1 + ".");
		}
		if (delta < 0) {
			System.out.println("Ce Trinome n'a pas de solutions dans R");
		}
	}
	
	//Affichage en Boite de Dialogue
	public void afficheRacines2() {
		JOptionPane.showMessageDialog(null, "Racine1 = " + r1 + "\n" + "Racine2 = " + r2);
	}
}
