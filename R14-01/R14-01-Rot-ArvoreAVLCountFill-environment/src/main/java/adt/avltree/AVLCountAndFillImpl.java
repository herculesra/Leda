package adt.avltree;

import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;
import adt.bst.BSTNode;
import adt.bt.Util;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {
		
	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		if(array.length > 0) {
			sort(array, 0, array.length-1);
			if(root.isEmpty()) {
				mediana(array);
			}else {
				T[] arrayOrder = order().clone();
				cleanTree();
				mediana(merge(array, arrayOrder));
			}
		}
	}
	
	private T[] merge(T[] array, T[] arrayOrder) { //concatena os 2 arrays e ordena em 1 unico
		T[] arrayAux = (T[]) new Comparable[array.length + arrayOrder.length];
		for(int i = 0; i < array.length; i++) {
			arrayAux[i] = array[i];
		}
		int j = 0;
		for(int i = array.length; i < arrayAux.length; i ++) {
			arrayAux[i] = arrayOrder[j];
			j++;
		}
		
		sort(arrayAux, 0, arrayAux.length - 1);
		
		return arrayAux;
	}

	private void mediana(T[] array) {
		// TODO Auto-generated method stub
		
	}

	private void cleanTree() {
		root.setLeft(new BSTNode<T>());
		root.setRight(new BSTNode<T>());
		root.setData(null);
		root.setParent(null);
	}



	//Utilizacao do mergesort para ordenar o array inicialmente
	private void sort(T[] array, int leftIndex, int rightIndex) {
		if(leftIndex < rightIndex) {
			int middleIndex = (leftIndex + rightIndex) / 2;

			sort(array, leftIndex, middleIndex);
			sort(array, middleIndex + 1, rightIndex);

			merge(array, leftIndex, middleIndex, rightIndex);
		}

	}

	private void merge(T[] array, int leftIndex, int middleIndex, int rightIndex) {
		int n1 = middleIndex - leftIndex;
		int n2 = rightIndex - middleIndex;

		List<T> leftArray = new LinkedList<T>();
		List<T> rightArray = new LinkedList<T>();


		for(int i = 0; i <= n1; i++) {
			leftArray.add(array[leftIndex + i]);
		}

		for(int j = 0; j < n2; j++) {
			rightArray.add(array[middleIndex + 1 + j]);
		}

		int i = 0;
		int j = 0;
		int k = leftIndex;

		while(i <= n1 && j < n2) {
			if(leftArray.get(i).compareTo(rightArray.get(j)) < 0) {
				array[k] = leftArray.get(i);
				i++;
			} else {
				array[k] = rightArray.get(j);
				j++;
			}
			k++;
		}

		while(i <= n1) {
			array[k] = leftArray.get(i);
			i++;
			k++;
		}

		while(j < n2) {
			array[k] = rightArray.get(j);
			j++;
			k++;
		}
	}


	@Override
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);
		if(Math.abs(balance) > 1) {
			if(balance > 0 && !node.getLeft().getLeft().isEmpty()) { // pesando para esquerda(CASO LL). Rotacao para direita 
				BSTNode<T> aux = Util.rightRotation(node);
				if(node == root) {
					root = aux;
				}
				LLcounter ++;
			}else {
				if(balance < 0 && !node.getRight().getRight().isEmpty()) { // pesando para direta(CASO RR). Rotacao para esquerda
					BSTNode<T> aux = Util.leftRotation(node);	
					if(node == root) {
						root = aux;
					}
					RRcounter ++;
				}else {
					if(balance > 0 && node.getLeft().getLeft().isEmpty() && !node.getLeft().getRight().isEmpty()) { 
						// pesando para esquerda(CASO LR). Rotacao para esquerda no filho e rotacao para direita no pai
						node.setLeft(Util.leftRotation((BSTNode<T>) node.getLeft()));
						BSTNode<T> aux = Util.rightRotation(node);
						if(node == root) {
							root = aux;
						}
						LRcounter ++;
					}else {
						if(balance < 0 && node.getRight().getRight().isEmpty() && !node.getRight().getLeft().isEmpty()) {
							// pesando para direita (Caso RL). Rotacao para direita no filho e rotacao para esquerda no pai
							node.setRight(Util.rightRotation((BSTNode<T>) node.getRight()));
							BSTNode<T> aux = Util.leftRotation(node);	
							if(node == root) {
								root = aux;
							}
							RLcounter ++;
						}
					}
				}	
			}
		}
	}


}
