package partieB;

import java.util.HashSet;
import java.util.LinkedList;

public class AStarAlgo {
	public static LinkedList<Integer> AStar(WeightedGraph graph, int start, int end, int nb_col, int numberV, Board board) {
		int number_tries, i, min_v, to_try;
		HashSet<Integer> to_visit = new HashSet<Integer>();
		
		number_tries = 0;
		graph.vertexlist.get(start).timeFromSource = 0;
		for(Vertex vertex : graph.vertexlist)
			to_visit.add( vertex.num );
		i = 0;
		for(Vertex vertex : graph.vertexlist) {
			vertex.heuristic = 	distance(i % nb_col, i / nb_col, end % nb_col, end / nb_col);
			i++;
		}
		while (to_visit.contains(end)) {
			min_v = 0;
			double timeFromSourceHeuristicMinimale = Double.POSITIVE_INFINITY;
			for(Integer vertexNum : to_visit ) {
				if( ( graph.vertexlist.get(vertexNum).timeFromSource + graph.vertexlist.get(vertexNum).heuristic ) <= timeFromSourceHeuristicMinimale ) {
					min_v = vertexNum;
					timeFromSourceHeuristicMinimale = graph.vertexlist.get(vertexNum).timeFromSource + graph.vertexlist.get(vertexNum).heuristic;
				}
			}
			to_visit.remove(min_v); 
			number_tries += 1;
			for (i = 0 ; i < graph.vertexlist.get(min_v).adjacencylist.size() ; i++) {
				if( to_visit.contains(graph.vertexlist.get(min_v).adjacencylist.get(i).destination)) {
					to_try = graph.vertexlist.get(min_v).adjacencylist.get(i).destination;
					
					if(((graph.vertexlist.get(min_v).timeFromSource + graph.vertexlist.get(min_v).adjacencylist.get(i).weight )  < (graph.vertexlist.get(to_try).timeFromSource))) {
						graph.vertexlist.get(to_try).timeFromSource = ( graph.vertexlist.get(min_v).timeFromSource + graph.vertexlist.get(min_v).adjacencylist.get(i).weight );
						graph.vertexlist.get(to_try).prev =  graph.vertexlist.get(min_v);
					}
				}
			}
			try {
	    	    board.update(graph, min_v); 
	    	    Thread.sleep(10);
	    	}catch(InterruptedException e) {
	    	    System.out.println("stop");
	    	}   
		}
		System.out.println("Done! Using A*:");
		System.out.println("	Number of nodes explored: " + number_tries);
		System.out.println("	Total time of the path: " + graph.vertexlist.get(end).timeFromSource);
		
		LinkedList<Integer> path = new LinkedList<Integer>();
		Vertex noeudParent = graph.vertexlist.get(end).prev;
		
		path.addFirst(end);
		while(noeudParent != null) {
			path.addFirst(noeudParent.num);
			noeudParent = graph.vertexlist.get(noeudParent.num).prev; 
		}
		board.addPath(graph, path);
		return(path);
	}
	
	private static double distance(int Xa, int Ya, int Xb, int Yb) {
		double resultX = Math.pow((Xb-Xa), 2);
		double resultY = Math.pow((Yb-Ya), 2);
		return(Math.sqrt(resultX + resultY));
   }
}
