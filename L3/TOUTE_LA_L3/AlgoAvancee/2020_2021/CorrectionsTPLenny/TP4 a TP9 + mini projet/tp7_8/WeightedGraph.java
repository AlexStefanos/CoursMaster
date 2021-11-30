// Par Sylvain Lobry, pour le cours "IF05X040 Algorithmique avanc�e"
// de l'Universit� de Paris, 11/2020
package tp7_8;

import java.util.LinkedList;
import java.util.ArrayList;

/**
 * Une classe qui repr�sente un graphe pond�r�.
 * Un graphe pond�r� (weighted graph) ou un r�seau (network) est un graphe dans lequel
 * un nombre (un poids) est attribu� � chaque ar�te. 
 * De tels poids peuvent repr�senter par exemple des co�ts, des longueurs ou des capacit�s, 
 * selon le probl�me � r�soudre. De tels graphes surviennent dans de nombreux contextes,
 * par exemple dans les probl�mes du plus court chemin.
 * (Source : en.wikipedia.org - CC BY-SA 3.0)
 * 
 * @author Sylvain Lobry (commentaires : Leonard NAMOLARU)
 * 
 */
public class WeightedGraph {
	
	/** 
	 * Sous-classe qui repr�sente une arr�te.
	 * Une ar�te (edge) est une liaison entre deux sommets d'un graphe. 
	 * (Source : fr.wikipedia.org - CC BY-SA 3.0)
	 */
    static class Edge {
    	
    	/**
    	 * Le sommet de d�part de l'ar�te.
    	 */	
        int source;
        
    	/**
    	 * Le sommet de destination de l'ar�te. 
    	 */	
        int destination;
        
    	/**
    	 * Le nombre (poids) qui est associ� � l'ar�te.
    	 */	        
        double weight;
        
    	/**
    	 * Constructeur : cr�er une nouvelle instance de l'objet Edge
    	 * 
    	 * @param source        Le sommet de d�part de l'ar�te.
    	 * @param destination 	Le sommet de destination de l'ar�te. 
    	 * @param weight        Le nombre (poids) qui est associ� � l'ar�te.
    	 */
        public Edge(int source, int destination, double weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }
    
	/** 
	 * Sous-classe qui repr�sente un sommet.
	 * Un sommet, aussi appel� n�ud, est l'unit� fondamentale d'un graphe. 
	 * (Source : fr.wikipedia.org - CC BY-SA 3.0)
	 */
    static class Vertex {
    	
    	/**
    	 * Distance du n�ud courant au voisin.
    	 * Fait r�f�rence � la formule (tA + tB)/2,
    	 * o� tX est le temps n�cessaire pour parcourir horizontalement ou verticalement la case X.
    	 * Le poids d�une arr�te allant d�une case A � B (connexes) 
    	 * doit �tre une combinaison du temps pour parcourir une case d�finie dans le fichier texte.
    	 * (Source : Consignes.pdf)
    	 */
    	double indivTime;
    	
    	/**
    	 * Le co�t du chemin que construit Dijkstra.
    	 * Co�t du chemin que construit Dijkstra une fois arriv� au sommet en question.
    	 */
    	double timeFromSource;
    	
    	/**
    	 * Une distance estim�e au n�ud d�arriver.
    	 * Estimation du co�t r�el (si on avait d�roul� le Dijkstra jusqu�au bout)
    	 */  	
    	double heuristic;
    	
    	/**
    	 * Le sommet pr�c�dent (le n�ud parent).
    	 */  	
    	Vertex prev;
    	
    	/**
    	 * Liste des ar�tes voisins.
    	 * (Ar�tes dont l'une des extr�mit�s est ce sommet).
    	 */  	
    	LinkedList<Edge> adjacencylist;
    	
    	/**
    	 * Le num�ro du sommet.
    	 */
    	int num;
    	
    	/**
    	 * Constructeur : cr�er une nouvelle instance de l'objet Vertex
    	 * 
    	 * @param num	Le num�ro du sommet.
    	 */
    	public Vertex(int num) {
    		this.indivTime = Double.POSITIVE_INFINITY;
    		this.timeFromSource = Double.POSITIVE_INFINITY;
    		this.heuristic = -1;
    		this.prev = null;
    		this.adjacencylist = new LinkedList<Edge>();
    		this.num = num;
    	}
    	
    	/**
    	 * Une fonction qui ajoute une ar�te au d�but de la liste des ar�tes voisines
    	 * (ar�tes dont l'une des extr�mit�s est ce sommet).
    	 * 
    	 * @param e	Une instance de l'objet Edge (une ar�te).
    	 */
    	public void addNeighbor(Edge e) {
    		this.adjacencylist.addFirst(e);
    	}
    }

	/** 
	 * Sous-classe qui repr�sente un graphe.
	 * Un graphe est une structure compos�e d'objets dans laquelle certaines paires d'objets sont en relation.
	 * Les objets correspondent � des abstractions math�matiques et sont appel�s sommets, 
	 * et les relations entre sommets sont des ar�tes.
	 * (Source : fr.wikipedia.org - CC BY-SA 3.0)
	 */
    static class Graph {
    	/**
    	 * Liste des sommets du graphe.
    	 */  	
        ArrayList<Vertex> vertexlist;
        
    	/**
    	 * Le nombre total de sommets dans le graphe.
    	 */  	
        int num_v = 0;
        
    	/**
    	 * Constructeur : cr�er une nouvelle instance de l'objet Graph.
    	 * 
    	 */
        Graph() {
            vertexlist = new ArrayList<Vertex>();
        }
        
    	/**
    	 * Une fonction qui ajoute un nouveau sommet au graphe.
    	 * 
    	 * @param indivTime		Fait r�f�rence � la formule tA + tB/2.
    	 */
        public void addVertex(double indivTime)
        {
        	Vertex v = new Vertex(num_v);
        	v.indivTime = indivTime;
        	vertexlist.add(v);
        	num_v = num_v + 1;
        }
        
    	/**
    	 * Une fonction qui ajoute un nouvelle ar�te au graphe.
    	 * 
    	 * @param source        Le sommet de d�part de l'ar�te.
    	 * @param destination 	Le sommet de destination de l'ar�te. 
    	 * @param weight        Le nombre (poids) qui est associ� � l'ar�te.
    	 */
        public void addEgde(int source, int destination, double weight) {
            Edge edge = new Edge(source, destination, weight);
            vertexlist.get(source).addNeighbor(edge);
        }

    }
    
      public static void main(String[] args) {
            //int vertices = 6; // Le nombre total de sommets dans le graphe.
            Graph graph = new Graph(); // Appel au constructeur : cr�ation d'une nouvelle instance de l'objet Graph.
            graph.addVertex(10); // Ajout du sommet 0 au graphe.
            graph.addVertex(10); // Ajout du sommet 1 au graphe.
            graph.addVertex(10); // Ajout du sommet 2 au graphe.
            graph.addVertex(10); // Ajout du sommet 3 au graphe.
            graph.addVertex(10); // Ajout du sommet 4 au graphe.
            graph.addVertex(10); // Ajout du sommet 5 au graphe.
            graph.addEgde(0, 1, 4); // Ar�te entre les sommets 0 et 1. Le poids de l'ar�te est 4.
            graph.addEgde(0, 2, 3); // Ar�te entre les sommets 0 et 2. Le poids de l'ar�te est 3.
            graph.addEgde(1, 3, 2); // Ar�te entre les sommets 1 et 3. Le poids de l'ar�te est 2.
            graph.addEgde(1, 2, 5); // Ar�te entre les sommets 1 et 2. Le poids de l'ar�te est 5.
            graph.addEgde(2, 3, 7); // Ar�te entre les sommets 2 et 3. Le poids de l'ar�te est 7.
            graph.addEgde(3, 4, 2); // Ar�te entre les sommets 3 et 4. Le poids de l'ar�te est 2.
            graph.addEgde(4, 0, 4); // Ar�te entre les sommets 4 et 0. Le poids de l'ar�te est 4.
            graph.addEgde(4, 1, 4); // Ar�te entre les sommets 4 et 1. Le poids de l'ar�te est 4.
            graph.addEgde(4, 5, 6); // Ar�te entre les sommets 4 et 5. Le poids de l'ar�te est 6.
            //graph.printGraph();
        }
}