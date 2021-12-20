package partieB;

import java.util.HashMap;
import java.util.LinkedList;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;

public class Tableau extends JComponent {
	private static final long serialVersionUID = 1L;
	Graphe graphe;
	HashMap<Integer, String> couleurs;
	LinkedList<Integer> chemin;
	int taillePixel, nbColonnes, nbLignes, debut, fin, actuel;
	double max_distance;
	
	public Tableau(Graphe graphe, int taillePixel, int nbColonnes, int nbLignes, HashMap<Integer, String> couleurs, int debut, int fin) {
		super();
		this.graphe = graphe;
		this.taillePixel = taillePixel;
		this.nbColonnes = nbColonnes;
		this.nbLignes = nbLignes;
		this.couleurs = couleurs;
		this.debut = debut;
		this.fin = fin;
		this.max_distance = nbColonnes * nbLignes;
		this.actuel = -1;
		this.chemin = null;
		}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		int num, i, j, precedent, i2, j2;
		float grapheValeur;
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.cyan);
		g2.fill(new Rectangle2D.Double(0,0,this.nbColonnes*this.taillePixel, this.nbLignes*this.taillePixel));
		num = 0;
		for (Sommet v : this.graphe.listeSommets) {
			double type = v.tempsIndividuel;
			i = (num / this.nbColonnes);
			j = (num % this.nbColonnes);

			if (couleurs.get((int)type).equals("green"))
				g2.setPaint(Color.green);
			if (couleurs.get((int)type).equals("gray"))
				g2.setPaint(Color.gray);
			if (couleurs.get((int)type).equals("blue"))
				g2.setPaint(Color.blue);
			if (couleurs.get((int)type).equals("yellow"))
				g2.setPaint(Color.yellow);
			g2.fill(new Rectangle2D.Double(j*this.taillePixel, i*this.taillePixel, this.taillePixel, this.taillePixel));
			if (num == this.actuel) {
				g2.setPaint(Color.red);
				g2.draw(new Ellipse2D.Double(j*this.taillePixel+this.taillePixel/2, i*this.taillePixel+this.taillePixel/2, 6, 6));
			}
			if (num == this.debut) {
				g2.setPaint(Color.white);
				g2.fill(new Ellipse2D.Double(j*this.taillePixel+this.taillePixel/2, i*this.taillePixel+this.taillePixel/2, 4, 4));
			}
			if (num == this.fin) {
				g2.setPaint(Color.black);
				g2.fill(new Ellipse2D.Double(j*this.taillePixel+this.taillePixel/2, i*this.taillePixel+this.taillePixel/2, 4, 4));
			}
			num += 1;
		}
		num = 0;
		for (Sommet v : this.graphe.listeSommets) {
			i = num / this.nbColonnes;
			j = num % this.nbColonnes;
			if (v.tempsSource < Double.POSITIVE_INFINITY) {
				grapheValeur = (float) (1 - v.tempsSource / this.max_distance);
				if (grapheValeur < 0)
					grapheValeur = 0;
				g2.setPaint(new Color(grapheValeur, grapheValeur, grapheValeur));
				g2.fill(new Ellipse2D.Double(j*this.taillePixel+this.taillePixel/2, i*this.taillePixel+this.taillePixel/2, 4, 4));
				Sommet sommetPrecedent = v.precedent;
				if (sommetPrecedent != null) {
					i2 = sommetPrecedent.num / this.nbColonnes;
					j2 = sommetPrecedent.num % this.nbColonnes;
					g2.setPaint(Color.black);
					g2.draw(new Line2D.Double(j * this.taillePixel + this.taillePixel/2, i * this.taillePixel + this.taillePixel/2, j2 * this.taillePixel + this.taillePixel/2, i2 * this.taillePixel + this.taillePixel/2));
				}
			}	
			num += 1;
		}
		precedent = -1;
		if (this.chemin != null) {
			g2.setStroke(new BasicStroke(3.0f));
			for (int cur : this.chemin) {
				if (precedent != -1) {
					g2.setPaint(Color.red);
					i = (precedent / this.nbColonnes);
					j = (precedent % this.nbColonnes);
					i2 = (cur / this.nbColonnes);
					j2 = (cur % this.nbColonnes);
					g2.draw(new Line2D.Double(j * this.taillePixel + this.taillePixel/2, i * this.taillePixel + this.taillePixel/2, j2 * this.taillePixel + this.taillePixel/2, i2 * this.taillePixel + this.taillePixel/2));
				}
				precedent = cur;
			}
		}
	}

	public void mAJ(Graphe graphe, int actuel) {
		this.graphe = graphe;
		this.actuel = actuel;
		repaint();
	}

	public void ajouterChemin(Graphe graphe, LinkedList<Integer> chemin) {
		this.graphe = graphe;
		this.chemin = chemin;
		this.actuel = -1;
		repaint();
	}
}
