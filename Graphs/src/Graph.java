import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// Adjacency List representation of Graph
public class Graph {
	private Map<Node, List<Node>> graph;
	private boolean directed;

	public Graph(boolean directed) {
		this.directed = directed;
		graph = new HashMap<>();
	}

	public static class Node {
		private String label;

		public Node(String label) {
			this.label = label;
		}

		public String toString() {
			return label;
		}
	}

	public void addNode(Node node) {
		graph.putIfAbsent(node, new LinkedList<>());
	}

	public void addEdge(Node src, Node destn) {
		addNode(src);
		addNode(destn);
		graph.get(src).add(destn);
		if(!directed)
			graph.get(destn).add(src);
	}

	public void removeNode(Node node) {
		for(List<Node> l : graph.values()) {
			l.remove(node);
		}
		graph.remove(node);
	}

	public void removeEdge(Node src, Node destn) {
		if(graph.get(src) != null)
			graph.get(src).remove(destn);
		if(graph.get(destn) != null)
			graph.get(destn).remove(src);
	}

	public List<Node> getAdjacentNodes(Node node){
		return graph.get(node);
	}

	public void getDFSTraversal(Node src) {
		Set<Node> visited = new HashSet<>();
		traverseDFS(src, visited);
	}

	private void traverseDFS(Node src, Set<Node> visited) {
		if(src == null || visited.contains(src)) {
			return;
		}
		visited.add(src);
		System.out.println("Node visited : " + src.label);
		for(Node next : graph.get(src)) {
			traverseDFS(next, visited);
		}
	}

	public void getBFSTraversal(Node src) {
		Set<Node> visited = new HashSet<>();
		Queue<Node> q = new LinkedList<>();

		traverseBFS(src, visited, q);
	}

	private void traverseBFS(Node src, Set<Node> visited, Queue<Node> q) {
		if(src == null || visited.contains(src))
			return;

		visited.add(src);
		System.out.println("Node visited : " + src.label);
		for(Node n : graph.get(src)) {
			q.add(n);
		}
		while(!q.isEmpty()) {
			traverseBFS(q.poll(), visited, q);
		}
	}
}
