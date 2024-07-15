import java.awt.Color;
import java.awt.Graphics;

public class GameOverPage extends Page{
	private Images mainMenuButton;
	private Images playAgainButton;
	private Images gameOverBackground;
	private Images gameOverText;
	 
	public GameOverPage(){	//set images
		playAgainButton = new Images(140,200,"playAgainButton.png");
		mainMenuButton = new Images(132,300,"mainMenuButton.png");
		gameOverBackground = new Images(0,0,"characterSelectionBackground.jpg");
		gameOverText = new Images(-70,-10,"gameOverText.png");
	}



	public void draw(Graphics g) {//draw images
	
		g.drawImage(gameOverBackground.getImg(), gameOverBackground.getImgX(), gameOverBackground.getImgY(),null);
		g.drawImage(playAgainButton.getImg(),playAgainButton.getImgX(),playAgainButton.getImgY(),null);
		g.drawImage(mainMenuButton.getImg(),mainMenuButton.getImgX(),mainMenuButton.getImgY(),null);
		g.drawImage(gameOverText.getImg(), gameOverText.getImgX(), gameOverText.getImgY(), null);
		
		
	}



	public Images getMainMenuButton() {
		return mainMenuButton;
	}



	public void setMainMenuButton(Images mainMenuButton) {
		this.mainMenuButton = mainMenuButton;
	}



	public Images getPlayAgainButton() {
		return playAgainButton;
	}



	public void setPlayAgainButton(Images playAgainButton) {
		this.playAgainButton = playAgainButton;
	}
	
	
	
	
	
	
	
	
}