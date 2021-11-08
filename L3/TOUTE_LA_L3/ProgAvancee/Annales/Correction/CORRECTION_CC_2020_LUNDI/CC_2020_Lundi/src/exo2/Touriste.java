package exo2;

public class Touriste {
	private String nom;
	private String prenom;
	private String numTelephone;
	private String mail;
	private double age;
	
	public Touriste(String nom, String prenom, String numTelephone, String mail, double age) {
		this.nom = nom;
		this.prenom = prenom;
		this.numTelephone = numTelephone;
		this.mail = mail;
		this.age = age;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNumTelephone() {
		return numTelephone;
	}

	public void setNumTelephone(String numTelephone) {
		this.numTelephone = numTelephone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public double getAge() {
		return age;
	}

	public void setAge(double age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Touriste [nom=" + nom + ", prenom=" + prenom + ", numTelephone=" + numTelephone + ", mail=" + mail
				+ ", age=" + age + "]";
	}
	
}
