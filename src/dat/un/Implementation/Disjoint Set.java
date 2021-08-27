class DisjointSets {
	int[] rank, parent;
	int n;

	// Constructor
	public DisjointSets(int n)
	{
		rank = new int[n];
		parent = new int[n];
		this.n = n;
		makeSet();
	}

	void makeSet(){
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}
	}


	int find(int x){
		if (parent[x] != x) {
			parent[x] = find(parent[x]);
			// Uso de recursion para path compression
		}

		return parent[x];
	}

	void union(int x, int y){
		int xRoot = find(x);
		int	yRoot = find(y);

		if (xRoot == yRoot)
			return;

		// Union por rank
		if (rank[xRoot] < rank[yRoot])
			parent[xRoot] = yRoot;

		else if (rank[yRoot] < rank[xRoot])
			parent[yRoot] = xRoot;

		else {
			parent[yRoot] = xRoot;
			rank[xRoot] = rank[xRoot] + 1;
		}
	}
	
	// Prueba si estan en el mismo set
	void test(int x, int y) {
		if (this.find(x) == this.find(y))
			System.out.println(x + " y " + y + " estan en el mismo set");
		else
			System.out.println(x + " y " + y + " no estan en el mismo set");
	}
}

public class Main {
	public static void main(String[] args){
		// TODO Auto-generated method stub
		
		long start1 = System.nanoTime();
		// Crea de 0 a n sets
		int n = 10000;
		System.out.println("Numero de datos: "+ n);
		
		System.out.println();
		
		DisjointSets set = new DisjointSets(n);
		long end1 = System.nanoTime();
		System.out.println("Tiempo de makeSet(): "+ (end1-start1));
		
		System.out.println();
		
		long start2 = System.nanoTime();
		set.find(555);
		long end2 = System.nanoTime();
		System.out.println("Tiempo de find(int): "+ (end2-start2));
		
		System.out.println();
		
		long start3 = System.nanoTime();
		set.union(0, 2);
		long end3 = System.nanoTime();
		System.out.println("Tiempo de union(int,int): "+ (end3-start3));
		
		System.out.println();
		
		long start4 = System.nanoTime();
		set.test(0, 2);
		long end4 = System.nanoTime();
		System.out.println("Tiempo de test(int,int): "+ (end4-start4));
		
	}
}
