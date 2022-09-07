package TD04;

public class Notation {
	private int coeff,note;
	
	public Notation(int coeff, int note) {
		this.coeff = coeff;
		this.note = note;
	}
	
	public int getCoeff() {
		return coeff;
	}
	
	public int getNote() {
		return note;
	}
	
	public void affichage() {
		System.out.println("Note : " + note);
		System.out.println("Coeff : " + coeff);
	}
}
