package partieB;

import java.util.LinkedList;

public class Sommet {
	Sommet precedent;
	LinkedList<Arrete> listeAdjacence;
	int num;
	double tempsIndividuel, tempsSource, heuristique;
	
	public Sommet(int num) {
		this.tempsIndividuel = Double.POSITIVE_INFINITY;
		this.tempsSource = Double.POSITIVE_INFINITY;
		this.heuristique = -1;
		this.precedent = null;
		this.listeAdjacence = new LinkedList<Arrete>();
		this.num = num;
	}

	public void ajouterVoisin(Arrete arrete) {
		this.listeAdjacence.addFirst(arrete);
	}
}
