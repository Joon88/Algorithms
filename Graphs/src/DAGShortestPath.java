import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

// O(V+E) to find single source shortest path using DAG, the src is passed in the method
public class DAGShortestPath {
	public class Edge{
		private Node des;
		private int weight;
		
		public Edge(Node d, int w) {
			des = d;
			weight = w;
		}
	}
	
	public class Node{
		private int id;
		private int key;
		
		public Node(int i) {
			id = i;
			key = Integer.MAX_VALUE;
		}
		
		public String toString() {
			return String.valueOf(id);
		}
	}
	
	public class TopologicalSort {
		private Map<Node, List<Edge>> graph = new HashMap<>();

		private void addNode(Node i) {
			graph.putIfAbsent(i, new LinkedList<Edge>());
		}

		public void addEdge(Node src, Node des, int weight) {
			addNode(src);
			addNode(des);

			graph.get(src).add(new Edge(des, weight));
		}

		public Stack<Node> topologicalSort() {
			Set<Node> visitedSet = new HashSet<>();
			Stack<Node> processedStack = new Stack<>();
			for (Node node : graph.keySet()) {
				topSort(visitedSet, processedStack, node);
			}
			
			System.out.println("Bottom to top of stack : " + processedStack);
			return processedStack;
		}

		private void topSort(Set<Node> visitedSet, Stack<Node> processedStack, Node node) {
			if(!visitedSet.contains(node)) {
				visitedSet.add(node);
				for(Edge child : graph.get(node)) {
					topSort(visitedSet, processedStack, child.des);
					
				}
				processedStack.push(node);
			} else {
				return;
			}
			
		}
		
		public Map<Node, List<Edge>> getTheGraph() {
			return graph;
		}
	}
	
	public void getShortestPath(int source) {
		
		TopologicalSort ts = new TopologicalSort();
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		Node n6 = new Node(6);
		Node n7 = new Node(7);
		Node n8 = new Node(8);
		
		ts.addEdge(n1, n3, 1);
		ts.addEdge(n3, n5, 4);
		ts.addEdge(n5, n6, 1);
		ts.addEdge(n5, n8, 2);
		ts.addEdge(n6, n7, 3);
		ts.addEdge(n2, n3, 7);
		ts.addEdge(n2, n4, 3);
		ts.addEdge(n4, n5, 2);
		
		Stack<Node> stack = ts.topologicalSort();
		Map<Node, List<Edge>> g = ts.getTheGraph();
		
		while(stack.peek().id != source) {
			stack.pop();
		}
		Node src = stack.peek();
		src.key = 0;
		
		while(!stack.empty()) {
			Node node = stack.pop();
			for(Edge e : g.get(node)) {
				relaxEdge(node, e);
			}
		}
		
		for(Node n : g.keySet()) {
			System.out.println(n.id + " --> " + n.key);
		}
		
	}
	
	private void relaxEdge(Node node, Edge e) {
		if(node.key < Integer.MAX_VALUE && e.des.key > node.key + e.weight)
			e.des.key = node.key + e.weight;
	}
}
