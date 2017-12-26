/**
	A class to store a linked list of Strings.
**/
import java.util.ArrayList;
import java.util.Collections;


public class LinkedList {

	private Node head;
	private Node tail; 
	private int count;

	//Constructor to create a new list
	public LinkedList() {
		this.head = null;
		this.tail = null;
		this.count = 0;
	}

	public Node getHead() {
		return head;
	}
	/**
		Add a new item at the head of the list.
	**/
	public void addAtHead(String data) {

		//create a node
		Node newNode = new Node(data);

		if(head==null) 
		{
			newNode.setNext(head);
			tail = head = newNode;
			count++;
		}
		else {
		//set next reference of new node to the first item in the list
		newNode.setNext(head);
		//link together head reference and the new node
		head = newNode;
		//increment count
		count++;
		}
	}

		/**
		Add a new item at the tail of the list.
	**/
	public void addAtTail(String data) {

		//create a node
		Node newNode = new Node(data);

		if(head==null || tail==null) 
		{
			newNode.setNext(head);
			tail = head = newNode;
			count++;
		}
		else {
			tail.setNext(newNode);
			tail = newNode;
			count++;
		}
	}


	/**
		get data(String) at index
	**/


	public String get(int index) {
		Node positionNode = head;
		for(int i = 0; i < index-1; i++) 
		{
			
			positionNode = positionNode.getNext(); //traverses

		}
		return positionNode.getData();
	}

	/**
		toString method
	**/
	public String toString() {
		return toString(head);
	}

	public String toString(Node n) {

		//base case
		if(n == null) { 
			return "";
		}

		String rest = toString(n.getNext());
		if(n.getNext() == null) 
		{
			return n.getData() + rest;	
		} //calls again
		return n.getData() + "\n" + rest; //attatches values 	

	}
	/**
		Returns size of LinkedList
	**/

	public int size() {
		Node cur = head;
		int size = 0;
		while(cur != null) //while not at end of list
		{ 
			size++;
			cur = cur.getNext();
		}
		return size;
	}
}