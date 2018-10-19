package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionOpenAddress;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if(!isFull()) {
			if(element != null && search(element) == null) {
				 boolean collision = true;
		               int i = 0;
		               while (collision) {
		                  int index = ((HashFunctionOpenAddress<T>) getHashFunction()).hash(element, i);
		                  if (table[index] == null || table[index].equals(super.deletedElement)) {
		                     table[index] = element;
		                     super.elements++;
		                     collision = false;
		                  } else {
		                     super.COLLISIONS++;
		                     i++;
		                  }
		               }       
			}
		}else {
			throw new HashtableOverflowException();
		}
	}
	@Override
	   public void remove(T element) {
	      if (element != null && !isEmpty()) {
	         int index = indexOf(element);
	         if (index != -1) {
	            table[index] = deletedElement;
	            super.elements--;
	         }
	      }
	   }

	   @SuppressWarnings("unchecked")
	   @Override
	   public T search(T element) {
	      T result = null;
	      if (element != null && !isEmpty()) {
	         int index = indexOf(element);
	         if (index != -1) {
	            result = (T) table[index];
	         }
	      }
	      return result;
	   }

	   @Override
	   public int indexOf(T element) {
	      int result = -1;
	      if (element != null && !isEmpty()) {
	         boolean achou = false;
	         int i = 0;
	         int index = ((HashFunctionOpenAddress<T>) getHashFunction()).hash(element, i);
	         while (!achou && table[index] != null && i < table.length) {
	            if (table[index].equals(element)) {
	               result = index;
	               achou = true;
	            } else {
	               i++;
	            }

	            index = ((HashFunctionOpenAddress<T>) getHashFunction()).hash(element, i);
	         }
	      }
	      return result;
	   }
}
