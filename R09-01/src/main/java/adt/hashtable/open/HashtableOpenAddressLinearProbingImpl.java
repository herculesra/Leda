package adt.hashtable.open;

import org.hamcrest.core.IsInstanceOf;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;
import adt.hashtable.hashfunction.HashFunctionOpenAddress;
import adt.hashtable.hashfunction.HashFunctionOpenAddressMethod;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
		AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size,
			HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if(!isFull()) {
			if(element != null) {
				boolean collision = true;{
					int i = 0;
					while(collision){
						int index = ((HashFunctionOpenAddress<T>) getHashFunction()).hash(element, i);
						if(table[index] == null || table[index].equals(super.deletedElement)) {
							table[index] = element;
							super.elements ++;
							collision = false;
						}else {
							super.COLLISIONS ++;
							i++;
						}	
					}
				}	
			}
		}else {
			throw new HashtableOverflowException();
		}
	}

	@Override
	public void remove(T element) {
		if(element != null && !isEmpty()) {
			int index = indexOf(element);
			if(index != -1) {
				table[index] = deletedElement;
				super.elements --;
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T search(T element) {
		T result = null;
		if(element != null && !isEmpty()) {
			int index = indexOf(element);
			if(index != -1) {
				result = (T) table[index];
			}
		}
		return result;
	}

	@Override
	public int indexOf(T element) {
		int result = -1;
		if(element != null && !isEmpty()) {
			boolean achou = false;
			int i = 0;
			int index = ((HashFunctionOpenAddress<T>) getHashFunction()).hash(element, i);
			while(!achou && table[index] != null && i < table.length) {
				if(table[index].equals(element)) {
					result = index;
					achou = true;
				}else {
					i++;
				}
				
				index = ((HashFunctionOpenAddress<T>) getHashFunction()).hash(element, i);
			}
		}
		return result;
	}
}
