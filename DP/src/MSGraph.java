import java.util.Arrays;

// O(n^2)
public class MSGraph {
	public void msGraph(int[][] graph) {
		System.out.println(graph.length);
		int[] cost = new int[graph.length];
		Arrays.fill(cost, Integer.MAX_VALUE);
		cost[cost.length-1] = 0;
		for(int i = cost.length-2 ; i >= 0 ; i--) {
			for(int j = i+1 ; j < cost.length ; j++) {
				if(graph[i][j] < Integer.MAX_VALUE && (graph[i][j] + cost[j]) < cost[i]) {
					cost[i] = graph[i][j] + cost[j];
				}
			}
		}
		System.out.println(Arrays.toString(cost));
		
		System.out.println("Min cost from src to des : " + cost[0]);
	}
}
