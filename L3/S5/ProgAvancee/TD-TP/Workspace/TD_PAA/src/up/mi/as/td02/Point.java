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
	
	/*public Point translation(Vecteur v) {
		if((v.getVectP2X() - v.getVectP1X() >= 0) && (v.getVectP2Y() - v.getVectP1Y() >= 0)) {
			x += Math.sqrt(Math.pow(v.getVectP2X() - v.getVectP1X(), 2) + Math.pow(v.getVectP2Y() - v.getVectP2Y(), 2));
			y += Math.sqrt(Math.pow(v.getVectP2X() - v.getVectP1X(), 2) + Math.pow(v.getVectP2Y() - v.getVectP2Y(), 2));
			return();
		}
	} VOIR CORRIGE */
}