// Par Sylvain Lobry, pour le cours "IF05X040 Algorithmique avanc�e"
// de l'Universit� de Paris, 11/2020

package tp7_8;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JFrame;

import tp7_8.WeightedGraph.Graph;


/**
 * La classe principale de l'application. 
 */
public class App {
	
	//M�thode principale
	public static void main(String[] args) {
		//Lecture de la carte et cr�ation du graphe
		try {
			  //TODO: obtenir le fichier qui d�crit la carte
		      File myObj = new File( args[0] );  // Le chemin d'acc�s au fichier source est obtenu � l'aide d'un argument qui est pass� par le terminal
		      Scanner myReader = new Scanner(myObj);
		      String data = "";
		      
		      /* On ignore les deux premi�res lignes
		       * Les deux premi�res lignes (lignes 0 + 1) :
		       * ==Metadata==
		       * =Size=
		       */
		      for (int i = 0 ; i < 3 ; i++)
		    	  data = myReader.nextLine();
		      
		      /* Lecture du nombre de lignes (ligne 2)
		       * nlines = <int: Le nombre de lignes>
		       * Exemple : nlines=50
		       */
		      int nlines = Integer.parseInt(data.split("=")[1]);
		      
		      // Lecture du nombre de colonnes (ligne 3)
		      data = myReader.nextLine();
		      
		      /* Ligne 3
		       * ncol=<int: Le nombre de colonnes>
		       * Exemple : ncol=100
		       */
		      int ncols = Integer.parseInt(data.split("=")[1]);
		      
		      // Initialisation du graphe
		      Graph graph = new Graph();
		      
		      HashMap<String, Integer> groundTypes = new HashMap<String, Integer>();
		      HashMap<Integer, String> groundColor = new HashMap<Integer, String>();
		      
		      data = myReader.nextLine(); // Ligne 4 : =Types=
		      
		      data = myReader.nextLine();
		      // Lire les diff�rents types de cases
		      while (!data.equals("==Graph=="))
		      {
		    	  String name = data.split("=")[0];
		    	  int time = Integer.parseInt(data.split("=")[1]); // G=<int: Le temps n�cessaire pour parcourir verticalement ou horizontalement une case de ce type>
		    	  
		    	  data = myReader.nextLine();
		    	  String color = data; // <string: La couleur de la case du type pr�c�dent (sont utilisables sans modificationdu code: �green�, �gray�, �blue� et �yellow�)>
		    	  
		    	  groundTypes.put(name, time);
		    	  groundColor.put(time, color);
		    	  
		    	  data = myReader.nextLine();
		      }
		      
		      //On ajoute les sommets dans le graphe (avec le bon type)
		      for (int line=0; line < nlines; line++)
		      {
		    	  data = myReader.nextLine();
		    	  for (int col=0; col < ncols; col++)
		    	  {
		    		  graph.addVertex(groundTypes.get(String.valueOf(data.charAt(col))));
		    	  }
		      }
		      
		      //TODO: ajouter les arr�tes
		      for (int line = 0 ; line < nlines ; line++)
		      {
		    	  for (int col = 0 ; col < ncols ; col++)
		    	  {
		    		  int source = line *ncols + col;
		    		  int dest;
		    		  double weight;
						for (int i = -1; i <= 1; i++) {
							for (int j = -1; j <= 1; j++) {
								if ( (i != 0) || (j != 0) ) {
									if((line + i) >= 0 && (line + i) < nlines && (col + j) >= 0 && (col + j) < ncols) {
										dest = (line + i) * ncols + col + j;
										weight = ( (graph.vertexlist.get(dest).indivTime + graph.vertexlist.get(source).indivTime) / 2 ) * (Math.sqrt(Math.abs(i) + Math.abs(j)));
										graph.addEgde(source, dest, weight);
									} // if
								} // if
							} // for
						} // for		    	  
		    	  }	// for(col)
		      } // for(line)
		      
		      //On obtient les noeuds de d�part et d'arriv�
		      data = myReader.nextLine();
		      data = myReader.nextLine();
		      int startV = Integer.parseInt(data.split("=")[1].split(",")[0]) * ncols + Integer.parseInt(data.split("=")[1].split(",")[1]);
		      data = myReader.nextLine();
		      int endV = Integer.parseInt(data.split("=")[1].split(",")[0]) * ncols + Integer.parseInt(data.split("=")[1].split(",")[1]);

		      myReader.close();
		      
		      //A changer pour avoir un affichage plus ou moins grand
		      int pixelSize = 10;
		      Board board = new Board(graph, pixelSize, ncols, nlines, groundColor, startV, endV);
		      drawBoard(board, nlines, ncols, pixelSize);
		      board.repaint();
		      
		      try {
		    	    Thread.sleep(100);
		    	} catch(InterruptedException e) {
		    	    System.out.println("stop");
		    	}
		      
		    //TODO: laisser le choix entre Dijkstra et A*
		     LinkedList<Integer> path; 
		     if(args[1].equals("dijkstra")) { 
			      path = DijkstraAlgo.Dijkstra(graph, startV, endV, nlines*ncols, board); 
		     } else { 
		    	 path = AStarAlgo.AStar(graph, startV, endV, ncols, nlines * ncols, board); 
		     } 
		      
		      
		      //�criture du chemin dans un fichier de sortie
		      try {
			      File file = new File("out.txt");
			      if (!file.exists()) {
			    	  file.createNewFile();
			      } 
			      FileWriter fw = new FileWriter(file.getAbsoluteFile());
			      BufferedWriter bw = new BufferedWriter(fw);
			      
			      for (int i: path)
			      {
			    	  bw.write(String.valueOf(i));
			    	  bw.write('\n');
			      }
			      bw.close();	
		      
			} catch (IOException e) {
				e.printStackTrace();
			  } 
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	/**
	 * Initialise l'affichage.
	 * @param board  l'affichage
	 * @param nlines
	 * @param ncols
	 * @param pixelSize
	 */
	private static void drawBoard(Board board, int nlines, int ncols, int pixelSize)
	{
	    JFrame window = new JFrame("Plus court chemin");
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setBounds(0, 0, ncols*pixelSize+20, nlines*pixelSize+40);
	    window.getContentPane().add(board);
	    window.setVisible(true);
	}
}
