package minha.solucao;

public class Main {
	public static void main(String[] args) {
		Quadrado q1 = new Quadrado(5);
		Circulo circulo = new Circulo(3.5);
		Retangulo retangulo = new Retangulo(5, 12.3);
		Triangulo triangulo = new Triangulo(6, 2);
		
		System.out.println(q1.calcularArea());
		System.out.println(circulo.calcularArea());
		System.out.println(retangulo.calcularArea());
		System.out.println(triangulo.calcularArea());
	}
}
