package adt.heap.extended;


import java.util.PriorityQueue;


/**
 * A classe HeapSumImpl herda de PriorityQueue, que funciona como uma min heap.
 * 
 * @author adalbertocajueiro
 *
 */
public class HeapSumImpl extends PriorityQueue<Integer> implements HeapSum {
	
	@Override
	public Integer sumRangeOrderStatistics(Integer k1, Integer k2) {
		int soma = 0;
		int cont = 1;
		while(cont <= k2) {
			if(cont >= k1) {
				soma += this.poll();
			}else {
				this.poll();
			}
			cont ++ ;
		}
		return soma;
	}

	@Override
	public Integer sumRangeBetween(Integer v1, Integer v2) {
		int soma = 0;
		boolean aux = true;
		if(!this.isEmpty()) {
			while(this.peek() != null && aux) {
				if(this.peek() >= v1 && this.peek() <= v2) {
					soma += this.poll();
				}else {
					if(this.peek() <= v2) {
						aux = true;
						this.poll();
					}else {
						aux = false;
					}
				}
			}
		}
		
		return soma;
	}

	@Override
	public Integer sumRangeAtLevel(int level) {
		int soma = 0;
		Integer[] array = new Integer[this.toArray().length];
		array = cast(array, this.toArray());

		int inicio = (int) Math.pow(2, level) - 1;
		int fim = (int) Math.pow(2, level + 1) - 2;
		int cond = 0;
		if(inicio < array.length) {                     // Verifica se a heap contem o nivel
			if(fim > array.length) {
				cond = array.length - 1;
			}else {
				cond = fim;
			}
			for(int i = inicio; i <= cond; i ++) {
				if(array[i] != null) {
					soma += array[i];
				}
			}

		}
		return soma;
	}
	
	// metodo auxiliar para fazer o cast dos arrays;
	private Integer[] cast(Integer[] array, Object[] array2) {
		for(int i = 0; i < array2.length; i++) {
			array[i] = (Integer) array2[i];
		}
		
		return array;
	}

}
