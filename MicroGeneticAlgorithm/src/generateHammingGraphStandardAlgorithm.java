import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class generateHammingGraphStandardAlgorithm {

	 public static void generateCsvFile(ArrayList array)
	   {
		try
		{
		    FileWriter writer = new FileWriter("c:\\NUIG\\test.csv");
			 
		    String value=array.toString();
		    String [] a= new  String [array.size()];
		   for(int i=0;i<array.size();i++){
			   a[i]=array.get(i).toString();
		   } 
		    
		   // writer.append(value+"\n");
		   for(int i=0;i<array.size();i++){
			   writer.append(a[i]+"\n");
		   } 
		
				
		    //generate whatever data you want
				
		    writer.flush();
		    writer.close();
		}
		catch(IOException e)
		{
		     e.printStackTrace();
		} 
	    }
	
	
}
