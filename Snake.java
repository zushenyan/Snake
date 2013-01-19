package snake;

import java.awt.Point;

public class Snake {
	public static final int DEAD = -1;
	public static final int ALIVE = 0;
	public static final int INVALID_VALUE = 1;
	
	private LinkedList m_body;
	private int m_sizeLeft;
	private int m_state;
	
	public Snake(SnakePoint startPoint){
		this(startPoint, 1);
	}
	
	public Snake(SnakePoint startPoint, int snakeSize){
		this.m_body = new LinkedList();
		this.m_body.appendToTail(startPoint);
		this.m_sizeLeft = snakeSize > 0 ? snakeSize : 1;
		this.setState(Snake.ALIVE);
	}
	
	public int move(){
		Node current;
		SnakePoint sp;
		int x, y, dir;
		
		if(this.isCollidedWithItself()){
			return Snake.DEAD;
		}
		
		//check remainder starting nodes first
		this.checkRemainderSize();
		
		//move body
		current = this.m_body.getLastNode();
		while(current.getPreviousNode() != null){
			sp = (SnakePoint)current.getPreviousNode().getData();
			x = (int)sp.getLocation().getX();
			y = (int)sp.getLocation().getY();
			dir = sp.getDirection();
			
			current.setData(new SnakePoint(new Point(x, y), dir));
			
			current = current.getPreviousNode();
		}
		
		//move head
		current = this.m_body.getFirstNode();
		sp = (SnakePoint)current.getData();
		x = (int) sp.getLocation().getX();
		y = (int) sp.getLocation().getY();
		switch(sp.getDirection()){
			case SnakePoint.DIRECTION_RIGHT:
				sp.setLocation(new Point(++x, y));
				break;
			case SnakePoint.DIRECTION_LEFT:
				sp.setLocation(new Point(--x, y));
				break;
			case SnakePoint.DIRECTION_UP:
				sp.setLocation(new Point(x, --y));
				break;
			case SnakePoint.DIRECTION_DOWN:
				sp.setLocation(new Point(x, ++y));
				break;
		}
		current.setData(sp);
		
		return Snake.ALIVE;
	}
	
	private void checkRemainderSize(){
		if(this.m_sizeLeft > 1){
			this.grow();
			this.m_sizeLeft--;
		}
	}
	
	private boolean isCollidedWithItself(){
		Node first = this.m_body.getFirstNode();
		SnakePoint sp = (SnakePoint)first.getData();
		Node current = first.getNextNode();
		SnakePoint csp;
		
		while(current != null){
			csp = (SnakePoint)current.getData();
			
			if(sp.getLocation().equals(csp.getLocation())){
				return true;
			}
			
			current = current.getNextNode();
		}
		
		return false;
	}
	
	public int turnDirection(int direction){
		Node head = this.getHead();
		Node next = head.getNextNode();
		SnakePoint sp = (SnakePoint) head.getData();
		SnakePoint nsp = (SnakePoint) next.getData();
		
		//check whether it is a invalid value or not
		if(direction != SnakePoint.DIRECTION_DOWN &&
				direction != SnakePoint.DIRECTION_LEFT &&
				direction != SnakePoint.DIRECTION_RIGHT &&
				direction != SnakePoint.DIRECTION_UP ||
				(sp.getDirection() + direction) == 0 ||
				(nsp.getDirection() + direction) == 0){
			return Snake.INVALID_VALUE;
		}
		
		sp.setDirection(direction);
		head.setData(sp);
		return direction;
	}
	
	public void grow(){
		SnakePoint previous = (SnakePoint) this.m_body.getLastNode().getData();
		int x = (int) previous.getLocation().getX();
		int y = (int) previous.getLocation().getY();
		
		SnakePoint newBody = new SnakePoint(null, -1);
		
		switch(previous.getDirection()){
			case SnakePoint.DIRECTION_RIGHT:
				newBody = new SnakePoint(new Point(--x, y), SnakePoint.DIRECTION_RIGHT);
				break;
			case SnakePoint.DIRECTION_LEFT:
				newBody = new SnakePoint(new Point(++x, y), SnakePoint.DIRECTION_LEFT);
				break;
			case SnakePoint.DIRECTION_UP:
				newBody = new SnakePoint(new Point(x, --y), SnakePoint.DIRECTION_UP);
				break;
			case SnakePoint.DIRECTION_DOWN:
				newBody = new SnakePoint(new Point(x, ++y), SnakePoint.DIRECTION_DOWN);
				break;
		}
		
		this.m_body.appendToTail(newBody);
	}
	
	public Node getHead(){
		return this.m_body.getFirstNode();
	}
	
	public int setState(int state){
		if(state != Snake.ALIVE || state != Snake.DEAD){
			return Snake.INVALID_VALUE;
		}
		this.m_state = state;
		return state;
	}
	
	public int getState(){
		return this.m_state;
	}
	
	public int getLength(){
		return this.m_body.getLength();
	}
	
}
