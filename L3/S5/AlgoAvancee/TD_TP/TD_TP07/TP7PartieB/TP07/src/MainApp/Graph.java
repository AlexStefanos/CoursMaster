package MainApp;

import java.util.ArrayList;

public class Graph {
    ArrayList<Vertex> vertexlist;
    int num_v = 0;

    Graph() {
        vertexlist = new ArrayList<Vertex>();
    }

    public void addVertex(double indivTime)
    {
    	Vertex v = new Vertex(num_v);
    	v.indivTime = indivTime;
    	vertexlist.add(v);
    	num_v = num_v + 1;
    }
    
    public void addEdge(int source, int destination, double weight) {
        Edge edge = new Edge(source, destination, weight);
        vertexlist.get(source).addNeighbor(edge);
    }

}
