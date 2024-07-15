import java.awt.Graphics;

public class MainMenuPage extends Page {
	
	private Images MenuBackground;
	private Images MenuStartButton;
	private Images TitleText;
	private Images settingsButton;
	private Images characterSelectButton;
	
	public MainMenuPage() {//set images
		
		MenuStartButton = new Images(125,210,"MainMenuStartButton.png");
		settingsButton = new Images(170,350,"settingsButton.jpg");
		characterSelectButton = new Images(300,350,"characterSelectionScreenButton.png");
		MenuBackground = new Images(0,0,"CrossyRoadMainMenuBackground.jpeg");
		TitleText = new Images(-75,-25,"Animal Crossing Title Text.jpeg");
		
	}
	
	public void draw(Graphics g) {//draw images
		
		g.drawImage(MenuBackground.getImg(), MenuBackground.getImgX(), MenuBackground.getImgY(), null);
		g.drawImage(MenuStartButton.getImg(), MenuStartButton.getImgX(), MenuStartButton.getImgY(), null);
		g.drawImage(settingsButton.getImg(), settingsButton.getImgX(), settingsButton.getImgY(), null);
		g.drawImage(characterSelectButton.getImg(), characterSelectButton.getImgX(), characterSelectButton.getImgY(), null);
		g.drawImage(TitleText.getImg(), TitleText.getImgX(), TitleText.getImgY(), null);
		
	}

	public Images getMenuBackground() {
		return MenuBackground;
	}

	public void setMenuBackground(Images menuBackground) {
		MenuBackground = menuBackground;
	}

	public Images getMenuStartButton() {
		return MenuStartButton;
	}

	public void setMenuStartButton(Images menuStartButton) {
		MenuStartButton = menuStartButton;
	}

	public Images getTitleText() {
		return TitleText;
	}

	public void setTitleText(Images titleText) {
		TitleText = titleText;
	}

	public Images getSettingsButton() {
		return settingsButton;
	}

	public void setSettingsButton(Images settingsButton) {
		this.settingsButton = settingsButton;
	}

	public Images getCharacterSelectButton() {
		return characterSelectButton;
	}

	public void setCharacterSelectButton(Images characterSelectButton) {
		this.characterSelectButton = characterSelectButton;
	}
	
}