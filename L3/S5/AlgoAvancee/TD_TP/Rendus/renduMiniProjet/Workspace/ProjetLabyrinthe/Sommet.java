import java.util.LinkedList;

public class Sommet {
	double indivTime, timeFromSource, heuristic;
	Sommet prev;
	LinkedList<Arrete> adjacencylist;
	int num, i, j;
	char etiquette;
	
	public Sommet(int num, char etiquette, int i, int j) {
		this.indivTime = Double.POSITIVE_INFINITY;
		this.timeFromSource = Double.POSITIVE_INFINITY;
		this.heuristic = -1;
		this.prev = null;
		this.adjacencylist = new LinkedList<Arrete>();
		this.num = num;
		this.etiquette = etiquette;
		this.i = i;
		this.j = j;
		}
    	
	public void addNeighbor(Arrete e) {
		this.adjacencylist.addFirst(e);
    }
}
