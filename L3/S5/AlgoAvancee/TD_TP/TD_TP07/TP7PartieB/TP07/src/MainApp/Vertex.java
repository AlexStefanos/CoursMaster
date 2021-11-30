package MainApp;

import java.util.LinkedList;

public class Vertex {
	double indivTime;
	double timeFromSource;
	double heuristic;
	Vertex prev;
	LinkedList<Edge> adjacencylist;
	int num;
	
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
