package list;

public class LinkedList <T> {

	private Node<T> head, tail, selected;
	
	public LinkedList(Node<T> head) {
		this.head = head;
		this.tail = head;
		this.selected = tail;
	}
	
	public LinkedList(T[] nodes) {
		this.head = (Node<T>) nodes[0];
		tail = head;
		selected = head; 
		for(int i = 1; i < nodes.length; i++) {
			tail.next = (Node<T>) nodes[i];
		}
	}
	
	public void add(T data) {
		tail.next = new Node<T>(data, tail, null);
		tail = tail.next;
	}
	
	public void remove(int i) {
		Node<T> current = head;
		int index = 0;
		while(current.next != null) {
			
			if(index == i) {
				current.prev.next = current.next;
				break;
			}
			
			index++;
			current = current.next;
		}
	}
	
	public int size() {
		Node<T> current = head;
		int i = 0;
		while(current.next != null) {
			i++;
			current = current.next;
		}
		
		return i;
	}
	
	public Node<T> getCurrent() {
		return selected;
	}
	
	public Node<T> getNext() {
		if(selected != null) {
			selected = selected.next;
		}
		return selected;
	}
	
	public Node<T> getHead() {
		return head;
	}
	
	public Node<T> getTail() {
		return tail;
	}
}
