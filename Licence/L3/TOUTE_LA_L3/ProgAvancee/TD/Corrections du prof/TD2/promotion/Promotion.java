package up.mi.jgm.td02.promotion;

import java.util.ArrayList;

/**
 * Represente une promotion d'etudiants comme une ArrayList<Etudiant>
 * 
 * @author Jean-Guy Mailly
 *
 */
public class Promotion extends ArrayList<Etudiant> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Ajoute une note de controle continu pour un etudiant particulier, dans une
	 * unite d'enseignement donnee. Si le nom de l'etudiant n'existe pas dans la
	 * promotion, la methode ne fait rien.
	 * 
	 * @param nomEtu le nom de l'etudiant
	 * @param ue     l'unite d'enseignement
	 * @param note   la note de controle continu
	 */
	public void ajoutNoteCC(String nomEtu, UniteEnseignement ue, double note) {
		ajoutNote(nomEtu, ue, note, false);
	}

	/**
	 * Ajoute une note d'examen pour un etudiant particulier, dans une unite
	 * d'enseignement donnee. Si le nom de l'etudiant n'existe pas dans la
	 * promotion, la methode ne fait rien.
	 * 
	 * @param nomEtu le nom de l'etudiant
	 * @param ue     l'unite d'enseignement
	 * @param note   la note d'examen
	 */
	public void ajoutNoteExam(String nomEtu, UniteEnseignement ue, double note) {
		ajoutNote(nomEtu, ue, note, true);
	}

	/**
	 * Ajoute une note pour un etudiant particulier.
	 * 
	 * @param nomEtu le nom de l'etudiant
	 * @param ue     l'unite d'enseignement
	 * @param note   la note
	 * @param exam   booleen qui indique si la note est une note d'examen ou de
	 *               controle continu
	 */
	private void ajoutNote(String nomEtu, UniteEnseignement ue, double note, boolean exam) {
		Etudiant etudiant = null;
		for (Etudiant etu : this) {
			if (etu.getNom().equals(nomEtu))
				etudiant = etu;
		}
		if (etudiant != null) {
			if (exam) {
				etudiant.setNoteExam(ue, note);
			} else {
				etudiant.setNoteCC(ue, note);
			}
		}
	}

	/**
	 * Calcule la moyenne de la promotion
	 * 
	 * @return la moyenne de la promotion
	 */
	public double moyennePromo() {
		double somme = 0;
		for (Etudiant etu : this) {
			somme += etu.getMoyenne();
		}
		return somme / this.size();
	}

	/**
	 * Calcule la moyenne de la promotion pour une unite d'enseignement
	 * 
	 * @param ue l'unite dont on veut la moyenne
	 * @return la moyenne de la promotion pour l'unite ue
	 */
	public double moyennePromo(UniteEnseignement ue) {
		double somme = 0;
		for (Etudiant etu : this) {
			somme += etu.getNote(ue);
		}
		return somme / this.size();
	}

	/**
	 * Determine le major de la promotion
	 * 
	 * @return le major de promotion, ou null si la promotion est vide
	 */
	public Etudiant majorPromo() {
		if (!this.isEmpty()) {
			Etudiant major = this.get(0);
			for (int i = 1; i < this.size(); i++) {
				if (this.get(i).getMoyenne() > major.getMoyenne()) {
					major = this.get(i);
				}
			}
			return major;
		}
		return null;
	}

	/**
	 * Determine le major de la promotion pour une unite d'enseignement
	 * 
	 * @param ue l'unite dont on veut le major
	 * @return le major de promotion pour l'unite ue, ou null si la promotion est
	 *         vide
	 */
	public Etudiant majorPromo(UniteEnseignement ue) {
		if (!this.isEmpty()) {
			Etudiant major = this.get(0);
			for (int i = 1; i < this.size(); i++) {
				if (this.get(i).getNote(ue) > major.getNote(ue)) {
					major = this.get(i);
				}
			}
			return major;
		}
		return null;
	}

}
