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
		parent[i] = i; // can be set to -1 also, its just to identify that this is the absolute parent
		rank[i] = 0;   // rank can be thought of as the height of the tree
	}

	// path compression is done here
	public int findSet(int i) {  // O(1) time for each FIND Operation
		if(parent[i] == i)
			return i;
		int set = findSet(parent[i]);
		parent[i] = set;
		return set;
	}

	// union by rank done here
	public boolean union(int i, int j) { // O(1) time for each UNION Operation
		System.out.println(Arrays.toString(parent));
		System.out.println(Arrays.toString(rank));

		int s1 = findSet(i);
		int s2 = findSet(j);

		if(s1 == s2)
			return false;   // cycle exists
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
	// O(E+V) time and O(V) space
	public boolean cyclePresent(Graph g) {
		for(int i : g.graph.keySet())   // O(V) time to form the disjoint sets
			makeSet(i);

		List<Edge> edges = new LinkedList<>();
		for(List<Edge> l : g.graph.values())
			edges.addAll(l);

		boolean isCycle = false;
		for(int i = 0 ; i < edges.size(); i++) { // O(E) time to find if cycle exists
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
			// Even though disjoint sets only works for undirected graph, for 2 vertices u, v
			// we need not store both (u,v) edge & (v,u) edge, as UNION(u,v) is same as UNION(v,u), and
			// UNION is the only thing we need to do for these edges.

			// while using disjoint set no need to add two edges, as we just need the two ends of the edge
			// and not the source and sink
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
