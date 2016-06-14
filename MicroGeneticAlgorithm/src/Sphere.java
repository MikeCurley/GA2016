import java.util.ArrayList;
import java.util.Random;
public class Sphere {

	public static void main(String[] args) {

		int [][] binaryValue = new int[4][4];
		Random rn = new Random();
		ArrayList<Double> aa = new ArrayList<Double>();
		ArrayList<Double> bb = new ArrayList<Double>();
		aa.add(10.0);
		aa.add(15.0);
		
	    bb.add(11.0);
		bb.add(12.0);
		
		System.out.println(aa);
		for(int i=0;i<aa.size();i++){
		aa.set(i,aa.get(i)+bb.get(i));
		}
		
		System.out.println(aa);


		for(int i=0;i<binaryValue.length;i++){
			for(int k=0;k<binaryValue.length;k++){
				binaryValue[i][k]=rn.nextInt(2);	
			}
		}


		printPop(binaryValue);
		passPopulation(binaryValue);




	}
	public static void passPopulation(int [][] binaryValue){
		int result=0;
		int fitness [] = new int[4];
		for(int i=0;i<binaryValue.length;i++){
			
				if(binaryValue[i][0]==1){
					result=result+1;
				}
				if(binaryValue[i][1]==1){
					result=result+2;
				}
				if(binaryValue[i][2]==1){
					result=result+4;
				}
				if(binaryValue[i][3]==1){
					result=result+8;
				}
			fitness[i]=result;
			result=0;
		}
		
		
		if(-2<-1){
			System.out.println("WO WO ");
		}
		
		
		for(int i=0;i<fitness.length;i++){
			System.out.println(fitness[i]);
		}
		

	}
	public static void printPop(int [][] binaryValue){
		for(int i=0;i<binaryValue.length;i++){
			for(int k=binaryValue.length-1;k>=0;k--){
				System.out.print(binaryValue[i][k]);	
			}
			System.out.println();
		}
		System.out.println();

	}

}


