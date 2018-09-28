package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;
	private boolean recebeu;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
		recebeu = false;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(!isFull()) {
			array[++ tail] = element;
			elements ++;
			if(tail == array.length -1) {
				tail = (tail % (array.length -1)) - 1;
			}
			if(!recebeu) { // So irá entrar aqui uma unica vez em todo o procedimento da fila. 
							//Caso inicial quando adiciona o primeiro elemento
				head ++;
				recebeu = true;
			}
		}else {
			throw new QueueOverflowException();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T result = array[head];
		if(!isEmpty()) {
			elements --;
			if(head == array.length -1) {
				head = (head % (array.length -1));
			}else {
				head ++;
			}
		}else {
			throw new QueueUnderflowException();
		}
		return result;
	}

	@Override
	public T head() {
		T result = null;
		if(!isEmpty()) {
			result = array[head];
		}
		return result;
	}

	@Override
	public boolean isEmpty() {
		return elements == 0;
	}

	@Override
	public boolean isFull() {
		boolean result = false;
		if(tail == array.length -1 && head == 0) { // caso em que a cabeca esta no comeco e o tail chegou no tamanho total da fila
			result = true;
		}
		if(tail + 1 == head) { // caso quando a fila esta cheia onde o proximo elemento e a cabeca
			result = true;
		}
		return result;
	}

}
