package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The combsort algoritm.
 * 
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if((leftIndex + rightIndex) > 0) {
			int gap = rightIndex; // inicializa o Gap com o maior indice
			boolean trocas = true;
			// Enquanto o gap for diferente de 1 e acontecer trocas, o loop continua.
			while(gap != 1 && trocas){
				gap = (int) (gap / 1.25);
				// condicao de parada do laco
				if(gap < 1 ) { 
					gap = 1;
				}
				int i = leftIndex;
				trocas = false; // condicao de parada
				
				while((i+gap) <= rightIndex) {
					// condicao para trocar os elementos e ordenar o array
					if(array[i].compareTo(array[i+gap]) > 0) {
						Util.swap(array, i, i+gap);
						trocas = true;
					}
					i++;
				}
			}
		}
	}
}
