package adt.bst;

import java.util.Arrays;

import adt.bt.BTNode;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return getHeightTree(root);
	}
	
	private int getHeightTree(BSTNode<T> node) {
		if(node.isEmpty()) {
			return -1;
		}else {
			return 1 + Math.max(getHeightTree((BSTNode<T>) node.getLeft()), getHeightTree((BSTNode<T>) node.getRight()));
		}		
	}

	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> result = new BSTNode<>();
		if(element != null) {
			result = search(element, root);
		}
		return result;
	}
	private BSTNode<T> search(T element, BSTNode<T> node){
		BSTNode<T> result = new BSTNode<>();
		if(!node.isEmpty()) {
			if(node.getData().equals(element)) {
				result = node;
			}else if(element.compareTo(node.getData()) < 0) {
				result = search(element, (BSTNode<T>) node.getLeft());
			}else if(element.compareTo(node.getData()) > 0) {
			result = search(element, (BSTNode<T>) node.getRight());
			}
		}	
		return result;
	}

	@Override
	public void insert(T element) {
		if(element != null) {
			if(this.isEmpty()) {
				this.root.setData(element);
				this.root.setParent(null);
				this.root.setLeft(new BSTNode<T>());
				this.root.setRight(new BSTNode<T>());
			}else {
				insert(element, root);
			}
		}
	}
	
	private void insert(T element, BSTNode<T> node) {
		if(element.compareTo(node.getData()) < 0) {
			if(node.getLeft().isEmpty()) {
				node.getLeft().setData(element);
				node.getLeft().setLeft(new BSTNode<T>());
				node.getLeft().setParent(node);
				node.getLeft().setRight(new BSTNode<T>());
			}else {
				insert(element, (BSTNode<T>) node.getLeft());
			}
		}else if(element.compareTo(node.getData()) > 0) {
			if(node.getRight().isEmpty()){
				node.getRight().setData(element);
				node.getRight().setLeft(new BSTNode<T>());
				node.getRight().setParent(node);
				node.getRight().setRight(new BSTNode<T>());
			}else {
				insert(element, (BSTNode<T>) node.getRight());
			}
		}
	}
	

	private BSTNode<T> createNode(T element, BSTNode<T> node){
		BSTNode<T> newNode = (BSTNode<T>) new BSTNode.Builder<T>()
				.data(element)
				.left((BSTNode<T>) new BSTNode<>())
				.parent(node)
				.right((BSTNode<T>) new BSTNode<>())
				.build();
		
		return newNode;
	}
	@Override
	public BSTNode<T> maximum() {
		BSTNode<T> result = null;
		if(!this.isEmpty()) {
			result = maximum(root);
		}
		return result;
	}
	
	private BSTNode<T> maximum(BSTNode<T> node){
		BSTNode<T> result = null;
		if(node.isLeaf() || node.getRight().isEmpty()) {
			result = node;
		}else {
			result = maximum((BSTNode<T>) node.getRight());
		}
		return result;
	}

	@Override
	public BSTNode<T> minimum() {
		BSTNode<T> result = null;
		if(!this.isEmpty()) {
			result = minimum(root);
		}
		return result;
	}
	
	private BSTNode<T> minimum(BSTNode<T> node){
		BSTNode<T> result = null;
		if(node.isLeaf() || node.getLeft().isEmpty()) {
			result = node;
		}else {
			result = maximum((BSTNode<T>) node.getLeft());
		}
		return result;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		 BSTNode<T> elementNode = search(element);
	        BSTNode<T> result = null;
	        if (!elementNode.isEmpty()) {
	            if(!elementNode.getRight().isEmpty()){
	                result = minimum((BSTNode<T>) elementNode.getRight());
	            }else{
	                BSTNode<T> parent = (BSTNode<T>) elementNode.getParent();
	                BSTNode<T> actual = elementNode;
	                while(parent != null && actual.equals(parent.getRight())){
	                    actual = parent;
	                    parent = (BSTNode<T>) parent.getParent();
	                }

	                result = parent;
	            }
	        }

	        return result;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> result = null;
        if(element != null) {
            BSTNode<T> elementNode = search(element);
            if (!elementNode.isEmpty()) {
                if (!elementNode.getLeft().isEmpty()) {
                    result = maximum((BSTNode<T>) elementNode.getLeft());
                } else {
                    BSTNode<T> parent = (BSTNode<T>) elementNode.getParent();
                    BSTNode<T> actual = elementNode;
                    while (parent != null && actual.equals(parent.getLeft())) {
                        actual = parent;
                        parent = (BSTNode<T>) parent.getParent();
                    }

                    result = parent;
                }
            }
        }

        return result;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
        if(!node.isEmpty()){
            remove(node);
        }
	}
	
	private void remove(BSTNode<T> node){
        if(!node.isEmpty()){
            if(node.isLeaf()){
                node.setData(null);
            }else if((node.getLeft().isEmpty() && !node.getRight().isEmpty()) || (!node.getLeft().isEmpty() && node.getRight().isEmpty())){
                if(node != root){
                    if(node.getParent().getLeft().equals(node)){
                        if(!node.getLeft().isEmpty()) {
                            node.getParent().setLeft(node.getLeft());
                            node.getLeft().setParent(node.getParent());
                        }else{
                            node.getParent().setLeft(node.getRight());
                            node.getRight().setParent(node.getParent());
                        }
                    }else{
                        if(!node.getLeft().isEmpty()){
                            node.getParent().setRight(node.getLeft());
                            node.getLeft().setParent(node.getParent());
                        }else{
                            node.getParent().setRight(node.getRight());
                            node.getRight().setParent(node.getParent());
                        }
                    }
                }else {
                    if(node.getRight().isEmpty()){
                        root = (BSTNode<T>) node.getLeft();
                    }else {
                        root = (BSTNode<T>) node.getRight();
                    }
                }
            }else{
                BSTNode<T> sucessor = sucessor(node.getData());
                node.setData(sucessor.getData());
                remove(sucessor);
            }
        }
    }

	
	@SuppressWarnings("unchecked")
	@Override
	public T[] preOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		preOrder(root, array, 0);
		return array;
	}
	
	private void preOrder(BSTNode<T> node, T[] array, int i) {
		if(!node.isEmpty()) {
			array[i] = node.getData();
			preOrder((BSTNode<T>) node.getLeft(), array, i++);
			preOrder((BSTNode<T>)node.getRight(), array, i++);
		}
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public T[] order() {
		T[] array = (T[]) new Object[this.size()];
		order(root, array, 0);
		return array;
	}
	
	private void order(BSTNode<T> node, T[] array, int i) {
		if(!node.isEmpty()) {
			preOrder((BSTNode<T>) node.getLeft(), array, i++);
			array[i] = node.getData();
			preOrder((BSTNode<T>)node.getRight(), array, i++);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] postOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		int index = 0;
		postOrder(root, array, index);
		return array;
	}

	private void postOrder(BSTNode<T> node, T[] array, int i) {
		if(!node.isEmpty()) {
			postOrder((BSTNode<T>) node.getLeft(), array, ++i);
			postOrder((BSTNode<T>)node.getRight(), array, ++i);
			array[i] = node.getData();
		}
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}
	
	public static void main(String[] args) {
		BSTImpl<Integer> tree = new BSTImpl<>();
		tree.insert(50);
		tree.insert(100);
		System.out.println(Arrays.toString(tree.preOrder()));
		System.out.println(tree.size());
		
	}

}