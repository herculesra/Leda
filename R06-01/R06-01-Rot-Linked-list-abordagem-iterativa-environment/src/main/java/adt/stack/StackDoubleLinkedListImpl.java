package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;
import adt.linkedList.SingleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if(!isFull()) {
			top.insertFirst(element);
		} else {
			throw new StackOverflowException();
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if(!isEmpty()) {
			T result = ((SingleLinkedListImpl<T>) top).getHead().getData();
			top.removeFirst();
			return result;
		}
		throw new StackUnderflowException();
	}

	@Override
	public T top() {
		T result = null;
		if(!isEmpty()) {
			result = ((SingleLinkedListImpl<T>) top).getHead().getData();
		}
		return result;
	}

	@Override
	public boolean isEmpty() {
		return ((SingleLinkedListImpl<T>) top).getHead().isNIL();
	}

	@Override
	public boolean isFull() {
		return size == top.size();
	}
}
