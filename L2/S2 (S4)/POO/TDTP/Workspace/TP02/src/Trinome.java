import javax.swing.JOptionPane;

public class Trinome {
	//Attributs
	private double a,b,c;
	private double delta;
	private double r0,r1,r2;
	
	//Constructeur(s)
	public Trinome(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
		delta = 0.0;
		r0 = 0.0;
		r1 = 0.0;
		r2 = 0.0;
	}
	
	//Méthodes
	public void calculRacines() {
		delta = b*b - 4*a*c;
		if (delta > 0) {
			r1 = (-b - Math.sqrt(delta) / 2*a);
			r2 = (-b - Math.sqrt(delta) / 2*a);
		}
		if (delta == 0) {
			r0 = (-b / 2*a);
		}
	}
	
	//Affichage en ligne de commande
	public void afficheRacines() {
		if (delta > 0) {
			System.out.println();
		}
		else if (delta == 0) {
		
		}
		else {
			
		}
	}
	//Affichage dans une boite de dialogue
	public void afficheRacinesJOptionPanel() {
		if (delta > 0) {
			JOptionPane.showMessageDialog(null, "Racine1 = " + r1 + "\n" + "Racine2 = " + r2);
		}
	}
}