package TD06;

public class Polygone {
	//Attribut
	private Point[] sommets;
	
	//Constructeurs
	public Polygone(Point[] sommets) {
		this.sommets = sommets;
	}
	
	public Polygone(int nbSommets) {
		sommets = new Point[nbSommets];
	}
	
	//Methodes
	//Ajoute un Sommet dans le tableau sommets
	public void setUnSommet(Point p, int pos) {
		sommets[pos] = p;
	}
	
	//Mettre � jour tous les sommets
	public void setSommets(Point[] sommets) {
		this.sommets = sommets;
	}
	
	//Retourne le nom de la figure (Polygone dans le cas pr�sent)
	protected String nomFigure() {
		return "Polygone";
	}
	
	//Retourne le nombre de sommets
	public int nombredeSommets() {
		int total = 0;
		for(int i = 0; i<sommets.length; i++) {
			total++;
		}
		return total;
	}
	
	//Retourne une chaine contenant les coordonn�es des sommets
	//Elle se pr�sente sous la forme : "Polygone : [(0.0,0.0) (2.0,0.0) (2.0,2.0) (0.0,2.0)]"
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i<=sommets.length; i++) {
			sb.append(sommets[i].toString()+" ");
		}
		return (nomFigure() + " : [" + sb.toString() + "]");
	}
	
	//Affiche les coordonn�es des sommets
	public void affiche() {
		System.out.println(toString());
	}
	
	//Calcul le p�rim�tre (somme des cotes) du polygone
	public float perimetre() {
		float peri = sommets[0].distance(sommets[sommets.length-1]);
		for (int i=0; i<sommets.length-1; i++) {
			peri += sommets[i].distance(sommets[i+1]);
		}
		return peri;
	}
	
	
}
