package ticTacToeWithEvolution;

public class tic_Tac_Toe_With_Evolution {


	public static final int _numChildren = 50;
	public static final int _maxIters = 5000000;
	public static void main(String[] args) {
		int[] p1Genes = new int[249];
		int[] p2Genes = new int[444];
		int[] dummyP1Genes = new int[249];
		int[] dummyP2Genes = new int[444];
		int[][] mutant1Genes = new int [_numChildren][249];
		int[][] mutant2Genes = new int [_numChildren][444];
		int[] mutant1Scores = new int [_numChildren];
		int[] mutant2Scores = new int [_numChildren];
		//int[] zeros = new int[_numChildren];
		p1Genes = randGenes1(); // randomly assign player 1's genes
		p2Genes = randGenes2(); // randomly assign player 2's genes
		boolean done = false;
		int iters = 0;
		int ties = 0;
		int winner = 0;
		int mutantWinner = 0;
		int maxScore = 0;
		int maxIndx = 0;
		int numP1Ties = 0;
		int numP2Ties = 0;
		while (!done){
			if (iters%1000==0) System.out.println(/*"iters = " + iters*/);
			winner = play(p1Genes,p2Genes, iters%1000==0);
			//printGenes(p1Genes,p2Genes);
			if (true /*winner == 0*/){
				//System.out.println(">> iters " + iters + " is a draw with " + ties + " ties");
				ties++; // implement the right side of the flowchart
				// create 50 mutants of player 1 and 2's genes
				//run the "play" method" for every pair of p1's and p2's
				//keep a counter for each mutant that counts how many wins, losses, and ties (+1 for win, 0 for tie, -1 for loss) they have
				//pick the best mutant for player 1 and for player 2 (pick any one if there is a tie)
				for (int j = 0; j<249; j++) mutant1Genes[0][j] = p1Genes[j];
				for (int j = 0; j<444; j++) mutant2Genes[0][j] = p2Genes[j];
				for (int i = 1; i < _numChildren; i++){
					dummyP1Genes = mutateActiveGenesP1(p1Genes, p2Genes);
					for (int j = 0; j<249; j++) mutant1Genes[i][j] = dummyP1Genes[j];
					//mutant1Genes[i] = mutatePlayerOne(p1Genes);//mutateActiveGenesP1(p1Genes, p2Genes);
					//printP1Genes(p1Genes);
				}
				for (int i = 1; i < _numChildren; i++){
					dummyP2Genes = mutateActiveGenesP2(p1Genes, p2Genes);
					for (int j = 0; j<444; j++) mutant2Genes[i][j] = dummyP2Genes[j];
					//mutant2Genes[i] = mutatePlayerTwo(p2Genes);//mutateActiveGenesP2(p1Genes, p2Genes);
				}
				for (int i = 0; i < _numChildren; i++){
					for (int j = 0; j < _numChildren; j++){
						//printGenes(mutant1Genes[i],mutant2Genes[j]);
						mutantWinner = play(mutant1Genes[i], mutant2Genes[j],false);
						if (mutantWinner == 1) {
							mutant1Scores[i]++;
							mutant2Scores[j]--;
						}
						if (mutantWinner == 2) {
							mutant1Scores[i]--;
							mutant2Scores[j]++;
						}
					}
				}
				// pick the best mutant from among the player 1 mutants
				maxScore = -99999; // initiate the max score to something much smaller than the possible lowest score of -50				
				for (int i = 0; i< _numChildren; i++) {
					if (maxScore < mutant1Scores[i]) {
						maxScore = mutant1Scores[i];
						maxIndx = i;
					}
				}
				numP1Ties = 0; // figure out how many player 1 mutants tied for first place				
				for (int i = 0; i< _numChildren; i++) {
					if (maxScore == mutant1Scores[i]) {
						numP1Ties++;
					}
				}
				p1Genes = mutant1Genes[maxIndx];
				System.out.println ("In iteration " + iters + " there were " + numP1Ties + " ties for player one with a score of " + maxScore);
				if (ties > 1) System.out.println ("****** Ties in a row:" + ties);
				maxScore = -99999; // initiate the max score to something much smaller than the possible lowest score of -50				
				for (int i = 0; i< _numChildren; i++) {
					if (maxScore < mutant2Scores[i]) {
						maxScore = mutant2Scores[i];
						maxIndx = i;
					}
				}
				numP2Ties = 0; // figure out how many player 2 mutants tied for first place				
				for (int i = 0; i< _numChildren; i++) {
					if (maxScore == mutant2Scores[i]) {
						numP2Ties++;
					}
				}
				p2Genes = mutant2Genes[maxIndx];	
				for (int i = 0; i< _numChildren; i++) {
					mutant1Scores[i] =0;
					mutant2Scores[i] =0;

				}
				System.out.println ("In iteration " + iters + " there were " + numP2Ties + " ties for player two with a score of " + maxScore);
			}
			if (false /*winner == 1*/){
				// implement left side of the flowchart
				//System.out.println(">> iters " + iters + " player 1 won");
				p2Genes = mutateActiveGenesP2(p1Genes,p2Genes); // player 2 needs to mutate
				ties = 0;
			}
			if (false /*winner == 2*/){
				// implement middle of the flowchart
				//System.out.println(">> iters " + iters +" player 2 won");
				p1Genes = mutateActiveGenesP1(p1Genes,p2Genes); // player 1 needs to mutate
				ties = 0;
			}
			// more stuff here
			iters++;
			done = ((iters>=_maxIters)/*||(ties>=20)*/);			
		}
		play(p1Genes,p2Genes,true);
		System.out.println();
		System.out.println("Ties = " + ties + "  iterations = " + iters);
		System.out.println("Player 1 Genes");
		System.out.println(p1Genes);
		System.out.println("Player 2 Genes");
		System.out.println(p2Genes);
	}

	public static int[] randGenes1() {
		int[] p1Genes = new int[249];
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
		return p1Genes;
	}

	public static int[] randGenes2() {
		int[] p2Genes = new int[444];
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
		return p2Genes;
	}


	public static int[] mutatePlayerTwo(int[] genes) {
		int mutantGeneLocation = 0;
		int numberOfAlleles = 0;
		int SNP = 0;
		for (int i=0; i<20; i++) { // in each iteration we will change 1 random gene
			mutantGeneLocation = (int) (Math.random()*444);
			if (mutantGeneLocation < 3) {
				numberOfAlleles = 8;
			} else if (mutantGeneLocation < 24) {
				numberOfAlleles = 6;
			} else if (mutantGeneLocation < 129) {
				numberOfAlleles = 4;
			} else {
				numberOfAlleles = 2;
			}
			SNP = (int) (Math.random()*numberOfAlleles);
			genes[mutantGeneLocation] = SNP;
			//System.out.println("SNP is " + SNP + "at location " + mutantGeneLocation);
		}
		return genes;
	}

	public static int[] mutatePlayerOne(int[] genes) {
		int mutantGeneLocation = 0;
		int numberOfAlleles = 0;
		for (int i=0; i<10; i++) { // in each iteration we will change 1 random gene
			mutantGeneLocation = (int) (Math.random()*249);
			if (mutantGeneLocation == 0) {
				numberOfAlleles = 3;
			} else if (mutantGeneLocation < 9) {
				numberOfAlleles = 7;
			} else if (mutantGeneLocation < 57) {
				numberOfAlleles = 5;
			} else {
				numberOfAlleles = 3;
			}
			genes[mutantGeneLocation] = (int) (Math.random()*numberOfAlleles);
		}
		return genes;
	}
	
//	move[0] = p1[0];
//	move[1] = p2[move[0]];
//	move[2] = p1[1+move[1]];
//	move[3] = p2[3+move[0]*7+move[2]];
//	move[4] = p1[9+move[1]*6+move[3]];
//	move[5] = p2[24+move[0]*35+move[2]*5+move[4]];
//	move[6] = p1[57+move[1]*24+move[3]*4+move[5]]; // fix me by putting the right formula in the brackets
//	move[7] = p2[129+move[0]*105+move[2]*15+move[4]*3+move[6]]; // fix me by putting the right formula in the brackets+
//	move[8] = 0;
	
	public static int[] mutateActiveGenesP1(int[] genes1, int[] genes2){
		int mutantGeneLocation = 0;
		int numberOfAlleles = 0;
		int mutateMove = (int) (Math.random()*4);
		int[] move = new int[9];
		move[0] = genes1[0];
		move[1] = genes2[move[0]];
		move[2] = genes1[1+move[1]];
		move[3] = genes2[3+move[0]*7+move[2]];
		move[4] = genes1[9+move[1]*6+move[3]];
		move[5] = genes2[24+move[0]*35+move[2]*5+move[4]];
		move[6] = genes1[57+move[1]*24+move[3]*4+move[5]]; // fix me by putting the right formula in the brackets
		move[7] = genes2[129+move[0]*105+move[2]*15+move[4]*3+move[6]]; // fix me by putting the right formula in the brackets+
		move[8] = 0;
		if (mutateMove == 0) {
			mutantGeneLocation = 0;
			numberOfAlleles = 3;
		} else if (mutateMove == 1) {
			mutantGeneLocation = 1+move[1];
			numberOfAlleles = 7;
		} else if (mutateMove == 2) {
			mutantGeneLocation = 9+move[1]*6+move[3];
			numberOfAlleles = 5;
		} else {
			mutantGeneLocation = 57+move[1]*24+move[3]*4+move[5];
			numberOfAlleles = 3;
		}
		int newGene = (int) (Math.random()*numberOfAlleles);
		while (genes1[mutantGeneLocation] == newGene){
			newGene = (int) (Math.random()*numberOfAlleles);
		}
		genes1[mutantGeneLocation] = newGene;
		//System.out.println(">> Hey, I think just mutated location # " + mutantGeneLocation);
		return genes1;
	}
	
	public static int[] mutateActiveGenesP2(int[] genes1, int[] genes2){
		int mutantGeneLocation = 0;
		int numberOfAlleles = 0;
		int mutateMove = (int) (Math.random()*4);
		int[] move = new int[9];
		move[0] = genes1[0];
		move[1] = genes2[move[0]];
		move[2] = genes1[1+move[1]];
		move[3] = genes2[3+move[0]*7+move[2]];
		move[4] = genes1[9+move[1]*6+move[3]];
		move[5] = genes2[24+move[0]*35+move[2]*5+move[4]];
		move[6] = genes1[57+move[1]*24+move[3]*4+move[5]]; // fix me by putting the right formula in the brackets
		move[7] = genes2[129+move[0]*105+move[2]*15+move[4]*3+move[6]]; // fix me by putting the right formula in the brackets+
		move[8] = 0;
		if (mutateMove == 0) {
			mutantGeneLocation = move[0];
			numberOfAlleles = 8;
		} else if (mutateMove == 1) {
			mutantGeneLocation = 3+move[0]*7+move[2];
			numberOfAlleles = 6;
		} else if (mutateMove == 2) {
			mutantGeneLocation = 24+move[0]*35+move[2]*5+move[4];
			numberOfAlleles = 4;
		} else {
			mutantGeneLocation = 129+move[0]*105+move[2]*15+move[4]*3+move[6];
			numberOfAlleles = 2;
		}
		int newGene = (int) (Math.random()*numberOfAlleles);
		while (genes2[mutantGeneLocation] == newGene){
			newGene = (int) (Math.random()*numberOfAlleles);
		}
		genes2[mutantGeneLocation] = newGene;
		return genes2;
	}
	
	
	public static int play(int[] p1, int[] p2, boolean printFlag) {
		// First we will figure out what moves each player makes
		int[] move = new int[9];
		move[0] = p1[0];
		move[1] = p2[move[0]];
		move[2] = p1[1+move[1]];
		move[3] = p2[3+move[0]*7+move[2]];
		move[4] = p1[9+move[1]*6+move[3]];
		move[5] = p2[24+move[0]*35+move[2]*5+move[4]];
		move[6] = p1[57+move[1]*24+move[3]*4+move[5]]; // fix me by putting the right formula in the brackets
		move[7] = p2[129+move[0]*105+move[2]*15+move[4]*3+move[6]]; // fix me by putting the right formula in the brackets+
		move[8] = 0;
		//for (int i=0; i<9;i++) System.out.println("move # " + i + " is " + move[i]);
		int[][] board = new int[3][3]; // this defines a two dimensional array of 3 rows and three columns as the tic tac toe board
		// by default, java puts a zero for every position in the board.  We will use "1" to mean "X" on the board, and "2" to mean "O"

		// now that we know the sequence of actual moves, the game begins
		// first, fill the square as per the first move
		if (move[0] == 0) { // a zero on the first move means an x on the top right corner
			board[0][0]=1;
		} else {
			if (move[0] == 1) { // a one on the first move means an x in the center square
				board[1][1]=1;
			} else {
				board[1][0]=1; // otherwise put an x on the middle row left square
			}
		}
		// now for the second through ninth move, we can use a loop
		boolean done = false;
		int i = 1;
		int winningPlayer = 0;
		while (!done) {
			board = placeChip(move[i],board,(i%2)+1);
			winningPlayer = checkWinner(board);
			done = (winningPlayer>0)||i==8;
			i++;
		}
		if (printFlag) drawBoard(board);
		return winningPlayer;
	}

	public static void drawBoard (int[][] board) {
		for (int row = 0; row<=2; row++) {
			System.out.println();
			System.out.println("------");
			for (int col = 0; col<=2; col++) {
				if (board[row][col] == 0) System.out.print(" |");
				if (board[row][col] == 1) System.out.print("X|");
				if (board[row][col] == 2) System.out.print("O|");
			}
		}
		System.out.println();
	}

	public static int[][] placeChip(int move, int [][] board, int player) {
		int countBlanks = 0;
		for (int i=0; i<=2; i++) {
			for (int j = 0; j<=2; j++) {
				if (board[i][j] == 0) {
					if (countBlanks == move) {
						board[i][j] = player;
						return board;
					}
					countBlanks++;
				}
			}
		}
		System.out.println("IT'S A TRAP:O");
		return board;
	}
	public static int checkWinner(int[][] board) {
		for (int i = 0; i<=2; i++) {
			if ((board[i][0]*board[i][1]*board[i][2]==1) // check to see if player 1 won on any of the 3 rows
					|| (board[0][i]*board[1][i]*board[2][i]==1)){ // check to if player 1 won on any of the 3 columns
				return 1;
			}
			if ((board[i][0]*board[i][1]*board[i][2]==8) // check to see if player 2 won on any of the 3 rows
					|| (board[0][i]*board[1][i]*board[2][i]==8)){ // check to see if player 2 won on any of the 3 columns
				return 2;
			}
		}
		if ((board[0][0]*board[1][1]*board[2][2]==1) // check to see if player 1 won on the main diagonal
				|| (board[0][2]*board[1][1]*board[2][0]==1)) { // check to see if player 1 won on the reverse diagonal
			return 1;
		}
		if ((board[0][0]*board[1][1]*board[2][2]==8) // check to see if player 2 won on the main diagonal
				|| (board[0][2]*board[1][1]*board[2][0]==8)) { // check to see if player 2 won on the reverse diagonal
			return 2;
		}
		return 0;
	}
	
	public static void printGenes(int[] p1, int[] p2) {
		System.out.print(p1[0] + ":");
		for (int i = 1; i<9; i++) System.out.print(p1[i]);
		System.out.print(":");
		for (int i = 9; i<57; i++) System.out.print(p1[i]);
		System.out.println();
		for (int i = 57; i<249; i++) System.out.print(p1[i]);
		for (int i = 0; i<3; i++) System.out.print(p2[i]);
		System.out.print(":");
		for (int i = 3; i<24; i++) System.out.print(p2[i]);
		System.out.print(":");
		for (int i = 24; i<129; i++) System.out.print(p2[i]);
		System.out.print(":");
		for (int i = 129; i<444; i++) System.out.print(p2[i]);
//		for (int i = 0; i<249; i++) System.out.print(p1[i]);
//		for (int i = 0; i<444; i++) System.out.print(p2[i]);
		System.out.println();

	}
	
	public static void printP1Genes(int[] p1) {
		for (int i = 0; i<249; i++) System.out.print(p1[i]);
		System.out.println();
	}
}
