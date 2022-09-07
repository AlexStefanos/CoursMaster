package partieB;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		try {
		      File objet = new File(args[0]);
		      Scanner scanner = new Scanner(objet);
		      String donnee, nom, couleur;
		      Graphe graphe = new Graphe();
		      HashMap<String, Integer> dicoTypes = new HashMap<String, Integer>();
		      HashMap<Integer, String> dicoCouleurs = new HashMap<Integer, String>();
		      int nbLignes, nbColonnes, temps, source, destination, sommetDebut, sommetFin, taillePixel;
		      double poids;

		      donnee = "";
		      for(int i = 0 ; i < 3 ; i++)
		    	  donnee = scanner.nextLine();
		      nbLignes = Integer.parseInt(donnee.split("=")[1]);
		      donnee = scanner.nextLine();
		      nbColonnes = Integer.parseInt(donnee.split("=")[1]);
		      donnee = scanner.nextLine();
		      donnee = scanner.nextLine();
		      while(!donnee.equals("==graphe==")) {
		    	  nom = donnee.split("=")[0];
		    	  temps = Integer.parseInt(donnee.split("=")[1]);
		    	  donnee = scanner.nextLine();
		    	  couleur = donnee;
		    	  dicoTypes.put(nom, temps);
		    	  dicoCouleurs.put(temps, couleur);
		    	  donnee = scanner.nextLine();
		      }
		      for(int line = 0; line < nbLignes; line++) {
		    	  donnee = scanner.nextLine();
		    	  for(int col = 0; col < nbColonnes; col++)
		    		  graphe.ajouterSommet(dicoTypes.get(String.valueOf(donnee.charAt(col))));
		      }
		      for(int line = 0 ; line < nbLignes ; line++) {
		    	  for (int col = 0 ; col < nbColonnes ; col++) {
		    		  source = line * nbColonnes + col;
						for (int i = -1; i <= 1; i++) {
							for (int j = -1; j <= 1; j++) {
								if ((i != 0) || (j != 0)) {
									if((line + i) >= 0 && (line + i) < nbLignes && (col + j) >= 0 && (col + j) < nbColonnes) {
										destination = (line + i) * nbColonnes + col + j;
										poids = ((graphe.listeSommets.get(destination).tempsIndividuel + graphe.listeSommets.get(source).tempsIndividuel) / 2 ) * (Math.sqrt(Math.abs(i) + Math.abs(j)));
										graphe.ajouterArrete(source, destination, poids);
									}
								}
							}
						}
		    	  }
		      }
		      donnee = scanner.nextLine();
		      donnee = scanner.nextLine();
		      sommetDebut = Integer.parseInt(donnee.split("=")[1].split(",")[0]) * nbColonnes + Integer.parseInt(donnee.split("=")[1].split(",")[1]);
		      donnee = scanner.nextLine();
		      sommetFin = Integer.parseInt(donnee.split("=")[1].split(",")[0]) * nbColonnes + Integer.parseInt(donnee.split("=")[1].split(",")[1]);
		      scanner.close();
		      taillePixel = 10;
		      Tableau tableau = new Tableau(graphe, taillePixel, nbColonnes, nbLignes, dicoCouleurs, sommetDebut, sommetFin);
		      dessinerTableau(tableau, nbLignes, nbColonnes, taillePixel);
		      tableau.repaint();
		      try {
		    	    Thread.sleep(100);
		    	}catch(InterruptedException e) {
		    	    System.out.println("stop");
		    	}
		     LinkedList<Integer> path; 
		     if(args[1].equals("dijkstra"))
			      path = DijkstraAlgo.Dijkstra(graphe, sommetDebut, sommetFin, nbLignes*nbColonnes, tableau);  
		     else 
		    	 path = AStarAlgo.AStar(graphe, sommetDebut, sommetFin, nbColonnes, nbLignes * nbColonnes, tableau); 
		     try {
			      File file = new File("out.txt");
			      if (!file.exists())
			    	  file.createNewFile();
			      FileWriter fw = new FileWriter(file.getAbsoluteFile());
			      BufferedWriter bw = new BufferedWriter(fw);
			      for(int i : path) {
			    	  bw.write(String.valueOf(i));
			    	  bw.write('\n');
			      }
			      bw.close();	      
			}catch (IOException e) {
				e.printStackTrace();
			} 
		    }catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}

	private static void dessinerTableau(Tableau tableau, int nbLignes, int nbColonnes, int taillePixel) {
	    JFrame window = new JFrame("Plus court chemin");
	    
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setBounds(0, 0, nbColonnes*taillePixel+20, nbLignes*taillePixel+40);
	    window.getContentPane().add(tableau);
	    window.setVisible(true);
	}
}
