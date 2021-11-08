// Par Sylvain Lobry, pour le cours "IF05X040 Algorithmique avancée"
// de l'Université de Paris, 11/2020
package projetLabyrinthe;

import java.util.LinkedList;
import java.util.ArrayList;

/**
 * Une classe qui représente un graphe pondéré.
 * Un graphe pondéré (weighted graph) ou un réseau (network) est un graphe dans lequel
 * un nombre (un poids) est attribué à chaque arête. 
 * De tels poids peuvent représenter par exemple des coûts, des longueurs ou des capacités, 
 * selon le problème à résoudre. De tels graphes surviennent dans de nombreux contextes,
 * par exemple dans les problèmes du plus court chemin.
 * (Source : en.wikipedia.org - CC BY-SA 3.0)
 * 
 * @author Sylvain Lobry / TP7Partie B (commentaires : Leonard NAMOLARU)
 * 
 */
public class WeightedGraph {
	
	/** 
	 * Sous-classe qui représente une arrête.
	 * Une arête (edge) est une liaison entre deux sommets d'un graphe. 
	 * (Source : fr.wikipedia.org - CC BY-SA 3.0)
	 */
    static class Edge {
    	
    	/**
    	 * Le sommet de départ de l'arête.
    	 */	
        int source;
        
    	/**
    	 * Le sommet de destination de l'arête. 
    	 */	
        int destination;
        
    	/**
    	 * Le nombre (poids) qui est associé à l'arête.
    	 */	        
        double weight;
        
    	/**
    	 * Constructeur : créer une nouvelle instance de l'objet Edge
    	 * 
    	 * @param source        Le sommet de départ de l'arête.
    	 * @param destination 	Le sommet de destination de l'arête. 
    	 * @param weight        Le nombre (poids) qui est associé à l'arête.
    	 */
        public Edge(int source, int destination, double weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }
    
	/** 
	 * Sous-classe qui représente un sommet.
	 * Un sommet, aussi appelé nœud, est l'unité fondamentale d'un graphe. 
	 * (Source : fr.wikipedia.org - CC BY-SA 3.0)
	 */
    static class Vertex {
    	
    	/**
    	 * Distance du nœud courant au voisin.
    	 * Fait référence à la formule (tA + tB)/2,
    	 * où tX est le temps nécessaire pour parcourir horizontalement ou verticalement la case X.
    	 * Le poids d’une arrête allant d’une case A à B (connexes) 
    	 * doit être une combinaison du temps pour parcourir une case définie dans le fichier texte.
    	 * (Source : Consignes.pdf)
    	 */
    	double indivTime;
    	
    	/**
    	 * Le coût du chemin que construit Dijkstra.
    	 * Coût du chemin que construit Dijkstra une fois arrivé au sommet en question.
    	 */
    	double timeFromSource;
    	
    	/**
    	 * Une distance estimée au nœud d’arriver.
    	 * Estimation du coût réel (si on avait déroulé le Dijkstra jusqu’au bout)
    	 */  	
    	double heuristic;
    	
    	/**
    	 * Le sommet précédent (le nœud parent).
    	 */  	
    	Vertex prev;
    	
    	/**
    	 * Liste des arêtes voisins.
    	 * (Arêtes dont l'une des extrémités est ce sommet).
    	 */  	
    	LinkedList<Edge> adjacencylist;
    	
    	/**
    	 * Le numéro du sommet.
    	 */
    	int num;
    	
    	/**
    	 * Chaque position est représentée par un symbole ayant la signification suivante :
    	 *  '.' = libre ; '#' = mur ; 'F' = feu ; 'D' = début ; 'S' = sortie.
    	 */
    	char etiquette;
    	
    	/**
    	 * Coordonnée i de la position dans la pièce que représente le sommet.
    	 */
    	int i;
    	
    	/**
    	 * Coordonnée j de la position dans la pièce que représente le sommet.
    	 */
    	int j;
    	
    	/**
    	 * Constructeur : créer une nouvelle instance de l'objet Vertex
    	 * 
    	 * @param num	Le numéro du sommet.
         * @param etiquette	Chaque position est représentée par un symbole.
    	 * @param i Coordonnée i de la position dans la pièce que représente le sommet.
    	 * @param j Coordonnée j de la position dans la pièce que représente le sommet.
    	 */
    	public Vertex(int num, char etiquette, int i, int j) {
    		this.indivTime = Double.POSITIVE_INFINITY;
    		this.timeFromSource = Double.POSITIVE_INFINITY;
    		this.heuristic = -1;
    		this.prev = null;
    		this.adjacencylist = new LinkedList<Edge>();
    		this.num = num;
    		this.etiquette = etiquette;
    		this.i = i;
    		this.j = j;
    	}
    	
    	/**
    	 * Une fonction qui ajoute une arête au début de la liste des arêtes voisines
    	 * (arêtes dont l'une des extrémités est ce sommet).
    	 * 
    	 * @param e	Une instance de l'objet Edge (une arête).
    	 */
    	public void addNeighbor(Edge e) {
    		this.adjacencylist.addFirst(e);
    	}
    }

	/** 
	 * Sous-classe qui représente un graphe.
	 * Un graphe est une structure composée d'objets dans laquelle certaines paires d'objets sont en relation.
	 * Les objets correspondent à des abstractions mathématiques et sont appelés sommets, 
	 * et les relations entre sommets sont des arêtes.
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
    	 * Constructeur : créer une nouvelle instance de l'objet Graph.
    	 * 
    	 */
        Graph() {
            vertexlist = new ArrayList<Vertex>();
        }
        
    	/**
    	 * Une fonction qui ajoute un nouveau sommet au graphe.
    	 * 
         * @param etiquette	    Chaque position est représentée par un symbole.
    	 * @param indivTime		Temps pour parcourir une case.
    	 * @param i Coordonnée i de la position dans la pièce que représente le sommet.
    	 * @param j Coordonnée j de la position dans la pièce que représente le sommet.
    	 */
        public void addVertex(char etiquette, double indivTime, int i, int j)
        {
        	Vertex v = new Vertex(num_v, etiquette, i, j);
        	v.indivTime = indivTime;
        	vertexlist.add(v);
        	num_v = num_v + 1;
        }
        
    	/**
    	 * Une fonction qui ajoute un nouvelle arête au graphe.
    	 * 
    	 * @param source        Le sommet de départ de l'arête.
    	 * @param destination 	Le sommet de destination de l'arête. 
    	 * @param weight        Le nombre (poids) qui est associé à l'arête.
    	 */
        public void addEgde(int source, int destination, double weight) {
            Edge edge = new Edge(source, destination, weight);
            vertexlist.get(source).addNeighbor(edge);
        }
    }
}