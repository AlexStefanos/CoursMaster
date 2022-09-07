package TP03;

public class Point {
	private int x,y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getY() {
		return y;
	}
	public int getX() {
		return x;
	}
	
	public void affiche() {
		System.out.println("La valeur x = " + x + ".");
		System.out.println("La valeur y = " + y + ".");
	}
}
