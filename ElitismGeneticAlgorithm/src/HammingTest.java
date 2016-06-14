import java.util.ArrayList;
import java.util.Random;


public class HammingTest {
	public static void main(String args[]){
		int cols =10;
		//cols are the amount of genes that are in each chromosome 
		int[][] population = new int[10][cols];

		population=generatePopulation(population,cols);
		printPopulation(population,cols);
		double hD=hammingDistanceDeJong(population,cols);



	}
	public static int [][] generatePopulation(int [][] population,int cols){
		Random rn = new Random();

		for(int i=0;i<population.length;i++){
			for(int k=0;k<cols-1;k++){
				population[i][k]=rn.nextInt(2);	 
			}	 
		}

		return population;
	}
	public static void printPopulation(int [][] population,int cols){
		for(int i=0;i<population.length;i++){
			for(int k=0;k<cols;k++){
				System.out.print(population[i][k]);	 
			}	 
			System.out.println();
		}
	}
	public static double hammingDistanceDeJong(int [][] twoD,int cols){
	 int coltotal=0;
	 ArrayList<Integer> hammingArray = new ArrayList(); 
	 for(int i=0;i<twoD.length;i++){
		 for(int j=0;j<twoD.length;j++){
			 coltotal=coltotal+twoD[j][i];
		 }
		 hammingArray.add(coltotal);
		 coltotal=0;
	 }
	 System.out.println(hammingArray);
	 

	return 0;
}


}
