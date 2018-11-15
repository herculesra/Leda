package adt.avltree;

import java.util.Arrays;

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
		int result = 0;
		if(!node.isEmpty()) {
			result = height((BSTNode<T>) node.getLeft()) - height((BSTNode<T>) node.getRight()); 
		}
		return result;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);

		if(Math.abs(balance) > 1) {
			int fb = calculateBalance(node);
			if(Math.abs(fb) > 1) {
				boolean rotacionNode = fb < 0; // HEAVYCHILD IN RIGHT CHILD INDICATE TRUE
				// ELSE HEAVY IN LEFT CHILD INDICATE FALSE
				boolean rotacionSimple; 	   // SIMPLE INDICATE TRUE
				// DUPLE INDICATE FALSE
				boolean rotacionRR = rotacionNode && node.getRight() != null && calculateBalance((BSTNode<T>)node.getRight()) <= 0;
				boolean rotacionLL = !rotacionNode && node.getLeft() != null && calculateBalance((BSTNode<T>)node.getLeft()) >= 0;
				rotacionSimple = rotacionRR || rotacionLL;

				BSTNode<T> newNode;
				if(rotacionSimple) { // SIMPLE ROTATION
					if(rotacionNode) {
						newNode = Util.leftRotation(node);
					}else
						newNode = Util.rightRotation(node);
				}else {	// DUPLE ROTATION
					if(rotacionNode) {
						node.setRight(Util.rightRotation((BSTNode<T>) node.getRight()));
						newNode = Util.leftRotation(node);
					}else {
						node.setLeft(Util.leftRotation((BSTNode<T>) node.getLeft()));
						newNode = Util.rightRotation(node);
					}
				}
				if(node == getRoot()) {
					root = newNode;
					newNode.setParent(null);
				}else {
					boolean child = newNode.getParent().getRight() == node; // NEWNODE IS RIGHT CHILD
					// ELSE NEWNODE IS LEFT CHILD
					if(child)
						newNode.getParent().setRight(newNode);
					else
						newNode.getParent().setLeft(newNode);
				}
			}
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		while(parent != null) { 
			rebalance(parent);
			parent = (BSTNode<T>) parent.getParent();
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
	
	public static void main(String[] args) {
		AVLTreeImpl<Integer> avl = new AVLTreeImpl<>();
		System.out.println(avl.isEmpty());
		avl.insert(0);
		avl.insert(1);
		avl.insert(2);
		avl.insert(3);
		avl.insert(4);
		avl.insert(5);
		System.out.println(avl.size());
		System.out.println(avl.height());
		System.out.println(Arrays.toString(avl.order()));
		
	}

}
