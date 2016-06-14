import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class ElitismGA {
	static LinkedList<Integer> convergenceCountMax = new LinkedList();
	static LinkedList<Integer> convergenceCountMin = new LinkedList();
	static LinkedList<Double> allAverages = new LinkedList();
	static LinkedList<Double> allAveragePreviousGen = new LinkedList();
	static LinkedList<Double> convergence = new LinkedList();
	static LinkedList<Double> averageAverageBest = new LinkedList();
	static LinkedList<Double> averageAverageArray = new LinkedList();
	static int runNumber=20;
	static double sum=0;
	static ArrayList<Double> hammingArray = new ArrayList(); //static to access static method
	static boolean firstGen=true;
	static Boolean convergenceCheckMax=false;
	static Boolean convergenceCheckMin=false;

	public static void main(String args[]){

		ArrayList<Double> averageAverageArray= new ArrayList();
		int cols =33;
		//cols are the amount of genes that are in each chromosome 
		int[][] population = new int[50][cols];//population is the amount of chromosomes
		double [] fitnessValues = new double[population.length];
		int genStop=0;
		int genStop1=0;
		
		
		Scanner sc = new Scanner(System.in);		
		//max loop

		for(int run=0;run<runNumber;run++){
			System.out.println("Start Run "+run);
			convergenceCheckMax=false;
			Random rn = new Random();
			double crossOverRate=0.9;
			//int cols =33;
			//cols are the amount of genes that are in each chromosome 
			//int[][] population = new int[50][cols];//population is the amount of chromosomes
			//double [] fitnessValues = new double[population.length];
			int sum=0;
			ArrayList<Double> averageArray= new ArrayList();
			
			double globalMinMax=0;


			population=generatePopulation(population,cols);
			printPopulation(population, cols);
			//double hD=hammingDistance(population,cols);
			//hammingArray.add(hD);

			genStop=50;
			
			for(int gen=0;gen<genStop;gen++){

					System.out.println("Start Generation no **************** "+gen);

					//printPopulation(population,cols);

					fitnessValues=calculateFitness(fitnessValues,population,cols);
					printFitness(fitnessValues);

					globalMinMax=78.6;
					population=ElitismtournmentSelectionMax(fitnessValues,population,cols,globalMinMax);
					System.out.println("After elitism");
					printFitness(fitnessValues);
					averageFitness(fitnessValues);
					population=crossOver(fitnessValues,population, cols);
					population=mutation(population, cols);
					System.out.println("After mutation");
					fitnessValues=calculateFitness(fitnessValues,population,cols);
					printFitness(fitnessValues);

					//hD=hammingDistance(population,cols);
					//hammingArray.add(hD);
					hammingDistanceDeJong(population,cols);
					//double hD=hammingDistance(population,cols);
					//double hD=hammingDistance(check,c);

					//generateHammingGraph(hD);

					double average=averageFitness(fitnessValues);
					double max=argmax(fitnessValues);
					averageArray.add(average);
					averageAverage(average,run,gen);
					averageBest(max,run,gen);
					gaConvergenceMax(average,gen);
					System.out.println("End of generation "+gen);
				}//generaton loop

				convergence.clear();
				System.out.println("gen array");
				for(int r=0;r<averageArray.size();r++){
					System.out.println(averageArray.get(r));
				}
		
			//for loop to find min solution
			System.out.println("End of Run "+run);
		}//run loop


		getAverageAverageArray();
		getAverageAverageBestArray();
		///System.out.println(hammingArray);
		
		//zeero the value of the fitness value
		for(int i=0;i<fitnessValues.length;i++){
			fitnessValues[i]=0;	 
		}
		for(int i=0;i<fitnessValues.length;i++){
			System.out.println(fitnessValues[i]);	 
		}
		getallAveragesArray();
		getallAveragePreviousGenArray();
		getconvergenceCountMax();
		System.out.println("MAX VALUES enter 1 to continue ");
		int x = sc.nextInt();
		
		//min loop
		genStop1=300;
		for(int run=0;run<runNumber;run++){
			System.out.println("Start Run "+run);
			Random rn = new Random();
			double crossOverRate=0.9;
			//int cols =33;
			//cols are the amount of genes that are in each chromosome 
			//int[][] population = new int[50][cols];//population is the amount of chromosomes
			///double [] fitnessValues = new double[population.length];
			int sum=0;
			ArrayList<Double> averageArray= new ArrayList();
		
			double globalMinMax=0;


			population=generatePopulation(population,cols);
			printPopulation(population, cols);
			//double hD=hammingDistance(population,cols);
			//hammingArray.add(hD);

			
			for(int gen=0;gen<genStop1;gen++){
				//for(int gen=0;gen<genStop;gen++){

					System.out.println("Start Generation no **************** "+gen);

					//printPopulation(population,cols);

					fitnessValues=calculateFitness(fitnessValues,population,cols);
					printFitness(fitnessValues);

					globalMinMax=0;
					population=ElitismtournmentSelectionMin(fitnessValues,population,cols,globalMinMax);

					averageFitness(fitnessValues);
					population=crossOver(fitnessValues,population, cols);
					population=mutation(population, cols);
					System.out.println("After mutation");
					fitnessValues=calculateFitness(fitnessValues,population,cols);
					printFitness(fitnessValues);
					//hD=hammingDistance(population,cols);
					//hammingArray.add(hD);

					//double hD=hammingDistance(population,cols);
					//double hD=hammingDistance(check,c);
					
					//generateHammingGraph(hD);

					double average=averageFitness(fitnessValues);

					double max=argmin(fitnessValues);
					averageArray.add(average);
					averageAverage(average,run,gen);
					averageBest(max,run,gen);
					gaConvergenceMin(average);
					System.out.println("End of generation "+gen);
				}//generaton loop

				convergence.clear();
				System.out.println("gen array");
				for(int r=0;r<averageArray.size();r++){
					System.out.println(averageArray.get(r));
				}
		
			//for loop to find min solution
			System.out.println("End of Run "+run);
		}//run loop
		getAverageAverageArray();
		getAverageAverageBestArray();
		///System.out.println(hammingArray);	

	}
	public static double argmax (double [] elems){
		int bestIdx = -1;
		double max = Double.NEGATIVE_INFINITY;
		for (int i = 0; i < elems.length; i++) {
			double elem = elems[i];
			if (elem > max) {
				max = elem;
				bestIdx = i;
			}
		}
		return max;
	}
	
	public static double argmin (double [] elems){
		int bestIdx = -1;
		double min = 100;
		for (int i = 0; i < elems.length; i++) {
			double elem = elems[i];
			if (elem < min) {
				min = elem;
				bestIdx = i;
			}
		}
		return min;
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
			for(int k=cols-1;k>=0;k--){
				System.out.print(population[i][k]);	 
				if(k==10 || k==20 || k==30){
					System.out.print(":");
				}
			}	 
			System.out.println();
		}
	}
	public static double[] calculateFitness(double [] fitnessValues,int [][] population,int cols){
		double x=0;
		double y=0;
		double z=0;
		for(int i=0;i<fitnessValues.length;i++){
			fitnessValues[i]=0;	 
		}

		for(int i=0;i<population.length;i++){
			//x cordinate
			if(population[i][0]==1){
				x=x+1;
			}
			if(population[i][1]==1){
				x=x+2;
			}
			if(population[i][2]==1){
				x=x+4;
			}
			if(population[i][3]==1){
				x=x+8;
			}
			if(population[i][4]==1){
				x=x+16;
			}
			if(population[i][5]==1){
				x=x+32;
			}
			if(population[i][6]==1){
				x=x+64;
			}
			if(population[i][7]==1){
				x=x+128;
			}
			if(population[i][8]==1){
				x=x+256;
			}
			if(population[i][9]==1){
				x=x+512;
			}

			// y cordinate 

			if(population[i][10]==1){
				y=y+1;
			}
			if(population[i][11]==1){
				y=y+2;
			}
			if(population[i][12]==1){
				y=y+4;
			}
			if(population[i][13]==1){
				y=y+8;
			}
			if(population[i][14]==1){
				y=y+16;
			}
			if(population[i][15]==1){
				y=y+32;
			}
			if(population[i][16]==1){
				y=y+64;
			}
			if(population[i][17]==1){
				y=y+128;
			}
			if(population[i][18]==1){
				y=y+256;
			}
			if(population[i][19]==1){
				y=y+512;
			}

			//z cordinate
			if(population[i][20]==1){
				z=z+1;
			}
			if(population[i][21]==1){
				z=z+2;
			}
			if(population[i][22]==1){
				z=z+4;
			}
			if(population[i][23]==1){
				z=z+8;
			}
			if(population[i][24]==1){
				z=z+16;
			}
			if(population[i][25]==1){
				z=z+32;
			}
			if(population[i][26]==1){
				z=z+64;
			}
			if(population[i][27]==1){
				z=z+128;
			}
			if(population[i][28]==1){
				z=z+256;
			}
			if(population[i][29]==1){
				z=z+512;
			}

			//negative postive values 
			if(firstGen==true){

				if(population[i][30]==1){
					x=x*-1;
				}
				if(population[i][31]==1){
					y=y*-1;
				}
				if(population[i][32]==1){
					z=z=z*-1;
				}
			}
			if(x<=-1){
				population[i][30]=1;
			}
			if(y<=-1){
				population[i][31]=1;
			}
			if(z<=-1){
				population[i][32]=1;
			}
			if(x>=0){
				population[i][30]=0;
			}
			if(y>=0){
				population[i][31]=0;
			}
			if(z>=0){
				population[i][32]=0;
			}

			x=x/100;
			y=y/100;
			z=z/100;
			// boundary conditions
			if(x>5.12){
				x=5.12;
			}
			if(y>5.12){
				y=5.12;
			}
			if(z>5.12){
				z=5.12;
			}

			if(x<-5.12){
				x=-5.12;
			}
			if(y<-5.12){
				y=-5.12;
			}
			if(z<-5.12){
				z=-5.12;
			}

			//System.out.println("X "+x+" Y "+y+" Z "+z);

			fitnessValues[i]=(x*x)+(y*y)+(z*z);
			x=0;
			y=0;
			z=0;
		}


		return fitnessValues;
	}

	public static double averageFitness(double [] fitnessValues){


		double avg=0;
		for(int i=0;i<fitnessValues.length;i++){
			avg=avg+fitnessValues[i];
		}
		avg=avg/fitnessValues.length;
		System.out.println("The average for this generation is "+avg);
		return avg;
	}
	public static void printFitness(double [] fitnessValues){
		for(int i=0;i<fitnessValues.length;i++){
			System.out.println(fitnessValues[i]);	 
		}
	}
	public static int [][] ElitismtournmentSelectionMax(double [] fitnessValues,int [][] population,int cols,double globalMinMax){
		double temp=0;
		int[][] tempPop = new int[population.length][cols];
		double temp1=globalMinMax;
		int indexPosition=0;
		int indexPosition1=0;
		double tempTemp=0;

		for(int i=0;i<fitnessValues.length;i++){
			if(fitnessValues[i]>=temp){
				temp=fitnessValues[i];
				indexPosition=i;
			}
		}
		for(int ii=0;ii<fitnessValues.length;ii++){
			if(fitnessValues[ii]<=temp1){
				temp1=fitnessValues[ii];
				indexPosition1=ii;
			}

		}

		System.out.println("best value is "+temp+" and was found at index position "+indexPosition);	
		System.out.println("worst value is "+temp1+" and was found at index position "+indexPosition1);	

		tempTemp=fitnessValues[0];
		fitnessValues[0]=fitnessValues[indexPosition];
		fitnessValues[indexPosition]=tempTemp;

		for(int k=0;k<cols;k++){//good guy
			tempPop[0][k]=population[indexPosition][k];
		}

		for(int k=0;k<cols;k++){//any guy
			tempPop[1][k]=population[0][k];
		}

		for(int k=0;k<cols;k++){
			population[0][k]=tempPop[0][k];
		}
		for(int k=0;k<cols;k++){
			population[indexPosition][k]=tempPop[1][k];
		}


		Random rn = new Random();
		for(int i=0;i<fitnessValues.length;i++){
			int p1=0;
			int p2=0;
			do{
				p1=rn.nextInt(fitnessValues.length);
				p2=rn.nextInt(fitnessValues.length);
			}while(p1 ==0 || p2==0);

			double index1=fitnessValues[p1];
			double index2=fitnessValues[p2];
			//System.out.println(p1+" "+index1+"  "+p2+" "+index2);

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
	public static int [][] ElitismtournmentSelectionMin(double [] fitnessValues,int [][] population,int cols,double globalMinMax){

		double temp=100;
		double temp1=0;
		int indexPosition=0;
		int indexPosition1=0;

		for(int i=0;i<fitnessValues.length;i++){
			if(fitnessValues[i]<=temp){
				temp=fitnessValues[i];
				indexPosition=i;
			}
		}
		for(int ii=0;ii<fitnessValues.length;ii++){
			if(fitnessValues[ii]>=temp1){
				temp1=fitnessValues[ii];
				indexPosition1=ii;
			}

		}

		System.out.println("best value1 is "+temp+" and was found at index position "+indexPosition);	
		System.out.println("worst value1 is "+temp1+" and was found at index position "+indexPosition1);	

		for(int k=0;k<cols;k++){
			population[indexPosition1][k]=population[indexPosition][k];
		}




		Random rn = new Random();
		for(int i=0;i<fitnessValues.length;i++){
			int p1=rn.nextInt(fitnessValues.length);
			int p2=rn.nextInt(fitnessValues.length);

			double index1=fitnessValues[p1];
			double index2=fitnessValues[p2];
			//System.out.println(p1+" "+index1+"  "+p2+" "+index2);

			if(index1<index2){
				for(int k=0;k<cols;k++){
					population[i][k]=population[p1][k];
				}
			}else if(index2<index1){
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
	public static int [][] crossOver(double [] fitnessValues,int [][] population,int cols){
		Random rn = new Random();
		double crossOverRate=0.9;
		double crossOverProb=Math.random();

		if(crossOverProb<crossOverRate){
			int father=rn.nextInt(population.length);
			int mother=rn.nextInt(population.length);
			int co=rn.nextInt(cols);

			//System.out.println("father "+father+" mother "+mother+" cross-over point "+co);


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

	public static int [][] mutation(int [][] population, int cols){
		double mutationRate=1.0/population.length;//mutation rate of 0.1
		Random rn = new Random();

		for(int i=0;i<population.length;i++){
			for(int k=0;k<cols;k++){
				double mutationProb=Math.random();
				if(mutationProb<mutationRate){
					//System.out.println("Chrom "+i+" gene "+k);
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
	public static double hammingDistanceDeJong(int [][] twoD,int cols){
		 int coltotal=0;
		 System.out.println("DeJong");
		 ArrayList<Integer> hammingArray = new ArrayList(); 
		 for(int i=0;i<cols;i++){
			 for(int j=0;j<twoD.length;j++){
				 coltotal=coltotal+twoD[j][i];
			 }
			 hammingArray.add(coltotal);
			 coltotal=0;
		 }
		 System.out.println(hammingArray);

		return 0;
	}
	public static double hammingDistance(int [][] twoD,int cols){
		ArrayList<Integer> hammingArray = new ArrayList(); 

		int sum=0;
		int numberOnes=0;
		int numberZeros=0;
		double totalAllies=twoD.length*cols;

		for(int i=0;i<twoD.length;i++){
			for(int j=1;j<twoD.length;j++){
				for(int k=0;k<cols;k++){
					if(twoD[i][k]==twoD[j][k] && i<j){
						sum=sum+0;
						//System.out.println("twoD[i][k]"+twoD[i][k]+" twoD[j][k] "+twoD[j][k]);
					}else if(twoD[i][k]!=twoD[j][k] && i<j){
						sum=sum+1;
						//System.out.println("twoD[i][k]"+twoD[i][k]+" twoD[j][k] "+twoD[j][k]);
					}

				}
				if(i<j){
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
		System.out.println("total bits       "+totalAllies);

		return hammingSum;
	}

	public static void generateHammingGraph(double hammingDistance){
		hammingArray.add(hammingDistance);
		//generateHammingGraphStandardAlgorithm.generateCsvFile(hammingArray);
	}

	public static int [][] immigrationWithPickWorstIndividuals(double [] ft,int [][] newPop,int cols){

		double temp=cols;
		int indexPosition=0;

		int i=0;
		for(i=0;i<ft.length;i++){
			if(ft[i]<=temp){
				temp=ft[i];
				indexPosition=i;
			}

		}	
		Random rn = new Random();
		int[] tempArray= new int [cols];

		for(int k=0;k<cols;k++){
			tempArray[k]=rn.nextInt(2);
		}	

		for(int k=0;k<cols;k++){
			newPop[indexPosition][k]=tempArray[k];
		} 
		return newPop;
	}

	public static void averageAverage(double average,int run,int gen){

		if(run<=0){
			averageAverageArray.add(average);
		}else{
			averageAverageArray.set(gen,averageAverageArray.get(gen)+average);
		}

		if(run==runNumber-1){
			averageAverageArray.set(gen,averageAverageArray.get(gen)/runNumber);
		}
	}

	public static void getAverageAverageArray(){
		System.out.println("Average Average");
		for(int i=0; i<averageAverageArray.size();i++){
			System.out.println(averageAverageArray.get(i));
		}	
	}
	public static void getallAveragesArray(){
		System.out.println("allAverages ");
		for(int i=0; i<allAverages.size();i++){
			System.out.println(allAverages.get(i));
		}	
	}
	public static void getallAveragePreviousGenArray(){
		System.out.println("allAveragePreviousGen");
		for(int i=0; i<allAveragePreviousGen.size();i++){
			System.out.println(allAveragePreviousGen.get(i));
		}	
	}
	public static void getconvergenceCountMax(){
		System.out.println("convergenceCountMax");
		for(int i=0; i<convergenceCountMax.size();i++){
			System.out.println(convergenceCountMax.get(i));
		}	
	}
	public static void averageBest(double max,int run,int gen){

		if(run<=0){
			averageAverageBest.add(max);
		}else{
			averageAverageBest.set(gen,averageAverageBest.get(gen)+max);
		}

		if(run==runNumber-1){
			averageAverageBest.set(gen,averageAverageBest.get(gen)/runNumber);
		}
	}

	public static void getAverageAverageBestArray(){
		System.out.println("Average Best Array");
		for(int i=0; i<averageAverageBest.size();i++){
			System.out.println(averageAverageBest.get(i));
		}	
	}
	public static int gaConvergenceMax(double average,int gen){
		Scanner sc = new Scanner(System.in);	
		double sumConverg=0;
		int run=50;

		if(convergence.size()<=3){
			convergence.add(average);
			if(convergence.size()==4){
				for(int i=0;i<convergence.size();i++){
					sumConverg=sumConverg+convergence.get(i);
				}
				sumConverg=sumConverg/convergence.size();
			}
		}else{
			convergence.pollFirst();
			convergence.add(average);
			for(int i=0;i<convergence.size();i++){
				sumConverg=sumConverg+convergence.get(i);
			}

			sumConverg=sumConverg/convergence.size();
		}
		System.out.println(" Convergence Array  "+convergence);
		System.out.println("Average Convergence  "+sumConverg);
		allAverages.add(average);
		allAveragePreviousGen.add(sumConverg);
		if(average<=sumConverg){
			System.out.println("CONVERGENCE IS OCCURING for MAX  at generation "+gen);
			run=2;
			convergenceCountMax.add(gen);
			
		}
		sumConverg=0;	
		
		return run;
	}
	public static int gaConvergenceMin(double average){
		Scanner sc = new Scanner(System.in);	
		double sumConverg=0;
		int run=50;

		if(convergence.size()<=3){
			convergence.add(average);
			if(convergence.size()==4){
				for(int i=0;i<convergence.size();i++){
					sumConverg=sumConverg+convergence.get(i);
				}
				sumConverg=sumConverg/convergence.size();
			}
		}else{
			convergence.pollFirst();
			convergence.add(average);
			for(int i=0;i<convergence.size();i++){
				sumConverg=sumConverg+convergence.get(i);
			}

			sumConverg=sumConverg/convergence.size();
		}
		System.out.println(" Convergence Array  "+convergence);
		System.out.println("Average Convergence  "+sumConverg);

		if(average>=sumConverg){
			System.out.println("CONVERGENCE IS OCCURING for MIN  ");
			run=2;
			
		}
		sumConverg=0;	
		return run;
	}
	public static int [][] elitismImmigrationMax(int [][] newPop, double [] fitness ,double globalMinMax,int cols){

		double temp=0;
		double temp1=globalMinMax;
		int indexPosition=0;
		int indexPosition1=0;

		for(int i=0;i<fitness.length;i++){
			if(fitness[i]>=temp){
				temp=fitness[i];
				indexPosition=i;
			}
		}
		for(int ii=0;ii<fitness.length;ii++){
			if(fitness[ii]<=temp1){
				temp1=fitness[ii];
				indexPosition1=ii;
			}

		}

		System.out.println("best value is "+temp+" and was found at index position "+indexPosition);	
		System.out.println("worst value is "+temp1+" and was found at index position "+indexPosition1);	

		for(int k=0;k<cols;k++){
			newPop[indexPosition1][k]=newPop[indexPosition][k];
		}
		return newPop;
	}

	public static int [][] elitismImmigrationMin(int [][] newPop, double [] fitness ,double globalMinMax,int cols){

		double temp=100;
		double temp1=0;
		int indexPosition=0;
		int indexPosition1=0;

		for(int i=0;i<fitness.length;i++){
			if(fitness[i]<=temp){
				temp=fitness[i];
				indexPosition=i;
			}
		}
		for(int ii=0;ii<fitness.length;ii++){
			if(fitness[ii]>=temp1){
				temp1=fitness[ii];
				indexPosition1=ii;
			}

		}

		System.out.println("best value1 is "+temp+" and was found at index position "+indexPosition);	
		System.out.println("worst value1 is "+temp1+" and was found at index position "+indexPosition1);	

		for(int k=0;k<cols;k++){
			newPop[indexPosition1][k]=newPop[indexPosition][k];
		}
		return newPop;
	}

	public static void calculateaverageAverageArray(ArrayList<Double> generationAverage){



	}


}


