
public class MoveableEntity extends Entity {
	
	private int speed;
	
	

	public MoveableEntity(int x, int y, String i, int s) {
		super(x,y,i);
		speed = s;
	}

	
	
	public MoveableEntity(int x, int y, int w, int h, String i, int s) {
		super(x,y,w,h,i);
		speed = s;
	}
	
	


	public int getSpeed() {
		return speed;
	}



	public void setSpeed(int speed) {
		this.speed = speed;
	}


		
	
}