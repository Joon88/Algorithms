import java.util.HashSet;
import java.util.Set;

public class TSP {
	public int[][] memo;
	public int getTSPCost(int[][] graph, int node, Set<Integer> s) {
		int min = Integer.MAX_VALUE;
		if(s.isEmpty())
			return graph[node][0];
		else {
			for(int v : s) {
				Set<Integer> copySet = new HashSet<>(s);
				copySet.remove(v);
				int currCost = graph[node][v] + getTSPCost(graph, v, copySet);
				if(min > currCost) {
					min = currCost;
				}
			}
		}
		return min;
	}
	
	public int getTSPByDP(int[][] graph, int node, int mask) {
		
		if(mask == (1 << graph.length)-1) {
			memo[node][mask] = graph[node][0];
		} else if(memo[node][mask] > -1) {
			return memo[node][mask];
		} else {
			int min = Integer.MAX_VALUE;
			for(int i = 1 ; i < graph.length ; i++) {
				if((mask&(1<<i)) == 0) {
					int currMin = graph[node][i] + getTSPByDP(graph, i, mask | (1 << i));
					min = Math.min(min, currMin);
				}
			}
			
			memo[node][mask] = min;
		}
		return memo[node][mask];
	}
	
	public void printMinWtHamiltonianPath(int mask, int src, int rsltCost, int[][] graph) {
		if(mask == (1<<graph.length)-1) {
			System.out.print(" --> 0");
			return;
		}
		int tmpMask = mask, cost = 0, i;
		for(i = 1 ; i < graph.length ; i++) {
			if((mask&(1<<i)) == 0) {
				tmpMask = mask | (1 << i);
				cost = memo[i][tmpMask];
				if(rsltCost == graph[src][i] + cost) {
					System.out.print(" --> " + i);
					break;
				}
			}
		}
		printMinWtHamiltonianPath(tmpMask, i, cost, graph);
	}
}
