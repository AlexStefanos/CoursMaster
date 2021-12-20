package partieB;

import java.util.HashSet;
import java.util.LinkedList;

public class DijkstraAlgo {
	public static LinkedList<Integer> Dijkstra(Graphe graphe, int debut, int fin, int nbSommets, Tableau tableau) {
		HashSet<Integer> pasVisiter = new HashSet<Integer>();
		int nbEssais, minSommet, pasEssayer;
		double tempsSourceMinimal;
		
		graphe.listeSommets.get(debut).tempsSource = 0;
		nbEssais = 0;
		for(Sommet sommet : graphe.listeSommets)
			pasVisiter.add( sommet.num );
		while (pasVisiter.contains(fin)) {
			minSommet = 0;
			tempsSourceMinimal = Double.POSITIVE_INFINITY;
			for(Integer numSommet : pasVisiter ) {
				if(graphe.listeSommets.get(numSommet).tempsSource <= tempsSourceMinimal) {
						minSommet = numSommet;
						tempsSourceMinimal = graphe.listeSommets.get(numSommet).tempsSource;
				}
			}
			pasVisiter.remove(minSommet); 
			nbEssais += 1;
			for (int i = 0 ; i < graphe.listeSommets.get(minSommet).listeAdjacence.size() ; i++) {
				if( pasVisiter.contains(graphe.listeSommets.get(minSommet).listeAdjacence.get(i).destination)) {
					pasEssayer = graphe.listeSommets.get(minSommet).listeAdjacence.get(i).destination;
					if((( graphe.listeSommets.get(minSommet).tempsSource + graphe.listeSommets.get(minSommet).listeAdjacence.get(i).poids) < (graphe.listeSommets.get(pasEssayer).tempsSource))) {
						graphe.listeSommets.get(pasEssayer).tempsSource = ( graphe.listeSommets.get(minSommet).tempsSource + graphe.listeSommets.get(minSommet).listeAdjacence.get(i).poids);
						graphe.listeSommets.get(pasEssayer).precedent =  graphe.listeSommets.get(minSommet);
					}
				}
			}
			try {
	    	    tableau.mAJ(graphe, minSommet); 
	    	    Thread.sleep(10);
	    	}catch(InterruptedException e) {
	    	    System.out.println("stop");
	    	}       
		}		
		System.out.println("Done! Using Dijkstra:");
		System.out.println("Number of nodes explored: " + nbEssais);
		System.out.println("Total time of the chemin: " + graphe.listeSommets.get(fin).tempsSource);
		
		LinkedList<Integer> chemin=new LinkedList<Integer>();
		Sommet noeudParent = graphe.listeSommets.get(fin).precedent; 
		chemin.addFirst(fin);
		while(noeudParent != null) {
			chemin.addFirst(noeudParent.num);
			noeudParent = graphe.listeSommets.get(noeudParent.num).precedent; 
		}		
		tableau.ajouterChemin(graphe, chemin);
		return(chemin);
	}
}
