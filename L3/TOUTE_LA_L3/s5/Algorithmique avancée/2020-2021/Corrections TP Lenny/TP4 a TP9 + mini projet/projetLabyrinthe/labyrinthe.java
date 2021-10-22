package projetLabyrinthe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import projetLabyrinthe.WeightedGraph.Graph;
import projetLabyrinthe.WeightedGraph.Vertex;

public class labyrinthe {
	
    /**
     * Une fonction qui vérifie si le prisonnier peut se déplacer vers la position vertex_num.
     * 
     * @param vertex_num Le numéro de l'emplacement vers lequel nous voulons vérifier s'il est possible de se déplacer.
     * @param vertexList Une liste de tous les nœuds du graphe qui représente la pièce à partir de laquelle le prisonnier tente de s'échapper.
     * @param numberV Le nombre de nœuds dans le graphe qui représente la pièce d'où le prisonnier tente de s'échapper.
     * @return true si le prisonnier peut se déplacer vers la position vertex_num ; false sinon.
     */
	public static boolean can_move_dir(int vertex_num, ArrayList<Vertex> vertexList, int numberV) {
		  /* On vérifie qu'il y a bien un sommet du nombre vertex_num dans le graphe,
		   * s'il y en a un, on s'assure que c'est un sommet vide
		   * ou un sommet qui représente la sortie de la pièce.
		   */
		  return (vertex_num < numberV) ? (vertexList.get(vertex_num).etiquette == '.' || vertexList.get(vertex_num).etiquette == 'S') : false; 
	} // can_move_dir()
	
	/**
	 * La méthode A* (à pronnoncer A star ou A étoile) peut être vu comme une extension de l’algorithme
	 * de Dijkstra. La différence se fait dans le choix du nœud à explorer. Au lieu de choisir celui ayant la plus
	 * petite distance temporaire, on sélectionne en fonction de la somme entre la distance temporaire et une
	 * heuristique (ici, une distance estimée au nœud d’arriver) à définir.
	 * Source de certains des commentaires : TP7partieB/Consignes.pdf.

	 * @author Sylvain Lobry / TP7Partie B
	 * 
	 * @param graph	le graphe représentant la carte.
	 * @param start un entier représentant la case de départ (entier unique correspondant à la case obtenue dans le sens de la lecture).
	 * @param end   un entier représentant la case d'arrivée (entier unique correspondant à la case obtenue dans le sens de la lecture).
	 * @param ncols le nombre de colonnes dans la carte.
	 * @param numberV le nombre de cases dans la carte.
	 * @return Une liste de sommets correspondant au chemin.
	 */
	private static ArrayList<Vertex> AStar(Graph graph, int start, int end, int ncols, int numberV)
	{
		// Path : Une liste de sommets correspondant au chemin.
		ArrayList<Vertex> path = new ArrayList<Vertex>();

		graph.vertexlist.get(start).timeFromSource = 0; // Le point de départ a une distance nulle depuis le point de départ.
		
		// Mettre tous les noeuds du graphe dans la liste des noeuds à visiter :
		HashSet<Integer> to_visit = new HashSet<Integer>();
		for(Vertex vertex : graph.vertexlist)
			to_visit.add( vertex.num );
		
		// Remplir l'attribut graph.vertexlist.get(v).heuristic pour tous les noeuds v du graphe:
		//  heuristic : une distance estimée au nœud d’arriver.
		int i = 0;
		for(Vertex vertex : graph.vertexlist) {
			// distance(int Xa, int Ya, int Xb, int Yb)
			vertex.heuristic = 	distance(i % ncols, i / ncols, end % ncols, end / ncols);
			i++;
		}
		
		while (to_visit.contains(end))
		{
			// Trouver le noeud min_v parmis tous les noeuds v ayant la distance temporaire
			//      (graph.vertexlist.get(v).timeFromSource + heuristic) minimale.
			int min_v = 0;
			double timeFromSourceHeuristicMinimale = Double.POSITIVE_INFINITY;
			for(Integer vertexNum : to_visit ) {
				if( ( graph.vertexlist.get(vertexNum).timeFromSource + graph.vertexlist.get(vertexNum).heuristic ) <= timeFromSourceHeuristicMinimale ) {
					min_v = vertexNum;
					timeFromSourceHeuristicMinimale = graph.vertexlist.get(vertexNum).timeFromSource + graph.vertexlist.get(vertexNum).heuristic;
				} // if
			} // for			
			
		    // Ajouter à la liste des nœuds de l'itinéraire.
			path.add( graph.vertexlist.get(min_v) );
			
			// On l'enlève des noeuds à visiter
			to_visit.remove(min_v); 
			
			// Pour tous ses voisins, on vérifie si on est plus rapide en passant par ce noeud.
			for (i = 0 ; i < graph.vertexlist.get(min_v).adjacencylist.size() ; i++) {
				if( to_visit.contains(graph.vertexlist.get(min_v).adjacencylist.get(i).destination)) {
					int to_try = graph.vertexlist.get(min_v).adjacencylist.get(i).destination;
					boolean can_move = can_move_dir(to_try, graph.vertexlist, numberV); // true si le prisonnier peut se déplacer vers la position vertex_num.
																
					if(can_move) { // true si le prisonnier peut se déplacer vers la position vertex_num.
						if( (  ( graph.vertexlist.get(min_v).timeFromSource + graph.vertexlist.get(min_v).adjacencylist.get(i).weight )  < (graph.vertexlist.get(to_try).timeFromSource) ) ) { // si la distance en passant par le nœud courant (donc distance temporaire plus distance du nœud courant au voisin) est plus petite que la distance temporaire
							graph.vertexlist.get(to_try).timeFromSource = ( graph.vertexlist.get(min_v).timeFromSource + graph.vertexlist.get(min_v).adjacencylist.get(i).weight ); // on mets à jour la distance temporaire
							graph.vertexlist.get(to_try).prev =  graph.vertexlist.get(min_v); // on enregistre le nœud courant comme nœud parent du voisin
						} // if
					} // if(can_move)
				} // if
			} // for
		} // while
		return path; // Path : Une liste de sommets correspondant au chemin.
	} // AStar()
	
	/**
	 * Une fonction qui calcule la distance entre deux points a et b.
	 * @param Xa La coordonnée X du point a.
	 * @param Ya La coordonnée Y du point a.
	 * @param Xb La coordonnée X du point b.
	 * @param Yb La coordonnée Y du point b.
	 * @return La distance entre les deux points a et b.
	 */
	static double distance(int Xa, int Ya, int Xb, int Yb) {
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
   } // distance()
	
	/**
	 * Une fonction qui parcourt la liste des nœuds de l'itinéraire le plus court pour le prisonnier 
	 * (une liste créée par la fonction AStar) et renvoie en conséquence la liste des mouvements que
	 * le prisonnier doit effectuer à chaque tour pour suivre cet itinéraire.
	 * 'T' = top ; 'B' = bottom ; 'L' = left ; 'R' = right.
	 * 
	 * @param graph	le graphe représentant la carte.
	 * @param start un entier représentant la case de départ (entier unique correspondant à la case obtenue dans le sens de la lecture).
	 * @param end   un entier représentant la case d'arrivée (entier unique correspondant à la case obtenue dans le sens de la lecture).
	 * @param ncols le nombre de colonnes dans la carte.
	 * @param numberV le nombre de cases dans la carte.
	 * @return La liste des mouvements que le prisonnier doit effectuer à chaque tour.
	 */
	static ArrayList<Character> DirectionMouvementPourChaqueTour(Graph graph, int start, int end, int ncols, int numberV){
		// La fonction renvoie l'itinéraire le plus court entre le départ et la sortie
		ArrayList<Vertex> chemin = AStar(graph, start, end, ncols, numberV);
		// La liste des mouvements que le prisonnier doit effectuer à chaque tour pour suivre cet itinéraire.
		ArrayList<Character> directions = new ArrayList<Character>();
		
		for(int i = 0 ; i < ( chemin.size() - 1) ; i++) {
			int mouvement = chemin.get(i + 1).num - chemin.get(i).num;
			if( mouvement == 1 )
				directions.add('R'); // 'R' = right
			else if( mouvement == -1 )
				directions.add('L'); // 'L' = left
			else if( mouvement == ncols )
				directions.add('B'); // 'B' = bottom
			else if( mouvement == ( -1 * ncols) )
				directions.add('T'); // 'T' = top
			else return directions;
		} // for
		return directions; // La liste des mouvements que le prisonnier doit effectuer à chaque tour
	} // DirectionMouvementPourChaqueTour()

	
	/**
	 * La fonction prend comme argument une liste de sommets qui représente la pièce 
	 * et un numero vertex_num d'une position dans lequel il y a du feu. 
	 * La fonction place le caractère « A » aux positions autour du feu 
	 * pour éviter que les flammes soient propagées le même tour où elles sont crées.
	 * Si l'une des positions autour du feu est le point 
	 * de début ou de sortie, le jeu est terminé.
	 * 
     * @param vertex_num Le numéro de l'emplacement dans lequel il y a du feu.
     * @param vertexList Une liste de tous les nœuds du graphe qui représente la pièce à partir de laquelle le prisonnier tente de s'échapper.
	 * @param nlignes Le nombre de lignes dans la carte.
     * @param ncols le nombre de colonnes dans la carte.
	 * @return true si gameover ; false sinon.
	 */
	static boolean burn_around(int vertex_num, ArrayList<Vertex> vertexList, int nlignes, int ncols) {
		  //'A' pour éviter que les flammes soient propagées le même tour où elles sont crées
		  
		  if (vertexList.get(vertex_num).j != 0) { // Si nous ne sommes pas au début d'une ligne
			    if (vertexList.get(vertex_num - 1).etiquette == '.') // Signification : libre
			    	vertexList.get(vertex_num - 1).etiquette = 'A'; //'A' pour éviter que les flammes soient propagées le même tour où elles sont crées
			    else if (vertexList.get(vertex_num - 1).etiquette == 'S' || vertexList.get(vertex_num - 1).etiquette == 'D') // D = début , S = sortie
			    	return true; // La fonction burn_around retourne true si gameover
		  } //  if (vertexList.get(vertex_num).j != 0)
		  
		  if (vertexList.get(vertex_num).j != (ncols - 1) ) { // Si nous ne sommes pas au bout d'une ligne
			    if (vertexList.get(vertex_num + 1).etiquette == '.') // Signification : libre
			    	vertexList.get(vertex_num + 1).etiquette = 'A'; //'A' pour éviter que les flammes soient propagées le même tour où elles sont crées
			    else if (vertexList.get(vertex_num + 1).etiquette == 'S' || vertexList.get(vertex_num + 1).etiquette == 'D') 
			    	return true; // La fonction burn_around retourne true si gameover
		  } //  if (vertexList.get(vertex_num).j != (ncols - 1) )

		  if (vertexList.get(vertex_num).i != 0) { // Si nous ne sommes pas au début d'une colonne
		    if (vertexList.get(vertex_num - ncols).etiquette == '.') // Signification : libre
		    	vertexList.get(vertex_num - ncols).etiquette = 'A';  //'A' pour éviter que les flammes soient propagées le même tour où elles sont crées
		    else if (vertexList.get(vertex_num - ncols).etiquette == 'S' || vertexList.get(vertex_num - ncols).etiquette == 'D') // D = début , S = sortie
		    	return true; // La fonction burn_around retourne true si gameover
		  } // if (vertexList.get(vertex_num).i != 0)
		  		  
		  if ( vertexList.get(vertex_num).i != (nlignes-1)) { // Si nous ne sommes pas à la fin d'une colonne
		    if (vertexList.get(vertex_num + ncols).etiquette == '.') // Signification : libre
		    	vertexList.get(vertex_num + ncols).etiquette = 'A'; //'A' pour éviter que les flammes soient propagées le même tour où elles sont crées
		    else if (vertexList.get(vertex_num + ncols).etiquette == 'S' || vertexList.get(vertex_num + ncols).etiquette == 'D') // D = début , S = sortie
		    	return true; // La fonction burn_around retourne true si gameover
		  } //  if ( vertexList.get(vertex_num).i != (nlignes-1))
		  		  
		  return false; // La fonction burn_around retourne true si gameover ; false sinon
		} // burn_around()
		
				
		/**
		 * La fonction vérifie si le prisonnier est à une position du point de sortie 
		 * (à une position de la victoire).
		 * 
		 * @param debut  Numero du sommet de la position dans laquelle se trouve maintenant le prisonnier.
         * @param vertexList Une liste de tous les nœuds du graphe qui représente la pièce à partir de laquelle le prisonnier tente de s'échapper.
	     * @param nlignes Le nombre de lignes dans la carte.
         * @param ncols le nombre de colonnes dans la carte.
		 * @return true  si le prisonnier est à une position du point de sortie ; false sinon.
		 */
		static boolean win_move(int debut, ArrayList<Vertex> vertexList, int nlignes, int ncols) {
		  
		  /*  S = sortie  */
		  boolean left = vertexList.get(debut).j != 0 && ( vertexList.get(debut - 1).etiquette == 'S'); // Si nous ne sommes pas au début d'une ligne
		  boolean right = vertexList.get(debut).j != (ncols - 1) && ( vertexList.get(debut + 1).etiquette == 'S'); // Si nous ne sommes pas au bout d'une ligne
		  boolean top = vertexList.get(debut).i != 0 && vertexList.get(debut - ncols).etiquette == 'S'; // Si nous ne sommes pas au début d'une colonne
		  boolean bottom = vertexList.get(debut).i != (nlignes - 1) && vertexList.get(debut + ncols).etiquette == 'S'; // Si nous ne sommes pas à la fin d'une colonn
		  
		  return top || left || right || bottom;
		} // win_move()
		
		/**
		 * Une fonction qui effectue le mouvement du prisonnier vers une position.
		 * @param directionMouvementPossiblePourCeTour 'T' = top ; 'B' = bottom ; 'L' = left ; 'R' = right.
		 * @param vertexList Une liste de tous les nœuds du graphe qui représente la pièce à partir de laquelle le prisonnier tente de s'échapper.
	     * @param nlignes Le nombre de lignes dans la carte.
         * @param ncols le nombre de colonnes dans la carte.
		 * @param end  un entier représentant la case d'arrivée (entier unique correspondant à la case obtenue dans le sens de la lecture).
		 * @return true si victoire ; false sinon
		 */
		static boolean move_prisoner(char directionMouvementPossiblePourCeTour, ArrayList<Vertex> vertexList, int nlignes, int ncols, int end) {
		   int debut = 0; // Initialisation de la variable.
		   
		   // Nous parcourons la carte et localisons l'emplacement du prisoner
		   for(int i = 0 ; i < vertexList.size() ; i++) {
		     if( vertexList.get(i).etiquette == 'D' ) // Signification : début
		    	 debut = i;
		   } // for
		  
		  /* La fonction win_move() vérifie si le prisonnier est à une position du point de sortie 
		   * (à une position de la victoire)
		   */
		  boolean win = win_move(debut, vertexList, nlignes, ncols); 
		  
		  if (win) 
			  return true; // La fonction move_prisoner retourne true si victoire
		  else {
			    vertexList.get(debut).etiquette = 'L';  //L signifie que le prisonnier est déjà passé par cette case
			    
			    if (directionMouvementPossiblePourCeTour == 'B') //'B' = bottom
			    	vertexList.get(debut + ncols).etiquette = 'D'; // On fait descendre le prisonnier.
			    else if (directionMouvementPossiblePourCeTour == 'T') //'T' = top
			    	vertexList.get(debut - ncols).etiquette = 'D'; // Nous déplaçons le prisonnier vers le haut.
			    else if (directionMouvementPossiblePourCeTour == 'L') //'L' = left
			    	vertexList.get(debut - 1).etiquette = 'D'; // Nous déplaçons le prisonnier vers la gauche.
			    else if (directionMouvementPossiblePourCeTour == 'R') //'R' = right.
			    	vertexList.get(debut + 1).etiquette = 'D'; // Nous déplaçons le prisonnier vers la droite.
		   }
		   return false;
		} // move_prisoner()
		
		/**
		 * Une fonction qui vérifie si le prisonnier a une chance d'être pardonné. 
	     * @param graph	le graphe représentant la carte.
	     * @param start un entier représentant la case de départ (entier unique correspondant à la case obtenue dans le sens de la lecture).
	     * @param end   un entier représentant la case d'arrivée (entier unique correspondant à la case obtenue dans le sens de la lecture).
	     * @param ncols le nombre de colonnes dans la carte.
	     * @param numberV le nombre de cases dans la carte.
		 * @return  "Y" si le prisonnier a une chance d'être pardonné ; sinon, "N".
		 */
		static char run_instance(Graph graph, int start, int end, int nlignes, int ncols) {
		  int turn = 0; // Le numéro du premier tour est 0.
		  
		  // La liste des mouvements quele prisonnier doit effectuer à chaque tour.
		  ArrayList<Character> directions = DirectionMouvementPourChaqueTour(graph, start, end, ncols, nlignes * ncols); 
		  
		  while ( turn < directions.size() ) {
			  
		    for(int i = 0 ; i < graph.vertexlist.size() ; i++) {
		    	if( graph.vertexlist.get(i).etiquette == 'A' ) // La lettre A représente un carré qui était autour du feu lors du tour précédent. Autrement dit, maintenant le feu se déplace.
		    		graph.vertexlist.get(i).etiquette = 'F'; // Signification : feu
		    } // for
		    
		    // Si l'une des positions autour du feu est le pointde début ou de sortie, le jeu est terminé.
		    for(int i = 0 ; i < graph.vertexlist.size() ; i++) {
		    	if( graph.vertexlist.get(i).etiquette == 'F' ) { // Signification : feu
			          if ( burn_around(i, graph.vertexlist, nlignes, ncols) ) // La fonction burn_around retourne true si gameover ; false sinon
			        	  return 'N'; //  le prisonnier a PAS une chance d'être pardonné.
		    	} // if
		    } // for
		    
		    if ( move_prisoner( directions.get(turn), graph.vertexlist, nlignes, ncols, end ) ) // La fonction move_prisoner retourne true si victoire ; false sinon
		    	return 'Y'; //  le prisonnier a une chance d'être pardonné.
		    
		    turn++; // Le numéro du prochain tour
		  } // while
		  return 'N'; // le prisonnier a PAS une chance d'être pardonné.
		} // run_instance()

		public static void main(String[] args) {
		  Scanner sc = new Scanner(System.in);
		  
		  // On garde dans cette liste le résultat de chaque instance afin de tout imprimer à la fin.
		  List<Character> resultats = new ArrayList<Character>();
		  
		  /* Utilisation de l'entrée de chaîne (nextLine()) au lieu de l'entrée d'un int (nextInt())
		   * en raison de problèmes avec le tampon en raison du mélange entre l'entrée de int
		   * et l'entrée d'une chaîne. Par conséquent, l'entrée du nombre d'instances est effectuée
		   * sous forme de String, puis convertie en un entier.
		   */
		  int instances;
		  instances = Integer.parseInt( sc.nextLine() );
		  
		  // Un tableau qui conserve temporairement le caractère de chaque carré de la pièce jusqu'à ce que le graphe représentant la pièce soit créé.
		  char[][] map = null;
		  
		  int n = 0, m = 0; // n - nombre de lignes, m - nombre de colonnes
		  for (int i = 0 ; i < instances ; i++) {
		    
		    /* L'utilisateur entre le nombre de lignes et le nombre de colonnes dans une ligne 
		     * avec un espace les séparant. Ensuite, la séparation entre les deux nombres 
		     * est effectuée à l'aide de StringTokenizer.
		     */
	        StringTokenizer tokenizer = new StringTokenizer(sc.nextLine(), " "); 
		    n = ( tokenizer.hasMoreTokens() ) ?  Integer.parseInt( tokenizer.nextToken() ) : 0;
		    m = ( tokenizer.hasMoreTokens() ) ?  Integer.parseInt( tokenizer.nextToken() ) : 0;
		    
		    // Représentation de la pièce: n lignes et m colonnes
		    map = new char[n][m];
		    
		    /* Chacune des n lignes contient exactement m symboles représentant
		     * une pièce d'où le prisonnier doit s'échapper.
		     */
		    for (int j = 0 ; j < n ; j++) {
		      String entree = sc.nextLine();
       
		      for (int k = 0 ; k < m ; k++)
			        map[j][k] = entree.charAt(k); 
		    } // for(j)
		    
            Graph graph = new Graph(); // Appel au constructeur : création d'une nouvelle instance de l'objet Graph.
		    int startV = 0, endV = 0; // Point de départ et point de sortie.
		    for (int j = 0 ; j < n ; j++) {       
			      for (int k = 0 ; k < m ; k++) {
			    	  graph.addVertex(map[j][k], 1, j, k); // Ajout d'un sommet au graphe.
			    	  	if(map[j][k] == 'D')
			    	  		startV = j * m + k;
			    	  	if(map[j][k] == 'S')
			    	  		endV = j * m + k;
			      }
		    }
		    
		    // Ajout des arrêtes
		     for (int line = 0 ; line < n ; line++) {
		    	  for (int col = 0 ; col < m ; col++) {
		    		  int source = line * m + col;
		    		  int dest;
		    		  double weight;
						for (int j = -1; j <= 1; j++) {
							for (int k = -1; k <= 1; k++) {
								if ( (j != 0) || (k != 0) ) {
									if((line + j) >= 0 && (line + j) < n && (col + k) >= 0 && (col + k) < m) {
										dest = (line + j) * m + col + k;
										weight = 1;
										if( Math.abs(source - dest) == 1 || Math.abs(source - dest) == m) // Afin d'éviter les connexions entre les nœuds en diagonale.
											graph.addEgde(source, dest, weight);
									} // if
								} // if
							} // for
						} // for		    	  
		    	  }	// for(col)
		     } // for(line)
		     
		    resultats.add( run_instance( graph, startV, endV, n, m ) );
		  } // for(i)
		  
		  for(Character character : resultats)
			  System.out.println( character );
		  
		  sc.close();
		} // main()
} // class labyrintheResolutionPasNaive