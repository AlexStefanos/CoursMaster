package TD07;

public class Revue extends Document {
	private String date;
	
	public Revue(int numero, String titre, String date) {
		super (numero, titre);
		this.date = date;
	}
	
	public String getDate() {
		return (super.toString() + " " +  " ");
	}
}
