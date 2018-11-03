package adt.bst;

import java.util.Arrays;

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
      if (node.isEmpty()) {
         return -1;
      } else {
         return 1 + Math.max(getHeightTree((BSTNode<T>) node.getLeft()), getHeightTree((BSTNode<T>) node.getRight()));
      }
   }

   @Override
   public BSTNode<T> search(T element) {
      BSTNode<T> result = new BSTNode<>();
      if (element != null) {
         result = search(element, root);
      }
      return result;
   }

   private BSTNode<T> search(T element, BSTNode<T> node) {
      BSTNode<T> result = new BSTNode<>();
      if (!node.isEmpty()) {
         if (node.getData().equals(element)) {
            result = node;
         } else if (element.compareTo(node.getData()) < 0) {
            result = search(element, (BSTNode<T>) node.getLeft());
         } else if (element.compareTo(node.getData()) > 0) {
            result = search(element, (BSTNode<T>) node.getRight());
         }
      }
      return result;
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
   }

   @Override
   public BSTNode<T> maximum() {
      if (size() == 0) {
         return null;
      }
      return maximum(this.root);
   }

   private BSTNode<T> maximum(BSTNode<T> node) {
      if (node.getRight().isEmpty()) {
         return node;
      }
      return maximum((BSTNode<T>) node.getRight());
   }

   @Override
   public BSTNode<T> minimum() {
      if (size() == 0) {
         return null;
      }
      return minimum(this.root);
   }

   private BSTNode<T> minimum(BSTNode<T> node) {
      if (node.getLeft().isEmpty()) {
         return node;
      }
      return minimum((BSTNode<T>) node.getLeft());
   }

   @Override
   public BSTNode<T> sucessor(T element) {
      BSTNode<T> elementNode = search(element);
      BSTNode<T> result = null;
      if (!elementNode.isEmpty()) {
         if (!elementNode.getRight().isEmpty()) {
            result = minimum((BSTNode<T>) elementNode.getRight());
         } else {
            BSTNode<T> parent = (BSTNode<T>) elementNode.getParent();
            BSTNode<T> actual = elementNode;
            while (parent != null && actual.equals(parent.getRight())) {
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
      if (element != null) {
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
      if (!node.isEmpty()) {
         remove(node);
      }
   }

   private void remove(BSTNode<T> node) {
      if (!node.isEmpty()) {
         if (node.isLeaf()) {
            node.setData(null);
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
            } else {
               if (node.getRight().isEmpty()) {
                  root = (BSTNode<T>) node.getLeft();
               } else {
                  root = (BSTNode<T>) node.getRight();
               }
            }
         } else {
            BSTNode<T> sucessor = sucessor(node.getData());
            node.setData(sucessor.getData());
            remove(sucessor);
         }
      }
   }

   @Override
	public T[] preOrder() {
		T[] array = (T[]) new Comparable[] {};

		if (size() > 0) {
			array = (T[]) new Comparable[size()];
			int index = 0;
			preOrder(array, index, root);		 
		}   
		return array;
	}

   private int preOrder(T[] array, int index, BSTNode<T> node) {
      if (!node.isEmpty()) {
         array[index] = node.getData();
         index++;
         index = preOrder(array, index, (BSTNode<T>) node.getLeft());
         index = preOrder(array, index, (BSTNode<T>) node.getRight());
         return index;
      } else {
         return index;
      }
   }

   @Override
	public T[] order() {
		T[] array = (T[]) new Comparable[] {};

		if (size() > 0) {
			array = (T[]) new Comparable[size()];
			int index = 0;
			order(array, index, root);		 
		}   
		return array;
	}

   private int order(T[] array, int index, BSTNode<T> node) {
      if (!node.isEmpty()) {
         index = order(array, index, (BSTNode<T>) node.getLeft());
         array[index] = node.getData();
         index++;
         index = order(array, index, (BSTNode<T>) node.getRight());
         return index;
      } else {
         return index;
      }
   }

   @Override
	public T[] postOrder() {
		T[] array = (T[]) new Comparable[] {};

		if (size() > 0) {
			array = (T[]) new Comparable[size()];
			int index = 0;
			postOrder(array, index, root);		 
		}   
		return array;
	}

   private int postOrder(T[] array, int index, BSTNode<T> node) {
      if (!node.isEmpty()) {
         index = postOrder(array, index, (BSTNode<T>) node.getLeft());
         index = postOrder(array, index, (BSTNode<T>) node.getRight());
         array[index] = node.getData();
         index++;
         return index;
      } else {
         return index;
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
         result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
      }
      return result;
   }
}