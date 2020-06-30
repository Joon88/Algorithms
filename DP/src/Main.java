import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
	private static int INF = Integer.MAX_VALUE;
	public static void main(String args[]) {
		System.out.println("MCM------------------------------");
		matrixChain();
		System.out.println("LCS--------------------------------");
		lcs();
		System.out.println("Multi-stage graph--------------------");
		multiStageG();
		System.out.println("Knapsack--------------------------");
		knapsack();
		System.out.println("Subset sum--------------------------");
		subsetSum();
		System.out.println("TSP------------------------------");
		tsp();
		System.out.println("Floyd Warshall-----------------------");
		fw();
	}
	
	public static void matrixChain() {
		int[] p = {1,2,1,4,1};
		MatrixChain m = new MatrixChain();
		m.getMatrixChainDataTD(p);
		System.out.println();
	}
	
	public static void lcs() {
		LCS l = new LCS();
		char[] c1 = {'a', 'b', 'c', 'd', 'e', 'f'};
		char[] c2 = {'a', 'c', 'e', 'd'};
		//char[] c1 = {'a', 'b', 'c', 'd', '4'};
		//char[] c2 = {'a', 'b', 'c', 'd', '5'};
		l.findLSC(c1, c2);
	}
	
	public static void multiStageG() {
		MSGraph msg = new MSGraph();
		int[][] graph = {{INF, 1, 2, 5, INF, INF, INF, INF}, 
		        {INF, INF, INF, INF, 4, 11, INF, INF}, 
		        {INF, INF, INF, INF, 9, 5, 16, INF}, 
		        {INF, INF, INF, INF, INF, INF, 2, INF}, 
		        {INF, INF, INF, INF, INF, INF, INF, 18}, 
		        {INF, INF, INF, INF, INF, INF, INF, 13}, 
		        {INF, INF, INF, INF, INF, INF, INF, 2},
		        {INF, INF, INF, INF, INF, INF, INF, INF}};
		msg.msGraph(graph);
	}
	
	public static void knapsack() {
		ZeroOneKnapSack k = new ZeroOneKnapSack();
		k.memo = new int[4][7];
		for(int[] a : k.memo) {
			System.out.println(Arrays.toString(a));
		}
		System.out.println(k.knapsack(3, 6, new int[] {0,1,2,4}, new int[] {0,10,12,28}));
		for(int[] a : k.memo) {
			System.out.println(Arrays.toString(a));
		}
	}
	
	public static void subsetSum() {
		ZeroOneKnapSack k = new ZeroOneKnapSack();
		k.memo = new int[5][15];
		for(int[] a : k.memo) {
			Arrays.fill(a,  -1);
			System.out.println(Arrays.toString(a));
		}
		System.out.println(k.subsetSum(4, 14, new int[] {2, 4, 7, 10}));
		for(int[] a : k.memo) {
			System.out.println(Arrays.toString(a));
		}
	}
	
	public static void tsp() {
		TSP t = new TSP();
		Set<Integer> set = new HashSet<>();
		//set.add(0);
		set.add(1);
		set.add(2);
		set.add(3);
		t.memo = new int[4][(int)Math.pow(2, 4)];
		
		for(int[] a : t.memo) {
			Arrays.fill(a, -1);
		}
		
		int[][] graph = new int[][]{{0,1,15,6},
 			 {2,0,7,3},
 			 {9, 6, 0, 12},
 			 {10, 4, 8, 0}};
		
		int cost = t.getTSPByDP(graph, 0, 1);
		System.out.println(cost);
		for(int[] a : t.memo) {
			System.out.println(Arrays.toString(a));
		}
		System.out.println();
		System.out.print("0");
		t.printMinWtHamiltonianPath(1, 0, cost, graph);
	}
	
	public static void fw() {
		System.out.println();
		FloydWarshall fw = new FloydWarshall();
		fw.getAllPairShortestPath(new int[][] {{0,3,6,15},
											   {INF,0,-2,INF},
											   {INF, INF, 0, 2},
											   {1,INF,INF,0}});
	}
}
