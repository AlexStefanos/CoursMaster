package up.mi.as.td03;

import java.util.ArrayList;

public class AdditionNAire extends Operateur {
	private ArrayList<Operateur> valeurs;
	
	public AdditionNAire() {
		super(0);
		valeurs = new ArrayList<Operateur>();
	}
	
	@Override
	public double evaluer() {
		double result = 0;
		for(int i = 0; i < valeurs.size(); i++) {
			result += valeurs.get(i).evaluer();
		}
		return(result);
	}
}
