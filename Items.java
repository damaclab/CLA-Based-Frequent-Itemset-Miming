
import java.io.*;
import java.util.*;

class ListComparator<T extends Comparable<T>> implements Comparator<List<T>> {

  @Override
  public int compare(List<T> o1, List<T> o2) {
    for (int i = 0; i < Math.min(o1.size(), o2.size()); i++) {
      int c = o1.get(i).compareTo(o2.get(i));
      if (c != 0) {
        return c;
      }
    }
    return Integer.compare(o1.size(), o2.size());
  }

}

class Items{
	//List of items in the store(Sample set)
	Item[] listOfItems;
	int n;
	int totalTransactions=0;

	Items(int n){
		listOfItems=new Item[n];
		this.n=n;
	}

	int iterator=0;

	//adds an item to the list
	void add(Item item){
		if(iterator>=n)
			return;
		listOfItems[iterator]=item;
		iterator++;
	}

	Item getItem(int i){
		return listOfItems[i];
	}

	//Hash set of outputs obtained from algorithm
	Set<ArrayList<Integer>> outputSet2Item;
	Set<ArrayList<Integer>> outputSetItems;


	void getOutput(Transaction[] transactions,int threshold){
		//Initialising the graph(automata)
		// Graph graph = new Graph(n);

		Graph graph = new Graph(n,threshold);
		outputSet2Item=new HashSet<ArrayList<Integer>>();

		for(Transaction transaction : transactions){
			// checkMemory();
			//reinforcement and making links for each transaction
			int transactionSize=transaction.size();
			for(int i=0;i<transactionSize;i++){
				int id1=transaction.getI(i);
				for(int j=i+1;j<transactionSize;j++){
					int id2=transaction.getI(j);
					int p=graph.getIJ(id1,id2);

					if(transactionSize>2){
						graph.setDist(id1,id2,graph.getDist(id1,id2)-1);
						graph.setDist(id2,id1,graph.getDist(id2,id1)-1);
					}
					
					//if threshold is reached the pair is added to the output set else links are strengthened 
					if(p+1==threshold){
						graph.setIJ(id1,id2,p+1);
						graph.setIJ(id2,id1,p+1);
						ArrayList<Integer> tmp = new ArrayList<Integer>(2);
						tmp.add(id1);
						tmp.add(id2);
						outputSet2Item.add(tmp);
					}
					else if(p+1<threshold){
						graph.setIJ(id1,id2,p+1);
						graph.setIJ(id2,id1,p+1);
					}
				}
			}

			totalTransactions++;
			//Printing graph position after each transaction
			// for(int i=0;i<n;i++){
			// 	for(int j=0;j<n;j++)
			// 		System.out.print(graph.getDist(i,j)+" ");
			// 	System.out.println();
			// }
			// for(int i=0;i<n;i++){
			// 	for(int j=0;j<n;j++)
			// 		System.out.print(graph.getIJ(i,j)+" ");
			// 	System.out.println();
			// }
		}

		getOutputHelper(graph);
	}

	void getOutputHelper(Graph graph){
		outputSetItems=new HashSet<ArrayList<Integer>>();
		int size = outputSet2Item.size();
		///boolean[] seen = new boolean[size];
		int i=-1;
		for(ArrayList<Integer> tmp:outputSet2Item){
			i++;
			boolean[] seen = new boolean[size];
			seen[i]=true;
			int first = tmp.get(0);
			int second = tmp.get(1);
			int third = -1;
			int j=-1;
			for(ArrayList<Integer> tmp1:outputSet2Item){
				j++;
				if(seen[j] == true)
					continue;
				if(tmp1.get(0)==second || tmp1.get(1)==second){
					third=(tmp1.get(0)==second)?tmp1.get(1):tmp1.get(0);
					seen[j]=true;
					int k=-1;
					for(ArrayList<Integer> tmp2:outputSet2Item){
						k++;
						if(seen[k]==true)
							continue;
						// ArrayList<Integer> tmp2=i3.next();
						if(tmp2.get(0)==third || tmp2.get(1) == third){
							int check = (tmp2.get(0)==third)?tmp2.get(1):tmp2.get(0);
							if(check==first){
								if(graph.getDist(first,second)<=0 && graph.getDist(second,third)<=0 && graph.getDist(third,first)<=0){
									ArrayList<Integer> entry = new ArrayList<Integer>();
									entry.add(first);
									entry.add(second);
									entry.add(third);
									Collections.sort(entry);
									outputSetItems.add(entry);
								}
							}
						}
					}
					seen[j]=false;
				}
			}
			seen[i]=false;
			// checkMemory();
		}
	}
	private double maxmemory=0;
	void checkMemory(){
		double currentMemory = (Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory())/1024d/1024d;
	 	if(currentMemory>maxmemory)
			maxmemory=currentMemory;
	}

	void printOutput(long time, int threshold){

		List<ArrayList<Integer>> finalOutputSetA = new ArrayList<ArrayList<Integer>>(outputSet2Item);
		List<ArrayList<Integer>> finalOutputSetB = new ArrayList<ArrayList<Integer>>(outputSetItems);
		Collections.sort(finalOutputSetA, new ListComparator<>());
		Collections.sort(finalOutputSetB, new ListComparator<>());

		// for(ArrayList<Integer> itemSet:outputSet2Item){
		// 	System.out.println("["+getItem(itemSet.get(0)).getName()+", "+getItem(itemSet.get(1)).getName()+ "]");
		// 	count++;
		// }
		int n1=finalOutputSetA.size();
		int n2=finalOutputSetB.size();

		System.out.println("\nFrequent item sets :");

		for(int i=0;(i<n1) && (i<10);i++){
			System.out.println(finalOutputSetA.get(i));
		}
		for(int i=0;i<(10-n1) && i<n2;i++){
			System.out.println(finalOutputSetB.get(i));
		}

		// for(ArrayList<Integer> itemSet:outputSetItems){
		// 	finalOutputSet.add(itemSet);
		// 	// System.out.println("("+getItem(itemSet.get(0)).getName()+", "+getItem(itemSet.get(1)).getName()+", "+getItem(itemSet.get(2)).getName()+  ")");
		// 	// count++;
		// }
		
		checkMemory();
		System.out.println("================Statistics=================");
		System.out.println("Number of transactions : " + totalTransactions);
		System.out.println("Threshold: "+threshold);
		System.out.println("Max memory : "+ maxmemory +"MB");
		System.out.println("Time taken : "+ time +"ms");
		System.out.println("===========================================");

		// System.out.println("No. of frequent itemsets : "+(finalOutputSetA.size()+finalOutputSetB.size()));

	}
}