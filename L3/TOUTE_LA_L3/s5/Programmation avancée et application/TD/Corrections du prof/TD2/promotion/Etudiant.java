package up.mi.jgm.td02.promotion;

import java.util.HashMap;

/**
 * Represente un etudiant
 * 
 * @author Jean-Guy Mailly
 *
 */
public class Etudiant {
	/**
	 * Notes de l'etudiant, associees aux unites d'enseignement
	 */
	private HashMap<UniteEnseignement, Notes> notesEtu;

	/**
	 * Le nom de l'etudiant
	 */
	private String nom;

	/**
	 * Construit un etudiant dont le nom est donne
	 * 
	 * @param nom le nom de l'etudiant
	 */
	public Etudiant(String nom) {
		this.nom = nom;
		notesEtu = new HashMap<UniteEnseignement, Notes>();
		for (UniteEnseignement ue : UniteEnseignement.values()) {
			notesEtu.put(ue, new Notes());
		}
	}

	/**
	 * Permet d'obtenir le nom de l'etudiant
	 * 
	 * @return le nom de l'etudiant
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Permet d'obtenir la note finale d'un etudiant pour une unite d'enseignement
	 * donnee
	 * 
	 * @param ue l'unite d'enseignement
	 * @return la note de l'etudiant dans l'unite ue, calculee avec la formule
	 *         max(EX,(EX+CC)/2)
	 */
	public double getNote(UniteEnseignement ue) {
		return notesEtu.get(ue).getNoteFinale();
	}

	/**
	 * Permet d'obtenir la moyenne generale de l'etudiant
	 * 
	 * @return la moyenne de l'etudiant pour toutes les unites d'enseignement
	 */
	public double getMoyenne() {
		double somme = 0;
		for (UniteEnseignement ue : notesEtu.keySet()) {
			somme += getNote(ue);
		}
		return somme / notesEtu.keySet().size();
	}

	/**
	 * Permet de specifier la note de controle continu de l'etudiant dans une unite
	 * d'enseignement
	 * 
	 * @param ue   l'unite d'enseignement
	 * @param note la note
	 */
	public void setNoteCC(UniteEnseignement ue, double note) {
		notesEtu.get(ue).setNoteCC(note);
	}

	/**
	 * Permet de specifier la note d'examen de l'etudiant dans une unite
	 * d'enseignement
	 * 
	 * @param ue   l'unite d'enseignement
	 * @param note la note
	 */
	public void setNoteExam(UniteEnseignement ue, double note) {
		notesEtu.get(ue).setNoteExam(note);
	}

	@Override
	public String toString() {
		StringBuilder build = new StringBuilder(nom);
		build.append(" ; moyenne = ");
		build.append(getMoyenne());
		build.append(" ; ");
		for (UniteEnseignement ue : notesEtu.keySet()) {
			build.append("note de l'unite ");
			build.append(ue);
			build.append(" = ");
			build.append(getNote(ue));
			build.append(" ; ");
		}
		return build.toString();
	}
}
