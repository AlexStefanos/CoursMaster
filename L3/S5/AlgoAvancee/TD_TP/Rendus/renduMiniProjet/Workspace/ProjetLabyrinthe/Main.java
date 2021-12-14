import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<Character> resultats = new ArrayList<Character>();
		int instances;
		instances = Integer.parseInt(sc.nextLine());
		char[][] map = null;	  
		int n = 0, m = 0;
		
		for(int i = 0 ; i < instances; i++) {
			StringTokenizer tokenizer = new StringTokenizer(sc.nextLine(), " "); 
			n = ((tokenizer.hasMoreTokens()) ?  Integer.parseInt(tokenizer.nextToken()) : 0);
			m = ((tokenizer.hasMoreTokens()) ?  Integer.parseInt(tokenizer.nextToken()) : 0);
			map = new char[n][m];
			    
			for(int j = 0; j < n; j++) {
				String entree = sc.nextLine();
				for(int k = 0 ; k < m ; k++)
					map[j][k] = entree.charAt(k);
			}
			    
			Graph graph = new Graph();
			int startV = 0, endV = 0;
			for(int j = 0; j < n; j++) {       
				for(int k = 0; k < m; k++) {
					graph.addVertex(map[j][k], 1, j, k);
					if(map[j][k] == 'D')
						startV = j * m + k;
					if(map[j][k] == 'S')
						endV = j * m + k;
				}
			}
			for(int line = 0 ; line < n ; line++) {
				for(int col = 0 ; col < m ; col++) {
					int source = line * m + col;
					int dest;
					double weight;
					for(int j = -1; j <= 1; j++) {
						for(int k = -1; k <= 1; k++) {
							if((j != 0) || (k != 0)) {
								if((line + j) >= 0 && (line + j) < n && (col + k) >= 0 && (col + k) < m) {
									dest = (line + j) * m + col + k;
									weight = 1;
									if(Math.abs(source - dest) == 1 || Math.abs(source - dest) == m)
										graph.addEgde(source, dest, weight);
								}
							}
						}
					}
				}
			}
			resultats.add(Labyrinthe.run_instance(graph, startV, endV, n, m));
		}
		for(Character character : resultats)
			System.out.println( character );
			  
		sc.close();
	}
}
