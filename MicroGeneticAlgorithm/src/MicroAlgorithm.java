import java.util.ArrayList;
import java.util.Random;



public class MicroAlgorithm {
	//static int cols=10;
	static ArrayList<Double> hammingArray = new ArrayList(); //static to access static method
	public static void main(String args []){
		Random rn = new Random();
		double crossOverRate=0.9;
		int cols =10;

		int[][] population = new int[30][cols];
		int [] fitnessValues = new int[population.length];
		int sum=0;

		population=generatePopulation(population,cols);

		boolean flag=true;
		for(int gen=1;gen<=30;gen++){
		//for(int gen=1;flag==true;gen++){
			System.out.println("generation ************************  "+gen);

			printPopulation(population,cols);



			fitnessValues=calculateFitness(fitnessValues,population,cols);

			printFitness(fitnessValues);


			population=tournmentSelection(fitnessValues,population,cols);

			printPopulation(population,cols);


			population=crossOver(fitnessValues,population, cols);

			printPopulation(population,cols);

			population=mutation(population, cols);

			printPopulation(population,cols);

			double hD=hammingDistance(population,cols);
			System.out.println("hamming distance  "+hD);
			generateHammingGraph(hD);
			if(hD<200){
				int bestValue=max(population,fitnessValues);
				System.out.println("Pop re-initilise but keep chrom "+bestValue);
				for(int k=0;k<cols;k++){
					population[0][k]=population[bestValue][k];	
				}


				for(int i=1;i<population.length;i++){
					for(int k1=0;k1<cols;k1++){
						population[i][k1]=rn.nextInt(2);	 
					}	 
				}
				printPopulation(population,cols);

			}

		}

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
	public static int[] calculateFitness(int [] fitnessValues,int [][] population,int cols){

		for(int i=0;i<fitnessValues.length;i++){
			fitnessValues[i]=0;	 
		}
		for(int i=0;i<population.length;i++){
			for(int k=0;k<cols;k++){
				if(population[i][k]==1){
					fitnessValues[i]=population[i][k]+fitnessValues[i];
				}
			}	
		}
		return fitnessValues;
	}

	public static void printFitness(int [] fitnessValues){
		for(int i=0;i<fitnessValues.length;i++){
			System.out.println(fitnessValues[i]);	 
		}
	}

	public static int [][] tournmentSelection(int [] fitnessValues,int [][] population,int cols){
		Random rn = new Random();
		for(int i=0;i<fitnessValues.length;i++){
			int p1=rn.nextInt(fitnessValues.length);
			int p2=rn.nextInt(fitnessValues.length);

			int index1=fitnessValues[p1];
			int index2=fitnessValues[p2];
			System.out.println(p1+" "+index1+"  "+p2+" "+index2);

			if(index1>index2){
				for(int k=0;k<cols;k++){
					population[i][k]=population[p1][k];
				}
			}else if(index2>index1){
				for(int k=0;k<cols;k++){
					population[i][k]=population[p2][k];
				}	
			}else{
				for(int k=0;k<cols;k++){
					population[i][k]=population[p1][k];
				}
			}

		}
		return population;
	}
	public static int [][] crossOver(int [] fitnessValues,int [][] population,int cols){
		Random rn = new Random();
		double crossOverRate=0.9;
		double crossOverProb=Math.random();

		if(crossOverProb<crossOverRate){
			int father=rn.nextInt(population.length);
			int mother=rn.nextInt(population.length);
			int co=rn.nextInt(cols);

			System.out.println("father "+father+" mother "+mother+" cross-over point "+co);


			for(int k=0;k<co;k++){
				population[father][k]=population[father][k];
			}
			for(int k=co;k<cols;k++){
				population[father][k]=population[father][k];
			}

			for(int k=co;k<cols;k++){
				population[mother][k]=population[mother][k];
			}
			for(int k=0;k<co;k++){
				population[mother][k]=population[mother][k];
			}
		}		
		return population;
	}

	public static int [][] mutation(int [] [] population, int cols){
		double mutationRate=1.0/population.length;//mutation rate of 0.1
		Random rn = new Random();

		for(int i=0;i<population.length;i++){
			for(int k=0;k<cols;k++){
				double mutationProb=Math.random();
				if(mutationProb<mutationRate){
					System.out.println("Chrom "+i+" gene "+k);
					if(population[i][k]==1){
						population[i][k]=0;
					}else if(population[i][k]==0){
						population[i][k]=1;
					}


				}
			} 

		}
		return population;
	} 

	public static int  max(int [][] population,int [] fv){
		int temp=0;
		int indexPosition=0;

		int i=0;
		for(i=0;i<fv.length;i++){
			if(fv[i]>temp){
				temp=fv[i];
				indexPosition=i;
			}

		}

		System.out.println("Largest value is "+temp+" and was found at index position "+indexPosition);	
		return indexPosition;
	}

	public static double hammingDistance(int [][] twoD,int cols){
		ArrayList<Integer> hammingArray = new ArrayList(); 

		int sum=0;
		for(int i=0;i<twoD.length;i++){
			for(int j=1;j<twoD.length;j++){
				for(int k=0;k<cols;k++){
					if(twoD[i][k]==twoD[j][k] && i!=j){
						sum=sum+0;
						//System.out.println("twoD[i][k]"+twoD[i][k]+" twoD[j][k] "+twoD[j][k]);
					}else if(twoD[i][k]!=twoD[j][k] && i!=j){
						sum=sum+1;
						//System.out.println("twoD[i][k]"+twoD[i][k]+" twoD[j][k] "+twoD[j][k]);
					}

				}
				if(i!=j){
					//System.out.println("hamming distance = "+sum);
					hammingArray.add(sum);
				}
				sum=0;

			}
			//System.out.println();
		}	    
		double hammingSum=0; 
		double hammingAverage=0;
		for(int i=0;i<hammingArray.size();i++){
			hammingSum=(hammingSum+hammingArray.get(i));
		}
		//hammingAverage=hammingSum/hammingArray.size();
		System.out.println("Hamming Distance  "+hammingSum);
		return hammingSum;
	}
	public static void generateHammingGraph(double hammingDistance){
		hammingArray.add(hammingDistance);
		generateHammingGraphStandardAlgorithm.generateCsvFile(hammingArray);
	}

}
