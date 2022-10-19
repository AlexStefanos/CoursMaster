package ubs.info.as.tp03;

/**
 * TP03 Programmation Multi-Paradigme : Classe Ligne
 * @author Alexandre Stefanos
 *
 */
public class Ligne {
	int numero;
	
	/**
	 * Constructeur de la classe Ligne
	 * @param numero_ : numero de la ligne
	 */
	public Ligne(int numero_) {
		this.numero = numero_;
	}
	
	/**
	 * Methode permettant de retourner le numero de la ligne
	 * @return le numero de la ligne
	 */
	public int getNumero() {
		return(this.numero);
	}
	
	/**
	 * Methode permettant de changer le numero de la ligne
	 * @param numero_ : nouveau numero de la ligne
	 */
	public void setNumero(int numero_) {
		this.numero = numero_;
	}
	
	/**
	 * Methode permettant de verifier a l'aide d'un bouleen si la ligne est egale a la ligne donnee en parametre
	 * @param obj_ : ligne permettant de verifier
	 */
	@Override
	public boolean equals(Object obj_) {
		if(obj_ == null)
			return(false);
		if(obj_.getClass() != this.getClass())
			return(false);
		Ligne ligne = (Ligne)obj_;
		if(ligne.getNumero() != this.numero)
			return(false);
		return(true);
	}
	
	/**
	 * Methode permettant de retourner le hashcode de cette ligne
	 * @return le hashcode de cette ligne
	 */
	@Override
	public int hashCode() {
		return(this.numero);
	}
	
	/**
	 * Methode permettant de retourner l'ensemble des donnees de cette ligne dans une chaine de caractere
	 * @return la chaine de caractere 
	 */
	public String toString() {
		return(new String("Ligne " + this.numero));
	}
}
