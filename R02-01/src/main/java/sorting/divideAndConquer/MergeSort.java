package sorting.divideAndConquer;

import java.lang.reflect.Array;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		    int i, j, k, metadeTamanho;
		    
		    if(leftIndex == rightIndex) return;
		    metadeTamanho = (leftIndex + rightIndex ) / 2;

		    sort(array, leftIndex, metadeTamanho);
		    sort(array, metadeTamanho + 1, rightIndex);

		    i = leftIndex;
		    j = metadeTamanho + 1;
		    k = 0;
			T[] vetorTemp = (T[]) new Object[rightIndex - leftIndex + 1];

		    while(i < metadeTamanho + 1 || j  < rightIndex + 1) { // O laco so vai quebrar quando i e j forem maiores que suas extremidades
		    	if (i == metadeTamanho + 1 ) { 
		            vetorTemp[k] = array[j];
		            j++;
		            k++;
		        }
		        else {
		            if (j == rightIndex + 1) {
		                vetorTemp[k] = array[i];
		                i++;
		                k++;
		            }
		            else {
		                if (array[i].compareTo(array[j]) < 0) {
		                    vetorTemp[k] = array[i];
		                    i++;
		                    k++;
		                }
		                else {
		                    vetorTemp[k] = array[j];
		                    j++;
		                    k++;
		                }
		            }
		        }

		    }
		    for(int u = leftIndex; u <= rightIndex; u++) {
		        array[u] = vetorTemp[u - leftIndex];
		    }
		}
	}
