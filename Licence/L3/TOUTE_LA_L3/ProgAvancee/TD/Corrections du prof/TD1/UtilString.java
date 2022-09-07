package up.mi.jgm.td01;

public class UtilString {
	/**
	 * Formate un nombre entier < 10000 sous forme de String sur 5 caracteres
	 * @param nombre le nombre a formater
	 * @return nombre represente sous forme de String avec espaces a gauche
	 */
	public static String formatage(int nombre) {
		if(nombre < 10)
			return "    " + nombre ;
		if(nombre < 100)
			return "   " + nombre ;
		if(nombre < 1000)
			return "  " + nombre ;
		return " " + nombre ;
	}
	
	public static void main(String[] args) {
		System.out.println("formatage(5) = (" + formatage(5) + ")");
		System.out.println("formatage(25) = (" + formatage(25) + ")");
		System.out.println("formatage(325) = (" + formatage(325) + ")");
		System.out.println("formatage(9325) = (" + formatage(9325) + ")");
	}
}
