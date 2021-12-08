package partieB;

import java.util.ArrayList;

public class WeightedGraph {	
    ArrayList<Vertex> vertexlist;	
    int num_v;
    
    public WeightedGraph() {
        vertexlist = new ArrayList<Vertex>();
        num_v = 0;
    }
    
    public void addVertex(double indivTime) {
    	Vertex v = new Vertex(num_v);
    	
    	v.indivTime = indivTime;
    	vertexlist.add(v);
    	num_v = num_v + 1;
    }
    
    public void addEgde(int source, int destination, double weight) {
        Edge edge = new Edge(source, destination, weight);
        
        vertexlist.get(source).addNeighbor(edge);
    }
}