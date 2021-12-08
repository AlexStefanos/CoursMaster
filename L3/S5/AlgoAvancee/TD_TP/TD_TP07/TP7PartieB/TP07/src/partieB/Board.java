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

public class Board extends JComponent {
	private static final long serialVersionUID = 1L;
	WeightedGraph graph;
	HashMap<Integer, String> colors;
	LinkedList<Integer> path;
	int pixelSize, nb_col, nb_line, start, end, current;
	double max_distance;
	
	public Board(WeightedGraph graph, int pixelSize, int nb_col, int nb_line, HashMap<Integer, String> colors, int start, int end) {
		super();
		this.graph = graph;
		this.pixelSize = pixelSize;
		this.nb_col = nb_col;
		this.nb_line = nb_line;
		this.colors = colors;
		this.start = start;
		this.end = end;
		this.max_distance = nb_col * nb_line;
		this.current = -1;
		this.path = null;
		}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		int num_case, i, j, prev, i2, j2;
		float g_value;
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.cyan);
		g2.fill(new Rectangle2D.Double(0,0,this.nb_col*this.pixelSize, this.nb_line*this.pixelSize));
		num_case = 0;
		for (Vertex v : this.graph.vertexlist) {
			double type = v.indivTime;
			i = (num_case / this.nb_col);
			j = (num_case % this.nb_col);

			if (colors.get((int)type).equals("green"))
				g2.setPaint(Color.green);
			if (colors.get((int)type).equals("gray"))
				g2.setPaint(Color.gray);
			if (colors.get((int)type).equals("blue"))
				g2.setPaint(Color.blue);
			if (colors.get((int)type).equals("yellow"))
				g2.setPaint(Color.yellow);
			g2.fill(new Rectangle2D.Double(j*this.pixelSize, i*this.pixelSize, this.pixelSize, this.pixelSize));
			if (num_case == this.current) {
				g2.setPaint(Color.red);
				g2.draw(new Ellipse2D.Double(j*this.pixelSize+this.pixelSize/2, i*this.pixelSize+this.pixelSize/2, 6, 6));
			}
			if (num_case == this.start) {
				g2.setPaint(Color.white);
				g2.fill(new Ellipse2D.Double(j*this.pixelSize+this.pixelSize/2, i*this.pixelSize+this.pixelSize/2, 4, 4));
			}
			if (num_case == this.end) {
				g2.setPaint(Color.black);
				g2.fill(new Ellipse2D.Double(j*this.pixelSize+this.pixelSize/2, i*this.pixelSize+this.pixelSize/2, 4, 4));
			}
			num_case += 1;
		}
		num_case = 0;
		for (Vertex v : this.graph.vertexlist) {
			i = num_case / this.nb_col;
			j = num_case % this.nb_col;
			if (v.timeFromSource < Double.POSITIVE_INFINITY) {
				g_value = (float) (1 - v.timeFromSource / this.max_distance);
				if (g_value < 0)
					g_value = 0;
				g2.setPaint(new Color(g_value, g_value, g_value));
				g2.fill(new Ellipse2D.Double(j*this.pixelSize+this.pixelSize/2, i*this.pixelSize+this.pixelSize/2, 4, 4));
				Vertex previous = v.prev;
				if (previous != null) {
					i2 = previous.num / this.nb_col;
					j2 = previous.num % this.nb_col;
					g2.setPaint(Color.black);
					g2.draw(new Line2D.Double(j * this.pixelSize + this.pixelSize/2, i * this.pixelSize + this.pixelSize/2, j2 * this.pixelSize + this.pixelSize/2, i2 * this.pixelSize + this.pixelSize/2));
				}
			}	
			num_case += 1;
		}
		prev = -1;
		if (this.path != null) {
			g2.setStroke(new BasicStroke(3.0f));
			for (int cur : this.path) {
				if (prev != -1) {
					g2.setPaint(Color.red);
					i = (prev / this.nb_col);
					j = (prev % this.nb_col);
					i2 = (cur / this.nb_col);
					j2 = (cur % this.nb_col);
					g2.draw(new Line2D.Double(j * this.pixelSize + this.pixelSize/2, i * this.pixelSize + this.pixelSize/2, j2 * this.pixelSize + this.pixelSize/2, i2 * this.pixelSize + this.pixelSize/2));
				}
				prev = cur;
			}
		}
	}

	public void update(WeightedGraph graph, int current) {
		this.graph = graph;
		this.current = current;
		repaint();
	}

	public void addPath(WeightedGraph graph, LinkedList<Integer> path) {
		this.graph = graph;
		this.path = path;
		this.current = -1;
		repaint();
	}
}
