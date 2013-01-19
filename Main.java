package snake;

import javax.swing.*;
import java.awt.*;

public class Main {
	public static void main(String args[]){
		int size = 20;
		int map[][] = new int[size][size];
		
		UserInterface ui = new UserInterface(map);
		
		JFrame frame = new JFrame("Snake Game ver1.0 by Zushen-Yan");
		
		frame.addKeyListener(ui);
		frame.getContentPane().add(ui);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
