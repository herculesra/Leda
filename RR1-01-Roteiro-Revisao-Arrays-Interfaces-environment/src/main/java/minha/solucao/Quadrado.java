package minha.solucao;

public class Quadrado implements Area {
	private double lado;
	
	public Quadrado(double lado) {
		this.lado = lado;
	}
	
	@Override
	public double calcularArea() {
		return (this.lado * this.lado);
	}
	
}
