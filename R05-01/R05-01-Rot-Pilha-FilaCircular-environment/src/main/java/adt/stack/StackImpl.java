package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		array = (T[]) new Object[size];
		top = -1;
	}

	@Override
	public T top(){
		T result = null;
		if(top != -1) {
			result = array[top];
		}
		return result;
	}

	@Override
	public boolean isEmpty() {
		return top == -1;
	}

	@Override
	public boolean isFull() {
		return top == array.length -1;
		
	}

	@Override
	public void push(T element) throws StackOverflowException {
		try {
			array[++ this.top] = element;
		}catch(Exception e) {
			throw new StackOverflowException();
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if(isEmpty()) {
			throw new StackUnderflowException();
		}else {
			this.top --;
			return array[this.top + 1];
		}
	}

}
