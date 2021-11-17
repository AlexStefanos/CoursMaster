package up.mi.as.td03;

public class Division extends Operateur {
	private Operateur a, b;
	
	public Division(Operateur a, Operateur b) {
		super(2);
		this.a = a;
		this.b = b;
	}

	@Override
	public double evaluer() {
		if(b.evaluer() != 0) {
			return(a.evaluer() / b.evaluer());
		}
		else
			return(0);
	}
}
