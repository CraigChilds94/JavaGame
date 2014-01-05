package list;

public class Node <T> {
	
	public Node<T> prev, next;
	private T data;
	
	public Node(T data, Node<T> prev, Node<T> next) {
		this.data = data;
		this.prev = prev;
		this.next = next;
	}
	
	public T getData() {
		return data;
	}
}
