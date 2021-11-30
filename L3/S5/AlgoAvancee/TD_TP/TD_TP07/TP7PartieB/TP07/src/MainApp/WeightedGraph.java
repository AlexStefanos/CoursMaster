// Par Sylvain Lobry, pour le cours "IF05X040 Algorithmique avanc�e"
// de l'Universit� de Paris, 11/2020

package MainApp;

import java.util.LinkedList;
import java.util.ArrayList;

public class WeightedGraph {
      public static void main(String[] args) {
            int vertices = 6;
            Graph graph = new Graph();
            graph.addVertex(10);
            graph.addVertex(10);
            graph.addVertex(10);
            graph.addVertex(10);
            graph.addVertex(10);
            graph.addVertex(10);
            graph.addEdge(0, 1, 4);
            graph.addEdge(0, 2, 3);
            graph.addEdge(1, 3, 2);
            graph.addEdge(1, 2, 5);
            graph.addEdge(2, 3, 7);
            graph.addEdge(3, 4, 2);
            graph.addEdge(4, 0, 4);
            graph.addEdge(4, 1, 4);
            graph.addEdge(4, 5, 6);
            //graph.printGraph();
        }
}
