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
		while(!aux.isNIL()) {
			size ++;
			aux = aux.getNext();
		}
		return size;
	}
	
	@Override
	public T search(T element) {
		T result = null;
		
		SingleLinkedListNode<T> aux = this.head;
		while(result != element && !aux.getNext().isNIL()) {
			result = aux.getNext().getData() ;				
			aux = aux.getNext();
		}
		return result;
	}

	@Override
	public void insert(T element) {
		if(isEmpty()) {
			this.head.setData(element);
			this.head.setNext(new SingleLinkedListNode<T>());
		}else {
			SingleLinkedListNode<T> aux = head;
			while(!aux.getNext().isNIL()) {
				aux = aux.getNext();
			}
			SingleLinkedListNode<T> newNode = new SingleLinkedListNode<>(element, new SingleLinkedListNode<>());
			aux.setNext(newNode);
		}
	}

	@Override
	public void remove(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented!");
	}

	@Override
	public T[] toArray() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented!");
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
