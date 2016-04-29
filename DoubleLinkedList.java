package listClasses;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BasicDoubleLinkedList<T> extends java.lang.Object implements
java.lang.Iterable<T> {
	protected Node<T> head; // protected
	protected Node<T> tail; // protected
	protected int size; // protected

	public BasicDoubleLinkedList() {
		size = 0;
		head = null;
		tail = null;
	}

	public int getSize() {
		return size;
	}

	public BasicDoubleLinkedList<T> addToEnd(T data) {
		if (size == 0) {
			// first element, make head and tail this element
			Node<T> first = new Node<T>(data);
			head = first;
			tail = first;
		} else {
			// already stuff in the list
			Node<T> newTail = new Node<T>(data);
			tail.next = newTail;
			newTail.prev = tail;
			tail = newTail;
		}
		size++;
		return this;
		/*
		 * Adds element to the end of the list. Do not use iterators to
		 * implement this method. Take care of the double references.
		 */
	}

	public BasicDoubleLinkedList<T> addToFront(T data) {
		if (size == 0) {
			// first element
			Node<T> first = new Node<T>(data);
			head = first;
			tail = first;
		} else {
			// already stuff in list
			Node<T> newHead = new Node<T>(data);
			head.prev = newHead;
			newHead.next = head;
			head = newHead;
		}
		size++;
		return this;
		/*
		 * Adds element to the front of the list. Do not use iterators to
		 * implement this method. Take care of the double references.
		 */
	}

	public BasicDoubleLinkedList<T> add(int index, T data) {
		if (index == 0) {
			// you want to add it to the front
			addToFront(data);
			return this;
		} else if (index >= 0 && index < size) {
			// you want to add it somewhere in the middle
			Node<T> curr = head;
			for (int i = 1; i < index; i++) {
				curr = curr.next;
			}
			// curr is the node before this index
			Node<T> add = new Node<T>(data);
			add.prev = curr;
			add.next = curr.next;
			curr.next = add;
			(add.next).prev = add;
			size++;
			return this;
		} else if (index == size) {
			// you want to add it to the end
			addToEnd(data);
			return this;
		} else {
			// NEED TO THROW EXCEPTION
			throw new IndexOutOfBoundsException();
		}
		/*
		 * Adds element at the index position in the list. Do not use iterators
		 * to implement this method. Zero indexing is to be used. Take care of
		 * the double references.
		 */
	}

	public T getFirst() {
		if (size == 0) {
			return null;
		} else {
			return head.data;
		}
		/*
		 * Returns but does not remove the first element from the list. If there
		 * are no elements the method returns null. Do not implement this method
		 * using iterators.
		 */
	}

	public T getLast() {
		if (size == 0) {
			return null;
		} else {
			return tail.data;
		}
		/*
		 * Returns but does not remove the last element from the list. If there
		 * are no elements the method returns null. Do not implement this method
		 * using iterators.
		 */
	}

	public T get(int index) {
		if (size == 0) {
			return null;
		} else if (index >= 0 && index < size) {
			Node<T> curr = head;
			for (int i = 1; i <= index; i++) {
				curr = curr.next;
			}
			return curr.data;
		} else {
			// THROW AN EXCPETION
			throw new IndexOutOfBoundsException();
		}
		/*
		 * Returns but does not remove the element at the index position in the
		 * list. If there are no elements the method returns null. Do not
		 * implement this method using iterators.
		 */
	}

	public T retrieveFirstElement() {
		if (size == 0) {
			return null;
		} else if (size == 1) {
			T returnThis = head.data;
			head = null;
			tail = null;
			size--;
			return returnThis;
		} else {
			T returnThis = head.data;
			head = head.next;
			head.prev = null;
			size--;
			return returnThis;
		}
		/*
		 * Removes and returns the first element from the list. If there are no
		 * elements the method returns null. Do not implement this method using
		 * iterators.
		 */
	}

	public T retrieveLastElement() {
		if (size == 0) {
			return null;
		} else if (size == 1) {
			T returnThis = tail.data;
			tail = null;
			head = null;
			size--;
			return returnThis;
		} else {
			T returnThis = tail.data;
			tail = tail.prev;
			tail.next = null;
			size--;
			return returnThis;
		}
		/*
		 * Removes and returns the last element from the list. If there are no
		 * elements the method returns null. Do not implement implement this
		 * method using iterators.
		 */
	}

	public T retrieve(int index) {
		if (size == 0) {
			return null;
		} else if (index >= 0 && index < size) {
			if (index == 0) {
				T data = retrieveFirstElement();
				return data;
			} else if (index == size - 1) {
				T data = retrieveLastElement();
				return data;
			} else {
				Node<T> curr = head;
				for (int i = 1; i <= index; i++) {
					curr = curr.next;
				}
				(curr.prev).next = curr.next;
				(curr.next).prev = curr.prev;
				size--;
				return curr.data;
			}
		} else {
			throw new IndexOutOfBoundsException();
		}

		/*
		 * Removes and returns the element at the index position from the list
		 * (zero indexed). If there are no elements the method returns null. Do
		 * not implement implement this method using iterators.
		 */
	}

	public BasicDoubleLinkedList<T> remove(T targetData,
			java.util.Comparator<T> comparator) {
		Node<T> curr = head;
		while (curr != null) {
			T data1 = curr.data;
			if (comparator.compare(data1, targetData) == 0) {
				if (size == 1) {// only thing in the list
					head = null;
					tail = null;
					curr = null;
				} else if (curr == head) {// at head
					head = curr.next;
					head.prev = null;
					curr = head;
				} else if (curr.next == null) {// at tail
					tail = tail.prev;
					tail.next = null;
					curr = null;
				} else {// everywhere else
					(curr.prev).next = curr.next;
					(curr.next).prev = curr.prev;
					curr = curr.next;
				}
				size--;
			} else {
				curr = curr.next;
			}

		}
		return this;
		/*
		 * Removes ALL instances of targetData from the list. Notice that you
		 * must remove the elements by performing a single traversal over the
		 * list. You may not use any of the other retrieval methods associated
		 * with the class in order to complete the removal process. You must use
		 * the provided comparator (do not use equals) to find those elements
		 * that match the target. Do not implement this method using iterators.
		 * Take care of the double references per node, i.e. the prev and next.
		 */
	}

	public java.util.Iterator<T> iterator() {
		Iterator<T> foo = new Iterator<T>() {
			private int index = 0;
			private Node<T> curr = head;
			private Node<T> lastAccessed;

			@Override
			public boolean hasNext() {
				return index < size;
			}

			@Override
			public T next() {
				if (curr == null && lastAccessed == null) {
					curr = head;
				}
				if (hasNext() == true) {
					lastAccessed = curr;
					T item = curr.data;
					curr = curr.next;
					index++;
					return item;
				} else {
					throw new NoSuchElementException();
				}
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
		return foo;
	}

	/*
	 * public class iter<T> implements Iterator<T>{ private int index=0; private
	 * Node<T> curr=(Node<T>) head;
	 * 
	 * @Override public boolean hasNext() { return index<size; }
	 * 
	 * @Override public T next() { System.out.println("in the next");
	 * System.out.println("head.data"+head.data);
	 * System.out.println("head "+head); System.out.println("curr "+curr);
	 * System.out.println("curr.data"+curr.data); if(hasNext()==true){ T
	 * item=curr.data; curr=curr.next; index++; return item; }else{ throw new
	 * NoSuchElementException(); }
	 * 
	 * }
	 * 
	 * }
	 */
}
