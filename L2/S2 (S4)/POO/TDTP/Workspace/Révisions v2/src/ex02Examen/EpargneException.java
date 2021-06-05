package ex02Examen;

public class EpargneException extends Exception {
	public EpargneException(String message) {
		super("Erreur dans l'Epargne : " + message);
	}
}
