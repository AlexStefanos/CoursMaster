
public class TestCompteur {

	public static void main(String[] args) {
		Compteur c1 = new Compteur();
		c1.valeur = 0;
		
		for (int i = 0; i < 3; i++)
			Ex01.incrementer(c1.valeur);

	}

}
