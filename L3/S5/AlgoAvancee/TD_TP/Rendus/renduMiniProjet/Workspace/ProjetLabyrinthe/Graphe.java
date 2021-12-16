import java.util.ArrayList;

public class Graphe {	
    ArrayList<Sommet> listeSommets;
    int num_v;
    
    public Graphe() {
        listeSommets = new ArrayList<Sommet>();
        num_v = 0;
    }
    
    public void addVertex(char etiquette, double indivTime, int i, int j) {
    	Sommet v = new Sommet(num_v, etiquette, i, j);
    	v.indivTime = indivTime;
    	listeSommets.add(v);
    	num_v = num_v + 1;
    }
    
    public void addEgde(int source, int destination, double weight) {
        Arrete edge = new Arrete(source, destination, weight);
        listeSommets.get(source).addNeighbor(edge);
    }
}
