package partieB;

import java.util.HashSet;
import java.util.LinkedList;

public class AStarAlgo {
	public static LinkedList<Integer> AStar(Graphe graphe, int start, int end, int nb_col, int numberV, Tableau tableau) {
		int nbEssais, i, minSommet, pasEssayer;
		HashSet<Integer> pasVisiter = new HashSet<Integer>();
		
		nbEssais = 0;
		graphe.listeSommets.get(start).tempsSource = 0;
		for(Sommet sommet : graphe.listeSommets)
			pasVisiter.add( sommet.num );
		i = 0;
		for(Sommet sommet : graphe.listeSommets) {
			sommet.heuristique = distance(i % nb_col, i / nb_col, end % nb_col, end / nb_col);
			i++;
		}
		while (pasVisiter.contains(end)) {
			minSommet = 0;
			double tempsSourceheuristiqueMinimale = Double.POSITIVE_INFINITY;
			for(Integer sommetNum : pasVisiter ) {
				if( ( graphe.listeSommets.get(sommetNum).tempsSource + graphe.listeSommets.get(sommetNum).heuristique ) <= tempsSourceheuristiqueMinimale ) {
					minSommet = sommetNum;
					tempsSourceheuristiqueMinimale = graphe.listeSommets.get(sommetNum).tempsSource + graphe.listeSommets.get(sommetNum).heuristique;
				}
			}
			pasVisiter.remove(minSommet); 
			nbEssais += 1;
			for (i = 0; i < graphe.listeSommets.get(minSommet).listeAdjacence.size(); i++) {
				if(pasVisiter.contains(graphe.listeSommets.get(minSommet).listeAdjacence.get(i).destination)) {
					pasEssayer = graphe.listeSommets.get(minSommet).listeAdjacence.get(i).destination;
					
					if(((graphe.listeSommets.get(minSommet).tempsSource + graphe.listeSommets.get(minSommet).listeAdjacence.get(i).poids)  < (graphe.listeSommets.get(pasEssayer).tempsSource))) {
						graphe.listeSommets.get(pasEssayer).tempsSource = (graphe.listeSommets.get(minSommet).tempsSource + graphe.listeSommets.get(minSommet).listeAdjacence.get(i).poids);
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
		System.out.println("Done! Using A*:");
		System.out.println("Number of nodes explored: " + nbEssais);
		System.out.println("Total time of the chemin: " + graphe.listeSommets.get(end).tempsSource);
		LinkedList<Integer> chemin = new LinkedList<Integer>();
		Sommet noeudParent = graphe.listeSommets.get(end).precedent;
		chemin.addFirst(end);
		while(noeudParent != null) {
			chemin.addFirst(noeudParent.num);
			noeudParent = graphe.listeSommets.get(noeudParent.num).precedent; 
		}
		tableau.ajouterChemin(graphe, chemin);
		return(chemin);
	}
	
	private static double distance(int Xa, int Ya, int Xb, int Yb) {
		double resultatX = Math.pow((Xb-Xa), 2);
		double resultatY = Math.pow((Yb-Ya), 2);
		return(Math.sqrt(resultatX + resultatY));
   }
}
