import java.util.ArrayList;

public class Graph {	
    ArrayList<Vertex> vertexlist;
    int num_v;
    
    public Graph() {
        vertexlist = new ArrayList<Vertex>();
        num_v = 0;
    }
    
    public void addVertex(char etiquette, double indivTime, int i, int j) {
    	Vertex v = new Vertex(num_v, etiquette, i, j);
    	v.indivTime = indivTime;
    	vertexlist.add(v);
    	num_v = num_v + 1;
    }
    
    public void addEgde(int source, int destination, double weight) {
        Edge edge = new Edge(source, destination, weight);
        vertexlist.get(source).addNeighbor(edge);
    }
}
