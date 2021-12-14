import java.util.ArrayList;
import java.util.HashSet;

public class Labyrinthe {
	public static boolean deplacement(int sommet, ArrayList<Sommet> listeSommets, int nbSommets) {
		return((sommet < nbSommets) ? (listeSommets.get(sommet).etiquette == '.' || listeSommets.get(sommet).etiquette == 'S') : false); 
	}
		
	private static ArrayList<Sommet> AStar(Graphe graphe, int debut, int fin, int nbColonnes, int nbSommets) {
		ArrayList<Sommet> chemin = new ArrayList<Sommet>();
		HashSet<Integer> pasVisite = new HashSet<Integer>();
		
		graphe.listeSommets.get(debut).timeFromSource = 0;	
		for(Sommet sommet : graphe.listeSommets)
			pasVisite.add(sommet.num);
		int i = 0;
		for(Sommet sommet : graphe.listeSommets) {
			sommet.heuristic = 	distance(i % nbColonnes, i / nbColonnes, fin % nbColonnes, fin / nbColonnes);
			i++;
		}
			
		while(pasVisite.contains(fin)) {
			int sommetMin;
			double chronoHeuristique = Double.POSITIVE_INFINITY;
			
			sommetMin = 0;
			for(Integer sommet : pasVisite) {
				if((graphe.listeSommets.get(sommet).timeFromSource + graphe.listeSommets.get(sommet).heuristic) <= chronoHeuristique) {
					sommetMin = sommet;
					chronoHeuristique = graphe.listeSommets.get(sommet).timeFromSource + graphe.listeSommets.get(sommet).heuristic;
				}
			}
			chemin.add(graphe.listeSommets.get(sommetMin));
			pasVisite.remove(sommetMin);
			for(i = 0; i < graphe.listeSommets.get(sommetMin).adjacencylist.size(); i++) {
				if(pasVisite.contains(graphe.listeSommets.get(sommetMin).adjacencylist.get(i).destination)) {
					int pasDEssaie = graphe.listeSommets.get(sommetMin).adjacencylist.get(i).destination;
					boolean deplacementPossible = deplacement(pasDEssaie, graphe.listeSommets, nbSommets);
																	
					if(deplacementPossible) {
						if(((graphe.listeSommets.get(sommetMin).timeFromSource + graphe.listeSommets.get(sommetMin).adjacencylist.get(i).weight)  < (graphe.listeSommets.get(pasDEssaie).timeFromSource))) {
							graphe.listeSommets.get(pasDEssaie).timeFromSource = (graphe.listeSommets.get(sommetMin).timeFromSource + graphe.listeSommets.get(sommetMin).adjacencylist.get(i).weight );
							graphe.listeSommets.get(pasDEssaie).prev =  graphe.listeSommets.get(sommetMin);
						}
					}
				}
			}
		}
		return(chemin);
	}
		
	public static double distance(int Xa, int Ya, int Xb, int Yb) {
		double xFinal, yFinal; 
		
		xFinal = Math.pow((Xb-Xa), 2);
		yFinal = Math.pow((Yb-Ya), 2);	
		return((Math.sqrt(xFinal + yFinal)));
	}
		
	public static ArrayList<Character> directionMouvementPourChaqueTour(Graphe graph, int debut, int fin, int nbColonnes, int nbSommets){
		ArrayList<Sommet> chemin = AStar(graph, debut, fin, nbColonnes, nbSommets);
		ArrayList<Character> directions = new ArrayList<Character>();
			
		for(int i = 0 ; i < ( chemin.size() - 1) ; i++) {
			int mouvement = chemin.get(i + 1).num - chemin.get(i).num;
			if(mouvement == 1)
				directions.add('R');
			else if(mouvement == -1)
				directions.add('L');
			else if(mouvement == nbColonnes)
				directions.add('B');
			else if(mouvement == (-1 * nbColonnes))
				directions.add('T');
			else 
				return(directions);
		}
		return(directions);
	}

	public static boolean pasDispoAutour(int sommet, ArrayList<Sommet> listeSommets, int nbLignes, int nbColonnes) {		  
		if(listeSommets.get(sommet).j != 0) {
			if (listeSommets.get(sommet - 1).etiquette == '.')
				listeSommets.get(sommet - 1).etiquette = 'A';
			else if (listeSommets.get(sommet - 1).etiquette == 'S' || listeSommets.get(sommet - 1).etiquette == 'D')
				return(true);
			}
			  
		if(listeSommets.get(sommet).j != (nbColonnes - 1)) {
			if(listeSommets.get(sommet + 1).etiquette == '.')
				listeSommets.get(sommet + 1).etiquette = 'A';
			else if (listeSommets.get(sommet + 1).etiquette == 'S' || listeSommets.get(sommet + 1).etiquette == 'D') 
				return(true);
			}

		if(listeSommets.get(sommet).i != 0) {
			if(listeSommets.get(sommet - nbColonnes).etiquette == '.')
				listeSommets.get(sommet - nbColonnes).etiquette = 'A';
			else if(listeSommets.get(sommet - nbColonnes).etiquette == 'S' || listeSommets.get(sommet - nbColonnes).etiquette == 'D')
				return(true);
			}
			  		  
		if(listeSommets.get(sommet).i != (nbLignes-1)) {
			if(listeSommets.get(sommet + nbColonnes).etiquette == '.')
				listeSommets.get(sommet + nbColonnes).etiquette = 'A';
			else if(listeSommets.get(sommet + nbColonnes).etiquette == 'S' || listeSommets.get(sommet + nbColonnes).etiquette == 'D')
				return(true);
		}
		return(false);
	}
	
	public static boolean win_move(int debut, ArrayList<Sommet> listeSommets, int nbLignes, int nbColonnes) {
		boolean left, right, top, bottom; 
		
		left = listeSommets.get(debut).j != 0 && ( listeSommets.get(debut - 1).etiquette == 'S');
		right = listeSommets.get(debut).j != (nbColonnes - 1) && ( listeSommets.get(debut + 1).etiquette == 'S');
		top = listeSommets.get(debut).i != 0 && listeSommets.get(debut - nbColonnes).etiquette == 'S';
		bottom = listeSommets.get(debut).i != (nbLignes - 1) && listeSommets.get(debut + nbColonnes).etiquette == 'S';	  
		return(top || left || right || bottom);
	}
	
	public static boolean move_prisoner(char directionMouvementPossiblePourCeTour, ArrayList<Sommet> listeSommets, int nbLignes, int nbColonnes, int fin) {
		int debut = 0;
		
		for(int i = 0; i < listeSommets.size(); i++) {
			if(listeSommets.get(i).etiquette == 'D')
				debut = i;
		}
		
		boolean win = win_move(debut, listeSommets, nbLignes, nbColonnes); 	  
		if(win)
			return(true);
		else {
			listeSommets.get(debut).etiquette = 'L';	    
			if (directionMouvementPossiblePourCeTour == 'B')
				listeSommets.get(debut + nbColonnes).etiquette = 'D';
			else if (directionMouvementPossiblePourCeTour == 'T')
				listeSommets.get(debut - nbColonnes).etiquette = 'D';
			else if (directionMouvementPossiblePourCeTour == 'L')
				listeSommets.get(debut - 1).etiquette = 'D';
			else if (directionMouvementPossiblePourCeTour == 'R')
				listeSommets.get(debut + 1).etiquette = 'D';
			}
		return(false);
		}
			
	public static char run_instance(Graphe graphe, int debut, int fin, int nbLignes, int nbColonnes) {
		int turn;
		ArrayList<Character> directions = directionMouvementPourChaqueTour(graphe, debut, fin, nbColonnes, nbLignes * nbColonnes); 
		
		turn = 0;
		while(turn < directions.size()) {
			for(int i = 0; i < graphe.listeSommets.size(); i++) {
				if( graphe.listeSommets.get(i).etiquette == 'A' )
					graphe.listeSommets.get(i).etiquette = 'F';
			    }
			for(int i = 0; i < graphe.listeSommets.size(); i++) {
				if(graphe.listeSommets.get(i).etiquette == 'F' ) {
					if(pasDispoAutour(i, graphe.listeSommets, nbLignes, nbColonnes) )
						return('N');
					}
			}
			if(move_prisoner( directions.get(turn), graphe.listeSommets, nbLignes, nbColonnes, fin))
				return('Y');
			turn++;
		}
		return('N');
	}
}