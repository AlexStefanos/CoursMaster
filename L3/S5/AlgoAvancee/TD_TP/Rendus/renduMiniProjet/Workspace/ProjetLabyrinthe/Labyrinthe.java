import java.util.ArrayList;
import java.util.HashSet;

public class Labyrinthe {
	public static boolean can_move_dir(int vertex_num, ArrayList<Vertex> vertexList, int numberV) {
		return((vertex_num < numberV) ? (vertexList.get(vertex_num).etiquette == '.' || vertexList.get(vertex_num).etiquette == 'S') : false); 
	}
		
	private static ArrayList<Vertex> AStar(Graph graph, int start, int end, int ncols, int numberV) {
		ArrayList<Vertex> path = new ArrayList<Vertex>();
		HashSet<Integer> to_visit = new HashSet<Integer>();
		
		graph.vertexlist.get(start).timeFromSource = 0;	
		for(Vertex vertex : graph.vertexlist)
			to_visit.add(vertex.num);
		int i = 0;
		for(Vertex vertex : graph.vertexlist) {
			vertex.heuristic = 	distance(i % ncols, i / ncols, end % ncols, end / ncols);
			i++;
		}
			
		while (to_visit.contains(end)) {
			int min_v;
			double timeFromSourceHeuristicMinimale = Double.POSITIVE_INFINITY;
			
			min_v = 0;
			for(Integer vertexNum : to_visit) {
				if((graph.vertexlist.get(vertexNum).timeFromSource + graph.vertexlist.get(vertexNum).heuristic) <= timeFromSourceHeuristicMinimale) {
					min_v = vertexNum;
					timeFromSourceHeuristicMinimale = graph.vertexlist.get(vertexNum).timeFromSource + graph.vertexlist.get(vertexNum).heuristic;
				}
			}
			path.add( graph.vertexlist.get(min_v));
			to_visit.remove(min_v); 
			for(i = 0; i < graph.vertexlist.get(min_v).adjacencylist.size(); i++) {
				if(to_visit.contains(graph.vertexlist.get(min_v).adjacencylist.get(i).destination)) {
					int to_try = graph.vertexlist.get(min_v).adjacencylist.get(i).destination;
					boolean can_move = can_move_dir(to_try, graph.vertexlist, numberV);
																	
					if(can_move) {
						if(((graph.vertexlist.get(min_v).timeFromSource + graph.vertexlist.get(min_v).adjacencylist.get(i).weight)  < (graph.vertexlist.get(to_try).timeFromSource))) {
							graph.vertexlist.get(to_try).timeFromSource = ( graph.vertexlist.get(min_v).timeFromSource + graph.vertexlist.get(min_v).adjacencylist.get(i).weight );
							graph.vertexlist.get(to_try).prev =  graph.vertexlist.get(min_v);
						}
					}
				}
			}
		}
		return(path);
	}
		
	public static double distance(int Xa, int Ya, int Xb, int Yb) {
		double resultX, resultY; 
		
		resultX = Math.pow((Xb-Xa), 2);
		resultY = Math.pow((Yb-Ya), 2);	
		return((Math.sqrt(resultX + resultY)));
	}
		
	public static ArrayList<Character> DirectionMouvementPourChaqueTour(Graph graph, int start, int end, int ncols, int numberV){
		ArrayList<Vertex> chemin = AStar(graph, start, end, ncols, numberV);
		ArrayList<Character> directions = new ArrayList<Character>();
			
		for(int i = 0 ; i < ( chemin.size() - 1) ; i++) {
			int mouvement = chemin.get(i + 1).num - chemin.get(i).num;
			if(mouvement == 1)
				directions.add('R');
			else if(mouvement == -1)
				directions.add('L');
			else if(mouvement == ncols)
				directions.add('B');
			else if(mouvement == (-1 * ncols))
				directions.add('T');
			else 
				return(directions);
		}
		return(directions);
	}

	public static boolean burn_around(int vertex_num, ArrayList<Vertex> vertexList, int nlignes, int ncols) {		  
		if(vertexList.get(vertex_num).j != 0) {
			if (vertexList.get(vertex_num - 1).etiquette == '.')
				vertexList.get(vertex_num - 1).etiquette = 'A';
			else if (vertexList.get(vertex_num - 1).etiquette == 'S' || vertexList.get(vertex_num - 1).etiquette == 'D')
				return(true);
			}
			  
		if(vertexList.get(vertex_num).j != (ncols - 1)) {
			if(vertexList.get(vertex_num + 1).etiquette == '.')
				vertexList.get(vertex_num + 1).etiquette = 'A';
			else if (vertexList.get(vertex_num + 1).etiquette == 'S' || vertexList.get(vertex_num + 1).etiquette == 'D') 
				return(true);
			}

		if(vertexList.get(vertex_num).i != 0) {
			if(vertexList.get(vertex_num - ncols).etiquette == '.')
				vertexList.get(vertex_num - ncols).etiquette = 'A';
			else if(vertexList.get(vertex_num - ncols).etiquette == 'S' || vertexList.get(vertex_num - ncols).etiquette == 'D')
				return(true);
			}
			  		  
		if(vertexList.get(vertex_num).i != (nlignes-1)) {
			if(vertexList.get(vertex_num + ncols).etiquette == '.')
				vertexList.get(vertex_num + ncols).etiquette = 'A';
			else if(vertexList.get(vertex_num + ncols).etiquette == 'S' || vertexList.get(vertex_num + ncols).etiquette == 'D')
				return(true);
		}
		return(false);
	}
	
	public static boolean win_move(int debut, ArrayList<Vertex> vertexList, int nlignes, int ncols) {
		boolean left, right, top, bottom; 
		
		left = vertexList.get(debut).j != 0 && ( vertexList.get(debut - 1).etiquette == 'S');
		right = vertexList.get(debut).j != (ncols - 1) && ( vertexList.get(debut + 1).etiquette == 'S');
		top = vertexList.get(debut).i != 0 && vertexList.get(debut - ncols).etiquette == 'S';
		bottom = vertexList.get(debut).i != (nlignes - 1) && vertexList.get(debut + ncols).etiquette == 'S';	  
		return(top || left || right || bottom);
	}
	
	public static boolean move_prisoner(char directionMouvementPossiblePourCeTour, ArrayList<Vertex> vertexList, int nlignes, int ncols, int end) {
		int debut = 0;
		
		for(int i = 0; i < vertexList.size(); i++) {
			if(vertexList.get(i).etiquette == 'D')
				debut = i;
		}
		
		boolean win = win_move(debut, vertexList, nlignes, ncols); 	  
		if(win)
			return(true);
		else {
			vertexList.get(debut).etiquette = 'L';	    
			if (directionMouvementPossiblePourCeTour == 'B')
				vertexList.get(debut + ncols).etiquette = 'D';
			else if (directionMouvementPossiblePourCeTour == 'T')
				vertexList.get(debut - ncols).etiquette = 'D';
			else if (directionMouvementPossiblePourCeTour == 'L')
				vertexList.get(debut - 1).etiquette = 'D';
			else if (directionMouvementPossiblePourCeTour == 'R')
				vertexList.get(debut + 1).etiquette = 'D';
			}
		return(false);
		}
			
	public static char run_instance(Graph graph, int start, int end, int nlignes, int ncols) {
		int turn;
		ArrayList<Character> directions = DirectionMouvementPourChaqueTour(graph, start, end, ncols, nlignes * ncols); 
		
		turn = 0;
		while(turn < directions.size()) {
			for(int i = 0 ; i < graph.vertexlist.size() ; i++) {
				if( graph.vertexlist.get(i).etiquette == 'A' )
					graph.vertexlist.get(i).etiquette = 'F';
			    }
			for(int i = 0 ; i < graph.vertexlist.size() ; i++) {
				if(graph.vertexlist.get(i).etiquette == 'F' ) {
					if(burn_around(i, graph.vertexlist, nlignes, ncols) )
						return 'N';
					}
			    }
			    
			if(move_prisoner( directions.get(turn), graph.vertexlist, nlignes, ncols, end))
				return 'Y';
			turn++;
		}
		return 'N';
	}
}