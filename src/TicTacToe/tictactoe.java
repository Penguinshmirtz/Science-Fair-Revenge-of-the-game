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
		boolean done = false;
		while (!done ) {
			
		}
	}
}
