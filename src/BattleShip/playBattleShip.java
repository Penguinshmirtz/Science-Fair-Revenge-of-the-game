package BattleShip;
import java.util.*;

public class playBattleShip {
	public static void main(String[] args){
		//The main method will play a game of Battleship, and determine the winner of the game
		//The strategies called by the main method can vary and include random

		//First we initialize all the variables used by the program
		int winner = 0;
		int[][]shipBoardP1 = new int[10][10];//this keeps track of where all of P1's ships are
		int[][]shipBoardP2 = new int[10][10];
		int[][]fireBoardP1 = new int[10][10];// 0 = not fired at, 1 = fired but missed, 2 == fired and hit
		int[][]fireBoardP2 = new int[10][10];
		boolean[]sunkShipsP1 = new boolean [5];//this array keeps track of the sunken ship types for P1
		boolean[]sunkShipsP2 = new boolean [5];
		int[] stateVar1 = {0, 0, 0}; // this array keeps track of the hunt target mode for player 1
		int[] stateVar2 = {0, 0, 0}; // this array keeps track of the hunt target model for player 2
		Stack<Integer> targetStackP1 = new Stack<Integer>();
		Stack<Integer> targetStackP2 = new Stack<Integer>();
		for (int i = 0; i<5; i++){
			sunkShipsP1[i] = false;
			sunkShipsP2[i] = false;
		}

		//Now we place the ships on the ship board
		shipBoardP1=placeShips(1);
		shipBoardP2=placeShips(2);
		// Neil, please draw four large squares lined up as per the design
		SimpleGraphics.addText(70, 20, "PLAYER 1 FIRE BOARD");
		SimpleGraphics.addText(350, 20, "PLAYER 2 FIRE BOARD");
		SimpleGraphics.addText(70, 300, "PLAYER 1 SHIP BOARD");
		SimpleGraphics.addText(350, 300, "PLAYER 2 SHIP BOARD");
		SimpleGraphics.drawRectangle( 20, 30, 250, 250);
		SimpleGraphics.drawRectangle(20, 310, 250, 250);
		SimpleGraphics.drawRectangle(290, 310, 250, 250);
		SimpleGraphics.drawRectangle(290, 30, 250, 250);
		while (winner == 0){
			winner = fire(fireBoardP1, shipBoardP2, sunkShipsP2, 1, stateVar1, targetStackP1);
			if(winner == 1){
				break;
			}
			winner = fire(fireBoardP2, shipBoardP1, sunkShipsP1, 2, stateVar2, targetStackP2);
			if (winner ==1) winner = 2;
		}
		System.out.println("Player " + winner + " has won!");
		/*System.out.println("Here is the Player 1 FireBoard");
		for (int i = 0;i<10;i++) {
			for (int j = 0; j<10; j++) {
				System.out.print(fireBoardP1[i][j]);
			}
			System.out.println();
		}
		System.out.println("Here is the Player 2 FireBoard");
		for (int i = 0;i<10;i++) {
			for (int j = 0; j<10; j++) {
				System.out.print(fireBoardP2[i][j]);
			}
			System.out.println();
		}*/
	}

	/**
	 * This method places the ships on a shipboard, ensuring that ships do not overlap, and updating the graphics to show the placement
	 * The current version randomly selects the location and orientation - but we've methodized this to allow us to easily use a smarter
	 * method of placement in the future.
	 * @param player 1 - for player 1 --- 2 - for player 2
	 * @return a 10 by 10 array of ints representing the placement of the ships
	 */
	public static int[][] placeShips(int player){
		int[][]shipBoard = new int[10][10];
		int orientation = 0;
		int shipLength = 0;
		int shipType = 0;
		int shipRow = 0;
		int shipColumn = 0;
		int[] matrixReturned = new int[] { 0, 0, 0};
		for(double i = 2.4; i < 5.5; i = i + 0.7){
			shipLength = (int) i;
			if (Math.abs(i-3.1) < 0.00001) {
				shipType = 6;
			} else {
				shipType = (int) i;
			}
			do {
				matrixReturned = randomPlacement(shipLength, shipType);
				orientation = matrixReturned[0];
				shipRow = matrixReturned[1];
				shipColumn = matrixReturned[2];
			} while (isShipThere(orientation, shipRow, shipColumn, shipLength, shipBoard)) ;
			//			System.out.println("Ship Type is : " + shipType);
			//			System.out.println("Ship Length is : " + shipLength);
			//			System.out.println("Ship Orientation is : " + orientation);
			//			System.out.println("Ship Row is : " + shipRow);
			//			System.out.println("Ship Column is : " + shipColumn);

			if (orientation == 0) { // place the ship from row, column to row - ship length + 1, column
				for(int j = shipRow; j > shipRow - shipLength; j--){
					shipBoard[j][shipColumn] = shipType;
					SimpleGraphics.fillCircle((22 + (player - 1)*270 + shipColumn*25), 313 + j*25, 5, "grey");
				}
			}
			if (orientation == 1) { // place the ship from row, column to row, column - ship length
				for(int j = shipColumn; j < shipColumn + shipLength; j++){
					shipBoard[shipRow][j] = shipType;
					SimpleGraphics.fillCircle((22 + (player - 1)*270 + j*25), 313 + shipRow*25, 5, "grey");
				}
			}
			if (orientation == 2) { // place the ship from row, column to row + ship length -1, column 
				for(int j = shipRow; j < shipRow + shipLength; j++){
					shipBoard[j][shipColumn] = shipType;
					SimpleGraphics.fillCircle((22 + (player - 1)*270 + shipColumn*25), 313 + j*25, 5, "grey");
				}
			}
			if (orientation == 3) { // place the ship from row, column to row, column + ship length
				for(int j = shipColumn; j > shipColumn - shipLength ; j--){
					shipBoard[shipRow][j] = shipType;
					SimpleGraphics.fillCircle((22 + (player - 1)*270 + j*25), 313 + shipRow*25, 5, "grey");
				}
				//printShipBoards(shipBoard);
			}
		}
		return shipBoard;
	}
	/***
	 * The fire method fires at a set of coordinates that have not been fired at previously.  The method for selecting the coordinates
	 * can be random, and in future versions we will add smarter methods of selecting the coordinates.
	 * @param fireBoard a 10 by 10 array of ints containing the fireboard of the player attempting to fire
	 * @param shipBoard a 10 by 10 array of ints containing the shipboard of the opponent of the player attempting to fire
	 * @param sunkShips an array of booleans indicating which ships have already been sunk (for the opponent of the player attempting to fire)
	 * @param player 1 for player 1 and 2 for player 2 - this is used to ensure the proper placement of graphics
	 * @return an int specifying:
	 * 		0 = the firing player has not won
	 * 		1 = the firing player has won
	 */
	public static int fire(int[][] fireBoard, int[][] shipBoard, boolean[]sunkShips, int player, int[] stateVar, Stack<Integer> targetStack){
		int shipX = 0;
		int shipY = 0;
		int[] coordinates;
		int shipsSunk = 0;
		String[] shipNames = new String[] {"speedboat","submarine","battleship","aircraft carrier","destroyer"};
		do {
			do {
				if (player == 1){coordinates = lazyHuntTargetParityCoordinates(fireBoard, stateVar, targetStack);}
				else{coordinates = randomCoordinates();}
				shipX = coordinates[0];
				shipY = coordinates[1];
			} while ((shipX<0)||(shipY<0)||(shipX>9)||(shipY>9)); // This condition ensures that the final targeting coordinates are on the board
		} while (fireBoard[shipX][shipY] != 0); // This condition ensures that the final targeting coordinates have not been fired on before
		if(shipBoard[shipX][shipY] != 0){
			shipBoard[shipX][shipY] = 0;
			fireBoard[shipX][shipY] = 2;
			stateVar[0] = 1;
			stateVar[1] = shipX;
			stateVar[2] = shipY;
			// push coordinates of north square
			targetStack.push(shipY-1);
			targetStack.push(shipX);
			// push coordinate of the south square
			targetStack.push(shipY+1);
			targetStack.push(shipX);
			// push coordinate of the east square
			targetStack.push(shipY);
			targetStack.push(shipX+1);
			// push coordinate of the west square
			targetStack.push(shipY);
			targetStack.push(shipX-1);
			SimpleGraphics.fillCircle((22 + (player - 1)*270 + shipY*25), 33 + shipX*25, 5, "red");
		}
		else{
			fireBoard[shipX][shipY] = 1;
			SimpleGraphics.fillCircle(22 + (player -1)*270 + shipY*25, 33 + shipX*25, 5, "black");
		}
		sleep(70);
		for(int i = 2; i < 7; i++){
			int shipsLeft = 0;
			for(int x = 0; x < 10; x++){
				for(int y = 0; y < 10; y++){
					if(shipBoard[x][y] == i){
						shipsLeft++;
					}
				}
			}
			if(shipsLeft == 0){
				shipsSunk++;
				if(!sunkShips[i-2]){
					System.out.println("You sunk the " + shipNames[i-2]);
					sleep(1000);
					sunkShips[i-2] = true;
					while(!targetStack.isEmpty()){
						targetStack.pop();
					}
				}
			}
		}
		if(shipsSunk == 5){
			return 1;
		}
		return 0;
	}
	/**
	 * A method used in debugging only - it prints to the console a textual representation of the ship board
	 * @param SB - a 10 by 10 array of ints containing the ship board you wish to print 
	 */
	public static void printShipBoards(int[][]SB) {
		System.out.println("Here is t 1 ShipBoard");
		for (int i = 0;i<10;i++) {
			for (int j = 0; j<10; j++) {
				System.out.print(SB[i][j]);
			}
			System.out.println();
		}
	}
	/**
	 * This method placed the thread (program) on pause for a specified number of milliseconds
	 * @param time an int specifying the number of milliseconds to pause
	 */
	public static void sleep(int time){
		try{
			Thread.sleep(time);
		} catch (Exception e) {}
	}
	/**
	 * this randomly selects a way to place 1 ship on the ship board
	 * @param shipLength an int for the length of the ship you are trying to place
	 * @param shipType an int that determines the type of ship you are trying to place
	 * 		2 = speedboat
	 * 		3 = submarine
	 * 		4 = battleship
	 * 		5 = aircraft carrier
	 * 		6 = destroyer
	 * @return a three int array with:
	 * 		the 0 element containing the orientation of the ship
	 * 		the 1 element containing a random row to place the ship
	 * 		the 2 element containing a random column to place the ship 
	 */
	public static int[] randomPlacement(int shipLength, int shipType){
		int randomColumn = 0;
		int randomRow = 0;
		int orientation = (int) (Math.random()*4);
		if (orientation%2 == 0){
			randomColumn = (int) (Math.random()*10);
			randomRow = (int) (Math.random() * (11-shipLength));
			if (orientation == 0){
				randomRow = randomRow + shipLength - 1;
			}
		}else {
			randomRow = (int) (Math.random()*10);
			randomColumn = (int) (Math.random() * (11-shipLength));
			if (orientation == 3){
				randomColumn = randomColumn + shipLength - 1;
			}

		}
		int[] matrixReturned = new int [] {orientation, randomRow, randomColumn};
		return matrixReturned;
	}
	/**
	 * determines if there is a ship on the squares that you are trying to place a new ship on
	 * @param orientation an int that determines the orientation of the ship
	 * 		0 = north
	 * 		1 = east
	 * 		2 = south
	 * 		3 = west
	 * @param shipRow an int for the row of the ship's starting point
	 * @param shipColumn an int for the column of the ship's starting point
	 * @param shipLength an int for the length of the ship
	 * @param shipBoard an array of ints that determine where the player's ships
	 * @return a true/false value whether there is a ship on one of the squares you want to put a ship on
	 */
	public static boolean isShipThere(int orientation, int shipRow, int shipColumn, int shipLength, int[][] shipBoard) {
		Boolean shipIsThere = false;
		if (orientation == 0) { // place the ship from row, column to row - ship length + 1, column
			for(int j = shipRow; j > shipRow - shipLength; j--){
				if (shipBoard[j][shipColumn] !=0) shipIsThere = true;
			}
		}
		if (orientation == 1) { // place the ship from row, column to row, column - ship length
			for(int j = shipColumn; j < shipColumn + shipLength; j++){
				if (shipBoard[shipRow][j] !=0) shipIsThere = true;
			}
		}
		if (orientation == 2) { // place the ship from row, column to row + ship length -1, column 
			for(int j = shipRow; j < shipRow + shipLength; j++){
				if (shipBoard[j][shipColumn] != 0) shipIsThere = true;
			}
		}
		if (orientation == 3) { // place the ship from row, column to row, column + ship length
			for(int j = shipColumn; j > shipColumn - shipLength ; j--){
				if(shipBoard[shipRow][j] != 0) shipIsThere = true;
			}
		}
		return shipIsThere;
	}
	/**
	 * This method randomly picks an X and Y coordinate
	 * @return an array of 1 X and 1 Y
	 */
	public static int[] randomCoordinates(){
		int []coordinates = new int[2];
		coordinates[0] = (int) (Math.random() * 10);//select a random Y from 0-10
		coordinates[1] = (int) (Math.random() * 10);//select a random X from 0 to 10
		return coordinates;
	}
	public static int[] huntTargetCoordinates(int[][] fireboard, int[] stateVar){
		if(stateVar[0] == 0){
			return randomCoordinates();
		}
		int lastHitX = stateVar[1];
		int lastHitY = stateVar[2];
		int targetCoordinates[] = new int [2];
		// We're going to try north if we're still on board and it hasn't been fired on
		if ((lastHitY-1>=0)) { /*Are we still on the board?*/
			if (fireboard[lastHitX][lastHitY-1]==0) { /*Was this not fired at before?*/
				targetCoordinates[0] = lastHitX;
				targetCoordinates[1] = lastHitY-1;
				return targetCoordinates;
			}
		}
		// We're going to try east if we're still on board and it hasn't been fired on
		if ((lastHitX+1<10)) { /*Are we still on the board?*/
			if (fireboard[lastHitX+1][lastHitY]==0) { /*Was this not fired at before?*/
				targetCoordinates[0] = lastHitX+1;
				targetCoordinates[1] = lastHitY;
				return targetCoordinates;
			}
		}
		// We're going to try west if we're still on board and it hasn't been fired on
		if ((lastHitX-1>=0)) { /*Are we still on the board?*/
			if (fireboard[lastHitX-1][lastHitY]==0) { /*Was this not fired at before?*/
				targetCoordinates[0] = lastHitX-1;
				targetCoordinates[1] = lastHitY;
				return targetCoordinates;
			}
		}
		// We're going to try south if we're still on board and it hasn't been fired on
		if ((lastHitY+1<10)) { /*Are we still on the board?*/
			if (fireboard[lastHitX][lastHitY+1]==0) { /*Was this not fired at before?*/
				targetCoordinates[0] = lastHitX;
				targetCoordinates[1] = lastHitY+1;
				return targetCoordinates;
			}
		}
		// We only get here if we are surrounded by squares that cannot be fired on, something went wrong
		stateVar[0] = 0; // go back to hunt mode
		return randomCoordinates();
	}
	public static int[] randomParity(int[][]fireboard){
		int []coordinates = new int[2];
		int remainder = 1;
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				if((i+j)%2 == 0){
					if(fireboard[i][j] != 0){
						remainder = 0;
					}
				}
			}
		}
		do{	
			coordinates[0] = (int) (Math.random() * 10);//select a random Y from 0-10
			coordinates[1] = (int) (Math.random() * 10);//select a random X from 0 to 10
		}while((coordinates[0]+coordinates[1])%2!= remainder);
		return coordinates;
	}
	public static int[] huntTargetParityCoordinates(int[][] fireboard, int[] stateVar, Stack<Integer> targetStack){
		if(targetStack.isEmpty()){
			return randomParity(fireboard);
		}
		int targetCoordinates[] = new int [2];
		// We're going to pop the next coordinates from the stack and return them
		targetCoordinates[0] = targetStack.pop();
		targetCoordinates[1] = targetStack.pop();
		return targetCoordinates;
		
	}
	public static int[] lazyHuntTargetParityCoordinates(int[][] fireboard, int[] stateVar, Stack<Integer> targetStack){
		if(targetStack.isEmpty()){
			return randomParity(fireboard);
		}
		int targetCoordinates[] = new int [2];
		// We're going to pop the next coordinates from the stack and return them
		targetCoordinates[0] = targetStack.pop();
		targetCoordinates[1] = targetStack.pop();
		return targetCoordinates;
		
	}
}
