package adt.rbtree;

import adt.bst.BSTImpl;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements RBTree<T> {

   public RBTreeImpl() {
      this.root = new RBNode<T>();
   }

   protected int blackHeight() {
      int result = 0;
      RBNode<T> aux = (RBNode<T>) root;

      while (!aux.isEmpty()) {
         if (aux.getColour() == Colour.BLACK) {
            result++;
         }
         aux = (RBNode<T>) aux.getLeft();
      }

      return result;
   }

   protected boolean verifyProperties() {
      boolean resp = verifyNodesColour() && verifyNILNodeColour() && verifyRootColour() && verifyChildrenOfRedNodes()
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
      return verifyChildrenOfRedNodes((RBNode<T>) root);
   }

   private boolean verifyChildrenOfRedNodes(RBNode<T> node) {
      boolean result = true;
      if (!node.isEmpty()) {
         if (node.getColour() == Colour.RED) {
            RBNode<T> left = (RBNode<T>) node.getLeft();
            RBNode<T> right = (RBNode<T>) node.getRight();

            if (left.getColour() == Colour.RED || right.getColour() == Colour.RED) {
               result = false;
            }
         }
         result = (verifyChildrenOfRedNodes((RBNode<T>) node.getLeft()) && verifyChildrenOfRedNodes((RBNode<T>) node
               .getRight()));

      }
      return result;
   }

   /**
    * Verifies the black-height property from the root.
    */
   private boolean verifyBlackHeight() {
      int left = verifyBlackHeight((RBNode<T>) this.root.getLeft(), 0);
      int right = verifyBlackHeight((RBNode<T>) this.root.getLeft(), 0);
      return left == right;
   }

   private int verifyBlackHeight(RBNode<T> node, int i) {
      if (node != null && node.isEmpty()) {
         if (node.getColour() == Colour.BLACK) {
            i += 1;
         }
         return Math.max(verifyBlackHeight((RBNode<T>) node.getLeft(), i),
               verifyBlackHeight((RBNode<T>) node.getRight(), i));
      }
      return i + 1;
   }

   @Override
   public void insert(T value) {
      RBNode<T> node = this.insert((RBNode<T>) this.root, value, new RBNode<>());
      node.setColour(Colour.RED);
      this.fixUpCase1(node);
   }

   private RBNode<T> insert(RBNode<T> node, T element, RBNode<T> parent) {
      if (node.isEmpty()) {
         node.setData(element);
         node.setLeft(new RBNode<T>());
         node.setRight(new RBNode<T>());
         node.setParent(parent);
         return node;
      } else if (element.compareTo(node.getData()) < 0) {
         return this.insert((RBNode<T>) node.getLeft(), element, node);
      } else if (element.compareTo(node.getData()) > 0) {
         return this.insert((RBNode<T>) node.getRight(), element, node);
      }
      return null;
   }

   @Override
   public RBNode<T>[] rbPreOrder() {
      RBNode<T>[] array = new RBNode[] {};
      if (size() > 0) {
         array = new RBNode[size()];
         int index = 0;
         rbPreOrder(array, index, (RBNode<T>) root);
      }
      return array;
   }

   private int rbPreOrder(RBNode<T>[] array, int index, RBNode<T> node) {
      if (!node.isEmpty()) {
         array[index] = node;
         index++;
         index = rbPreOrder(array, index, (RBNode<T>) node.getLeft());
         index = rbPreOrder(array, index, (RBNode<T>) node.getRight());
         return index;
      } else {
         return index;
      }
   }

   // FIXUP methods
   protected void fixUpCase1(RBNode<T> node) {
      if (node == root) {
         node.setColour(Colour.BLACK);
      } else {
         fixUpCase2(node);
      }
   }

   protected void fixUpCase2(RBNode<T> node) {
      if (!(((RBNode<T>) node.getParent()).getColour() == Colour.BLACK)) {
         fixUpCase3(node);
      }
   }

   protected void fixUpCase3(RBNode<T> node) {
      RBNode<T> avo = (RBNode<T>) node.getParent().getParent();
      RBNode<T> pai = (RBNode<T>) node.getParent();
      RBNode<T> tio = (RBNode<T>) node.getParent().getParent().getRight();

      if (tio.getColour() == Colour.RED) {
         pai.setColour(Colour.BLACK);
         tio.setColour(Colour.BLACK);
         avo.setColour(Colour.RED);
         fixUpCase1(avo);
      } else {
         fixUpCase4(node);
      }
   }

   protected void fixUpCase4(RBNode<T> node) {
      RBNode<T> next = node;
      RBNode<T> nodeFilhoDireito = (RBNode<T>) node.getParent().getRight();
      RBNode<T> nodeFilhoEsquerdo = (RBNode<T>) node.getParent().getLeft();
      RBNode<T> paiFilhoEsquerdo = (RBNode<T>) node.getParent().getParent().getLeft();
      RBNode<T> paiFilhoDireito = (RBNode<T>) node.getParent().getParent().getRight();
      RBNode<T> pai = (RBNode<T>) node.getParent();

      if (node == nodeFilhoDireito && pai == paiFilhoEsquerdo) { // node eh filho direito e pai eh filho esquerdo
         Util.leftRotation(pai);
         next = (RBNode<T>) next.getLeft();
      } else {
         if (node == nodeFilhoEsquerdo && pai == paiFilhoDireito) { // node eh filho esquerdo e pai eh filho direito
            Util.rightRotation(pai);
            next = (RBNode<T>) next.getRight();
         }
      }

      fixUpCase5(next);
   }

   protected void fixUpCase5(RBNode<T> node) {
      RBNode<T> pai = (RBNode<T>) node.getParent();
      RBNode<T> avo = (RBNode<T>) node.getParent().getParent();
      RBNode<T> nodeFilhoEsquerdo = (RBNode<T>) node.getParent().getLeft();
      pai.setColour(Colour.BLACK);
      avo.setColour(Colour.RED);

      if (node == nodeFilhoEsquerdo) {
         Util.rightRotation(avo);
      } else {
         Util.leftRotation(avo);
      }
   }
}
