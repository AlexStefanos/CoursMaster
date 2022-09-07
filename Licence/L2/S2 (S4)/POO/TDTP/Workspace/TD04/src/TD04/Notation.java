package TD04;

public class Notation {
	private double coef;
	private double note;
	
	public Notation(double note, double coef) {
		this.note = note;
		this.coef = coef;
	}
	
	public Notation(double note) {
		this(note, 1.0);
	}
	
	public double getCoef() {
		return coef;
	}
	
	public double getNote() {
		return note;
	}
	
	public void afficher() {
		System.out.println("coef : " + coef + "note : " + note);
	}
}
