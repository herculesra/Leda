package minha.solucao;

public class Circulo implements Area{
	private double raio;
	
	public Circulo(double raio) {
		this.raio = raio;
	}
	@Override
	public double calcularArea() {
		return (Math.PI * Math.pow(this.raio, 2));
	}

}
