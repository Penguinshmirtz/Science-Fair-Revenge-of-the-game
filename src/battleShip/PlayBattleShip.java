package battleShip;

public class PlayBattleShip {
	public static void main(){
		//The main method will play a random game of Battleship, and determine the winner of the game
		int winner = 0;
		int[][]shipBoardP1 = new int[10][10];
		int[][]shipBoardP2 = new int[10][10];
		int[][]fireBoardP1 = new int[10][10];// 0 = not fired at, 1 = fired but missed, 2 == fired and hit
		int[][]fireBoardP2 = new int[10][10];
		shipBoardP1=placeShips();
		shipBoardP2=placeShips();
		while (winner == 0){
			winner = fire(fireBoardP1, shipBoardP2);
			if(winner == 0){
				break;
			}
			winner = fire(fireBoardP2, shipBoardP1);
			if (winner ==1) winner = 2;
		}
		System.out.println("Player" + winner + "has won!");
	}

	/*
	 * placeShips places ships in a random place and orientation on the board
	 */
	public static int[][] placeShips(){
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
			if (orientation == 0) { // place the ship from row, colum to row - shiplentgh + 1, column
				for(int j = randomRow; j > randomRow - shipLength; j--){
					shipBoard[j][randomColumn] = shipType;
				}
			}
			if (orientation == 1) { // place the ship from row, column to row, column - shiplength + 1
				for(int j = randomColumn; j > randomColumn - shipLength; j--){
					shipBoard[randomRow][j] = shipType;
				}
			}
			if (orientation == 2) { // place the ship from row, column to row + shiplength -1, column 
				for(int j = randomRow; j < randomRow + shipLength; j++){
					shipBoard[j][randomColumn] = shipType;
				}
			}
			if (orientation == 3) { // place the ship from row, colum to row, column + shiplength -1
				for(int j = randomColumn; j < randomColumn + shipLength; j++){
					shipBoard[randomRow][j] = shipType;
				}
			}
		}
		return shipBoard;
	}
	public static int fire(int[][] fireBoard, int[][] shipBoard){
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
		}
		else{
			fireBoard[randomX][randomY] = 1;
		}
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
				System.out.println("You sunk the " + i + " ship");
				shipsSunk++;
			}
		}
		if(shipsSunk == 5){
			return 1;
		}
		return 0;
	}
}
