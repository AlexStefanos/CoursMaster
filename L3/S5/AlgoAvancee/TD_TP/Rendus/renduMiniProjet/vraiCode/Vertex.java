import java.util.LinkedList;

public class Vertex {
	double indivTime, timeFromSource, heuristic;
	Vertex prev;
	LinkedList<Edge> adjacencylist;
	int num, i, j;
	char etiquette;
	
	public Vertex(int num, char etiquette, int i, int j) {
		this.indivTime = Double.POSITIVE_INFINITY;
		this.timeFromSource = Double.POSITIVE_INFINITY;
		this.heuristic = -1;
		this.prev = null;
		this.adjacencylist = new LinkedList<Edge>();
		this.num = num;
		this.etiquette = etiquette;
		this.i = i;
		this.j = j;
		}
    	
	public void addNeighbor(Edge e) {
		this.adjacencylist.addFirst(e);
    }
}
