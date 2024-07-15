import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Obstacle {
	
	//VARIABLES TO BASE LEVEL OF ROW OFF OF
	private int levelWidth;
	private int levelHeight;
	private int yLevelOfRow;
	
	//TYPE OF ROW CHANGES ITS APPEARANCE AND ASSOCIATED IMAGES
	private int typeOfRow;
	
	//LILYPAD VARIABLES 
	private Images[] lilyPads;
	private int[] lilyPadLeftXValues;
	
	//ROCK VARIABLES
	private Images[] rocks;
	private int[] rockLeftXValues;
	
	//LOG VARIABLES
	private ArrayList<Images> logs; //ARRAYLIST TO ADD MORE LOGS AS TIME ++
	private int logSpeed;
	private int[] initialLogLeftXValues;
	
	//CAR VARIABLES
	private ArrayList<Images> cars; ////ARRAYLIST TO ADD MORE CARS AS TIME ++
	private int[] initialCarLeftXValues;
	private int carSpeed;
	
	//RAILROAD VARIABLES
	private Images train;
	private int trainSpeed;
	private Color trainWarning; //TO BLINK RED WHEN A TRAIN IS COMING
	
	//COIN IMAGE
	private Images coin;
	private double chanceOfCoin;
	
	//POWERUP IMAGE AND VARIATION
	private Images powerUp;
	private double chanceOfPowerup;
	int typeOfPowerUp;
	
	
	public Obstacle(int type, int y, int w, int h) {
		
		//EACH ROW WILL ALWAYS HAVE THESE VARIABLES 
		levelWidth = w;
		levelHeight = h;
		yLevelOfRow = y; //YLEVEL WILL SET THEM APART
		typeOfRow = type; 
		trainWarning = Color.GRAY;
		
		switch (typeOfRow) { //DETERMINES WHICH OBSTACLES ARE IN THE ROW
		case 0:
			newWaterWithLilyPads(yLevelOfRow);
			break;
		case 1:
			newGrassWithRocks(yLevelOfRow);
			break;
		case 2:
			newRailRoad(yLevelOfRow);
			break;
		case 3:
			newWaterWithLogs(yLevelOfRow);
			break;
		case 4:
			newRoad(yLevelOfRow);
			break;
		case 5:
			newGrassWithNoRocks(yLevelOfRow);
		}
		
		typeOfPowerUp = (int)(Math.random()*3); //THE POWERUP THAT MAY BE IN THE ROW IS 1 OF 3
		
		
		chanceOfCoin = Math.random();
		chanceOfPowerup = Math.random();
		
		if(chanceOfCoin < .2) //CHANCE OF A COIN IN THE ROW IS .2
			generateCoin(yLevelOfRow);

		if(chanceOfPowerup < .18) //CHANCE OF POWERUP IN ROW IS .18 IF NO COIN
			generatePowerUp(yLevelOfRow);
		
		
	}
	
	public void generateCoin(int yLevel) { //CREATE THE COIN
		coin = new Images((int)(Math.random()*12)*50, yLevel+22, "goldCoinsCharacterSelect.png");

	}
	
	public void generatePowerUp(int yLevel) {//CREATE THE POWERUP
		if(coin == null) {
			if(typeOfPowerUp == 0) {
				powerUp = new Images((int)(Math.random()*6)*90,yLevel+5,"ShieldPowerUp.png");
			}
			if(typeOfPowerUp == 1) {
				powerUp = new Images((int)(Math.random()*6)*90,yLevel+5,"SpeedPowerUp.png");
			}
			if(typeOfPowerUp == 2) {
				powerUp = new Images((int)(Math.random()*6)*90,yLevel+5,"CancelPowerUp.png");
			}
		}
	}
	
	public void newWaterWithLilyPads(int yLevel) { //MAKES ALL THE LILYPADS
		int numberOfLilyPads = (int)(Math.random()*6)+1; //MAX OF 5 LILYPADS, MINIMUM OF 1 SO USER CAN ALWAYS CROSS
		lilyPads = new Images[numberOfLilyPads];
		lilyPadLeftXValues =  new int[numberOfLilyPads];
		
		lilyPadLeftXValues[0] = (int)(Math.random()*5)*100; //ESTABLISH FIRST SO YOU CAN CHECK THE REST
		
		for(int i = 1; i<numberOfLilyPads; i++) {
			do {
				lilyPadLeftXValues[i] = (int)(Math.random()*5)*100; //BASED ON WIDTH, 5 POSSIBLE LOCATIONS FOR LILYPADS
			}while(lilyPadLeftXValues[i] == lilyPadLeftXValues[i-1]); //ENSURES THERE ARE NOT DUPLICATE SOLUTIONS
		}
		
		for(int i = 0; i<numberOfLilyPads; i++) {
			lilyPads[i] = new Images(lilyPadLeftXValues[i],yLevel+5,"lilyPad.png"); //USE LEFT VALUES SO NOT OVERLAPPING
			//YLEVEL + 5 TO BE CENTERED IN ROW 
			
		}
		
	}
	
	public void newGrassWithRocks(int yLevel) { //MAKES ALL THE ROCKS
		int numberOfRocks = (int)(Math.random()*3)+1; //MAX OF 3, MIN OF 1
		rocks = new Images[numberOfRocks];
		rockLeftXValues = new int[numberOfRocks]; 
		
		if(numberOfRocks > 0) {
			rockLeftXValues[0] = (int)(Math.random()*6)*100+5; //BASED ON WIDTH, 6 POSSIBLE LOCATIONS FOR ROCKS
			
			for(int i = 1; i<numberOfRocks;i++) {
				do {

					rockLeftXValues[i] = (int)(Math.random()*6)*100+5;
					
				}while(rockLeftXValues[i] == rockLeftXValues[i-1]); //ENSURE ROCKS DON'T OVERLAP
			}
			
			for(int i = 0;i<numberOfRocks; i++) {
				rocks[i] = new Images(rockLeftXValues[i],yLevel+5,"rock.png"); //USE GENERATED X VALUES
			}
		}
		
	}
	
	public void newWaterWithLogs(int yLevel) { //MAKES INITIAL LOGS ON SCREEN
		logSpeed = 4; 
		int direction = (int)(Math.random()*2); //RANDOMIZE IF LOGS GO LEFT OR RIGHT
		if(direction == 1) {
			logSpeed *= -1;
		}
		int numberOfLogs = (int)(Math.random()*2)+2; //ONLY 2 OR 3 INITIALLY
		logs = new ArrayList<Images>(); //ARRAYLIST SO MORE CAN BE ADDED IN MOVEMENT
		initialLogLeftXValues = new int[numberOfLogs];
		
		if(numberOfLogs == 2) { //IF THERE ARE TWO STARTING, THEY ARE BOTH BIG WITH SET LOCATIONS
			initialLogLeftXValues[0] = 25;

			initialLogLeftXValues[1] = 320; //THIS DISTANCE LEAVES SPACE BETWEEN THEM
			
			for(int i = 0; i<numberOfLogs;i++) { //ADD THE INITIAL LOGS TO THE ARRAY
				logs.add(new Images(initialLogLeftXValues[i],yLevel+8,"logBig.png"));  //16 tall, 8 from each height
			}
			if(logSpeed > 0) {//PUT A PENDING LOG ON THE SIDE SO WAIT ISN'T UNEVEN; LEFT IF MOVING RIGHT
				logs.add(new Images(-173,yLevel+8,"logSmall.png"));
			} 
			else {//PUT A PENDING LOG ON THE SIDE SO WAIT ISN'T UNEVEN; ON RIGHT IF MOVING LEFT
				logs.add(new Images(600,yLevel+8,"logSmall.png"));
			}
		}else {
			
			initialLogLeftXValues[0] = 10; //OTHERWISE, THREE SMALL LOGS WITH SET POSITIONS
			initialLogLeftXValues[1] = 233; 
			initialLogLeftXValues[2] = 456; 
			
			for(int i = 0; i<numberOfLogs;i++) { //ADD THEM TO THE ARRAY
				logs.add(new Images(initialLogLeftXValues[i],yLevel+8,"logSmall.png"));
			}
			if(logSpeed > 0) { //ADD A PENDING BIG LOG ON LEFT IF ITS MOVING RIGHT
				logs.add(new Images(-245,yLevel+8,"logBig.png"));
			} 
			else { //ADD A PENDING BIG LOG ON RIGHT IF ITS MOVING LEFT
				logs.add(new Images(640,yLevel+8,"logBig.png"));
			}
		}
		
	}
	
	public void newRoad(int yLevel) { //make one initially
		carSpeed = 10;
		cars = new ArrayList<Images>();
		int direction = (int)(Math.random()*2);
		if(direction == 1) { //RANDOMIZE IF CARS ARE MOVING LEFT OR RIGHT
			carSpeed *= -1;
		}
		
		//ONLY ONE CAR INITIALLY ON SCREEN
		
		int carType1 = (int)(Math.random()*3); //IF CAR IS RED, GREEN, OR BLUE
		int carPosition1 = (int)(Math.random()*4)*150;
		if(carSpeed > 0) { //IF MOVING RIGHT, USE RIGHT IMAGES
			if(carType1 == 0) {
				cars.add(new Images(carPosition1,yLevel+10,"Car_Right_Red.png"));
			}else if(carType1 == 1) {
				cars.add(new Images(carPosition1,yLevel+10, "Car_Right_Green.png"));
			}else {
				cars.add(new Images(carPosition1,yLevel+10, "Car_Right_Blue.png"));
			}
		}else { //IF MOVING LEFT, USE LEFT IMAGES
			if(carType1 == 0) {
				cars.add(new Images(carPosition1,yLevel+10,"Car_Left_Red.png"));
			}else if(carType1 == 1) {
				cars.add(new Images(carPosition1,yLevel+10, "Car_Left_Green.png"));
			}else {
				cars.add(new Images(carPosition1,yLevel+10, "Car_Left_Blue.png"));
			}
		}
	}
	
	public void newRailRoad(int yLevel) {//CREATE THE TRAIN, BUT PUT IT OFF-SCREEN
		trainSpeed = 200;
		int direction = (int)(Math.random()*2); //RANDOMIZE IF TRAIN IS MOVING LEFT OR RIGHT
		if(direction == 1) {
			trainSpeed *= -1;
			train = new Images(2096,yLevel+15,"train.png"); //IF MOVING LEFT, PUT IT ON THE RIGHT
		}else {
			train = new Images(-1495,yLevel+15,"train.png"); //IF MOVING RIGHT, PUT IT ON THE LEFT
		}	
	}
	
	public void newGrassWithNoRocks(int yLevel) {
		//NOTHING HERE TO GENERATE, BUT A GOOD PLACEHOLDER
	}
	
	public void drawRow(Graphics g) { //TIME TO DRAW THE ROW, BUT YOU NEED TO TAKE INTO ACCOUNT ITS TYPE
		
		switch(getTypeOfRow()) {
		case 0:
			drawNewWaterWithLilyPads(g, yLevelOfRow); //TAKE IN GRAPHICS TO DRAW, AND ITS YLEVEL TO BASE IMAGE POSITIONS
			break;
		case 1:
			drawNewGrassWithRocks(g, yLevelOfRow);
			break;
		case 2:
			drawNewRailRoad(g, yLevelOfRow);
			break;
		case 3:
			drawNewWaterWithLogs(g, yLevelOfRow);
			break;
		case 4:
			drawNewRoad(g, yLevelOfRow);

			break;
		case 5:
			drawNewGrassWithNoRocks(g, yLevelOfRow);
		}
	}
	
	
	public void drawCoinImage(Graphics g, int yLevel) {
		g.drawImage(coin.getImg(),coin.getImgX(),coin.getImgY(),null);
		}
	
	public void drawPowerUpImage(Graphics g, int yLevel) {
		g.drawImage(powerUp.getImg(),powerUp.getImgX(),powerUp.getImgY(),null);
	}
	
	public void drawPowerUpWithConditions(Graphics g, int yLevel) { //IF CONDITIONS ARE MET, DRAW THE POWERUP
		if(powerUp!=null) {
			switch(getTypeOfRow()) {
			case 2,4,5: //FOR THESE ROWS, NOTHING IS IN THE WAY
				drawPowerUpImage(g,yLevel);
				break;
			case 0:
				while(!inLilyPads(powerUp) && !inSecretSpot(powerUp)) { //IF THE COIN ISNT INSIDE LILYPADS OR IN ITS TRASH SPOT, YOU PUT IT IN A NEW SPOT
					generatePowerUp(yLevel);
				}
				drawPowerUpImage(g,yLevel);
				break;
			case 1:
				while(inRocks(powerUp)) { //IF THE COIN IS INSIDE ROCKS, REGENRATE A NEW POSITION
					generatePowerUp(yLevel);
				}
				drawPowerUpImage(g,yLevel);
				break;
			}
		}
	}
	
	public void drawCoinWithConditions(Graphics g, int yLevel) {//IF CONDITIONS ARE MET, DRAW THE COIN
		if(coin!=null) {
			switch(getTypeOfRow()) {
			case 2,4,5:
				drawCoinImage(g,yLevel);
				break;
			case 0:
				while(!inLilyPads(coin) && !inSecretSpot(coin)) { //IF NOT IN LILYPADS OR SECRET SPOT, REGENERATE
					generateCoin(yLevel);
				}
				drawCoinImage(g,yLevel);
				break;
			case 1:
				while(inRocks(coin)) { //IF IN ROCKS, REGENERATE
					generateCoin(yLevel);	
				}
				drawCoinImage(g,yLevel);
				break;
			}
		}
	}
	
	public boolean inLilyPads(Images coin) {//----------------------------------See if a coin is in a certain obstacle---------------------------------------
		for(int i = 0; i < lilyPads.length; i++) {
			if(coin.isCenterInside(lilyPads[i])) {
				return true;
			}
		}
		return false;
	}
	
	public boolean inSecretSpot(Images coin) {
		if(coin.getImgX() == -100 && coin.getImgY() == -100)
			return true;
		return false;
	}
	
	public boolean inRocks(Images coin) {
		for(int i = 0; i<rocks.length;i++) {
			if(coin.isCenterInside(rocks[i]))
				return true;
		}
		return false;
	}
	
	public boolean inLogs(Images coin) {
		for(int i = 0; i < logs.size();i++) {
			if(coin.isCenterInside(logs.get(i)))
				return true;
		}
		return false;
	}//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public void drawNewGrassWithNoRocks(Graphics g, int yLevel) {//methods to draw each kind of obstacle--------------------------------------------------------------------------
		g.setColor(Color.green);
		g.fillRect(0, yLevel, 600, 100);
		drawCoinWithConditions(g, yLevelOfRow);
		drawPowerUpWithConditions(g,yLevelOfRow);
	}
	
	public void drawNewRoad(Graphics g, int yLevel) {
		g.setColor(Color.GRAY);
		g.fillRect(0,yLevel, 600, 100); //background of road
		
		g.setColor(Color.white);
		for(int i = 1; i <8; i++) { //white stripes on road
			g.fillRect((100*i)-25, (yLevel +45), 26, 10);
		}
	
		drawCoinWithConditions(g, yLevel);
		drawPowerUpWithConditions(g,yLevelOfRow); 
		
		for(int i = 0; i < cars.size();i++) {
			g.drawImage(cars.get(i).getImg(), cars.get(i).getImgX(), cars.get(i).getImgY(), null);
		}
	}
	
	public void drawNewWaterWithLilyPads(Graphics g, int yLevel) {
		g.setColor(Color.cyan);
		g.fillRect(0, yLevel, 600, 100);

		for(int i = 0; i<lilyPads.length;i++) {
			g.drawImage(lilyPads[i].getImg(),lilyPads[i].getImgX(),lilyPads[i].getImgY(),null);
		}
	
		drawCoinWithConditions(g, yLevel);
		drawPowerUpWithConditions(g,yLevelOfRow);
	}
	
	public void drawNewGrassWithRocks(Graphics g, int yLevel) {
		g.setColor(Color.green);
		g.fillRect(0, yLevel, 600, 100); //draw background grass
		
		for(int i = 0; i<rocks.length; i++) {
			g.drawImage(rocks[i].getImg(),rocks[i].getImgX(),rocks[i].getImgY(),null);
		}
		
		drawCoinWithConditions(g, yLevel);
		drawPowerUpWithConditions(g,yLevelOfRow);
	}
	
	public void drawNewWaterWithLogs(Graphics g, int yLevel) {
		
		g.setColor(Color.cyan);
		g.fillRect(0, yLevel, 600, 100); //draw background water

		for(int i = 0; i<logs.size();i++) {
			g.drawImage(logs.get(i).getImg(),logs.get(i).getImgX(),logs.get(i).getImgY(),null);
		}
	}
	
	public void drawNewRailRoad(Graphics g, int yLevel) {
		g.setColor(Color.green);
		g.fillRect(0, yLevel, 600, 100); //background
		
		//draw tracks
		
		//vertical tracks
		g.setColor(new Color(56,42,41));
		for(int i = 0; i <=8;i++) {
			g.fillRect(i*75+53, yLevel, 19, 100);
		}
		
		//horizontal tracks second
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, yLevel+14, 600, 19);
		g.fillRect(0, yLevel+70, 600, 19);
		
		//red light thing
		g.setColor(Color.black);
		g.fillRect(484, yLevel+90, 20, 10);
		g.setColor(trainWarning);
		g.fillRect(484, yLevel+80, 20, 10);
		
	
		drawCoinWithConditions(g, yLevel);
		drawPowerUpWithConditions(g,yLevelOfRow);
		
		//draw train (but it's off-screen)
		g.drawImage(train.getImg(),train.getImgX(),train.getImgY(),null);
	}//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public void moveRow(int time) {
		switch(getTypeOfRow()) {
		case 3: //logs
			moveWaterWithLogs(time);
			break;
		case 2: //railroad
			moveRailRoad(time);
			break;
		case 4://road
			moveRoad(time);
			break;
		}
	}
	
	public void moveRailRoad(int time) {//methods to move each kind of obstacle (cars, train, and logs)-------------------------------------------------------------------------------
		int waitTime = (int)(Math.random()*20)+10; //between 20 and 10 ticks
		
		if(time >= waitTime && time%10 == 5 && time <= 30+waitTime) { //change the color of the light to warn when train's coming
			trainWarning = Color.red;
		}
		if(time >= waitTime && time%10 == 0 && time <= 30+waitTime) {//blink off/ blink on loop
			trainWarning = Color.gray;
		}
		if(time % 5 == 0 && time >= 35+waitTime) { //once blinking is done, then the train moves
			train.setImgX(train.getImgX()+trainSpeed);
		}
	}
	
	public void moveWaterWithLogs(int time) {
		
		//draw original logs, then generate new ones as time goes on
		for(int i = 0; i <logs.size();i++) {
			logs.get(i).setImgX(logs.get(i).getImgX()+logSpeed);
		} 
		int typeOfLog = (int)(Math.random()*2);
		
		if(time % 80 == 0) {
			if(typeOfLog == 0) { //randomly choose a big or small log
				if(getLogSpeed() > 0) {
					logs.add(new Images(-245,getyLevelOfRow()+8,"logBig.png"));
				}
				else {
					logs.add(new Images(600,getyLevelOfRow()+8,"logBig.png"));
				}
			}
			else {
				if(getLogSpeed() >0) {
					logs.add(new Images(-173,getyLevelOfRow()+8, "logSmall.png"));
				}else {
					logs.add(new Images(600,getyLevelOfRow()+8, "logSmall.png"));
				}
			}
		}
	}
	
	public void moveRoad(int time) {
		
		//draw initial car, then generate more in random positions as time goes on
		for(int i = 0; i<cars.size();i++) {
			cars.get(i).setImgX(cars.get(i).getImgX()+carSpeed);
		}
		
		int typeOfCar = (int)(Math.random()*3);
		int position = (int)(Math.random()*4)*150;
		
		
		if(time % 30 == 0) {
			if(carSpeed > 0) {
				if(typeOfCar == 0) {
					cars.add(new Images(0-position,getyLevelOfRow()+10,"Car_Right_Red.png"));
				}else if(typeOfCar == 1) {
					cars.add(new Images(0-position,getyLevelOfRow()+10, "Car_Right_Green.png"));
				}else {
					cars.add(new Images(0-position,getyLevelOfRow()+10, "Car_Right_Blue.png"));
				}
			}else {
				if(typeOfCar == 0) {
					cars.add(new Images(600+position,getyLevelOfRow()+10,"Car_Left_Red.png"));
				}else if(typeOfCar == 1) {
					cars.add(new Images(600+position,getyLevelOfRow()+10, "Car_Left_Green.png"));
				}else {
					cars.add(new Images(600+position,getyLevelOfRow()+10, "Car_Left_Blue.png"));
				}
			}
		}
	}//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	public int getyLevelOfRow() {
		return yLevelOfRow;
	}

	public void setyLevelOfRow(int yLevelOfRow) {
		this.yLevelOfRow = yLevelOfRow;
	}

	public int getTypeOfRow() {
		return typeOfRow;
	}

	public void setTypeOfRow(int typeOfRow) {
		this.typeOfRow = typeOfRow;
	}

	public int getLevelWidth() {
		return levelWidth;
	}

	public void setLevelWidth(int levelWidth) {
		this.levelWidth = levelWidth;
	}

	public int getLevelHeight() {
		return levelHeight;
	}

	public void setLevelHeight(int levelHeight) {
		this.levelHeight = levelHeight;
	}

	public Images[] getLilyPads() {
		return lilyPads;
	}

	public void setLilyPads(Images[] lilyPads) {
		this.lilyPads = lilyPads;
	}

	public int[] getLilyPadLeftXValues() {
		return lilyPadLeftXValues;
	}

	public void setLilyPadLeftXValues(int[] lilyPadLeftXValues) {
		this.lilyPadLeftXValues = lilyPadLeftXValues;
	}

	public Images[] getRocks() {
		return rocks;
	}

	public void setRocks(Images[] rocks) {
		this.rocks = rocks;
	}

	public int[] getRockLeftXValues() {
		return rockLeftXValues;
	}

	public void setRockLeftXValues(int[] rockLeftXValues) {
		this.rockLeftXValues = rockLeftXValues;
	}

	public ArrayList<Images> getLogs() {
		return logs;
	}

	public void setLogs(ArrayList<Images> logs) {
		this.logs = logs;
	}

	public int getLogSpeed() {
		return logSpeed;
	}

	public void setLogSpeed(int logSpeed) {
		this.logSpeed = logSpeed;
	}

	public int[] getInitialLogLeftXValues() {
		return initialLogLeftXValues;
	}

	public void setInitialLogLeftXValues(int[] initialLogLeftXValues) {
		this.initialLogLeftXValues = initialLogLeftXValues;
	}

	public ArrayList<Images> getCars() {
		return cars;
	}

	public void setCars(ArrayList<Images> cars) {
		this.cars = cars;
	}

	public int[] getInitialCarLeftXValues() {
		return initialCarLeftXValues;
	}

	public void setInitialCarLeftXValues(int[] initialCarLeftXValues) {
		this.initialCarLeftXValues = initialCarLeftXValues;
	}

	public int getCarSpeed() {
		return carSpeed;
	}

	public void setCarSpeed(int carSpeed) {
		this.carSpeed = carSpeed;
	}
	
	public Images getTrain() {
		return train;
	}

	public void setTrain(Images train) {
		this.train = train;
	}

	public int getTrainSpeed() {
		return trainSpeed;
	}

	public void setTrainSpeed(int trainSpeed) {
		this.trainSpeed = trainSpeed;
	}

	public Color getTrainWarning() {
		return trainWarning;
	}

	public void setTrainWarning(Color trainWarning) {
		this.trainWarning = trainWarning;
	}

	public Images getCoin() {
		return coin;
	}

	public void setCoin(Images coin) {
		this.coin = coin;
	}

	public double getChanceOfCoin() {
		return chanceOfCoin;
	}

	public void setChanceOfCoin(double chanceOfCoin) {
		this.chanceOfCoin = chanceOfCoin;
	}

	public Images getPowerUp() {
		return powerUp;
	}

	public void setPowerUp(Images powerUp) {
		this.powerUp = powerUp;
	}

	public double getChanceOfPowerup() {
		return chanceOfPowerup;
	}

	public void setChanceOfPowerup(double chanceOfPowerup) {
		this.chanceOfPowerup = chanceOfPowerup;
	}

	public int getTypeOfPowerUp() {
		return typeOfPowerUp;
	}

	public void setTypeOfPowerUp(int typeOfPowerUp) {
		this.typeOfPowerUp = typeOfPowerUp;
	}

	public boolean collided(int userX, int userY) {//check to see if the user is inside any of the obstacles--------------------------------------------------------------------------
	
		
		if (getTypeOfRow() == 4) { //if its in a car on a road

			for (int i = 0; i < this.getCars().size(); i++) {

				if (this.getCars().get(i).isInside(userX, userY)) {

					return true;

				}

			}

		}

		if (getTypeOfRow() == 1) { //if its in a rock

			for (int i = 0; i < this.getRocks().length; i++) {

				if (this.getRocks()[i].isInside(userX, userY)) {

					return true;

				}

			}

		}

		if (getTypeOfRow() ==2) { //if it is inside the train

			if (this.getTrain().isInside(userX, userY+10)) {

				return true;

			}

		}

		
		if(getTypeOfRow() == 0) { //if its on a lilypad its good, if in water its not
			if(userY >= getyLevelOfRow() && userY <= getyLevelOfRow()+100) {
				for(int i = 0; i <lilyPads.length;i++) {
					if(getLilyPads()[i].isInside(userX, userY))
						return false;
				}
			return true;
			}

		}
		
		if(getTypeOfRow() == 3) { //if its on a log its good, if in water its not
			if(userY >= getyLevelOfRow() && userY <= getyLevelOfRow()+100) {
				for(int i = 0; i <logs.size();i++) {
					if(getLogs().get(i).isInside(userX, userY))
						return false;
				}
				return true;
			}
		}
	
		return false;
		
	}//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public boolean touchingCoin(int userX, int userY) {
		if(getCoin() != null) {
			if (getCoin().isInside(userX, userY)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean touchingPowerUp(int userX, int userY) {
		if(getPowerUp() != null) {
			if(getPowerUp().isInside(userX,userY)) {
				return true;
			}
		}
		return false;
	}
	
}