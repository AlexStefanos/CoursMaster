package MainApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.HashSet;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class App {
	public static void main(String[] args) {
		try {
		      File myObj = new File("graph.txt");
		      Scanner myReader = new Scanner(myObj);
		      String data = "";
		      for (int i=0; i < 3; i++)
		    	  data = myReader.nextLine();
		      int nlines = Integer.parseInt(data.split("=")[1]);
		      data = myReader.nextLine();
		      int ncols = Integer.parseInt(data.split("=")[1]);
		      Graph graph = new Graph();
		      HashMap<String, Integer> groundTypes = new HashMap<String, Integer>();
		      HashMap<Integer, String> groundColor = new HashMap<Integer, String>();
		      data = myReader.nextLine();
		      data = myReader.nextLine();
		      while (!data.equals("==Graph=="))
		      {
		    	  String name = data.split("=")[0];
		    	  int time = Integer.parseInt(data.split("=")[1]);
		    	  data = myReader.nextLine();
		    	  String color = data;
		    	  groundTypes.put(name, time);
		    	  groundColor.put(time, color);
		    	  data = myReader.nextLine();
		      }
		      for (int line=0; line < nlines; line++)
		      {
		    	  data = myReader.nextLine();
		    	  for (int col=0; col < ncols; col++)
		    	  {
		    		  graph.addVertex(groundTypes.get(String.valueOf(data.charAt(col))));
		    	  }
		      }
		      for (int line=0; line < nlines; line++)
		      {
		    	  for (int col=0; col < ncols; col++)
		    	  {
		    		  int source = line*ncols+col;
		    		  int dest;
		    		  double weight;
		    		  for(int i = -1; i <= 1; i++) {
		    			  for(int j = -1; j <= 1; j++) {
		    				  if((i != 0) || (j != 0)) {
		    					  if((line + 1) >= 0 && (line + i) < nlines && (col + j) >= 0 && (col + j) < ncols) {
		    						  dest = (line + i) * ncols + col + j;
			    						  weight = ((graph.vertexlist.get(dest).indivTime + graph.vertexlist.get(source).indivTime) / 2) * (Math.sqrt(Math.abs(i) + Math.abs(j)));
		    						  graph.addEdge(source, dest, weight);
		    					  }
		    				  }
		    			  }  
		    		  }
		    	  }
		      }
		      data = myReader.nextLine();
		      data = myReader.nextLine();
		      int startV = Integer.parseInt(data.split("=")[1].split(",")[0]) * ncols + Integer.parseInt(data.split("=")[1].split(",")[1]);
		      data = myReader.nextLine();
		      int endV = Integer.parseInt(data.split("=")[1].split(",")[0]) * ncols + Integer.parseInt(data.split("=")[1].split(",")[1]);
		      myReader.close();
		      int pixelSize = 10;
		      Board board = new Board(graph, pixelSize, ncols, nlines, groundColor, startV, endV);
		      drawBoard(board, nlines, ncols, pixelSize);
		      board.repaint();
		      try {
		    	    Thread.sleep(100);
		    	} catch(InterruptedException e) {
		    	    System.out.println("stop");
		    	}
		      LinkedList<Integer> path;
		      if(args[1].equals("dijsktra"))
		    	  path = DijkstraAlgo.Dijkstra(graph, startV, endV, nlines * ncols, board);
		      else
		    	  path = AStarAlgo.AStar(graph, startV, endV, ncols, nlines * ncols, board);
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
	
	private static void drawBoard(Board board, int nlines, int ncols, int pixelSize) {
		JFrame window = new JFrame("Plus court chemin");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(0, 0, ncols * pixelSize+20, nlines*pixelSize+40);
		window.getContentPane().add(board);
		window.setVisible(true);
	}
}