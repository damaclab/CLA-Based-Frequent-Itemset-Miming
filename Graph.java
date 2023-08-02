import java.io.*;

class Graph {
	int[][] strength;
	int[][] distance;
	int size;

	Graph(int n){
		strength = new int[n][n];
		distance = new int[n][n];
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				distance[i][j]=1;
		size=n;
	}

	Graph(int n,int t){
		strength = new int[n][n];
		distance = new int[n][n];
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				distance[i][j]=t;
		size=n;
	}

	int getIJ(int i,int j){
		return strength[i][j];
	}

	void setIJ(int i,int j,int n){
		strength[i][j]=n;
	}

	int getDist(int i,int j){
		return distance[i][j];
	}

	void setDist(int i,int j, int val){
		distance[i][j]=val;
	}
}
