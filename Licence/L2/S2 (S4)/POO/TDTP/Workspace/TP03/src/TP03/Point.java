package TP03;

public class Point {
	private double x,y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Point() {
		this(0,0);
	}
	
	public double getX(double x) {
		return x;
	}
	
	public double getY(double y) {
		return y;
	}
	
	public void affichePoint() {
		System.out.println("La coordonnée x : " + x);
		System.out.println("La coordonnée y : " + y);
	}
}