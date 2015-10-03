package EightQueens;


public class eightQueensWithSelection {
	

	public static void main(String[] args) {

		int[] parentGenes = new int[] {0,1,2,3,4,5,6,7}; // Start by putting the queens on the main diagonal
		int[] childGenes = new int[8];
		int parentFitness = -28, childFitness;  // This is just to make sure that we enter the while loop at least once
		int q1 = 0, q2 = 0 ; // This is initializing the mutation locations
		int temporary = 0; // This is so we can swap without overwriting
		int countGenerations = 0;
		while (parentFitness < 0) {
			childGenes = parentGenes; // mutation
			q1 = (int)(8*Math.random());
			q2 = (int)(8*Math.random());
			temporary = childGenes[q1];
			childGenes[q1] = childGenes[q2];
			childGenes[q2] = temporary;
			childFitness = 0;
			countGenerations++;
			for (int i=0;i<7;i++) {  // evaluate fitness
				for (int j=i+1;j<8;j++) {
					if (Math.abs(j-i)==Math.abs(childGenes[j]-childGenes[i])) {
						childFitness = childFitness-1;
					}
				}
			}
			if (childFitness>=parentFitness) {  // selection
				parentFitness = childFitness;
				parentGenes = childGenes;
			}
			System.out.println("We are at generation "+countGenerations);
			System.out.println("This board was setup as: " + parentGenes[0] + parentGenes[1] + parentGenes[2]+parentGenes[3]+parentGenes[4]+parentGenes[5]+parentGenes[6]+parentGenes[7]);
			System.out.println("The fitness for this position: " + parentFitness);
		}
	}

}



