package up.mi.jgm.td02.chimie;

public class TestChimie {

	public static void main(String[] args) {
		Atome hydro = new Atome(0,1,"Hydrogene", "H");
		Atome oxy = new Atome(8,8,"Oxygene", "O");
		System.out.println(hydro);
		System.out.println(oxy);
		Molecule eau = new Molecule();
		eau.addAtome(hydro, 2);
		eau.addAtome(oxy);
		System.out.println(eau);
		System.out.println(eau.getNbProtons());

	}

}
