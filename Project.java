import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Project {
	public static void main(String[] args)throws IOException{
		if(args.length != 4) {
			System.out.println("Invalid parameter(s) or parameter(s) missing!");
		} else {
			String filename = (String)args[0];

			BufferedReader reader = new BufferedReader(new FileReader(filename));

			int n = Integer.parseInt(args[1]);
			
			Items items=new Items(n);
			for(int i=0;i<n;i++){
				items.add(new Item(i));
			}

			// Input transaction list
			int num = Integer.parseInt(args[2]);

			Transaction[] transactions = new Transaction[num];
			String line="";
			int i=0;
			while((line=reader.readLine())!=null){
				transactions[i++] = new Transaction(line);
			}

			reader.close();

			// Initialise Threshold
			double thresh = Double.parseDouble(args[3]);
			int threshold = (int)Math.ceil(thresh * num);

			// Generate frequent patterns
			long startTime = System.currentTimeMillis();
			items.getOutput(transactions,threshold);
			long endTime = System.currentTimeMillis();

			// Print frequent patterns
			items.printOutput((endTime-startTime),threshold);
		}
	}
}