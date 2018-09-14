package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if(array.length > 0) {
			int[] B = new int[array.length];
			int k = maior(array, leftIndex, rightIndex);
			coutingSort(array, B, k, leftIndex, rightIndex);
			for(int i = leftIndex; i <= rightIndex; i++) {
				array[i] = B[i];
			}
		}
	}
	
	/**
	 * 
	 * Metodo principal do algoritmo.
	 * 
	 * @param A, array original.
	 * @param B, array para guardar os elementos ordenados.
	 * @param k maior elemento do array original.
	 * @param leftIndex
	 * @param rightIndex
	 */
	private void coutingSort(Integer[] A, int[] B, int k, int leftIndex, int rightIndex) {
		// Array de contagem com tamanho igual ao maior elemento do array original/principal + 1.
		int[] C = new int[k+1];

		// Java automaticamente preenche qualquer array do tipo inteiro com 0(Zeros).
		// Assim nao ha necessidade de preencher o array C com zeros.
				

		// Contagem dos elementos
		for(int j = leftIndex; j <= rightIndex; j++) {
			C[A[j]] = C[A[j]] + 1;
		}

		// Soma dos prefixos
		for(int i = leftIndex + 1; i <= k; i++) {
			C[i] = C[i] + C[i-1];
		}

		// Inserindo os elementos em B, ordenados.
		for(int l = rightIndex; l >= leftIndex; l--) {
			B[C[A[l]] - 1] = A[l];
			C[A[l]] = C[A[l]] - 1;
		}
	}
	
	// Funcao para encontrar o maior elemento do array.
	
	private int maior(Integer[] array, int leftIdex, int rightIndex) {
		int maior = array[leftIdex];
		for(int i = leftIdex; i <= rightIndex; i++) {
			if(maior < array[i]) {
				maior = array[i];
			}
		}
		return maior;
	}
}
