import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Project {

	public static void main(String[] args)throws IOException{
		String filename = "kosarakPreprocessed.txt";
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		
		//Input items
		int n = Integer.parseInt(reader.readLine());
		Items items=new Items(n);
		for(int i=0;i<n;i++){
			items.add(new Item(i));
		}

		//Input transaction list
		int num = Integer.parseInt(reader.readLine());
		Transaction[] transactions = new Transaction[num];
		String line="";
		int i=0;
		while((line=reader.readLine())!=null){
			transactions[i++] = new Transaction(line);
		}

		reader.close();

		//Initialise Threshold
		double thresh = 10;
		int threshold = (int)Math.ceil(thresh * num);

		//get and print output
		long startTime = System.currentTimeMillis();
		items.getOutput(transactions,threshold);
		
		long endTime = System.currentTimeMillis();
		items.printOutput((endTime-startTime),threshold);

	}
}