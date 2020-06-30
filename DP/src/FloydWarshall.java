import java.util.Arrays;

// O(n^3), all pair shortest path for both directed and undirected graphs
public class FloydWarshall {
	public void getAllPairShortestPath(int[][] graph) {
		int[][] temp = new int[graph.length][graph.length];
		int[][] path = new int[graph.length][graph.length];
		for(int[] a : path)
			Arrays.fill(a, -1);
		for(int i = 0 ; i < graph.length ; i++) {
			for(int j = 0 ; j < graph.length ; j++) {
				if(graph[i][j] < Integer.MAX_VALUE) {
					path[i][j] = i;
				}
			}
		}
		
		
		int x[][] = new int[graph.length][graph.length];
		initialise(graph, temp);
		for(int k = 0 ; k < graph.length ; k++) {
			for(int i = 0 ; i < graph.length ; i++) {
				for(int j = 0 ; j < graph.length ; j++) {
					if(temp[i][k] < Integer.MAX_VALUE && temp[k][j] < Integer.MAX_VALUE) {
						x[i][j] = Math.min(temp[i][j], temp[i][k] + temp[k][j]);
					}
					else {
						x[i][j] = temp[i][j];
					}
					if(x[i][j] < temp[i][j]) {
						path[i][j] = k;
					}
				}
			}
			initialise(x, temp);
		}
		
		System.out.println("D^n :-------");
		for(int[] a : temp) {
			System.out.println(Arrays.toString(a));
		}
		System.out.println();
		System.out.println("Path :------------------");
		for(int[] a : path) {
			System.out.println(Arrays.toString(a));
		}
		
		for(int i = 0 ; i < path.length ; i++) {
			for(int j = 0 ; j < path.length ; j++) {
				if(i != j) {
					System.out.println(i + " --> " + j + " (totalCost = " + temp[i][j] + ") : ");
					printPath(path, i, j);
					System.out.println();
					System.out.println();
				}
			}
		}
	}
	
	private void initialise(int[][] g, int[][] t) {
		for(int i = 0 ; i < g.length ; i++) {
			for(int j = 0 ; j < g.length ; j++) {
				t[i][j] = g[i][j];
			}
		}
	}
	
	private void printPath(int[][] path, int i, int j) {
		System.out.print(i + " -to- ");
		while(path[i][j] != i) {
			i = path[i][j];
			System.out.print(i + " -to- ");
		}
		System.out.print(j);
	}
}
