import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Entity {
	
	
	public int EntityX;
	public int EntityY;
	public int width;
	public int height;
	public BufferedImage img;
	
	public Entity(int x, int y, String i) {
		
		EntityX = x;
		EntityY = y;
		
		try {
			img = ImageIO.read(new File(i));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		width = img.getWidth();
		height = img.getHeight();
		
		
	}
	
	
	
	public Entity(int x, int y, int w, int h, String i) {
		this(x, y, i);
		
		width = w;
		height = h;
		
	}
	
	
	
	
	
	
	
	public Boolean isInside(int otherX, int otherY) {
		
		return otherX >= EntityX && otherX <= EntityX + width && otherY >= EntityY && otherY <= EntityY + height;
		
	}
	
	

	public int getEntityX() {
		return EntityX;
	}

	public void setEntityX(int entityX) {
		EntityX = entityX;
	}

	public int getEntityY() {
		return EntityY;
	}

	public void setEntityY(int entityY) {
		EntityY = entityY;
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

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(String img) {
		try {
			this.img = ImageIO.read(new File(img));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}