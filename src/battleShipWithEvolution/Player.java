package battleShipWithEvolution;

import java.util.Stack;

public class Player {
	
	final int geneNumber = 5;
	private int[] genes;
	/**
	 * gene[0] determines the hunt action: 0 is random, 1 is parity
	 * gene[1] determines the target action: 0 is random, 1 is target, 2 is parity
	 * gene[2] determines the target type: 0 is thorough, 1 is lazy
	 * gene[3] determines the hunt complexity: 0 is simple (always uses mod 2), 1 is complex (uses the mod of the smallest ship left)
	 * gene[4] determines the starting parity: 0 means that mod2 will be compared to 0, 1 means that mod 2 will be compared to 1
	 */
	private int score;
	private int mode;//0 means hunt, 1 means target
	private Stack<Integer> targetStack;
	private int[][]shipBoard = new int[10][10];//this keeps track of where all of P1's ships are
	private int[][]fireBoard = new int[10][10];// 0 = not fired at, 1 = fired but missed, 2 == fired and hit
	private boolean[]sunkShips = new boolean [5];//this array keeps track of the sunken ship types for P1
	/**
	 * this is the constructor for a player
	 * @param playerGenes the constructor needs to be told the genes of the player
	 * All other instance variables are set to a default by the constructor
	 * e.g: the target stack is initialized as an empty stack
	 */
	public Player(int[] playerGenes) {
		genes = playerGenes;
		score = 0;
		mode = 0;
		targetStack = new Stack<Integer>();
		for (int i = 0; i<5; i++){
			sunkShips[i] = false;
		}
		// reset the fireBoard and shipBoards to all be empy
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				shipBoard[i][j] = 0;
				fireBoard[i][j] = 0;
			}
		}
	}
	public Player() {
		int[]playerGenes = new int[geneNumber];
		for(int i = 0; i < geneNumber; i++){
			if (i == 1){
				playerGenes[i] = (int) (Math.random()*3);
			} else {
				playerGenes[i] = (int) (Math.random()*2);
			}
		}
		genes = playerGenes;
		score = 0;
		mode = 0;
		targetStack = new Stack<Integer>();
		for (int i = 0; i<5; i++){
			sunkShips[i] = false;
		}
		// reset the fireBoard and shipBoards to all be empy
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				shipBoard[i][j] = 0;
				fireBoard[i][j] = 0;
			}
		}
	}
	
	
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void incScore() {
		this.score++;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	public int[][] getShipBoard() {
		return shipBoard;
	}
	public void setShipBoard(int[][] shipBoard) {
		this.shipBoard = shipBoard;
	}
	public int[][] getFireBoard() {
		return fireBoard;
	}
	public void setFireBoard(int[][] fireBoard) {
		this.fireBoard = fireBoard;
	}
	public void mutate(){
		int mutatedAllele = (int) (Math.random()*geneNumber);
		if (mutatedAllele == 1){
			genes[mutatedAllele] = (int) (Math.random()*3);
		} else {
			genes[mutatedAllele] = (int) (Math.random()*2);
		}
	}
	/**
	 * This method returns a new player given two players.  The returned player is random combination of each of the two player's genes.
	 * @param other - the second player to be combined
	 * @return : a new Player that is a random combination of the two given players. 
	 */
	public Player combine(Player other){
		int[] newGenes = new int[geneNumber];
		int firstPlayer = (int) (Math.random()*2);
		int cutPoint = (int) (Math.random()*4)+1;
		if (firstPlayer == 0){
			for(int i = 0; i < geneNumber; i++){
				if(i < cutPoint){
					newGenes[i] = this.genes[i];
				} else {
					newGenes[i] = other.genes[i];
				}
			}
		} else {
			for(int i = 0; i < geneNumber; i++){
				if(i >= cutPoint){
					newGenes[i] = this.genes[i];
				} else {
					newGenes[i] = other.genes[i];
				}
			}
		}
		return new Player(newGenes);
	}
	
	public int play1Game(Player other){
		int winner = 0;
		this.shipBoard = battleShipWithEvolution.placeShips();
		other.shipBoard = battleShipWithEvolution.placeShips();
		this.clearFireBoard();
		this.emptyTargetStack();
		other.clearFireBoard();
		other.emptyTargetStack();
		while (winner == 0){
			winner = this.fire(other);
			if(winner == 1){
				break;
			}
			winner = other.fire(this);
			if (winner ==1) {
				winner = 2;
			}
		}
		return winner;
	}
	public int fire(Player other){
		int shipX = 0;
		int shipY = 0;
		int[] coordinates = {0, 0};
		int shipsSunk = 0;
//		String[] shipNames = new String[] {"speedboat","submarine","battleship","aircraft carrier","destroyer"};
		do {
			do {
				if(this.mode == 0){
					// here we need to be hunting
					if (this.genes[0] == 0) {
						// here we need to be hunting using random
						coordinates = battleShipWithEvolution.randomCoordinates();
					} else {
						// here we need to be hunting using parity
						coordinates = battleShipWithEvolution.randomParity(this.fireBoard);
					}
				} else {
					// here we need to be targeting
					if (this.genes[1]== 0) {
						// here we need to be targeting randomly
						coordinates = battleShipWithEvolution.randomCoordinates();
					} else if(this.genes[1] == 1){
						// here we need to be targeting in thorough mode
						coordinates = battleShipWithEvolution.huntTargetParityCoordinates(this.fireBoard, this.targetStack);
					} else {
						// here we need to be targeting with parity
						coordinates = battleShipWithEvolution.randomParity(this.fireBoard);
					}
				}
				shipX = coordinates[0];
				shipY = coordinates[1];
			} while ((shipX<0)||(shipY<0)||(shipX>9)||(shipY>9)); // This condition ensures that the final targeting coordinates are on the board
		} while (fireBoard[shipX][shipY] != 0); // This condition ensures that the final targeting coordinates have not been fired on before
		if(other.shipBoard[shipX][shipY] != 0){
			this.mode = 1;
			other.shipBoard[shipX][shipY] = 0;
			this.fireBoard[shipX][shipY] = 2;
			// push coordinates of north square
			this.targetStack.push(shipY-1);
			this.targetStack.push(shipX);
			// push coordinate of the south square
			this.targetStack.push(shipY+1);
			this.targetStack.push(shipX);
			// push coordinate of the east square
			this.targetStack.push(shipY);
			this.targetStack.push(shipX+1);
			// push coordinate of the west square
			this.targetStack.push(shipY);
			this.targetStack.push(shipX-1);
//			SimpleGraphics.fillCircle((22 + (player - 1)*270 + shipY*25), 33 + shipX*25, 5, "red");
		}
		else{
			this.fireBoard[shipX][shipY] = 1;
//			SimpleGraphics.fillCircle(22 + (player -1)*270 + shipY*25, 33 + shipX*25, 5, "black");
		}
		//sleep(70);
		for(int i = 2; i < 7; i++){
			int shipsLeft = 0;
			for(int x = 0; x < 10; x++){
				for(int y = 0; y < 10; y++){
					if(other.shipBoard[x][y] == i){
						shipsLeft++;
					}
				}
			}
			if(shipsLeft == 0){
				shipsSunk++;
				if(!other.sunkShips[i-2]){
					//System.out.println("You sunk the " + shipNames[i-2]);
					//sleep(1000);
					other.sunkShips[i-2] = true;
					this.mode = 0;
					if(this.genes[2] == 1){
						while(!this.targetStack.isEmpty()){
							this.targetStack.pop();
						}
					}
				}
			}
		}
		if(shipsSunk == 5){
			return 1;
		}
		return 0;
	}
	public void clearFireBoard() {
		for( int i = 0; i < 10; i++){
			for( int j = 0; j < 10;j++){
				fireBoard[i][j] = 0;
			}
		}
	}
	public void emptyTargetStack() {
		while(!this.targetStack.isEmpty()){
			this.targetStack.pop();
		}
	}
	public String printGenes(int playerNumber){
		String returnString = "" + playerNumber;
		for(int i = 0; i < geneNumber; i++){
			returnString = returnString + (", " + genes[i]);
		}
		return returnString;
	}
}
