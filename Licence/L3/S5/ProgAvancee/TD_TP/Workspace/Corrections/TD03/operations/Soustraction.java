package up.mi.jgm.td03.operations;

/**
 * Represente un operateur de soustraction
 * @author Jean-Guy Mailly
 *
 */
public class Soustraction extends Operateur {
	/**
	 * Le premier operande de la soustraction
	 */
	private Operateur operande1;
	
	/**
	 * Le second operande de la soustraction
	 */
	private Operateur operande2;

	/**
	 * Construit une soustraction en fonction de ses deux operandes
	 * @param op1 le premier operande
	 * @param op2 le second operande
	 */
	public Soustraction(Operateur op1, Operateur op2) {
		super(2);
		operande1 = op1;
		operande2 = op2;
	}

	@Override
	public double evaluer() {
		return operande1.evaluer() - operande2.evaluer();
	}
	
	@Override
	public String toString() {
		StringBuilder build = new StringBuilder();
		if(operande1 instanceof Valeur) {
			build.append(operande1.toString());
		}else {
			build.append("(");
			build.append(operande1.toString());
			build.append(")");
		}
		build.append("-");
		if(operande2 instanceof Valeur) {
			build.append(operande2.toString());
		}else {
			build.append("(");
			build.append(operande2.toString());
			build.append(")");
		}
		return build.toString();
	}
}
