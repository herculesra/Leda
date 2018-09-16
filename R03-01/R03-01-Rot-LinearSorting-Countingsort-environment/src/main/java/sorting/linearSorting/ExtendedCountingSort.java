package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	private int menor;
	private int maior;
	
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		// Restricao para nao aceitar array vazio
		if(array.length > 0) {
			int[] B = new int[array.length];
			maiorEmenorValor(array, leftIndex, rightIndex);
			extendCountingSort(array, B, menor, maior, leftIndex, rightIndex);
			for(int i = 0; i < array.length; i++) {
				array[i] = B[i];
			}
		}
	}

	private void extendCountingSort(Integer[] A, int[] B, int menor, int maior, int leftIndex, int rightIndex) {
		// Array de contadores com tamanho mínimo
		int[] C = new int[maior - menor + 1];
		
		// contagem dos elementos
		for(int i = leftIndex; i <= rightIndex; i++) {
			C[A[i] - menor] = C[A[i] - menor] + 1;
		}
		
		// soma dos prefixos
		for(int j = 1; j < C.length; j++) {
			C[j] = C[j] + C[j-1];
		}
		
		// ordenando o array auxiliar 
		for(int l = rightIndex; l >= leftIndex; l--) {
			B[C[A[l] - menor] - 1] = A[l];
			C[A[l] - menor] = C[A[l] - menor] - 1;
		}	
	}
	
	// Metodo resposavel por encontrar o menor e maior elemento do array.
	private void maiorEmenorValor(Integer[] array, int leftIndex, int rightIndex) {
		menor = array[0];
		maior = array[0];
		for(int i = leftIndex; i <= rightIndex; i++) {
			if(menor > array[i]) {
				menor = array[i];
			}
			if(maior < array[i]) {
				maior = array[i];
			}
		}
	}

}
