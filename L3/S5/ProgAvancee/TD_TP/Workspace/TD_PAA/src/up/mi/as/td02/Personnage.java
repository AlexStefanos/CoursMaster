package up.mi.as.td02;

public class Personnage {
	protected String nom;
	protected int pv, forceAttaque1, forceAttaque2, nbAttaques1, nbAttaques2;
	
	public Personnage(String nom, int pv, int forceAttaque1, int forceAttaque2, int nbAttaques1, int nbAttaques2) {
		this.nom = nom;
		this.pv = pv;
		this.forceAttaque1 = forceAttaque1;
		this.forceAttaque2 = forceAttaque2;
		this.nbAttaques1= nbAttaques1;
		this.nbAttaques2 = nbAttaques2;
	}
	
	public int attaque1() {
		if (nbAttaques1> 0) {
			nbAttaques1 -= 1;
			return(forceAttaque1);
		}
		else {
			System.out.println("Vous n'avez plus d'attaques au corps à corps.");
			return(0);
		}
	}
	
	public int attaque2() {
		if (nbAttaques2> 0) { 
			nbAttaques2-= 1;
			return(forceAttaque2);
		}
		else {
			System.out.println("Vous n'avez plus d'attaques à distance.");
			return(0);
		}
	}
	
	public void blessures(int attaque) {
		System.out.println("Vous avez " + pv + "points de vie avant l'attaque.");
		pv -= attaque;
		System.out.println("Il vous reste" + pv + "points de vie après.");
	}
	
	public boolean mort() {
		if(pv <= 0) {
			System.out.println("Vous êtes mort !");
			return(true);
		}
		else {
			System.out.println("Vous êtes toujours vivant ! Vous pouvez encore gagner ");
			return(false);
		}
	}
}