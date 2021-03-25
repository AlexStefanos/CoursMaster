package TD_TP05;

public class TestMots {
	public static void main(String[] args) {
		String phrase = Saisie.lireChaine("Votre phrase ??");
		Mots mots = new Mots(phrase);
		mots.mirroirMots();
		mots.mirroirLettres();
	}
}
