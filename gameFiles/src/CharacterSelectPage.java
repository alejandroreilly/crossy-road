import java.awt.Graphics;

public class CharacterSelectPage extends Page {
	
	private Images characterSelectionBackground;
	private Images characterOne;
	private Images characterTwo;
	private Images characterThree;
	private Images selectButtonOne;
	private Images selectButtonTwo;
	private Images selectButtonThree;
	private Images secondAvatarCoin;
	private Images thirdAvatarCoin;
	private Images twentyCoinText;
	private Images fortyCoinText;
	private int characterActive;
	private int numOfCoins;
	private boolean ownCharacterTwo;
	private boolean ownCharacterThree;
	
	public CharacterSelectPage() { //set all images
		
		characterSelectionBackground = new Images(0,0,"characterSelectionBackground.jpg");
		selectButtonOne = new Images (225,175,"characterSelectButton.png");
		selectButtonTwo = new Images (225,425,"characterSelectButton.png");
		selectButtonThree = new Images (225,690,"characterSelectButton.png");
		characterOne = new Images (225,6, "chickenAvatarSelect.png");
		characterTwo = new Images (175,200, "pigAvatarSelect.png");
		characterThree = new Images (200,505, "frogAvatarSelect.png");
		secondAvatarCoin = new Images (400,425, "goldCoinsCharacterSelect.png");
		thirdAvatarCoin = new Images (400,690, "goldCoinsCharacterSelect.png");
		twentyCoinText = new Images (460, 425, "twentyCoinText.png");
		fortyCoinText = new Images (460, 690, "fourtyCoinText.png");
		numOfCoins = 0;
		
		ownCharacterTwo = false;
		
		ownCharacterThree = false;
		
	}
	
	public void draw(Graphics g) { // draw all images
		
		g.drawImage(characterSelectionBackground.getImg(), characterSelectionBackground.getImgX(), characterSelectionBackground.getImgY(), null);
		g.drawImage(selectButtonOne.getImg(), selectButtonOne.getImgX(), selectButtonOne.getImgY(), null);			
		g.drawImage(selectButtonTwo.getImg(), selectButtonTwo.getImgX(), selectButtonTwo.getImgY(), null);
		g.drawImage(selectButtonThree.getImg(), selectButtonThree.getImgX(), selectButtonThree.getImgY(), null);
		g.drawImage(characterOne.getImg(), characterOne.getImgX(), characterOne.getImgY(), null);			
		g.drawImage(characterTwo.getImg(), characterTwo.getImgX(), characterTwo.getImgY(), null);			
		g.drawImage(characterThree.getImg(), characterThree.getImgX(), characterThree.getImgY(), null);	
		if(ownCharacterTwo == false) {
			g.drawImage(twentyCoinText.getImg(), twentyCoinText.getImgX(), twentyCoinText.getImgY(), null);	
			g.drawImage(secondAvatarCoin.getImg(), secondAvatarCoin.getImgX(), secondAvatarCoin.getImgY(), null);
		}
		if(ownCharacterThree == false) {
			g.drawImage(fortyCoinText.getImg(), fortyCoinText.getImgX(), fortyCoinText.getImgY(), null);
			g.drawImage(thirdAvatarCoin.getImg(), thirdAvatarCoin.getImgX(), thirdAvatarCoin.getImgY(), null);
		}
		
	}
	
	

	public boolean isOwnCharacterTwo() {
		return ownCharacterTwo;
	}

	public void setOwnCharacterTwo(boolean ownCharacterTwo) {
		this.ownCharacterTwo = ownCharacterTwo;
	}

	public boolean isOwnCharacterThree() {
		return ownCharacterThree;
	}

	public void setOwnCharacterThree(boolean ownCharacterThree) {
		this.ownCharacterThree = ownCharacterThree;
	}

	public Images getCharacterSelectionBackground() {
		return characterSelectionBackground;
	}

	public void setCharacterSelectionBackground(Images characterSelectionBackground) {
		this.characterSelectionBackground = characterSelectionBackground;
	}

	public Images getCharacterOne() {
		return characterOne;
	}

	public void setCharacterOne(Images characterOne) {
		this.characterOne = characterOne;
	}

	public Images getCharacterTwo() {
		return characterTwo;
	}

	public void setCharacterTwo(Images characterTwo) {
		this.characterTwo = characterTwo;
	}

	public Images getCharacterThree() {
		return characterThree;
	}

	public void setCharacterThree(Images characterThree) {
		this.characterThree = characterThree;
	}

	public Images getSelectButtonOne() {
		return selectButtonOne;
	}

	public void setSelectButtonOne(Images selectButtonOne) {
		this.selectButtonOne = selectButtonOne;
	}

	public Images getSelectButtonTwo() {
		return selectButtonTwo;
	}

	public void setSelectButtonTwo(Images selectButtonTwo) {
		this.selectButtonTwo = selectButtonTwo;
	}

	public Images getSelectButtonThree() {
		return selectButtonThree;
	}

	public void setSelectButtonThree(Images selectButtonThree) {
		this.selectButtonThree = selectButtonThree;
	}

	public Images getSecondAvatarCoin() {
		return secondAvatarCoin;
	}

	public void setSecondAvatarCoin(Images secondAvatarCoin) {
		this.secondAvatarCoin = secondAvatarCoin;
	}

	public Images getThirdAvatarCoin() {
		return thirdAvatarCoin;
	}

	public void setThirdAvatarCoin(Images thirdAvatarCoin) {
		this.thirdAvatarCoin = thirdAvatarCoin;
	}

	public Images getTwentyCoinText() {
		return twentyCoinText;
	}

	public void setTwentyCoinText(Images twentyCoinText) {
		this.twentyCoinText = twentyCoinText;
	}

	public Images getFortyCoinText() {
		return fortyCoinText;
	}

	public void setFortyCoinText(Images fortyCoinText) {
		this.fortyCoinText = fortyCoinText;
	}

	public int getCharacterActive() {
		return characterActive;
	}

	public void setCharacterActive(int characterActive) {
		this.characterActive = characterActive;
	}

	public int getNumOfCoins() {
		return numOfCoins;
	}

	public void setNumOfCoins(int numOfCoins) {
		this.numOfCoins = numOfCoins;
	}
	
}