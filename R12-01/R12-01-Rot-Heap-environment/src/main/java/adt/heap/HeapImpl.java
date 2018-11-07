package adt.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 3 > 2),
 * essa heap deixa os elementos maiores no topo. Essa comparação não é feita 
 * diretamente com os elementos armazenados, mas sim usando um comparator. 
 * Dessa forma, dependendo do comparator, a heap pode funcionar como uma max-heap 
 * ou min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

   protected T[] heap;
   protected int index = -1;
   /**
    * O comparador é utilizado para fazer as comparações da heap. O ideal é
    * mudar apenas o comparator e mandar reordenar a heap usando esse
    * comparator. Assim os metodos da heap não precisam saber se vai funcionar
    * como max-heap ou min-heap.
    */
   protected Comparator<T> comparator;

   private static final int INITIAL_SIZE = 20;
   private static final int INCREASING_FACTOR = 10;

   /**
    * Construtor da classe. Note que de inicio a heap funciona como uma
    * min-heap.
    */
   @SuppressWarnings("unchecked")
   public HeapImpl(Comparator<T> comparator) {
      this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
      this.comparator = comparator;
   }

   // /////////////////// METODOS IMPLEMENTADOS
   private int parent(int i) {
      return (i - 1) / 2;
   }

   /**
    * Deve retornar o indice que representa o filho a esquerda do elemento
    * indexado pela posicao i no vetor
    */
   private int left(int i) {
      return (i * 2 + 1);
   }

   /**
    * Deve retornar o indice que representa o filho a direita do elemento
    * indexado pela posicao i no vetor
    */
   private int right(int i) {
      return (i * 2 + 1) + 1;
   }

   @Override
   public boolean isEmpty() {
      return (index == -1);
   }

   @Override
   public T[] toArray() {
      ArrayList<T> resp = new ArrayList<T>();
      for (T elem : this.heap) {
         if (elem != null) {
            resp.add(elem);
         }
      }
      return (T[]) resp.toArray(new Comparable[0]);
   }

   // ///////////// METODOS A IMPLEMENTAR
   /**
    * Valida o invariante de uma heap a partir de determinada posicao, que pode
    * ser a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
    * (comparados usando o comparator) elementos na parte de cima da heap.
    */
   private void heapify(int position) {
      int l, r, largest, size;
      l = this.left(position);
      r = this.right(position);
      size = size();
      
      if(l <= size  && comparator.compare(heap[l], heap[position]) > 0) {
    	  largest = l;
      }else {
    	  largest = position;
      }
      
      if(r <= size && comparator.compare(heap[r], heap[position]) > 0) {
    	  if(comparator.compare(heap[l], heap[r]) < 0) {
    		  largest = r;
    	  }  
      }
      if(largest != position) {
    	  Util.swap(getHeap(), position, largest); 
    	  heapify(largest); 
      }  
   }

   @Override
   public void insert(T element) {
	  if(element != null) {
		  index++;
		  // ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
		  if (index == heap.length - 1) {
			  heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		  }
		  int position = index;
	        while (position > 0 && comparator.compare(heap[parent(position)], element) < 0) {
	            heap[position] = heap[parent(position)];
	            position = parent(position);
	        }
	        heap[position] = element;
	  }
   }

   @Override
   public void buildHeap(T[] array) {
	   cleanHeap();
	   transferElementArrayToHeap(array);
       for (int i = index / 2; i >= 0; i--) {
           heapify(i);
       }
   }
   
   private void cleanHeap() {
	   for(T e : heap) {
		   e = null;
	   }
   }
   
   private void transferElementArrayToHeap(T[] array) {
	   int i = 0;
	   index = -1;
	   if(array.length > heap.length) {
		   heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
	   }
	   for(T e: array) {
		   if(e != null) {
			   heap[i] = e;
			   index++;
			   i++;  
		   }
	   }
   }
   
   @Override
   public T extractRootElement() {
      T result = null;
      if(size() > 0) {
    	  result = heap[0];
          heap[0] = heap[index];
          heap[index] = null;
          index--;
          heapify(0);
      }
      
      return result;
   }

   @Override
   public T rootElement() {
	  T result = null;
      if(!isEmpty()) {
    	  result = heap[0];
      }
      return result;
   }

   @Override
   public T[] heapsort(T[] array) {
       T[] copy = null;
       if (array != null) {
           copy = makeArrayOfComparable(array.length -1);
           buildHeap(array);
           for (int i = 0; i < array.length; i++) {
               copy[i] = extractRootElement();
           }
           if (array.length - 1 >= 0 && copy[array.length - 1].compareTo(copy[0]) < 0) {
               Collections.reverse(Arrays.asList(copy));
           }
       }
       return copy;
   }
   
   private T[] makeArrayOfComparable(int size) {
	      @SuppressWarnings("unchecked")
	      T[] array = (T[]) new Comparable[size];
	      return array;
   }

   @Override
   public int size() {
      return index + 1;
   }

   public Comparator<T> getComparator() {
      return comparator;
   }

   public void setComparator(Comparator<T> comparator) {
      this.comparator = comparator;
   }

   public T[] getHeap() {
      return heap;
   }

}
