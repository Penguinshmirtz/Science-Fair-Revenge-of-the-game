package battleShipWithEvolution;

import java.io.PrintWriter;
import java.util.Stack;

public class battleShipWithEvolution {
	public static void main(String[] args){
		//First we initialize all the constants
		final int playerNumber = 8;
		final int gameNumber = 200000;
		final int winNeeded = 50;
		boolean goFirst;
		int winner;
		int z = 130;
		//Then we will define all variables
		Player[] players = new Player[playerNumber];
		Player tempPlayer = new Player();
		String printString = "";
		//for(int i = 0; i < playerNumber; i++){
			//players[i] = new Player();
		//}
		int[] player0Genes = new int[] {1, 1, 1, 1, 1};
		int[] player1Genes = new int[] {1, 1, 1, 1, 1};
		int[] player2Genes = new int[] {0, 1, 1, 1, 1,};
		int[] player3Genes = new int[] {0, 1, 1, 1, 1};
		int[] player4Genes = new int[] {0, 1, 1, 1, 1};
		int[] player5Genes = new int[] {1, 1, 1, 0, 1};
		int[] player6Genes = new int[] {0, 1, 1, 1, 1};
		int[] player7Genes = new int[] {0, 1, 1, 1, 1};
		players[0]=new Player(player0Genes);
		players[1]=new Player(player1Genes);
		players[2]=new Player(player2Genes);
		players[3]=new Player(player3Genes);
		players[4]=new Player(player4Genes);
		players[5]=new Player(player5Genes);
		players[6]=new Player(player6Genes);
		players[7]=new Player(player7Genes);
		int winsInRow = 0;
		System.out.println("Generation, rank, gene_0, gene_1, gene_2, gene_3, gene_4, score");
		do {
			for (int i = 0; i< playerNumber; i++){
				players[i].setScore(0);
			}
			for(int i = 0; i < playerNumber - 1; i++){
				for(int j = i + 1; j < playerNumber; j++){
//					System.out.println("Starting tournament between " + i + " and " + j);
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
						//System.out.println("Game " + k + " between " + i + " and " + j + " just ended.");
					}
				}
			}
					
			//We will order the players from best score to worst score
//			System.out.println("Generation " + z + " has finished.  Players have been sorted from highest to lowest score:");
			for(int l = 0; l < playerNumber - 1; l++){
				for (int k = l+1; k< playerNumber; k++) {
					if(players[l].getScore() < players[k].getScore()){
						tempPlayer = players[l];
						players[l] = players[k];
						players[k] = tempPlayer;
					}
				}
			}
			for (int i = 0; i< playerNumber; i++){
				printString = z + ", " + i + ", "+ players[i].printGenes() + ", " + players[i].getScore();
				System.out.println(printString);
			}
//			System.out.println("The best player was player 0 with a score of " + players[0].getScore());
//			System.out.println("The second best player was player 1 with a score of " + players[1].getScore());
//			System.out.println("The worst player was player "+ (playerNumber - 1) +" with a score of" + players[playerNumber-1].getScore());
			// now we have evaluated how each player is doing...
			// here we will replace the lowest rated player with a new mutant that combines the top two scorers
			players[playerNumber - 1] = players[0].combine(players[1]);
			players[playerNumber - 1].mutate();
			z++;
		} while (/*winsInRow < winNeeded*/ z < 500);
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
	public static int[] randomComplexParity(int[][]fireboard, int[][]shipBoard){
		int moduloNumber = 0;
		int []coordinates = new int[2];
		for(int i = 6; i > 1; i--){
			for(int x = 0; x < 10; x++){
				for(int y = 0; y < 10; y++){
					if(shipBoard[x][y] == i){
						moduloNumber = i;
					}
				}
			}
		}
		int remainder = moduloNumber - 1;
		boolean foundIt = false;
		for (int parityNumber = 0; parityNumber < moduloNumber; parityNumber++) {
			for(int i = 0; i < 10; i++){
				for(int j = 0; j < 10; j++){
					if((i+j)%moduloNumber == parityNumber){
						if(fireboard[j][i] == 0){
							foundIt = true;
							remainder = parityNumber;
							break;
						}
					}
				}
				if (foundIt) break;
			}
			if (foundIt) break;
		}
		do{	
			coordinates[0] = (int) (Math.random() * 10);//select a random Y from 0-10
			coordinates[1] = (int) (Math.random() * 10);//select a random X from 0 to 10
		}while((coordinates[0]+coordinates[1])%moduloNumber != remainder);
		return coordinates;
	}
}
