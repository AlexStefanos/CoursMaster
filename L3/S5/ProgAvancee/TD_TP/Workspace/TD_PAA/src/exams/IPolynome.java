package exams;

public interface IPolynome {
	public int coefficient(int degre);
	
	public IPolynome addition(IPolynome p);
	
	public double evaluer(double x);
}
