package up.mi.as.td02;

public class Point {
	private int x;
	private int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public double distance(Point b) {		
		if(b == null)
			return 0.0;
		return (Math.sqrt(Math.pow(b.x - getX(), 2) + Math.pow(b.y - getY(), 2)));
	}
}