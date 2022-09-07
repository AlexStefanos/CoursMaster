package TP03;

public class Triangle {
	Point pA,pB,pC;
	
	public Triangle (Point pA, Point pB, Point pC) {
		this.pA = pA;
		this.pB = pB;
		this.pC = pC;
	}
	
	public void affiche() {
		System.out.println("pA : " + pA + ".");
		System.out.println("b : " + pB + ".");
		System.out.println("c : " + pC + ".");
	}
	
	public void isocele() {
		
	}
}
