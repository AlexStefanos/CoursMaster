package TD06;

public class Point {
	//Attributs
	private float x;
	private float y;
	
	//Constructeurs
	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Point() {
		this(0.0f, 0.0f);
	}
	
	//get
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	//Autres méthodes
	//Distance entre le point courant et le point passé en argument
	public float distance(Point p) {
		double dist = 0.0;
		dist = Math.sqrt(Math.pow(Math.sqrt(p.getX() - x), 2) + (Math.pow(Math.sqrt(p.getY() - y), 2)));
		return (float)dist;
	}
	
	//Redéfinition de la méthode toString() (de la classe Object)
	//pour avoir une chaine de la forme "(x,y)"
	public String toString() {
		String to = "(" + x + "," + y + ")";
		return to;
	}
	
	public void affiche() {
		System.out.println(toString());
	}
}
