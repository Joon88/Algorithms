import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// O(VE) - great for detecting -ve weight cycles
public class BellmanFord {
	public class Graph{
		private Map<Node, List<Edge>> graph = new HashMap<>();
		
		private void addNode(Node n) {
			graph.putIfAbsent(n, new LinkedList<Edge>());
		}
		
		public void addEdge(Node src, Node des, int weight) {
			addNode(src);
			addNode(des);
			// for undirected graph, we need to relax both 'src---des' and 'des---src'
			graph.get(src).add(new Edge(src, des, weight));
		}
	}
	
	public class Node{
		private int id;
		private int key;
		
		public Node(int i) {
			id = i;
			// subtracting 100, coz sometime while relaxing, we may add some weight to the max_value, making it -ve in 2's somplement
			key = Integer.MAX_VALUE-100;
		}
	}
	
	private class Edge{
		private Node src;
		private Node des;
		private int weight;
		
		public Edge(Node src, Node des, int weight) {
			this.src = src;
			this.des = des;
			this.weight = weight;
		}
	}
	
	public void runBellmanFord(Graph g, Node src) {
		src.key = 0;
		
		List<Edge> edges = new ArrayList<>();
		for(List<Edge> l : g.graph.values()) {
			edges.addAll(l);
		}
		
		for(int i = 0 ; i < g.graph.size()-1 ; i++) {
			for(Edge e : edges) {
				relaxEdge(e);
			}
		}
		
		boolean negativeWtPresent = false;
		for(Edge e : edges) {
			if(relaxEdge(e)) {
				negativeWtPresent = true;
				break;
			}
		}
		
		if(negativeWtPresent)
			System.out.println("-ve wt cycle present");
		else {
			for(Node n : g.graph.keySet()) {
				System.out.println(n.id + " -- " + n.key);
			}
		}
	}
	
	public boolean relaxEdge(Edge e) {
		boolean relaxed = false;
		
		if(e.des.key > (e.src.key + e.weight)) {
			e.des.key = e.src.key + e.weight;
			relaxed = true;
		}
		return relaxed;
	}
}
