package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserInterface extends JPanel implements DisplayListener, KeyListener{
	public static final int SCALE = 20;
	
	private int m_map[][];
	private GameBody m_body;
	private boolean m_isBegin;
	
	public UserInterface(int map[][]){
		this.m_map = map;
		this.m_isBegin = true;
		this.m_body = new GameBody(this, map, 100);
		
		this.setBackground(Color.WHITE);
		this.repaint();
	}
	
	/*public void setGameBody(GameBody body){
		this.m_body = body;
	}*/
	
	public void paint(Graphics g){
		super.paint(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		if(this.m_isBegin){
			g2.drawString("press any key to start", 120, 190);
			g2.drawString("press any key to stop / resume", 100, 210);
		}
		
		this.paintMap(g2);
	}
	
	private void paintMap(Graphics2D g2){
		for(int y = 0; y < this.m_map.length; y++){
			for(int x = 0; x < this.m_map[y].length; x++){
				if(this.m_map[y][x] == GameBody.SNAKE || 
						this.m_map[y][x] == GameBody.SNAKE_HEAD){
					g2.drawRect(x * UserInterface.SCALE, y * UserInterface.SCALE, 
							UserInterface.SCALE, UserInterface.SCALE);
					
				}
				else if(this.m_map[y][x] == GameBody.FRUIT){
					g2.fillOval(x * UserInterface.SCALE, y * UserInterface.SCALE,
							UserInterface.SCALE, UserInterface.SCALE);
				}
			}
		}
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(
				this.m_map[0].length * UserInterface.SCALE, 
				this.m_map.length * UserInterface.SCALE);
	}
	
	public void update(GameEvent e){
		this.m_map = e.getMap();
		if(e.getState() == Snake.DEAD){
			int respond = JOptionPane.showConfirmDialog(this, "Snake is DEAD!!\nPlay again?");
			if(respond == JOptionPane.OK_OPTION){
				this.m_isBegin = true;
				this.m_body = new GameBody(this, this.m_map, 125);
				System.gc();
			}
			else if(respond == JOptionPane.CANCEL_OPTION || respond == JOptionPane.NO_OPTION){
				System.exit(0);
			}
		}
		this.repaint();
	}
	
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_UP){
			this.m_body.turnSnakeDirection(SnakePoint.DIRECTION_UP);
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			this.m_body.turnSnakeDirection(SnakePoint.DIRECTION_DOWN);
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			this.m_body.turnSnakeDirection(SnakePoint.DIRECTION_LEFT);
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			this.m_body.turnSnakeDirection(SnakePoint.DIRECTION_RIGHT);
		}
		else{
			if(this.m_isBegin){
				this.m_body.start();
			}
			else{
				this.m_body.stop();
			}
			
			this.m_isBegin = !this.m_isBegin;
		}
	}

	public void keyReleased(KeyEvent e){
	}

	public void keyTyped(KeyEvent e){
		
	}
	
}
