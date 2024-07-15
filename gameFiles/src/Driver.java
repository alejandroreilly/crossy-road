import java.awt.Component;

import javax.swing.JFrame;

public class Driver {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame menuframe = new JFrame();
		menuframe.setSize(600,838);
		MyPanel menu = new MyPanel();
		menuframe.getContentPane().add(menu);
		menuframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuframe.setVisible(true);
		menuframe.setResizable(false);
		
	
		
		
	}
	
	
}