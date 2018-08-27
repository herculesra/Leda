package minha.solucao;

public class Retangulo implements Area {
	private double base;
	private double altura;
	
	public Retangulo(double base, double altura) {
		this.base = base;
		this.altura = altura;
	}

	@Override
	public double calcularArea() {
		return this.base * this.altura;
	}

}
