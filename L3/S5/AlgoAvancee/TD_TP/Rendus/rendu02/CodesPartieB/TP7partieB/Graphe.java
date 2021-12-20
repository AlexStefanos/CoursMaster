package partieB;

import java.util.ArrayList;

public class Graphe {	
    ArrayList<Sommet> listeSommets;	
    int nbSommets;
    
    public Graphe() {
        listeSommets = new ArrayList<Sommet>();
        nbSommets = 0;
    }
    
    public void ajouterSommet(double tempsIndividuel) {
    	Sommet sommet = new Sommet(nbSommets);
    	
    	sommet.tempsIndividuel = tempsIndividuel;
    	listeSommets.add(sommet);
    	nbSommets = nbSommets + 1;
    }
    
    public void ajouterArrete(int source, int destination, double weight) {
        Arrete arrete = new Arrete(source, destination, weight);
        listeSommets.get(source).ajouterVoisin(arrete);
    }
}