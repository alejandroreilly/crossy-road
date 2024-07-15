import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JOptionPane;

public class MyPanel extends JPanel {

	private MainMenuPage mainMenuPage;
	private CharacterSelectPage characterSelectPage;
	private LevelPage levelPage;
	private User user;
	private SettingsPage settingsPage;
	private GameOverPage gameOver;
	
	
	
	String activeAvatar = "chickenAvatar";
	
	private int mode; //1 is easy 2 is hard

//KICKS OFF INTEGER FOR WHICH SCREEN IS OPEN AS PRIVATE --------------------------------------------------

	private int page;

	private Timer timer;
	
	private int numberOfShieldPowerUps;
	
	private int timeWithSpeedBoost;
	
//CONSTRUCTOR FOR MY PANEL WITH NOTHING PASSED THROUGH ---------------------------------------------------
	
	public MyPanel() {

		user = new User(250, 710, "chickenAvatarRight.png", 100);
		gameOver = new GameOverPage();
		mainMenuPage = new MainMenuPage();
		characterSelectPage = new CharacterSelectPage();
		levelPage = new LevelPage(600, 800, user);
		settingsPage = new SettingsPage();
		
		mode = 1;

//INITIALIZES THE VARIABLES SAYING WHICH SCREEN IS UP TO BE THE MENU SCREEN -------------------------------

		page = 1;

//ADDS ABILITY TO DETECT MOUSE ACTIONS BY USER ------------------------------------------------------------

		addMouseListener(new Clicky());
		addKeyListener(new Controls());
		setFocusable(true);
		
		numberOfShieldPowerUps = 0;
		timeWithSpeedBoost = 0;
		
		timer = new Timer(100, new movement());
	

	}

//---------------------------------------------------------------------------------------------------------

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		
		if (page == 1) { // IF THE MENU SCREEN IS OPEN THEN PRINT ALL OF THE MENU SCREEN'S IMAGES --------------

			mainMenuPage.draw(g);

		}

		if (page == 2) {

			characterSelectPage.draw(g);
			g.setColor(Color.black);
			g.drawString("Number of Coins: " +characterSelectPage.getNumOfCoins(), 10, 20);

		}

		if (page == 3) {

			settingsPage.draw(g);
			
		}

		if (page == 4) {

			levelPage.draw(g);
			
			g.setColor(Color.black);
			g.drawString("Number of Coins: " +characterSelectPage.getNumOfCoins(), 10, 770);
			g.drawString("Number of Shield Power-Ups: "+numberOfShieldPowerUps, 10, 785);
			
			timer.start();
			
			if(user.getEntityY()<0) {
				user.setEntityY(710);
				levelPage = new LevelPage(600,800,user);
		
			}
			
			
		}
		
		
		if(page == 5) {
			gameOver.draw(g);
		}
			
		
		

	}
	
	public class movement implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			levelPage.setTime(levelPage.getTime()+1);
			
			levelPage.move(levelPage.getTime());
			
			collided();
			
			if(timeWithSpeedBoost > 0) {
				timeWithSpeedBoost--;
			}
			
			if(timeWithSpeedBoost == 0) {
				user.setSpeed(100);
			}
			repaint();
			
			//this is a lot of code for changing the user position if they are in contact with a log 
			
			for(int i = 0; i < levelPage.getObstacleRows().length; i++) { 
				if(levelPage.getObstacle(i).getTypeOfRow()==3) { //if there is a log row, THEN 
					for(int j = 0; j < levelPage.getObstacle(i).getLogs().size();j++) { //check if the user is in contact with any of the logs in the row
						if(levelPage.getObstacle(i).getLogs().get(j).isInside(user.getEntityX(), user.getEntityY()))
							user.setEntityX(user.getEntityX()+levelPage.getObstacle(i).getLogSpeed()); //then set it's x value
						}
					}
				}
			
			
			
			//move fire based on the difficulty chosen
				if(mode==1)
					levelPage.moveFire(levelPage.getTime(),20);
				else
					levelPage.moveFire(levelPage.getTime(), 50);
			
			//check for if the fire reaches the user
			if(levelPage.getFire().getImgY()<=user.getEntityY()+user.getWidth()-50) {
				timer.stop();
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				page = 5;
				
			}
				

			
			if(user.getEntityY()<0) {
				page = 4;
				
				repaint();
			}
				
			
			}	
		
	}
	
	
	

	public class Clicky extends MouseAdapter {

		public void mousePressed(MouseEvent e) {

			if (page == 1) {//if a button is clicked set that buttons status to true

				if (mainMenuPage.getMenuStartButton().isInside(e.getX(), e.getY())) {

					mainMenuPage.getMenuStartButton().setClicked(true);
					
					

				}

				if (mainMenuPage.getSettingsButton().isInside(e.getX(), e.getY())) {

					mainMenuPage.getSettingsButton().setClicked(true);

				}

				if (mainMenuPage.getCharacterSelectButton().isInside(e.getX(), e.getY())) {

					mainMenuPage.getCharacterSelectButton().setClicked(true);

				}

			}

			if (page == 2) {

				
				if (characterSelectPage.getSelectButtonOne().isInside(e.getX(), e.getY())) {

					characterSelectPage.getSelectButtonOne().setClicked(true);

				}

				if (characterSelectPage.getSelectButtonTwo().isInside(e.getX(), e.getY())) {

					if (characterSelectPage.getNumOfCoins() >= 20 || characterSelectPage.isOwnCharacterTwo()) {// if user has enough coins they can buy the character

						characterSelectPage.getSelectButtonTwo().setClicked(true);

					} else {

						JOptionPane.showMessageDialog(null, "You need " + (20 - characterSelectPage.getNumOfCoins())
								+ " more coins to unlock this avatar.");

					}

				}

				if (characterSelectPage.getSelectButtonThree().isInside(e.getX(), e.getY())) {

					if (characterSelectPage.getNumOfCoins() >= 40 || characterSelectPage.isOwnCharacterThree()) {// if user has enough coins they can buy the character

						characterSelectPage.getSelectButtonThree().setClicked(true);

					} else {

						JOptionPane.showMessageDialog(null, "You need " + (40 - characterSelectPage.getNumOfCoins())
								+ " more coins to unlock this avatar.");

					}

				}

			}
			
			if (page == 3) {
				
				if (settingsPage.getEasyModeButton().isInside(e.getX(), e.getY())) {

					settingsPage.getEasyModeButton().setClicked(true);

				}
				
				if (settingsPage.getHardModeButton().isInside(e.getX(), e.getY())) {

					settingsPage.getHardModeButton().setClicked(true);

				}
				
			}
			
			
			if(page == 5) {
				
				if(gameOver.getPlayAgainButton().isInside(e.getX(), e.getY())) {
					gameOver.getPlayAgainButton().setClicked(true);
				}
					
					
				if(gameOver.getMainMenuButton().isInside(e.getX(), e.getY())) {
					gameOver.getMainMenuButton().setClicked(true);
				}
				
				
			}
				
			
			

		}

		public void mouseReleased(MouseEvent e) {//when the mouse is released set the status of that button clicked to false and set the page to the appropriate number and repaint

			if (mainMenuPage.getMenuStartButton().isClicked()) {

				mainMenuPage.getMenuStartButton().setClicked(false);
				user.setEntityY(710);
				levelPage = new LevelPage (600,800,user);
				page = 4;
				repaint();

			}

			if (mainMenuPage.getSettingsButton().isClicked()) {

				mainMenuPage.getSettingsButton().setClicked(false);
				page = 3;
				repaint();

			}

			if (mainMenuPage.getCharacterSelectButton().isClicked()) {

				mainMenuPage.getCharacterSelectButton().setClicked(false);
				page = 2;
				repaint();

			}

			if (characterSelectPage.getSelectButtonOne().isClicked()) {

				characterSelectPage.getSelectButtonOne().setClicked(false);
				activeAvatar = "chickenAvatar";
				user.setImg(activeAvatar + "Right.png");
				page = 1;
				repaint();

			}

			if (characterSelectPage.getSelectButtonTwo().isClicked() && (characterSelectPage.getNumOfCoins()>=20 || characterSelectPage.isOwnCharacterTwo())) {

				characterSelectPage.getSelectButtonTwo().setClicked(false);
				
				if(characterSelectPage.isOwnCharacterTwo() == false)
					characterSelectPage.setNumOfCoins(characterSelectPage.getNumOfCoins()-20);
				
				characterSelectPage.setOwnCharacterTwo(true);
				activeAvatar = "pigAvatar";
				user.setImg(activeAvatar + "Right.png");
				page = 1;
				repaint();

			}

			if (characterSelectPage.getSelectButtonThree().isClicked()&& (characterSelectPage.getNumOfCoins()>=40|| characterSelectPage.isOwnCharacterThree())) {

				characterSelectPage.getSelectButtonThree().setClicked(false);
				
				if(characterSelectPage.isOwnCharacterThree() == false)
					characterSelectPage.setNumOfCoins(characterSelectPage.getNumOfCoins()-40);
				
				characterSelectPage.setOwnCharacterThree(true);
				activeAvatar = "frogAvatar";
				user.setImg(activeAvatar + "Right.png");
				page = 1;
				repaint();

			}
			
			if (settingsPage.getHardModeButton().isClicked()) {
				
				settingsPage.getHardModeButton().setClicked(false);	
				mode = 2;
				page = 1;
				repaint();
				
			}
			
			if (settingsPage.getEasyModeButton().isClicked()) {
				
				settingsPage.getEasyModeButton().setClicked(false);	
				mode = 1;
				page = 1;
				repaint();
				
			}
			
			if(gameOver.getPlayAgainButton().isClicked()) {
				gameOver.getPlayAgainButton().setClicked(false);
				user.setEntityX(250);
				user.setEntityY(710);
				timeWithSpeedBoost = 0;
				levelPage = new LevelPage(600,800,user);
				page = 4;
				repaint();
			}
			
			if(gameOver.getMainMenuButton().isClicked()) {
				gameOver.getMainMenuButton().setClicked(false);
				page = 1;
				repaint();
			}
			

		}

	}

	public class Controls implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
			if (page == 4) {

				// Move the character to the right and switch the image when right arrow key is
				// pressed
				if (KeyEvent.VK_RIGHT == e.getKeyCode()) {
					
					user.setImg(activeAvatar + "Right.png");
					user.setEntityX(user.getEntityX() + user.getSpeed()/3);
					collided();
					repaint();

				}

				// Move the character to the left and switch the image when left arrow key is
				// pressed
				if (KeyEvent.VK_LEFT == e.getKeyCode()) {
					
					user.setImg(activeAvatar + "Left.png");
					user.setEntityX(user.getEntityX() - user.getSpeed()/3);
					collided();
					repaint();
				}

				// Move the character up when up arrow key is pressed
				if (KeyEvent.VK_UP == e.getKeyCode()) {
					
					user.setEntityY(user.getEntityY() - user.getSpeed());
					collided();
					repaint();
				}

				// Move the character down when down arrow key is pressed
				if (KeyEvent.VK_DOWN == e.getKeyCode()) {
					
					user.setEntityY(user.getEntityY() + user.getSpeed());
					collided();
					repaint();
				}

			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}
	
	public void powerUpInAction(int typeOfPowerUp) { // 0 = shield, 1 = speed, 2 = cancel
		switch(typeOfPowerUp) {
			case 0:
				numberOfShieldPowerUps++;
				break;
			case 1:
				timeWithSpeedBoost = 50;
				user.setSpeed(200);
				break;
			case 2:
				for(int i = 0; i < 8; i ++) {
					levelPage.getObstacle(i).setTypeOfRow(5);
				}
				break;
		}
	}
	
	public void collided() {
		
		for (int i = 0; i < 8; i++) {// go through each obstacle and determine if the user is touching it
			
			//check left, middle, and right of the avatar to see if it's touching the coin
			if(levelPage.getObstacle(i).touchingCoin(user.getEntityX(),user.getEntityY()+user.getHeight()/2) ||
					levelPage.getObstacle(i).touchingCoin(user.getEntityX()+user.getWidth(), user.getEntityY()+user.getHeight()/2) ||
					levelPage.getObstacle(i).touchingCoin(user.getEntityX()+user.getWidth()/2,user.getEntityY()+user.getHeight()/2)) {
				//increment coins
				characterSelectPage.setNumOfCoins(characterSelectPage.getNumOfCoins()+1);
				//move coin offscreen, cant be set to null
				levelPage.getObstacle(i).setCoin(new Images(-100,-100,"goldCoinsCharacterSelect.png"));
			}
			
			//check if the user is touching a powerup (left side, middle, and right side)
			if(levelPage.getObstacle(i).touchingPowerUp(user.getEntityX(),user.getEntityY()+user.getHeight()/2) ||
					levelPage.getObstacle(i).touchingPowerUp(user.getEntityX()+user.getWidth(), user.getEntityY()+user.getHeight()/2)) {
				powerUpInAction(levelPage.getObstacle(i).typeOfPowerUp);
				levelPage.getObstacle(i).setPowerUp(new Images (-100,-100,"goldCoinsCharacterSelect.png"));
			}
				
			//if no shields and collides with an obstacle, then game over
			if (numberOfShieldPowerUps <= 0 && (levelPage.getObstacle(i).collided(user.getEntityX()+user.getWidth()/4, user.getEntityY()) || 
					levelPage.getObstacle(i).collided(user.getEntityX()+(3*user.getWidth())/4, user.getEntityY()))) {
				
				timeWithSpeedBoost = 0; //reset the speed boost powerup
				timer.stop();
				try {
					Thread.sleep(500); 			//give a sleep so user can see the mistake
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				page = 5; //give game over page
			}
			
			//if off screen, end the game
			
			if(user.isOffScreen()) {
				
				timeWithSpeedBoost = 0;
				timer.stop();
				page = 5;
			}
			
			//PURELY FOR SHIELD POWERUPS
			//if have a shield and collides, then de-increment shields and set row to grass to make it safe.
			
			if(numberOfShieldPowerUps > 0 && (levelPage.getObstacle(i).collided(user.getEntityX()+user.getWidth()/4, user.getEntityY()) || 
					levelPage.getObstacle(i).collided(user.getEntityX()+(3*user.getWidth())/4, user.getEntityY())))
			{
				numberOfShieldPowerUps--;
				levelPage.getObstacle(i).setTypeOfRow(5);
				
			}
		}
		
	}

}