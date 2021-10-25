package up.mi.jgm.td02.promotion;

/**
 * Classe utilisee pour representer le couple de notes (CC,EX) d'un etudiant
 * dans une unite d'enseignement particuliere. Tant que les notes n'ont pas ete
 * explicitement fixees avec les methodes setXXX, elles valent 0.
 * 
 * @author Jean-Guy Mailly
 *
 */
public class Notes {
	/**
	 * La note de controle continu
	 */
	private double noteCC = 0;

	/**
	 * La note d'examen
	 */
	private double noteExam = 0;

	/**
	 * Permet d'obtenir la note de controle continu
	 * 
	 * @return la note de controle continu
	 */
	public double getNoteCC() {
		return noteCC;
	}

	/**
	 * Permet de fixer la note de controle continu
	 * 
	 * @param noteCC la note de controle continu
	 */
	public void setNoteCC(double noteCC) {
		this.noteCC = noteCC;
	}

	/**
	 * Permet d'obtenir la note d'examen
	 * 
	 * @return la note d'examen
	 */
	public double getNoteExam() {
		return noteExam;
	}

	/**
	 * Permet de fixer la note d'examen
	 * 
	 * @param noteExam la note d'examen
	 */
	public void setNoteExam(double noteExam) {
		this.noteExam = noteExam;
	}

	/**
	 * Permet d'obtenir la note finale de l'etudiant
	 * 
	 * @return max(EX,(CC+EX)/2)
	 */
	public double getNoteFinale() {
		double moyenne = (noteCC + noteExam) / 2;
		return (moyenne > noteExam) ? moyenne : noteExam;
	}

}
