package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		BSTNode<T> nodeRight = (BSTNode<T>) node.getRight();
		BSTNode<T> nodeLeft = (BSTNode<T>) node.getLeft();

		int rightHeight = getHeightTree(nodeRight);
		int leftHeight = getHeightTree(nodeLeft);

		return leftHeight - rightHeight;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);
		if(Math.abs(balance) > 1) {
			if(balance > 0 && !node.getLeft().getLeft().isEmpty()) { // pesando para esquerda(CASO LL). Rotacao para direita 
				node = Util.rightRotation(node);
			}else {
				if(balance < 0 && !node.getRight().getRight().isEmpty()) { // pesando para direta(CASO RR). Rotacao para esquerda
					node = Util.leftRotation(node);
				}else {
					if(balance > 0 && node.getLeft().getLeft().isEmpty() && !node.getLeft().getRight().isEmpty()) { 
						// pesando para esquerda(CASO LR). Rotacao para esquerda no filho e rotacao para direita no pai
						node.setLeft(Util.leftRotation((BSTNode<T>) node.getLeft()));
						node = Util.rightRotation(node);
					}else {
						if(balance < 0 && node.getRight().getRight().isEmpty() && !node.getRight().getLeft().isEmpty()) {
							// pesando para direita (Caso RL). Rotacao para direita no filho e rotacao para esquerda no pai
							node.setRight(Util.rightRotation((BSTNode<T>) node.getRight()));
							node = Util.leftRotation(node);	
						}
					}
				}	
			}
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		int balance = calculateBalance((BSTNode<T>) node.getParent());
		while(parent != null && Math.abs(balance) > 1) {
			rebalance(parent);
			parent = (BSTNode<T>) parent.getParent();
			balance = Math.abs(calculateBalance(parent));
		}
	}
	
	@Override
	public void insert(T element) {
		if (element != null) {
			if (this.isEmpty()) {
				this.root.setData(element);
				this.root.setParent(null);
				this.root.setLeft(new BSTNode<T>());
				this.root.setRight(new BSTNode<T>());
			} else {
				insert(element, root);
			}
		}
	}

	private void insert(T element, BSTNode<T> node) {
		if (element.compareTo(node.getData()) < 0) {
			if (node.getLeft().isEmpty()) {
				node.getLeft().setData(element);
				node.getLeft().setLeft(new BSTNode<T>());
				node.getLeft().setParent(node);
				node.getLeft().setRight(new BSTNode<T>());
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
				insert(element, (BSTNode<T>) node.getRight());
			}
		}
		rebalance(node);
	}
	
	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		if (!node.isEmpty()) {
			remove(node);
		}
	}

	private void remove(BSTNode<T> node) {
		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				node.setData(null);
				rebalanceUp(node);
			} else if ((node.getLeft().isEmpty() && !node.getRight().isEmpty())
					|| (!node.getLeft().isEmpty() && node.getRight().isEmpty())) {
				if (node != root) {
					if (node.getParent().getLeft().equals(node)) {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setLeft(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setLeft(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					} else {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setRight(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setRight(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					}
					rebalanceUp(node);
				} else {
					if (node.getRight().isEmpty()) {
						root = (BSTNode<T>) node.getLeft();
					} else {
						root = (BSTNode<T>) node.getRight();
					}
				}	
			} else {
				BSTNode<T> sucessor = super.sucessor(node.getData());
				node.setData(sucessor.getData());
				remove(sucessor);
			}
		}
	}

}
