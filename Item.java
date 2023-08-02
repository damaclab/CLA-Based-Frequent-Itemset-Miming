import java.io.*;

class Item{
	//Store all relevant information with respect to an item
	int id;
	int barcode;
	String name;

	Item(String name,int id){
		this.name=name;
		this.id=id;
	}

	Item(int id){
		this.name=Integer.toString(id);
		this.id=id;
	}

	String getName(){
		return name;
	}

	int getId(){
		return id;
	}
}