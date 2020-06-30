import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstras {
	public class Node {
		private int id;
		private int key;
		
		public Node(int id) {
			this.id = id;
			this.key = Integer.MAX_VALUE;
		}
	}
	
	private class Edge{
		private int weight;
		private Node des;
		
		public Edge(Node des, int weight) {
			this.weight = weight;
			this.des = des;
		}
	}
	
	public class Graph{
		private Map<Node, List<Edge>> graph = new HashMap<>();
		
		private void addNode(Node n) {
			graph.putIfAbsent(n, new LinkedList<Edge>());
		}
		
		public void addEdge(Node src, Node des, int weight) {
			addNode(src);
			addNode(des);
			
			graph.get(src).add(new Edge(des, weight));
			graph.get(des).add(new Edge(src, weight));
		}
	}
	
	public void getShortestPath(Graph g, Node src) {
		src.key = 0;
		
		
		Map<Node, Boolean> inQueue = new HashMap<>(g.graph.keySet().size());
		for(Node n : g.graph.keySet()) {
			inQueue.put(n, true);
		}
		
		
		// TreeSet is used in place of priority queue as priority queue takes O(n) for remove
/*		TreeSet<Node> q = new TreeSet<>(new Comparator<Node>() {
			public int compare(Node o1, Node o2) {
				return Integer.valueOf(o1.key).compareTo(o2.key);
			}
		});
		
		for(Node n : g.graph.keySet())
			q.add(n);
*/		
		
		
		// But, if we skip the usage of remove() from priority queue its the best
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			public int compare(Node o1, Node o2) {
				return Integer.valueOf(o1.key).compareTo(o2.key);
			}
		});
		
		// here, I'm not adding all the nodes to the PQ, just the src
		pq.add(src);
		
		while(!pq.isEmpty()) {
			//Node n = q.pollFirst();
			Node n = pq.poll();
			inQueue.put(n, false);
			for(Edge e : g.graph.get(n)) {
				if(inQueue.get(e.des) && e.des.key > (n.key + e.weight)) {
					//q.remove(e.des);
					e.des.key = n.key + e.weight;
					pq.add(e.des);
				}
			}
		}
		
		for(Node n : g.graph.keySet()) {
			System.out.println(n.id + " --> " + n.key);
		}
		
	}
}
