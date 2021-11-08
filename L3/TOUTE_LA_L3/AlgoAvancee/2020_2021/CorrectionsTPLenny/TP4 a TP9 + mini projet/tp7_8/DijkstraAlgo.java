// Par Sylvain Lobry, pour le cours "IF05X040 Algorithmique avancée"
// de l'Université de Paris, 11/2020

package tp7_8;

import java.util.HashSet;
import java.util.LinkedList;

import tp7_8.WeightedGraph.Graph;
import tp7_8.WeightedGraph.Vertex;

public class DijkstraAlgo {
	
	/**
	 * L’algorithme de Dijkstra cherche le plus court chemin depuis le point de départ et le sommet d’arrivé dans un graphe (Source : Consignes.pdf).
	 * Source des commentaires dans le code : Consignes.pdf.
	 * 
	 * @param graph		le graphe représentant la carte
	 * @param start	    un entier représentant la case de départ (entier unique correspondant à la case obtenue dans le sens de la lecture)
	 * @param end	    un entier représentant la case d'arrivée (entier unique correspondant à la case obtenue dans le sens de la lecture)
	 * @param numberV 	le nombre de cases dans la carte
	 * @param board	    l'affichage
	 * @return une liste d'entiers correspondant au chemin.
	 */
	public static LinkedList<Integer> Dijkstra(Graph graph, int start, int end, int numberV, Board board)
	{
		graph.vertexlist.get(start).timeFromSource = 0; // Le point de départ a une distance nulle depuis le point de départ.
		int number_tries = 0; // Nombre de nœuds traversés
		
		//TODO: mettre tous les noeuds du graphe dans la liste des noeuds à visiter:
		HashSet<Integer> to_visit = new HashSet<Integer>();
		for(Vertex vertex : graph.vertexlist)
			to_visit.add( vertex.num );
				
		while (to_visit.contains(end))
		{
			//TODO: trouver le noeud min_v parmis tous les noeuds v ayant la distance temporaire
			//      graph.vertexlist.get(v).timeFromSource minimale.
			int min_v = 0;
			double timeFromSourceMinimale = Double.POSITIVE_INFINITY;
			for(Integer vertexNum : to_visit ) {
				if( graph.vertexlist.get(vertexNum).timeFromSource <= timeFromSourceMinimale) {
						min_v = vertexNum;
						timeFromSourceMinimale = graph.vertexlist.get(vertexNum).timeFromSource;
				} // if
			} // for	
			
			//On l'enlève des noeuds à visiter
			//get vertex with min dist
			to_visit.remove(min_v); 
			number_tries += 1;
			
			//TODO: pour tous ses voisins, on vérifie si on est plus rapide en passant par ce noeud.
			
			/* On regarde pour ses voisins si la distance en passant par le nœud courant (donc distance
			 * temporaire plus distance du nœud courant au voisin) est plus petite que la distance temporaire.
			 * Si tel est le cas, on mets à jour la distance temporaire, et on enregistre le nœud courant
			 * comme nœud parent du voisin dans le chemin (Source : Consignes.pdf).
			 */
			for (int i = 0 ; i < graph.vertexlist.get(min_v).adjacencylist.size() ; i++) // On regarde les voisins
			{	
				if( to_visit.contains(graph.vertexlist.get(min_v).adjacencylist.get(i).destination)) {
					int to_try = graph.vertexlist.get(min_v).adjacencylist.get(i).destination;
					
					if( (  ( graph.vertexlist.get(min_v).timeFromSource +graph.vertexlist.get(min_v).adjacencylist.get(i).weight )  < (graph.vertexlist.get(to_try).timeFromSource) ) ) { // si la distance en passant par le nœud courant (donc distance temporaire plus distance du nœud courant au voisin) est plus petite que la distance temporaire
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
		
		System.out.println("Done! Using Dijkstra:");
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
}
