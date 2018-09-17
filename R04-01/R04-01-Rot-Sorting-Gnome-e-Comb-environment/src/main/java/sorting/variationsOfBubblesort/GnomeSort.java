package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The implementation of the algorithm must be in-place!
 */

public class GnomeSort<T extends Comparable<T>> extends AbstractSorting<T> {
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		// inicializa o pivot
		int posicao = leftIndex + 1; 
		// enquanto nao ultrapassa o ultimo elemento
		while(posicao <= rightIndex) {
			// Se estiver na ordem correta, incrementa o pivot
			if(array[posicao].compareTo(array[posicao - 1]) >= 0) {
				posicao ++;
			// Se nao, faz a troca das posicoes
			}else {
				Util.swap(array, posicao, posicao - 1);
				// verifica se tem anterior
				if(posicao > leftIndex + 1) {
					posicao --;
				// se nao, prossegue.	
				}else {
					posicao ++;
				}
			}
		}
	}
}
