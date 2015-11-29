package battleShip;

public class PlayBattleShip {
	public static void main(){
		//The main method will play a random game of Battleship, and determine the winner of the game
		int[][]shipBoardP1 = new int[10][10];
		int[][]shipBoardP2 = new int[10][10];
		int[][]fireBoardP1 = new int[10][10];
		int[][]fireBoardP2 = new int[10][10];
		placeShips(shipBoardP1);
		placeShips(shipBoardP2);
		
	}

	public static int[][] placeShips(int[][] shipBoard){
		int orientation = 0;
		int shipLength = 0;
		int randomRow = 0;
		int randomColumn = 0;
		for(double i = 2.4; i < 5.5; i = i + 0.7){
			orientation = (int) (Math.random()*4);
			shipLength = (int) i;
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
		
		}
			
	}

}
