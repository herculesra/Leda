package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	public RecursiveSingleLinkedListImpl(T data,
			RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		return data == null;
	}

	@Override
	public int size() {
		int result = 0;
		if(!isEmpty()) {
			result = 1 + next.size();
		}
		return result;
	}

	@Override
	public T search(T element) {
		T result = null;
		if(element != null && !isEmpty()) {
			if(this.data.equals(element)) {
				result = this.data;
			}
			else {
				result = getNext().search(element);
			}	
		}
		return result;
	}

	@Override
	public void insert(T element) {
		if(isEmpty()) {
			data = element;
			next = new RecursiveSingleLinkedListImpl<>();
		}else {
			getNext().insert(element);
		}
	}

	@Override
	public void remove(T element) {
		if(!isEmpty() && element != null) { // Contra pegadinhas
			if(data.equals(element)) {
				setData(getNext().getData());
				setNext(getNext().getNext());
			}else {
				getNext().remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		int size = size();
		T[] result = (T[]) new Object[size];
		if(size > 0) {
			paraArray(result, this, 0);
		}
		return result;
	}
	
	public void paraArray(T[] result, RecursiveSingleLinkedListImpl<T> next, int index) {
		if(!next.isEmpty()) {
			result[index] = next.data;
			paraArray(result, next.next, ++index);
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
