package TicTacToe;

public class tictactoe {
	public static void main(String[] args) {
		int[] p1Genes = new int[249];
		int[] p2Genes = new int[444];
		// For p1's first gene we randomly select from it's 3 possible values
		p1Genes[0] = (int) (Math.random()*3);
		// For p1's next 8 genes, we randomly select from their 7 possible values
		for (int i = 1; i<= 8; i++) {
			p1Genes[i] = (int) (Math.random()*7);
		}
		// For p1's next 48 genes, we randomly select from their 5 possible values
		for (int i = 9; i<= 56; i++) {
			p1Genes[i] = (int) (Math.random()*5);
		}
		// For p1's next 192 genes, we randomly select from their 3 possible values
		for (int i = 57; i<= 248; i++) {
			p1Genes[i] = (int) (Math.random()*3);
		}
		// For p2's first 3 genes, we randomly select from their 8 possible values
		for (int i = 0; i<= 2; i++) {
			p2Genes[i] = (int) (Math.random()*8);
		}
		// For p2's next 21 genes, we randomly select from their 6 possible values
		for (int i = 3; i<= 23; i++) {
			p2Genes[i] = (int) (Math.random()*6);
		}
		// For p2's next 105 genes, we randomly select from their 4 possible values
		for (int i = 24; i<= 128; i++) {
			p2Genes[i] = (int) (Math.random()*4);
		}
		// For p1's next 315 genes, we randomly select from their 2 possible values
		for (int i = 129; i<= 443; i++) {
			p2Genes[i] = (int) (Math.random()*2);
		}
		System.out.println(play(p1Genes, p2Genes));
	}
	
    public static int play(int[] p1, int[] p2) {
        // First we will figure out what moves each player makes
        int move1 = p1[0];
        int move2 = p2[move1];
        int move3 = p1[1+move2];
        int move4 = p2[3+move1*7+move3];
        int move5 = p1[9+move2*6+move4];
        int move6 = p2[24+move1*35+move3*5+move5];
        int move7 = p1[57+move2*24+move4*4+move6]; // fix me by putting the right formula in the brackets
        int move8 = p2[129+move1*105+move3*15+move5*3+move7]; // fix me by putting the right formula in the brackets
        int[][] board = new int[3][3]; // this defines a two dimensional array of 3 rows and three columns as the tic tac toe board
        // by default, java puts a zero for every position in the board.  We will use "1" to mean "X" on the board, and "2" to mean "O"
        
        // now that we know the sequence of actual moves, the game begins
        // first, fill the square as per the first move
        if (move1 == 0) { // a zero on the first move means an x on the top right corner
            board[0][0]=1;
        } else {
            if (move1 == 1) { // a one on the first move means an x in the center square
                board[1][1]=1;
            } else {
                board[1][0]=1; // otherwise put an x on the middle row left square
            }
        }
        // now for the second through ninth move, we can use a loop
        boolean done = false;
        int i = 1;
        while (!done) {
                  
        }
    }}
