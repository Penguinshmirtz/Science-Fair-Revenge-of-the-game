package BattleShip;

public class playBattleShip {
	public static void main(String[] args){
		//The main method will play a random game of Battleship, and determine the winner of the game
		int winner = 0;
		int[][]shipBoardP1 = new int[10][10];//this keeps track of where all of P1's ships are
		int[][]shipBoardP2 = new int[10][10];
		int[][]fireBoardP1 = new int[10][10];// 0 = not fired at, 1 = fired but missed, 2 == fired and hit
		int[][]fireBoardP2 = new int[10][10];
		boolean[]sunkShipsP1 = new boolean [5];//this array keeps track of the sunken ship types for P1
		boolean[]sunkShipsP2 = new boolean [5];
		for (int i = 0; i<5; i++){
			sunkShipsP1[i] = false;
			sunkShipsP2[i] = false;
		}
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
			winner = fire(fireBoardP1, shipBoardP2, sunkShipsP2, 1);
			if(winner == 1){
				break;
			}
			winner = fire(fireBoardP2, shipBoardP1, sunkShipsP1, 2);
			if (winner ==1) winner = 2;
		}
		System.out.println("Player " + winner + " has won!");
		System.out.println("Here is the Player 1 FireBoard");
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
		}
	}

	/*
	 * placeShips places ships in a random place and orientation on the board
	 */
	public static int[][] placeShips(int player){
		int[][]shipBoard = new int[10][10];
		int orientation = 0;
		int shipLength = 0;
		int shipType = 0;
		int randomRow = 0;
		int randomColumn = 0;
		for(double i = 2.4; i < 5.5; i = i + 0.7){
			orientation = (int) (Math.random()*4);// 0 is north, 1 is east, 2 is south, and 3 is west
			shipLength = (int) i;
			if (Math.abs(i-3.1) < 0.00001) {
				shipType = 6;
			} else {
				shipType = (int) i;
			}
			if (orientation%2 == 0){
				randomColumn = (int) (Math.random()*10);
				randomRow = (int) (Math.random() * (11-shipLength));
				if (orientation == 0){
					randomRow = randomRow + shipLength - 1;
				}
			} else {
				randomRow = (int) (Math.random()*10);
				randomColumn = (int) (Math.random() * (11-shipLength));
				if (orientation == 3){
					randomColumn = randomColumn + shipLength - 1;
				}
				
			}
			System.out.println("Ship Type is : " + shipType);
			System.out.println("Ship Length is : " + shipLength);
			System.out.println("Ship Orientation is : " + orientation);
			System.out.println("Ship Row is : " + randomRow);
			System.out.println("Ship Column is : " + randomColumn);


			if (orientation == 0) { // place the ship from row, colum to row - shiplentgh + 1, column
				for(int j = randomRow; j > randomRow - shipLength; j--){
					shipBoard[j][randomColumn] = shipType;
					SimpleGraphics.fillCircle((22 + (player - 1)*270 + randomColumn*25), 313 + j*25, 5, "grey");
				}
			}
			if (orientation == 1) { // place the ship from row, column to row, column - shiplength + 1
				for(int j = randomColumn; j < randomColumn + shipLength; j++){
					shipBoard[randomRow][j] = shipType;
					SimpleGraphics.fillCircle((22 + (player - 1)*270 + j*25), 313 + randomRow*25, 5, "grey");
				}
			}
			if (orientation == 2) { // place the ship from row, column to row + shiplength -1, column 
				for(int j = randomRow; j < randomRow + shipLength; j++){
					shipBoard[j][randomColumn] = shipType;
					SimpleGraphics.fillCircle((22 + (player - 1)*270 + randomColumn*25), 313 + j*25, 5, "grey");
				}
			}
			if (orientation == 3) { // place the ship from row, colum to row, column + shiplength -1
				for(int j = randomColumn; j > randomColumn - shipLength ; j--){
					shipBoard[randomRow][j] = shipType;
					SimpleGraphics.fillCircle((22 + (player - 1)*270 + j*25), 313 + randomRow*25, 5, "grey");
				}
				printShipBoards(shipBoard);
			}
		}
		return shipBoard;
	}
	public static int fire(int[][] fireBoard, int[][] shipBoard, boolean[]sunkShips, int player){
		int randomX = (int) (Math.random() * 10);
		int randomY = (int) (Math.random() * 10);
		int shipsSunk = 0;
		while(fireBoard[randomX][randomY] != 0){
			randomX = (int) (Math.random() * 10);
			randomY = (int) (Math.random() * 10);
		}
		if(shipBoard[randomX][randomY] != 0){
			shipBoard[randomX][randomY] = 0;
			fireBoard[randomX][randomY] = 2;
			SimpleGraphics.fillCircle((22 + (player - 1)*270 + randomY*25), 33 + randomX*25, 5, "red");
		}
		else{
			fireBoard[randomX][randomY] = 1;
			SimpleGraphics.fillCircle(22 + (player -1)*270 + randomY*25, 33 + randomX*25, 5, "black");
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
					System.out.println("You sunk the " + i + " ship");
					sunkShips[i-2] = true;
				}
			}
		}
		if(shipsSunk == 5){
			return 1;
		}
		return 0;
	}
	public static void printShipBoards(int[][]SB1) {
		System.out.println("Here is t 1 ShipBoard");
		for (int i = 0;i<10;i++) {
			for (int j = 0; j<10; j++) {
				System.out.println(SB1[i][j]);
			}
			System.out.println();
		}
	}
	public static void sleep(int time){
		try{
			Thread.sleep(time);
		} catch (Exception e) {}
	}
}
