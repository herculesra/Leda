package adt.rbtree;

import java.awt.Color;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T>
		implements RBTree<T> {

	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}

	protected int blackHeight() {
		int result = 0;
		RBNode<T> aux = (RBNode<T>) root;

		while(!aux.isEmpty()) {
			if(aux.getColour() == Colour.BLACK){     
				result ++;
			}
			aux = (RBNode<T>) aux.getLeft();
		}

		return result;
	}

	protected boolean verifyProperties() {
		boolean resp = verifyNodesColour() && verifyNILNodeColour()
				&& verifyRootColour() && verifyChildrenOfRedNodes()
				&& verifyBlackHeight();

		return resp;
	}

	/**
	 * The colour of each node of a RB tree is black or red. This is guaranteed
	 * by the type Colour.
	 */
	private boolean verifyNodesColour() {
		return true; // already implemented
	}

	/**
	 * The colour of the root must be black.
	 */
	private boolean verifyRootColour() {
		return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
																// implemented
	}

	/**
	 * This is guaranteed by the constructor.
	 */
	private boolean verifyNILNodeColour() {
		return true; // already implemented
	}

	/**
	 * Verifies the property for all RED nodes: the children of a red node must
	 * be BLACK.
	 */
	private boolean verifyChildrenOfRedNodes() {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	/**
	 * Verifies the black-height property from the root.
	 */
	private boolean verifyBlackHeight() {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public void insert(T value) {
		
		if (value != null) {
			if (this.isEmpty()) {
				this.root.setData(value);
				this.root.setParent(null);
				this.root.setLeft(new BSTNode<T>());
				this.root.setRight(new BSTNode<T>());
				((RBNode<T>) root).setColour(Colour.BLACK);
				
			} else {
				insert(value, (RBNode<T>) root);
			}
		}


	}
	@Override
	private void insert(T element, RBNode<T> node) {
		if (element.compareTo(node.getData()) < 0) {
			if (node.getLeft().isEmpty()) {
				node.getLeft().setData(element);
				node.getLeft().setLeft(new RBTNode<T>());
				node.getLeft().setParent(node);
				node.getLeft().setRight(new RBTNode<T>());
			} else {
				insert(element, (BSTNode<T>) node.getLeft());
			}
		} else if (element.compareTo(node.getData()) > 0) {
			if (node.getRight().isEmpty()) {
				node.getRight().setData(element);
				node.getRight().setLeft(new BSTNode<T>());
				node.getRight().setParent(node);
				node.getRight().setRight(new BSTNode<T>());
			} else {
				insert(element, (RBTNode<T>) node.getRight());
			}
		}
	}

	@Override
	public RBNode<T>[] rbPreOrder() {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	// FIXUP methods
	protected void fixUpCase1(RBNode<T> node) {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	protected void fixUpCase2(RBNode<T> node) {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	protected void fixUpCase3(RBNode<T> node) {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	protected void fixUpCase4(RBNode<T> node) {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	protected void fixUpCase5(RBNode<T> node) {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}
}
