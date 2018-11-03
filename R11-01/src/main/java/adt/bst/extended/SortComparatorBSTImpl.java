package adt.bst.extended;

import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;
	
	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	private void cleanTree(BSTNode<T> node) {
		if (!node.isEmpty()) {
			cleanTree((BSTNode<T>) node.getLeft());
			cleanTree((BSTNode<T>) node.getRight());
			node.setData(null);
		}
	}

	@Override
	public T[] sort(T[] array) {
		T[] result = null;
		if (array != null) {
			cleanTree(root);
			for (T element : array) {
				insert(element);
			}
			result = order();
		}

		return result;
	}

	@Override
	public T[] reverseOrder() {
		T[] array = (T[]) new Comparable[] {};

		if (size() > 0) {
			array = (T[]) new Comparable[size()];
			int index = 0;
			reverseOrder(array, index, root);		 
		}   
		return array;
	}

	private int reverseOrder(T[] array, int index, BSTNode<T> node) {
		if (!node.isEmpty()) {
			index = reverseOrder(array, index, (BSTNode<T>) node.getRight());
			array[index] = node.getData();
			index++;
			index = reverseOrder(array, index, (BSTNode<T>) node.getLeft());
			return index;
		} else {
			return index;
		}
	}
	
	@Override
	protected int compareElements(T element1, T element2) {
		return comparator.compare(element1, element2);
	}
	
	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
}
