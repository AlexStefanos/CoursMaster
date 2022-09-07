package exams;

import java.util.HashMap;

public class Polynome implements IPolynome {
	private HashMap<Integer, Integer> polynome;
	
	public Polynome(HashMap<Integer, Integer> polynome) throws IllegalArgumentException {
		for(Integer degre : polynome.keySet()) {
			if(degre < 0)
				throw new IllegalArgumentException("Un des degrés passés en paramètre est négatif");
		}
		this.polynome = polynome;
	}
	
	@Override
	public int coefficient(int degre) {
		return(polynome.containsKey(degre) && polynome.get(degre) != null) ? polynome.get(degre) : 0;
	}
}
