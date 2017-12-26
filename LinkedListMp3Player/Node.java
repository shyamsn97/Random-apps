/**
	A class to represent a linked list node
	containing an String.
**/
public class Node {

	private String data;
	private Node next;

	public Node(String data, Node next) {
		this.data = data;
		this.next = next;
	}

	public Node(String data) {
		this(data, null);
	}

	public String getData() {
		return this.data;
	}

	public Node getNext() {
		return this.next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

}