import java.util.ArrayList;

public class Graphe {	
    ArrayList<Sommet> listeSommets;
    int nbSommets;
    
    public Graphe() {
        listeSommets = new ArrayList<Sommet>();
        nbSommets = 0;
    }
    
    public void addVertex(char etiquette, double tempsIndiv, int i, int j) {
    	Sommet sommet = new Sommet(nbSommets, etiquette, i, j);
    	sommet.tempsIndiv = tempsIndiv;
    	listeSommets.add(sommet);
    	nbSommets = nbSommets + 1;
    }
    
    public void addEgde(int source, int destination, double poids) {
        Arrete arrete = new Arrete(source, destination, poids);
        listeSommets.get(source).addNeighbor(arrete);
    }
}
