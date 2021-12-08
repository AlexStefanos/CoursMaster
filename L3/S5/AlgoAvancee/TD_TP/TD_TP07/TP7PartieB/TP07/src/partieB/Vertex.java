package partieB;

import java.util.LinkedList;

public class Vertex {
	Vertex prev;
	LinkedList<Edge> adjacencylist;
	int num;
	double indivTime, timeFromSource, heuristic;
	
	public Vertex(int num) {
		this.indivTime = Double.POSITIVE_INFINITY;
		this.timeFromSource = Double.POSITIVE_INFINITY;
		this.heuristic = -1;
		this.prev = null;
		this.adjacencylist = new LinkedList<Edge>();
		this.num = num;
	}

	public void addNeighbor(Edge e) {
		this.adjacencylist.addFirst(e);
	}
}
