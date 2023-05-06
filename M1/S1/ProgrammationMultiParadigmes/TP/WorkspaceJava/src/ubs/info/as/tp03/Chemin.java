package ubs.info.as.tp03;

public class Chemin {
	Station depart;
	Station arrivee;
	Ligne ligne;
	
	/**
	 * Constructeur de la classe chemin
	 * @param ligne_ : ligne de ce chemin
	 * @param depart_ : station de depart du chemin
	 * @param arrivee_ : station d'arrivee de ce chemin
	 */
	public Chemin(Ligne ligne_, Station depart_, Station arrivee_) {
		this.depart = depart_;
		this.arrivee = arrivee_;
		this.ligne = ligne_;
	}
	
	/**
	 * Methode permettant de retourner la station de depart de ce chemin
	 * @return station de depart de ce chemin
	 */
	public Station getDepart() {
		return(this.depart);
	}
	
	/**
	 * Methode permettant de retourner la station d'arrivee de ce chemin
	 * @return station d'arrivee de ce chemin
	 */
	public Station getArrivee() {
		return(this.arrivee);
	}
	
	/**
	 * Methode permettant de retourner la ligne de ce chemin
	 * @return la ligne de ce chemin
	 */
	public Ligne getLigne() {
		return(this.ligne);
	}
	
	/**
	 * Methode permettant de changer la station de depart de ce chemin
	 * @param depart_ : nouvelle station de depart de ce chemin
	 */
	public void setDepart(Station depart_) {
		this.depart = depart_;
	}
	
	/**
	 * Methode permettant de changer la station d'arrivee de ce chemin
	 * @param arrivee_ : nouvelle station d'arrivee de ce chemin
	 */
	public void setArrivee(Station arrivee_) {
		this.arrivee = arrivee_;
	}
	
	/**
	 * Methode permettant de changer la ligne de ce chemin
	 * @param ligne_ : nouvelle ligne de ce chemin
	 */
	public void setLigne(Ligne ligne_) {
		this.ligne = ligne_;
	}
	
	/**
	 * Methode permettant d'avoir l'ensemble des donnees de ce chemin
	 */
	public String toString() {
		String tmp = new String();
		tmp = "Depart : " + this.getDepart() + "\nArrivee : " + this.getArrivee() + "\nLigne : " + this.getLigne();
		return(tmp);
	}
	
	/**
	 * Methode permettant de savoir si ce chemin et un autre sont egaux
	 * @param obj_ : chemin compare
	 */
	@Override
	public boolean equals(Object obj_) {
		if(obj_ == null)
			return(false);
		if(obj_.getClass() != this.getClass())
			return(false);
		Chemin chemin = (Chemin)obj_;
		if(chemin.depart.equals(chemin.getDepart()) == false)
			return(false);
		if(chemin.arrivee.equals(chemin.getArrivee()) == false)
			return(false);
		if(chemin.ligne.equals(chemin.getLigne()) == false)
			return(false);
		return(true);
	}
	
	/**
	 * Methode permettant de retourner l'hashcode de ce chemin
	 */
	@Override
	public int hashCode() {
		return(this.getLigne().getNumero());
	}
}
