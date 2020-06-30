import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// Disjoint set with 'union by rank' and 'path compression'. Is used to find cycles in undirected graph and also in Kruskal's
// O(m) where m in number of operations done
public class DisjointSet {
	private int[] parent;
	private int[] rank;
	
	public DisjointSet(int size) {
		parent = new int[size];
		rank = new int[size];
	}
	
	public void makeSet(int i) {
		parent[i] = i;
		rank[i] = 0;
	}
	
	// path compression is done here
	public int findSet(int i) {
		if(parent[i] == i)
			return i;
		int set = findSet(parent[i]);
		parent[i] = set;
		return set;
	}
	
	// union by rank done here
	public boolean union(int i, int j) {
		System.out.println(Arrays.toString(parent));
		System.out.println(Arrays.toString(rank));
		
		int s1 = findSet(i);
		int s2 = findSet(j);
		
		if(s1 == s2)
			return false;
		if(rank[s1] > rank[s2]) {
			parent[s2] = s1;
		}else if(rank[s1] < rank[s2]) {
			parent[s1] = s2;
		} else {
			parent[s2] = s1;
			rank[s1]++;
		}
		System.out.println(Arrays.toString(parent));
		System.out.println(Arrays.toString(rank));
		return true;
	}
	
	public boolean cyclePresent(Graph g) {
		for(int i : g.graph.keySet())
			makeSet(i);
		
		List<Edge> edges = new LinkedList<>();
		for(List<Edge> l : g.graph.values())
			edges.addAll(l);
		
		boolean isCycle = false;
		for(int i = 0 ; i < edges.size(); i++) {
			Edge e = edges.get(i);
			if(!union(e.src, e.des)) {
				isCycle = true;
				break;
			}
		}
		return isCycle;
	}
	
	public class Graph{
		private Map<Integer, List<Edge>> graph = new HashMap<>();
		
		private void addNode(int i) {
			graph.putIfAbsent(i, new LinkedList<Edge>());
		}
		
		public void addEdge(int src, int des) {
			addNode(src);
			addNode(des);
			
			// while using disjoint set no need to add two edges, as we just need the two ends of the edge and not the source and sink
			graph.get(src).add(new Edge(src, des));
		}
	}
	
	private class Edge{
		private int src;
		private int des;
		
		public Edge(int i, int j) {
			src = i;
			des = j;
		}
	}
}
