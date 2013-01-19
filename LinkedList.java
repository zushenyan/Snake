package snake;

import java.awt.Point;

public class LinkedList {
	private Node m_first;
	private Node m_last;
	private int m_length;
	
	public LinkedList(){
		this.m_length = 0;
	}
	
	public void appendToHead(Object data){
		if(this.m_length == 0){
			this.m_first = new Node(null, null, data);
			this.m_last = this.m_first;
		}
		else if(this.m_length == 1){
			this.m_first = new Node(null, this.m_last, data);
			this.m_last.setPreviousNode(this.m_first);
		}
		else{
			Node temp = this.m_first;
			this.m_first = new Node(null, temp, data);
			temp.setPreviousNode(this.m_first);
		}
		
		this.m_length++;
	}
	
	public void appendToTail(Object data){
		if(this.m_length == 0){
			this.m_last = new Node(null, null, data);
			this.m_first = this.m_last;
		}
		else if(this.m_length == 1){
			this.m_last = new Node(this.m_first, null, data);
			this.m_first.setNextNode(this.m_last);
		}
		else{
			Node temp = this.m_last;
			this.m_last = new Node(temp, null, data);
			temp.setNextNode(this.m_last);
		}
		
		this.m_length++;
	}
	
	public Node getFirstNode(){
		return this.m_first;
	}
	
	public Node getLastNode(){
		return this.m_last;
	}
	
	public int getLength(){
		return this.m_length;
	}
}
