package battleShipWithEvolution;

import java.util.Stack;

public class battleShipWithEvolution {
	public static void main(String[] args){
		//First we initialize all the constants
		final int playerNumber = 6;
		final int gameNumber = 10000;
		final int winNeeded = 50;
		boolean goFirst;
		int winner;
		//Then we will define all variables
		Player[] players = new Player[playerNumber];
		for(int i = 0; i < playerNumber; i++){
			players[i] = new Player();
		}
		int winsInRow = 0;
		do {
			for (int i = 0; i< playerNumber; i++){
				players[i].setScore(0);
			}
			for(int i = 0; i < playerNumber - 1; i++){
				for(int j = i + 1; j < playerNumber; j++){
					for(int k = 0; k < gameNumber; k++){
						goFirst = Math.random()>= 0.5;
						if (goFirst) {
							winner = players[i].play1Game(players[j]);
							if ((winner) == 1) {
								players[i].incScore();
							} else {
								players[j].incScore();
							}
						} else {
							winner = players[j].play1Game(players[i]);
							if ((winner) == 1) {
								players[j].incScore();
							} else {
								players[i].incScore();
							}
						}
						System.out.println("Game " + k + " between " + i + " and " + j + " just ended.");
					}
				}
			}
			for (int i = 0; i< playerNumber; i++){
				System.out.println("Player " + i + " score was: " + players[i].getScore());
			}
		} while (/*winsInRow < winNeeded*/ false);
	}
	public static int[] randomCoordinates(){
		int []coordinates = new int[2];
		coordinates[0] = (int) (Math.random() * 10);//select a random Y from 0-10
		coordinates[1] = (int) (Math.random() * 10);//select a random X from 0 to 10
		return coordinates;
	}
	public static int[] randomParity(int[][]fireboard){
		int []coordinates = new int[2];
		int remainder = 1;
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				if((i+j)%2 == 0){
					if(fireboard[j][i] == 0){
						remainder = 0;
					}
				}
			}
		}
		do{	
			coordinates[0] = (int) (Math.random() * 10);//select a random Y from 0-10
			coordinates[1] = (int) (Math.random() * 10);//select a random X from 0 to 10
		}while((coordinates[0]+coordinates[1])%2 != remainder);
		return coordinates;
	}
	public static int[] huntTargetParityCoordinates(int[][] fireboard, Stack<Integer> targetStack){
		if(targetStack.isEmpty()){
			return randomParity(fireboard);
		}
		int targetCoordinates[] = new int [2];
		// We're going to pop the next coordinates from the stack and return them
		targetCoordinates[0] = targetStack.pop();
		targetCoordinates[1] = targetStack.pop();
		return targetCoordinates;
	}
	public static int[][] placeShips(){
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
						if (orientation == 0) { // place the ship from row, column to row - ship length + 1, column
				for(int j = shipRow; j > shipRow - shipLength; j--){
					shipBoard[j][shipColumn] = shipType;
				}
			}
			if (orientation == 1) { // place the ship from row, column to row, column - ship length
				for(int j = shipColumn; j < shipColumn + shipLength; j++){
					shipBoard[shipRow][j] = shipType;
				}
			}
			if (orientation == 2) { // place the ship from row, column to row + ship length -1, column 
				for(int j = shipRow; j < shipRow + shipLength; j++){
					shipBoard[j][shipColumn] = shipType;
				}
			}
			if (orientation == 3) { // place the ship from row, column to row, column + ship length
				for(int j = shipColumn; j > shipColumn - shipLength ; j--){
					shipBoard[shipRow][j] = shipType;
				}
			}
		}
		return shipBoard;
	}
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
}
