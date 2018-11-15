package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BSTNode<T> aux = new BSTNode<T>();
		aux.setData(node.getData());
		aux.setLeft(node.getLeft());
		aux.setRight(node.getRight());
		aux.setParent(node.getParent());
		BSTNode<T> pivot = (BSTNode<T>) aux.getRight();
		pivot.setParent((BSTNode<T>) aux.getParent());
		if(!pivot.getLeft().isEmpty()){
			aux.setRight(pivot.getLeft());
		}else {
			aux.setRight(new BSTNode<T>());
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
