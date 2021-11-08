package exo2;

public class Salle {
	private int numero;
	private int maxSpectateurs;
	private boolean films3d;
	
	public Salle(int numero, int maxSpectateurs, boolean films3d) {
		this.numero = numero;
		this.maxSpectateurs = maxSpectateurs;
		this.films3d = films3d;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getMaxSpectateurs() {
		return maxSpectateurs;
	}

	public void setMaxSpectateurs(int maxSpectateurs) {
		this.maxSpectateurs = maxSpectateurs;
	}

	public boolean isFilms3d() {
		return films3d;
	}

	public void setFilms3d(boolean films3d) {
		this.films3d = films3d;
	}

	@Override
	public String toString() {
		return "Salle [numero=" + numero + ", maxSpectateurs=" + maxSpectateurs + ", films3d=" + films3d + "]";
	}
		
}
