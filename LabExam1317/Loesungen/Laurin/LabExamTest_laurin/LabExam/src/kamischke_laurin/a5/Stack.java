package kamischke_laurin.a5;

public class Stack<T> implements Stack_I<T> {

	private Node<T> top;

	public Stack() {
	}

	@Override
	public void push(T data) {
		if (top != null) {
		Node<T> second = top;
		top = new Node<T>(data);
		top.setPrev(second);
		} else {
			top = new Node<T>(data);
		}
	}

	@Override
	public T pop() {
		assert (top != null);

		Node<T> temp = top;
		if (top.getPrev() != null) {
			Node<T> second = top.getPrev();
			top.setPrev(null);
			top = second;
		}
		else {
			top = null;
		}
		return temp.getData();
	}

	@Override
	public T top() {
		assert (top != null);
		return top.getData();
	}

	@Override
	public void clear() {
		while(top != null) {
			Node temp = top;
			top = top.getPrev();
			temp.setPrev(null);
		}
	}

	@Override
	public boolean isEmpty() {
		if (top == null) return true;
		return false;
	}



}
