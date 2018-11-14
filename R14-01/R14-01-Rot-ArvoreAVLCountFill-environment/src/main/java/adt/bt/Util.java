package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BSTNode<T> pivot = (BSTNode<T>) node.getRight();
		pivot.setParent((BSTNode<T>) node.getParent());
		BSTNode<T> aux = node;
		if(!pivot.getLeft().isEmpty()){
			aux.setRight(pivot.getLeft());
		}else {
			BSTNode<T> nil = new BSTNode<>();
			nil.setParent(aux);
			aux.setRight(nil);
		}
		pivot.setLeft(aux);
		aux.setParent(pivot);
		node = pivot;
		return node;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BSTNode<T> pivot = (BSTNode<T>) node.getLeft();
		pivot.setParent((BSTNode<T>) node.getParent());
		BSTNode<T> aux = node;
		if(!pivot.getRight().isEmpty()) {
			aux.setLeft((BSTNode<T>) pivot.getRight());			
		}else {
			BSTNode<T> nil = new BSTNode<>();
			nil.setParent(aux);
			aux.setLeft(nil);
		}
		pivot.setRight(aux);
		aux.setParent(pivot);
		node = pivot;
		return node;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
