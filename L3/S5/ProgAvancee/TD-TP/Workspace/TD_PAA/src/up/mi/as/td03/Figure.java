package up.mi.as.td03;

import up.mi.as.td02.Vecteur;

public abstract class Figure {
	public abstract double perimetre();
	public abstract double surface();
	public abstract Figure translation(Vecteur v);
}
