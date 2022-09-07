package up.mi.as.td02;

public class Attaque extends Personnage {

	public Attaque(String nom, int pv, int forceAttaque1, int forceAttaque2, int nbAttaques1, int nbAttaques2) {
		super(nom, pv, forceAttaque1, forceAttaque2, nbAttaques1, nbAttaques2);
	}
	
	public void menuAttaque() {
		if(nom == "Aragorn") {
			
		}
		else if(nom == "Gimli") {
			
		}
		else if(nom == "Legolas") {
			
		}
		else
			System.out.println("Votre personnage n'est pas valide.");
	}

}
