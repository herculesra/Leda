package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;
	
	
	public DoubleLinkedListImpl() {
		head = new DoubleLinkedListNode<>();
		last =  (DoubleLinkedListNode<T>) head;
	}
	
	@Override
	public T search(T element) {
		T result = null;
		DoubleLinkedListNode<T> auxHead = (DoubleLinkedListNode<T>) head;
		DoubleLinkedListNode<T> auxLast = last;
		
		while(!auxHead.getNext().equals(auxLast) && !auxHead.equals(auxLast) && 
				!auxHead.data.equals(element) && !auxLast.data.equals(element)) {
			auxHead = (DoubleLinkedListNode<T>) auxHead.next;
			auxLast = auxLast.previous;
		}
		if(auxHead.data.equals(element)) {
			result = auxHead.data;
		}
		if(auxLast.data.equals(element)) {
			result = auxLast.data;
		}
		
		return result;
	}
	
	@Override
	public void insert(T element) {
		DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>();
		newHead.setData(element);
		newHead.setNext(head);
		newHead.setPrevious(new DoubleLinkedListNode<>());
		if(head.isNIL()) {
			last = newHead;
		}
		head = newHead;
	}
	
	@Override
	public void insertFirst(T element) {
		insert(element);
	}

	@Override
	public void removeFirst() {
		if(!head.isNIL()) {
			head = head.next;
			if(head.isNIL()) {
				last = (DoubleLinkedListNode<T>) head;
			}
		}
		head = new DoubleLinkedListNode<>(); //Att
	}

	@Override
	public void removeLast() {
		if(!last.isNIL()) {
			last = last.previous;
			if(last.isNIL()){
				head = last;
			}
			last.next = new DoubleLinkedListNode<>();
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
