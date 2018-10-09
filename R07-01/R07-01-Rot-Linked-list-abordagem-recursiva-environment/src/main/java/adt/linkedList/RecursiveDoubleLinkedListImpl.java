package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	public RecursiveDoubleLinkedListImpl(T data,
			RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}
	
	
	@Override
	public void insert(T element) {
		if(element != null && isEmpty()) {
			this.data = element;
			this.next = new RecursiveDoubleLinkedListImpl<>();
			if(previous == null) {
				this.previous = new RecursiveDoubleLinkedListImpl<>();
			}
		}else {
			next.insert(element);
		}
	}
	
	@Override
	public void remove(T element) {
		if(element != null && !isEmpty()) {
			if(this.data.equals(element)) {
				if(previous.isEmpty() && next.isEmpty()) {
					this.data = null;
					this.next = null;
					this.previous = null;
				}else {
					setData(getNext().getData());
					setNext(getNext().getNext());
					if(!getNext().isEmpty()) {
						setPrevious(this);
					}
				}
			}else {
				next.remove(element);
			}
		}
	}
	
	@Override
	public void insertFirst(T element) {
		if(element != null && isEmpty()) {
			this.data = element;
			this.next = new RecursiveDoubleLinkedListImpl<>();
			this.previous = new RecursiveDoubleLinkedListImpl<>();
		}else {
			RecursiveDoubleLinkedListImpl<T> newNode = new RecursiveDoubleLinkedListImpl<>();
			newNode.data = this.data;
			newNode.next = this.next;
			newNode.previous = this;
			((RecursiveDoubleLinkedListImpl<T>) newNode.next).previous = newNode;
			this.data = element;
			this.next = newNode;
		}
	}

	@Override
	public void removeFirst() {
		if (!this.isEmpty()) {
			this.data = next.data;
			if(!this.next.isEmpty()) {
				((RecursiveDoubleLinkedListImpl<T>) this.next.next).previous = this;
				this.next = this.next.next;
			}
		}
	}

	@Override
	public void removeLast() {
		if(!this.next.isEmpty()) {
			((RecursiveDoubleLinkedListImpl<T>) this.next).removeLast();
		}else {
			this.data = null;
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
