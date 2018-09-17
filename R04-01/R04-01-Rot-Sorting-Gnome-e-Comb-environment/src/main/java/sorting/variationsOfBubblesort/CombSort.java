package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The combsort algoritm.
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if((leftIndex + rightIndex) > 0) {
			int gap = rightIndex;
			boolean trocas = true;
			while(gap != 1 && trocas){
				gap = (int) (gap / 1.25);
				if(gap < 1 ) { 
					gap = 1;
				}
				int i = leftIndex;
				trocas = false;
				
				while(i+gap >= rightIndex) {
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
