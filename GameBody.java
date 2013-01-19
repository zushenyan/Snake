package snake;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class GameBody implements ActionListener {
	public static final int EMPTY = 0;
	public static final int SNAKE = 1;
	public static final int SNAKE_HEAD = 2;
	public static final int FRUIT = 3;
	
	private Point m_fruit;
	private int m_map[][];
	private Snake m_snake;
	private DisplayListener m_listener;
	private Timer m_timer;

	public GameBody(DisplayListener listener, int map[][], int delay) {
		this.m_listener = listener;
		this.initGame(map);
		this.m_timer = new Timer(delay, this);
	}

	private void initGame(int map[][]) {
		this.m_map = map;
		int midw = this.m_map[0].length / 2;
		int midh = this.m_map.length / 2;
		
		this.m_fruit = null;
		this.m_snake = new Snake(new SnakePoint(new Point(midw, midh),
				SnakePoint.DIRECTION_RIGHT), 6);
		
		this.clearMap();
	}
	
	public void clearMap(){
		for (int y = 0; y < this.m_map.length; y++) {
			for (int x = 0; x < this.m_map[y].length; x++) {
				this.m_map[y][x] = GameBody.EMPTY;
			}
		}
	}

	private void updateMap() {
		Point p;
		Node current;
		int y, x;

		// filled with empty first
		this.clearMap();
		
		//filled with fruit
		if(this.m_fruit != null){
			x = (int)this.m_fruit.getX();
			y = (int)this.m_fruit.getY();
			this.m_map[y][x] = GameBody.FRUIT;
		}

		// and then filled with snake's body
		current = this.m_snake.getHead();
		while (current != null) {
			x = (int) ((SnakePoint) current.getData()).getLocation().getX();
			y = (int) ((SnakePoint) current.getData()).getLocation().getY();
			this.m_map[y][x] = GameBody.SNAKE;
			current = current.getNextNode();
		}

		// snake's head finally
		current = this.m_snake.getHead();
		x = (int) ((SnakePoint) current.getData()).getLocation().getX();
		y = (int) ((SnakePoint) current.getData()).getLocation().getY();
		this.m_map[y][x] = GameBody.SNAKE_HEAD;
	}

	private void generateFruit() {
		Point p;
		while(this.m_fruit == null) {
			p = this.randomlyFruitLocation();
			
			if(this.checkOverlap(p) == false){
				this.m_fruit = p;
			}
		}
	}

	private Point randomlyFruitLocation() {
		int x = (int) (Math.random() * this.m_map[0].length);
		int y = (int) (Math.random() * this.m_map.length);

		return new Point(x, y);
	}
	
	//check if the fruit overlaps the snake or not
	private boolean checkOverlap(Point p){
		Node current = this.m_snake.getHead();
		SnakePoint sp;

		while (current != null) {
			sp = (SnakePoint) current.getData();
			if(p.equals(sp.getLocation())){
				return true;
			}
			current = current.getNextNode();
		}
		return false;
	}
	
	private void checkIfFruitIsEaten(){
		Node head = this.m_snake.getHead();
		SnakePoint p = (SnakePoint)head.getData();
		if(p.getLocation().equals(this.m_fruit)){
			this.m_fruit = null;
			this.m_snake.grow();
		}
	}

	public void start() {
		this.m_timer.start();
	}

	public void stop() {
		this.m_timer.stop();
	}

	public void turnSnakeDirection(int direction) {
		this.m_snake.turnDirection(direction);
	}

	public void actionPerformed(ActionEvent e) {
		int state;
		
		this.generateFruit();
		state = this.m_snake.move();
		this.checkIfFruitIsEaten();
		try {
			this.updateMap();

			if (state == Snake.DEAD) {
				this.stop();
			}
		} catch (ArrayIndexOutOfBoundsException ee) {
			state = Snake.DEAD;
			this.stop();
		} finally {
			this.m_listener.update(new GameEvent(this.m_map, state));
		}
	}
}
