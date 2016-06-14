import java.util.ArrayList;
import java.util.Random;



public class MicroAlgorithm1 {
	//static int cols=10;
	static ArrayList<Double> hammingArray = new ArrayList(); //static to access static method
	public static void main(String args []){
		Random rn = new Random();
		double crossOverRate=0.9;
		int cols =33;
		double globalMinMax=0;
		int[][] population = new int[30][cols];
		double [] fitnessValues = new double[population.length];
		int sum=0;
		ArrayList<Double> averageArray= new ArrayList();

		population=generatePopulation(population,cols);

		boolean flag=true;

		for(int gen=1;gen<=50;gen++){
			//for(int gen=1;flag==true;gen++){
			System.out.println("generation ************************  "+gen);

			//printPopulation(population,cols);



			fitnessValues=calculateFitness(fitnessValues,population,cols);

			printFitness(fitnessValues);


			if(gen<=20){
			//firstGen=false;
				globalMinMax=78.6;
				population=tournmentSelectionMax(fitnessValues,population,cols);
			}else{
				globalMinMax=0;
				population=tournmentSelectionMin(fitnessValues,population,cols);
			}

			//printPopulation(population,cols);


			population=crossOver(fitnessValues,population, cols);

			//printPopulation(population,cols);

			population=mutation(population, cols);

			//printPopulation(population,cols);

			double hD=hammingDistance(population,cols);
			System.out.println("hamming distance  "+hD);
			generateHammingGraph(hD);
			
			double cR=convergenceRate(fitnessValues,globalMinMax);
			
			//if(cR>95){
				int bestValue=max(population,fitnessValues);
				System.out.println("Pop re-initilise but keep chrom "+bestValue);
				for(int k=0;k<cols;k++){
					population[0][k]=population[bestValue][k];	
				//}


				for(int i=1;i<population.length;i++){
					for(int k1=0;k1<cols;k1++){
						population[i][k1]=rn.nextInt(2);	 
					}	 
				}
				//printPopulation(population,cols);

			}
			
			
			double average=averageFitness(fitnessValues);

			averageArray.add(average);
			System.out.println("gen array");
			for(int r=0;r<averageArray.size();r++){
				System.out.println(averageArray.get(r));
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


			if(population[i][30]==1){
				x=x*-1;
			}
			if(population[i][31]==1){
				y=y*-1;
			}
			if(population[i][32]==1){
				z=z=z*-1;
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

			System.out.println("X "+x+" Y "+y+" Z "+z);

			fitnessValues[i]=(x*x)+(y*y)+(z*z);
			x=0;
			y=0;
			z=0;
		}


		return fitnessValues;
	}

	public static void printFitness(double [] fitnessValues){
		for(int i=0;i<fitnessValues.length;i++){
			System.out.println(fitnessValues[i]);	 
		}
	}

	public static int [][] tournmentSelectionMax(double [] fitnessValues,int [][] population,int cols){
		Random rn = new Random();
		for(int i=0;i<fitnessValues.length;i++){
			int p1=rn.nextInt(fitnessValues.length);
			int p2=rn.nextInt(fitnessValues.length);

			double index1=fitnessValues[p1];
			double index2=fitnessValues[p2];
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
	public static int [][] tournmentSelectionMin(double [] fitnessValues,int [][] population,int cols){
		Random rn = new Random();
		for(int i=0;i<fitnessValues.length;i++){
			int p1=rn.nextInt(fitnessValues.length);
			int p2=rn.nextInt(fitnessValues.length);

			double index1=fitnessValues[p1];
			double index2=fitnessValues[p2];
			System.out.println(p1+" "+index1+"  "+p2+" "+index2);

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

	public static int [][] mutation(int [][] population, int cols){
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

	public static int  max(int [][] population,double [] fv){
		double temp=0;
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
	public static double convergenceRate(double [] fitnessValues,double minOrMax){
		double convergenceRate=0;
		int value=(int)minOrMax;
		int arrayValue=0;
		
		for(int i=0;i<fitnessValues.length;i++){
			arrayValue=(int)fitnessValues[i];
			if(arrayValue==value){
				convergenceRate=convergenceRate+1;
			}
			arrayValue=0;
		}
		
		convergenceRate=(convergenceRate/fitnessValues.length)*100;
		System.out.println("convergenceRate "+convergenceRate+" %    value "+value+"  ");
		
		
	return convergenceRate;
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

}
