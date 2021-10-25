package RevisionsTD02;

public class Point {
	protected int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public double distance(Point p2) {
		return (Math.sqrt(Math.pow((p2.x - x), 2) + Math.pow((p2.y - y), 2)));
	}
}