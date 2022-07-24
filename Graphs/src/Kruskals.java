import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

// O(ElogE + ElogV) time and O(E+V) space to store the graph
// This uses PriorityQueue and Disjoint Set Union Find (DSUF)
// Look at TechDose video for Adjacency Matrix impl
public class Kruskals {
	private static class DisjointSet{
		public Map<Integer, Integer> parentMap = new HashMap<>();
		public Map<Integer, Integer> rankMap = new HashMap<>();

		public void makeSet(int i) {
			parentMap.put(i, i);
			rankMap.put(i, 0);
		}

		public int findSet(int i) {
			if(parentMap.get(i) == i)
				return i;
			int set = findSet(parentMap.get(i));
			parentMap.put(i, set);
			return set;
		}

		public boolean union(int i, int j) {
			if(findSet(i) == findSet(j))
				return false;

			int seti = parentMap.get(i);
			int setj = parentMap.get(j);

			if(rankMap.get(seti) > rankMap.get(setj)) {
				parentMap.put(setj, seti);
			} else if(rankMap.get(seti) < rankMap.get(setj)) {
				parentMap.put(seti, setj);
			} else {
				parentMap.put(setj, seti);
				rankMap.put(seti, rankMap.get(seti)+1);
			}
			return true;
		}
	}
	public static class Graph{
		private Map<Integer, List<Edge>> graph = new HashMap<>();

		private void addNode(int i) {
			graph.putIfAbsent(i, new LinkedList<Edge>());
		}

		public void addEdge(int src, int dest, int weight) {
			addNode(src);
			addNode(dest);

			// while using disjoint set no need to add two edges, as we just need the two ends of the edge and not the source and sink
			graph.get(src).add(new Edge(src, dest, weight));
		}

		public void printGraph() {
			for(Map.Entry<Integer, List<Edge>> entry : graph.entrySet()) {
				System.out.println(entry.getKey() + " --> " + entry.getValue());
			}
		}
	}
	// When we are directly dealing with edges e.g. sorting or comparing them, we must have
	// the source vertex, the destination vertex and the edge weight in the edge object
	private static class Edge {
		private int src;
		private int dest;
		private int weight;

		public Edge(int src, int dest, int weight) {
			this.src = src;
			this.dest = dest;
			this.weight = weight;
		}

		public String toString() {
			return src + " -to- " + dest;
		}
	}

	public void getMST(Graph graph) {
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>(new Comparator<Edge>() {
			public int compare(Edge o1, Edge o2) {
				return Integer.valueOf(o1.weight).compareTo(o2.weight);
			}
		});

		DisjointSet ds = new DisjointSet();

		for(List<Edge> l : graph.graph.values()) { // O(ElogE) (this can be optimised to O(E) if we
												   // initialise the PQ with add the edges in one go
												   // using pq.addAll(Collections<>))
			for(Edge e : l) {
				pq.add(e);
				ds.makeSet(e.src);     // O(1) amortized
				ds.makeSet(e.dest);
			}
		}

		List<Edge> edges = new ArrayList<>();

		while(!pq.isEmpty()) {
			Edge edge = pq.poll();

			if(!ds.union(edge.src, edge.dest)) // O(ElogV)
				continue;
			edges.add(edge);
		}

		System.out.println("Kruskals MST");
		int sum = 0;
		for(Edge e : edges) {
			sum += e.weight;
			System.out.println(e.src + " --> " + e.dest);
		}
		System.out.println(sum);
	}
}
