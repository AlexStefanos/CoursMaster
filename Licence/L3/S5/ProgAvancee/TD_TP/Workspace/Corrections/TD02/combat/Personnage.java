package up.mi.jgm.td02.combat;

public class Personnage {

	/***
	 * Nom du personnage
	 */
	private String nom;
	
	/***
	 * Point(s) de vie du personnage
	 */
	private int pointsDeVie;
	
	/***
	 * Première attaque du personnage 
	 */
	private Attaque attaque1;
	
	/***
	 * Seconde attaque du personnage
	 */
	private Attaque attaque2;
	
	/***
	 * Constructeur de la classe Personnage permettant de créer un personnage à partir 
	 * des caractéristiques suivantes :
	 * @param n
	 * @param pdv
	 * @param att1
	 * @param att2
	 */
	public Personnage(String n, int pdv, Attaque att1, Attaque att2) {
		this.nom = n;
		this.pointsDeVie = pdv;
		this.setAttaque1(att1);
		this.setAttaque2(att2);
	}
	
	/**
	 * Cree le personnage d'Aragorn
	 * @return
	 */
	public static Personnage creeAragorn() {
		Attaque att1 = new Attaque("épée",8,10);
		Attaque att2 = new Attaque("arc",5,8);
		return new Personnage("Aragorn",100,att1,att2);
	}
	
	/**
	 * Cree le personnage de Gimli
	 * @return
	 */
	public static Personnage creeGimli() {
		Attaque att1 = new Attaque("épée",8,8);
		Attaque att2 = new Attaque("hache",9,12);
		return new Personnage("Gimli",80,att1,att2);
	}
	
	/**
	 * Cree le personnage de Legolas
	 * @return
	 */
	public static Personnage creeLegolas() {
		Attaque att1 = new Attaque("épée",6,8);
		Attaque att2 = new Attaque("arc",7,15);
		return new Personnage("Legolas",120,att1,att2);
	}
	
	/***
	 * Retire des points vie au personnage 
	 * @param perte : nombre de point(s) de vie à retirer
	 */
	public void perdPDV(int perte) {
		this.pointsDeVie -= perte;
	}
	
	/***
	 * Vérifie si le personnage est mort (i.e., n'a plus de points de vie)
	 * @return true si le nombre de points de vie est inférieur ou égal à 0, false sinon
	 */
	public boolean estMort() {
		if(this.pointsDeVie <= 0)
			return true;
		else
			return false;
	}

	public Attaque getAttaque1() {
		return attaque1;
	}

	public void setAttaque1(Attaque attaque1) {
		this.attaque1 = attaque1;
	}

	public Attaque getAttaque2() {
		return attaque2;
	}

	public void setAttaque2(Attaque attaque2) {
		this.attaque2 = attaque2;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	@Override
	public String toString() {
		String res = this.nom + " (" + this.pointsDeVie + " pdv) :";
		res += this.attaque1.toString() + " | " + this.attaque2.toString();
		return res;
	}
}
