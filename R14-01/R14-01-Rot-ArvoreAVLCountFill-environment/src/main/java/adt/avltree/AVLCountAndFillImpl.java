package adt.avltree;

import java.util.List;
import java.util.LinkedList;
import adt.bst.BSTNode;
import adt.bt.Util;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends AVLTreeImpl<T> implements AVLCountAndFill<T> {

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
      if (array.length > 0) {
         sort(array, 0, array.length - 1);
         T[] arrayOrder = order().clone();
         cleanTree();
         insertBinaryForm(merge(array, arrayOrder), true, true);
      }
   }


private void insertBinaryForm(T[] arrayOrder, boolean direitoInserido, boolean esquerdoInserido) {
      if (arrayOrder.length <= 1) {
         insert(arrayOrder[0]);
      } else {
         int meio = (arrayOrder.length / 2);
         insert(arrayOrder[meio]);
         if(!esquerdoInserido) {
        	 esquerdoInserido = true;
        	 direitoInserido = false;
        	 T[] newArray1 = (T[]) new Comparable[arrayOrder.length - (meio + 1)];
        	 for (int i = 0; i < meio; i++) {
        		 newArray1[i] = arrayOrder[i];
        	 }
        	 insertBinaryForm(newArray1, direitoInserido, esquerdoInserido);
         }
         if(!direitoInserido) {
        	 direitoInserido = true;
        	 esquerdoInserido = false;
        	 T[] newArray2 = (T[]) new Comparable[arrayOrder.length - (meio + 1)];
        	 int j = 0;
        	 for (int i = meio + 1; i < arrayOrder.length; i++) {
        		 newArray2[j] = arrayOrder[i];
        		 j++;
        	 }
        	 insertBinaryForm(newArray2, direitoInserido, esquerdoInserido);
         }
      }

   }

   private T[] merge(T[] array, T[] arrayOrder) { //concatena os 2 arrays e ordena em 1 unico
      T[] arrayAux = (T[]) new Comparable[array.length + arrayOrder.length];
      for (int i = 0; i < array.length; i++) {
         arrayAux[i] = array[i];
      }
      int j = 0;
      for (int i = array.length; i < arrayAux.length; i++) {
         arrayAux[i] = arrayOrder[j];
         j++;
      }

      sort(arrayAux, 0, arrayAux.length - 1);

      return arrayAux;
   }

   private void cleanTree() {
      root.setLeft(new BSTNode<T>());
      root.setRight(new BSTNode<T>());
      root.setData(null);
      root.setParent(null);
   }

   //Utilizacao do mergesort para ordenar o array inicialmente
   private void sort(T[] array, int leftIndex, int rightIndex) {
      if (leftIndex < rightIndex) {
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

      for (int i = 0; i <= n1; i++) {
         leftArray.add(array[leftIndex + i]);
      }

      for (int j = 0; j < n2; j++) {
         rightArray.add(array[middleIndex + 1 + j]);
      }

      int i = 0;
      int j = 0;
      int k = leftIndex;

      while (i <= n1 && j < n2) {
         if (leftArray.get(i).compareTo(rightArray.get(j)) < 0) {
            array[k] = leftArray.get(i);
            i++;
         } else {
            array[k] = rightArray.get(j);
            j++;
         }
         k++;
      }

      while (i <= n1) {
         array[k] = leftArray.get(i);
         i++;
         k++;
      }

      while (j < n2) {
         array[k] = rightArray.get(j);
         j++;
         k++;
      }
   }

   @Override
   protected void rebalance(BSTNode<T> node) {
      int fb = calculateBalance(node);
      if (Math.abs(fb) > 1) {
         boolean rotacionNode = fb < 0; // HEAVYCHILD IN RIGHT CHILD INDICATE TRUE
         // ELSE HEAVY IN LEFT CHILD INDICATE FALSE
         boolean rotacionSimple; // SIMPLE INDICATE TRUE
         // DUPLE INDICATE FALSE
         boolean rotacionRR = rotacionNode && node.getRight() != null
               && calculateBalance((BSTNode<T>) node.getRight()) <= 0;
         boolean rotacionLL = !rotacionNode && node.getLeft() != null
               && calculateBalance((BSTNode<T>) node.getLeft()) >= 0;
         rotacionSimple = rotacionRR || rotacionLL;

         BSTNode<T> newNode;
         if (rotacionSimple) { // SIMPLE ROTATION
            if (rotacionNode) {
               newNode = Util.leftRotation(node); // weighing right (CASE RR). ROTATION FOR LEFT
               RRcounter++;
            } else {
               newNode = Util.rightRotation(node); // weighing left (CASE LL). ROTATION FOR RIGHT 
               LLcounter++;
            }
         } else { // DUPLE ROTATION
            if (rotacionNode) {
               // weighing right (CASE RL). ROTATION CHILD FOR RIGHT AND FATHER FOR LEFT
               node.setRight(Util.rightRotation((BSTNode<T>) node.getRight()));
               newNode = Util.leftRotation(node);
               RLcounter++;
            } else {
               // weighing left (CASE LR). ROTATION CHILD FOR LEFT AND FATHER FOR RIGHT
               node.setLeft(Util.leftRotation((BSTNode<T>) node.getLeft()));
               newNode = Util.rightRotation(node);
               LRcounter++;
            }
         }
         if (node == getRoot()) {
            root = newNode;
            newNode.setParent(null);
         } else {
            boolean child = newNode.getParent().getRight() == node; // NEWNODE IS RIGHT CHILD
            // ELSE NEWNODE IS LEFT CHILD
            if (child)
               newNode.getParent().setRight(newNode);
            else
               newNode.getParent().setLeft(newNode);
         }
      }
   }

}
