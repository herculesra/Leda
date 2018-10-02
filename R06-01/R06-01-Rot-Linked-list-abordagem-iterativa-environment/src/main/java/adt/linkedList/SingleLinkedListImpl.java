package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

   protected SingleLinkedListNode<T> head;

   public SingleLinkedListImpl() {
      this.head = new SingleLinkedListNode<T>();
   }

   @Override
   public boolean isEmpty() {
      return this.head.isNIL();
   }

   @Override
   public int size() {
      int size = 0;
      SingleLinkedListNode<T> aux = head;
      while (!aux.isNIL()) {
         size++;
         aux = aux.getNext();
      }
      return size;
   }

   @Override
   public T search(T element) {
      T result = null;
      SingleLinkedListNode<T> aux = this.head;
      while (!aux.isNIL()) {
         if (aux.getData().equals(element)) {
            result = aux.getData();
         }
         aux = aux.getNext();
      }
      return result;
   }

   @Override
   public void insert(T element) {
      if (isEmpty()) {
         this.head.setData(element);
         this.head.setNext(new SingleLinkedListNode<T>());
      } else {
         SingleLinkedListNode<T> aux = head;
         while (!aux.getNext().isNIL()) {
            aux = aux.getNext();
         }
         SingleLinkedListNode<T> newNode = new SingleLinkedListNode<>(element, new SingleLinkedListNode<>());
         aux.setNext(newNode);
      }
   }

   @Override
   public void remove(T element) {
      if (head.getData().equals(element)) {
         setHead(head.next);
      } else {
         SingleLinkedListNode<T> aux = head;
         SingleLinkedListNode<T> previous = aux;
         while (!aux.isNIL() && !aux.getData().equals(element)) {
            previous = aux;
            aux = aux.getNext();
         }
         if (!aux.isNIL()) {
            previous.next = aux.next;
         }
      }
   }

   @Override
   public T[] toArray() {
      SingleLinkedListNode<T> aux = head;
      T[] result = (T[]) new Object[size()];
      int index = 0;
      while (!aux.isNIL()) {
         result[index] = aux.data;
         aux = aux.getNext();
         index++;
      }
      return result;
   }

   public SingleLinkedListNode<T> getHead() {
      return head;
   }

   public void setHead(SingleLinkedListNode<T> head) {
      this.head = head;
   }

}
