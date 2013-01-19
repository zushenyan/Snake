package snake;

public class GameEvent {
	private int m_map[][];
	private int m_state;
	
	public GameEvent(int map[][], int state){
		this.m_map = map;
		this.m_state = state;
	}
	
	public int[][] getMap(){
		return this.m_map;
	}
	
	public int getState(){
		return this.m_state;
	}
}
