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

public class App {
	public static void main(String[] args) {
		try {
		      File object = new File(args[0]);
		      Scanner myReader = new Scanner(object);
		      String data, name, color;
		      WeightedGraph graph = new WeightedGraph();
		      HashMap<String, Integer> groundTypes = new HashMap<String, Integer>();
		      HashMap<Integer, String> groundColor = new HashMap<Integer, String>();
		      int nb_line, nb_col, time, source, dest, start_vertex, end_vertex, size_pixel;
		      double weight;

		      data = "";
		      for(int i = 0 ; i < 3 ; i++)
		    	  data = myReader.nextLine();
		      nb_line = Integer.parseInt(data.split("=")[1]);
		      data = myReader.nextLine();
		      nb_col = Integer.parseInt(data.split("=")[1]);
		      data = myReader.nextLine();
		      data = myReader.nextLine();
		      while(!data.equals("==Graph==")) {
		    	  name = data.split("=")[0];
		    	  time = Integer.parseInt(data.split("=")[1]);
		    	  data = myReader.nextLine();
		    	  color = data;
		    	  groundTypes.put(name, time);
		    	  groundColor.put(time, color);
		    	  data = myReader.nextLine();
		      }
		      for(int line = 0; line < nb_line; line++) {
		    	  data = myReader.nextLine();
		    	  for(int col = 0; col < nb_col; col++)
		    		  graph.addVertex(groundTypes.get(String.valueOf(data.charAt(col))));
		      }
		      for(int line = 0 ; line < nb_line ; line++) {
		    	  for (int col = 0 ; col < nb_col ; col++) {
		    		  source = line * nb_col + col;
						for (int i = -1; i <= 1; i++) {
							for (int j = -1; j <= 1; j++) {
								if ((i != 0) || (j != 0)) {
									if((line + i) >= 0 && (line + i) < nb_line && (col + j) >= 0 && (col + j) < nb_col) {
										dest = (line + i) * nb_col + col + j;
										weight = ((graph.vertexlist.get(dest).indivTime + graph.vertexlist.get(source).indivTime) / 2 ) * (Math.sqrt(Math.abs(i) + Math.abs(j)));
										graph.addEgde(source, dest, weight);
									}
								}
							}
						}
		    	  }
		      }
		      data = myReader.nextLine();
		      data = myReader.nextLine();
		      start_vertex = Integer.parseInt(data.split("=")[1].split(",")[0]) * nb_col + Integer.parseInt(data.split("=")[1].split(",")[1]);
		      data = myReader.nextLine();
		      end_vertex = Integer.parseInt(data.split("=")[1].split(",")[0]) * nb_col + Integer.parseInt(data.split("=")[1].split(",")[1]);
		      myReader.close();
		      size_pixel = 10;
		      Board board = new Board(graph, size_pixel, nb_col, nb_line, groundColor, start_vertex, end_vertex);
		      drawBoard(board, nb_line, nb_col, size_pixel);
		      board.repaint();
		      try {
		    	    Thread.sleep(100);
		    	}catch(InterruptedException e) {
		    	    System.out.println("stop");
		    	}
		     LinkedList<Integer> path; 
		     if(args[1].equals("dijkstra"))
			      path = DijkstraAlgo.Dijkstra(graph, start_vertex, end_vertex, nb_line*nb_col, board);  
		     else 
		    	 path = AStarAlgo.AStar(graph, start_vertex, end_vertex, nb_col, nb_line * nb_col, board); 
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

	private static void drawBoard(Board board, int nb_line, int nb_col, int size_pixel) {
	    JFrame window = new JFrame("Plus court chemin");
	    
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setBounds(0, 0, nb_col*size_pixel+20, nb_line*size_pixel+40);
	    window.getContentPane().add(board);
	    window.setVisible(true);
	}
}
