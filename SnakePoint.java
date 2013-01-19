package snake;

import java.awt.Point;

public class SnakePoint {
	public static final int DIRECTION_LEFT = -10;
	public static final int DIRECTION_RIGHT = 10;
	public static final int DIRECTION_DOWN = 20;
	public static final int DIRECTION_UP = -20;
	
	private Point m_location;
	private int m_direction;
	
	public SnakePoint(Point location, int direction){
		this.setLocation(location);
		this.setDirection(direction);
	}
	
	public void setLocation(Point location){
		this.m_location = location;
	}
	
	public Point getLocation(){
		return this.m_location;
	}
	
	public void setDirection(int direction){
		this.m_direction = direction;
	}
	
	public int getDirection(){
		return this.m_direction;
	}
	
	public String toString(){
		return "(" + (int)this.getLocation().getX() + "," + 
			(int)this.getLocation().getY() + ")" + "," + this.getDirection();
	}
}
