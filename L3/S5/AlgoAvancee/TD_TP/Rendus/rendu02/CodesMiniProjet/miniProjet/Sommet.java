import java.util.LinkedList;

public class Sommet {
	double tempsIndiv, tempsSource, heuristique;
	Sommet precedent;
	LinkedList<Arrete> listeAdjacence;
	int num, i, j;
	char etiquette;
	
	public Sommet(int num, char etiquette, int i, int j) {
		this.tempsIndiv = Double.POSITIVE_INFINITY;
		this.tempsSource = Double.POSITIVE_INFINITY;
		this.heuristique = -1;
		this.precedent = null;
		this.listeAdjacence = new LinkedList<Arrete>();
		this.num = num;
		this.etiquette = etiquette;
		this.i = i;
		this.j = j;
		}
    	
	public void addNeighbor(Arrete e) {
		this.listeAdjacence.addFirst(e);
    }
}
