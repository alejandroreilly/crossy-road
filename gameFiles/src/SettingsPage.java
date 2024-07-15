import java.awt.Graphics;

public class SettingsPage extends Page {

	private Images background;
	private Images EasyModeButton;
	private Images HardModeButton;
	
	public SettingsPage() {//set images
		
		background = new Images(0,0,"characterSelectionBackground.jpg");
		EasyModeButton = new Images(170,150,"EasyDifficultyButton.png");
		HardModeButton = new Images(170,450,"HardDifficultyButton.png");
		
	}
	
	
	public void draw(Graphics g) {//draw images
		// TODO Auto-generated method stub
		
		g.drawImage(background.getImg(), background.getImgX(), background.getImgY(), null);
		g.drawImage(EasyModeButton.getImg(), EasyModeButton.getImgX(), EasyModeButton.getImgY(), null);
		g.drawImage(HardModeButton.getImg(), HardModeButton.getImgX(), HardModeButton.getImgY(), null);
		
	}


	public Images getBackground() {
		return background;
	}


	public void setBackground(Images background) {
		this.background = background;
	}


	public Images getEasyModeButton() {
		return EasyModeButton;
	}


	public void setEasyModeButton(Images easyModeButton) {
		EasyModeButton = easyModeButton;
	}


	public Images getHardModeButton() {
		return HardModeButton;
	}


	public void setHardModeButton(Images hardModeButton) {
		HardModeButton = hardModeButton;
	}

}