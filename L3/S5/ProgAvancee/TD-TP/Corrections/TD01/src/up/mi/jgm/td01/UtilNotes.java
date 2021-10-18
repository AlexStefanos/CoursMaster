package up.mi.jgm.td01;

public class UtilNotes {
	/**
	 * Determine la note finale d'un etudiant en fonction du controle continu et de
	 * l'examen.
	 * 
	 * @param noteCC   la note de controle continu
	 * @param noteExam la note d'examen
	 * @return la note finale
	 */
	public static double noteFinale(double noteCC, double noteExam) {
		if (noteExam >= noteCC)
			return noteExam;
		else
			return (noteExam + noteCC) / 2;
	}

	/**
	 * Determine la moyenne d'un groupe d'étudiantes. Chaque case d'indice pair i
	 * correspond a la note de controle continue d'un etudiant, et la case d'indice
	 * i+1 correspond a la note d'examen de cet etudiant. On ne traite pas le cas
	 * d'un tableau de longueur impaire.
	 * 
	 * @param notes les notes des etudiants
	 * @return la moyenne du groupe
	 */
	public static double moyenneGroupe(double[] notes) {
		double[] notesEtudiants = new double[notes.length / 2];
		for (int i = 0; i < notesEtudiants.length; i++) {
			notesEtudiants[i] = noteFinale(notes[2 * i], notes[2 * i + 1]);
		}
		return UtilTab.moyenne(notesEtudiants);
	}
}
