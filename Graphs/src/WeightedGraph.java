import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

// Prim's and Kruskal's work only for undirected graphs(check for reason in GFG)
public class WeightedGraph {
	private Map<Node, List<Edge>> graph = new HashMap<>();
	
	public static class Node{
		private int id;
		private int key;
		
		public Node(int id) {
			this.id = id;
			key = Integer.MAX_VALUE;
		}

		@Override
		public int hashCode() {
			return id;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (id != other.id)
				return false;
			return true;
		}
		
		public String toString() {
			return String.valueOf(id);
		}
		
	}
	
	private static class Edge{
		private Node dest;
		private int weight;
		
		public Edge(Node dest, int weight) {
			this.dest = dest;
			this.weight = weight;
		}
		
		public String toString() {
			return dest.id + "-" + weight;
		}
	}
	
	private void addNode(Node node) {
		graph.putIfAbsent(node, new LinkedList<Edge>());
	}
	
	public void addEdge(Node srcNode, Node destNode, int weight) {
		addNode(srcNode);
		addNode(destNode);
		
		graph.get(srcNode).add(new Edge(destNode, weight));
		graph.get(destNode).add(new Edge(srcNode, weight));
	}
	
	public void printGraph() {
		for(Node n : graph.keySet()) {
			System.out.print(n.id + " --> ");
			for(Edge e : graph.get(n)) {
				System.out.println(e + ",");
			}
		}
	}
	
	public void primsMST() {
		Map<Node, Node> parentMap = new HashMap<>(graph.keySet().size());
		Map<Node, Boolean> inPQ = new HashMap<>(graph.keySet().size());
		
		for(Node n : graph.keySet()) {
			parentMap.put(n, null);
			inPQ.put(n, true);
		}
		
		Node src = graph.keySet().iterator().next();
		
		src.key = 0;
		
		// TreeSet is used in place of PriorityQueue for minHeap implementation, as remove() takes O(n) in priorityQueue but O(log n) in TreeSet
		TreeSet<Node> q = new TreeSet<>(new Comparator<Node>() {
			public int compare(Node o1, Node o2) {
				return Integer.valueOf(o1.key).compareTo(o2.key);
			}
		});
		
		// this loop is effectively running only once, as the "key" attribute for all nodes except src is same
		for(Node n : graph.keySet()) {
			q.add(n);
		}
		
		while(!q.isEmpty()) {
			Node node = q.pollFirst();
			
			inPQ.put(node, false);
			for(Edge e : graph.get(node)) {
				if(inPQ.get(e.dest) && e.dest.key > e.weight) {
					q.remove(e.dest);
					e.dest.key = e.weight;
					q.add(e.dest);
					parentMap.put(e.dest, node);
				}
			}
		}
		System.out.println("Source : " + src.id);
		int sum = 0;
		for(Map.Entry<Node, Node> entry : parentMap.entrySet()) {
			if(entry.getValue() != null) {
				for(Edge e : graph.get(entry.getValue())) {
					if(e.dest == entry.getKey())
						sum += e.weight;
				}
				System.out.println(entry.getValue() + " --> " + entry.getKey());
			}
		}
		System.out.println(sum);
	}
}
