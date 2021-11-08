// Par Sylvain Lobry, pour le cours "IF05X040 Algorithmique avancée"
// de l'Université de Paris, 11/2020

package tp7_8;

import java.util.HashSet;
import java.util.LinkedList;

import tp7_8.WeightedGraph.Graph;
import tp7_8.WeightedGraph.Vertex;

public class AStarAlgo {
	
	/**
	 * La méthode A* (à pronnoncer A star ou A étoile) peut être vu comme une extension de l’algorithme
	 * de Dijkstra. La différence se fait dans le choix du nœud à explorer. Au lieu de choisir celui ayant la plus
	 * petite distance temporaire, on sélectionne en fonction de la somme entre la distance temporaire et une
	 * heuristique (ici, une distance estimée au nœud d’arriver) à définir. (Source : Consignes.pdf)
	 * 
	 * Source de certains des commentaires dans le code : Consignes.pdf.
	 * 
	 * @param graph	le graphe représentant la carte.
	 * @param start un entier représentant la case de départ (entier unique correspondant à la case obtenue dans le sens de la lecture).
	 * @param end   un entier représentant la case d'arrivée (entier unique correspondant à la case obtenue dans le sens de la lecture).
	 * @param ncols le nombre de colonnes dans la carte.
	 * @param numberV le nombre de cases dans la carte.
	 * @param board l'affichage.
	 * @return une liste d'entiers correspondant au chemin.
	 */
	public static LinkedList<Integer> AStar(Graph graph, int start, int end, int ncols, int numberV, Board board)
	{
		graph.vertexlist.get(start).timeFromSource = 0; // Le point de départ a une distance nulle depuis le point de départ.
		int number_tries = 0; // Nombre de nœuds explorés.
		
		//TODO: mettre tous les noeuds du graphe dans la liste des noeuds à visiter:
		HashSet<Integer> to_visit = new HashSet<Integer>();
		for(Vertex vertex : graph.vertexlist)
			to_visit.add( vertex.num );
		
		//TODO: Remplir l'attribut graph.vertexlist.get(v).heuristic pour tous les noeuds v du graphe:
		//  heuristic : une distance estimée au nœud d’arriver.
		int i = 0;
		for(Vertex vertex : graph.vertexlist) {
			// distance(int Xa, int Ya, int Xb, int Yb)
			vertex.heuristic = 	distance(i % ncols, i / ncols, end % ncols, end / ncols);
			i++;
		}
		
		while (to_visit.contains(end))
		{
			//TODO: trouver le noeud min_v parmis tous les noeuds v ayant la distance temporaire
			//      (graph.vertexlist.get(v).timeFromSource + heuristic) minimale.
			int min_v = 0;
			double timeFromSourceHeuristicMinimale = Double.POSITIVE_INFINITY;
			for(Integer vertexNum : to_visit ) {
				if( ( graph.vertexlist.get(vertexNum).timeFromSource + graph.vertexlist.get(vertexNum).heuristic ) <= timeFromSourceHeuristicMinimale ) {
					min_v = vertexNum;
					timeFromSourceHeuristicMinimale = graph.vertexlist.get(vertexNum).timeFromSource + graph.vertexlist.get(vertexNum).heuristic;
				}
			}			
			
			//On l'enlève des noeuds à visiter
			to_visit.remove(min_v); 
			number_tries += 1; // Nombre de nœuds explorés.
			
			//TODO: pour tous ses voisins, on vérifie si on est plus rapide en passant par ce noeud.
			for (i = 0 ; i < graph.vertexlist.get(min_v).adjacencylist.size() ; i++)
			{
				if( to_visit.contains(graph.vertexlist.get(min_v).adjacencylist.get(i).destination)) {
					int to_try = graph.vertexlist.get(min_v).adjacencylist.get(i).destination;
					
					if( (  ( graph.vertexlist.get(min_v).timeFromSource + graph.vertexlist.get(min_v).adjacencylist.get(i).weight )  < (graph.vertexlist.get(to_try).timeFromSource) ) ) { // si la distance en passant par le nœud courant (donc distance temporaire plus distance du nœud courant au voisin) est plus petite que la distance temporaire
						graph.vertexlist.get(to_try).timeFromSource = ( graph.vertexlist.get(min_v).timeFromSource + graph.vertexlist.get(min_v).adjacencylist.get(i).weight ); // on mets à jour la distance temporaire
						graph.vertexlist.get(to_try).prev =  graph.vertexlist.get(min_v); // on enregistre le nœud courant comme nœud parent du voisin
					}

				}
			}
			
			//On met à jour l'affichage
			try {
	    	    board.update(graph, min_v); 
	    	    Thread.sleep(10);
	    	} catch(InterruptedException e) {
	    	    System.out.println("stop");
	    	}
	            
		}
		
		System.out.println("Done! Using A*:");
		System.out.println("	Number of nodes explored: " + number_tries);
		System.out.println("	Total time of the path: " + graph.vertexlist.get(end).timeFromSource);
		LinkedList<Integer> path=new LinkedList<Integer>();
		path.addFirst(end);
		
		//TODO: remplir la liste path avec le chemin
		Vertex noeudParent = graph.vertexlist.get(end).prev; 
		while(noeudParent != null) {
			path.addFirst(noeudParent.num);
			noeudParent = graph.vertexlist.get(noeudParent.num).prev; 
		}
		
		board.addPath(graph, path);
		return path;
	}
	
	private static double distance(int Xa, int Ya, int Xb, int Yb) {
		/* La distance entre deux points (Xa, Ya) et (Xb, Yb)
		* est : Math.sqrt( (Xb-Xa)^2 + (Yb-Ya)^2 );
		*/
		
		// public static double pow(double a, double b)
		// Returns the value of the first argument raised to the power of the second argument (Source : JavaDoc)
		double resultX = Math.pow( (Xb-Xa) , 2);
		double resultY = Math.pow( (Yb-Ya) , 2);
		
		// public static double sqrt(double a)
		// Returns the correctly rounded positive square root of a double value (Source : JavaDoc)
		return ( Math.sqrt(resultX + resultY) );
   }
}
