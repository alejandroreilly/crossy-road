import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images {
	
	private int imgX;
	private int imgY;
	private int width;
	private int height;
	private BufferedImage img;
	private boolean isClicked;
	
	public Images(int x, int y, String i) {
		
		imgX = x;
		imgY = y;
		isClicked = false;
		
		try {//set the image to the name of the string given as the parameter
			img = ImageIO.read(new File(i));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		width = img.getWidth();
		height = img.getHeight();
		
	}

	public boolean isClicked() {
		return isClicked;
	}

	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}

	public Boolean isInside(int clickX, int clickY) {//check if another entity is inside this entity
		
		return (clickX >= imgX && clickX <= imgX + width && clickY >= imgY && clickY <= imgY + height);
		
	}
	
	public Boolean isCenterInside(Images image) {//check if another entity is inside this entity based on the center
		return imgX+width/2 >= image.getImgX() && imgX+width/2 <= image.getImgX()+image.getWidth() && imgY+height/2 >= image.getImgY() && imgY+height/2 <= image.getImgY()+image.getWidth();
	}
	
	
	public int getImgX() {
		return imgX;
	}

	public void setImgX(int imgX) {
		this.imgX = imgX;
	}

	public int getImgY() {
		return imgY;
	}

	public void setImgY(int imgY) {
		this.imgY = imgY;
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
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
	
}