package up.mi.as.td02;
import java.util.concurrent.ThreadLocalRandom;

public class Ordinateur extends Personnage {
	
	public Ordinateur(String nom, int pv, int forceMelee, int forceDistance, int nbAttaquesMelee, int nbAttaquesDistance) {
		super(nom, pv, forceMelee, forceDistance, nbAttaquesMelee, nbAttaquesDistance);
	}

	public int choix() {
		return(ThreadLocalRandom.current().nextInt(1, 4));
	}
}
