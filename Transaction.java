import java.io.*;

class Transaction{
	String[] itemIds;
	int n;

	Transaction(String input){
		// String tmp = input.substring(1,input.length()-1);
		itemIds=input.split(",");
		n=itemIds.length;
	}

	int getI(int i){
		return Integer.parseInt(itemIds[i]);
	}

	int size(){
		return n;
	}

	void print(){
		for(int i=0;i<n;i++)
			System.out.print(itemIds[i]+" ");
		System.out.println();
	}
}