import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class User extends MoveableEntity{
	
    private boolean hitObstacle;
    private int coinCount;

	
	
	public User(int x, int y, String img, int speed) {
		super(x,y,img,speed);
		hitObstacle = false;
		coinCount = 0;
		
	}

	public boolean isOffScreen() {//check if the user moves outside the border of the frame
		if(EntityX >= 600) {
			return true;
		}
		
		if(EntityY>=800)
			return true;
		
		if(EntityX<=0-width)
			return true;
		
		return false;
		
	}
	


	public boolean isHitObstacle() {
		return hitObstacle;
	}


	public void setHitObstacle(boolean hitObstacle) {
		this.hitObstacle = hitObstacle;
	}


	public int getCoinCount() {
		return coinCount;
	}


	public void setCoinCount(int coinCount) {
		this.coinCount = coinCount;
	}


	
	


}