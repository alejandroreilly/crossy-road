import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class LevelPage extends Page {
	
	private int width, height;
	private Obstacle[] obstacleRows;
	private User user;
	private int time;
	private Images fire;
	
	public LevelPage(int w, int h, User user) {
		
		obstacleRows = new Obstacle[8];
		width = w;
		height = h;
		this.user = user;
		
		
		// The level screen will have eight rows on it, which are randomly selected from
		// the possible options.
		// Each row type is assigned a number, which is randomly generated, therefore
		// randomly generating a new
		// obstacle row type for each level page.

		for (int i = 0; i < 7; i++) {
			obstacleRows[i] = new Obstacle((int) (Math.random() * 6), i*100, width, height);
			// i*100 is for the height of the level, which we need to pass through so we can
			// gauge the height
			// of the rest of the objects.
		}
		
		fire = new Images(0, height, "fire.png");
		
		obstacleRows[7] = new Obstacle(5, 700, width, height); // first row is always just grass w/ no rocks (safe)
		time = 0;
	

	}

	public void draw(Graphics g) {//draw each obstacle, the fire, and the user

		for (int i = 0; i < obstacleRows.length; i++) {
			
			obstacleRows[i].drawRow(g);
		}
		
		drawFire(g);
		
		g.drawImage(user.getImg(), user.getEntityX(), user.getEntityY(), null);
		
		
	
	

	}
	
	public void move(int time) {
		
		for(int i = 0;i<obstacleRows.length;i++) {
			obstacleRows[i].moveRow(time);
		}
		
	}
	
	public void drawFire(Graphics g) {
		g.drawImage(fire.getImg(),fire.getImgX(),fire.getImgY(),null);
	}
	
	public void moveFire(int time, int speed) {
		if( time%20 == 0) {
			fire.setImgY(fire.getImgY()-speed);
		}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Obstacle[] getObstacleRows() {
		return obstacleRows;
	}

	public void setObstacleRows(Obstacle[] obstacleRows) {
		this.obstacleRows = obstacleRows;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public Obstacle getObstacle(int i) {
		
		return obstacleRows[i];
		
	}

	public Images getFire() {
		return fire;
	}

	public void setFire(Images fire) {
		this.fire = fire;
	}

}