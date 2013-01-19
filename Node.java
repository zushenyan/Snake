package snake;

import java.awt.Point;

public class Node {
	private Node m_nextNode;
	private Node m_previousNode;
	private Object m_data;
	
	public Node(){
		this(null, null);
	}
	
	public Node(Node previous, Node next){
		this(previous, next, null);
	}
	
	public Node(Node previous, Node next, Object o){
		this.m_previousNode = previous;
		this.m_nextNode = next;
		this.setData(o);
	}
	
	public void setPreviousNode(Node n){
		this.m_previousNode = n;
	}
	
	public void setNextNode(Node n){
		this.m_nextNode = n;
	}
	
	public Node getPreviousNode(){
		return this.m_previousNode;
	}
	
	public Node getNextNode(){
		return this.m_nextNode;
	}
	
	public void setData(Object o){
		this.m_data = o;
	}
	
	public Object getData(){
		return this.m_data;
	}
}
