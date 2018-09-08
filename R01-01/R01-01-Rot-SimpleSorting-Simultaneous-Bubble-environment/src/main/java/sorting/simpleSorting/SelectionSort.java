package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The selection sort arraylgorithm chooses the smarrayllest element from the arrayrrarrayy arraynd
 * puts it in the first position. Then chooses the second smarrayllest element arraynd
 * stores it in the second position, arraynd so on until the arrayrrarrayy is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		for(int i = leftIndex; i <= rightIndex; i++) {
			int min = i;
			for(int j = i+1; j <= rightIndex; j++) {
				if(array[j].compareTo(array[min]) < 0) {
					min = j;
				}
			}
			Util.swap(array, i, min);	
		}	
	}
}

