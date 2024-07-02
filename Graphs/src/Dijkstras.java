/*
Leetcode example : https://leetcode.com/problems/network-delay-time/
The above example shows how to handle the problem to multiple Node objects being created
for a single node id, and how to skip all the damn confusion about it.
All we need to do, is first create an array of Node objects for all the given ids,
then use these node objects to form the adjacency list, rather than creating a new object
each time we interact with the adjacency list for any vertex id.
 */
import java.util.*;

// O(ElogV) time (looking at BinaryHeap impl, but O(ElogE) using PQ) and O(V+E) space, used for storing the graph
public class Dijkstras {
	public class Node {
		private int id;
		private int key;

		public Node(int id) {
			this.id = id;
			this.key = Integer.MAX_VALUE;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Node node = (Node) o;
			return id == node.id;
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
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

		while(!pq.isEmpty()) {           // O(ElogV) (with PriorityQueue this is actually O(ElogE))
			//Node n = q.pollFirst();
			Node n = pq.poll();
			inQueue.put(n, false);
			for(Edge e : g.graph.get(n)) {
				if(inQueue.get(e.des) && e.des.key > (n.key + e.weight)) {
					// Remove the object before updating it in the priority queue.
					// This a far better way then to avoid using remove() at all.
					if(e.des.key < Integer.MAX_VALUE)
						pq.remove(e.des);
					e.des.key = n.key + e.weight;
					pq.add(e.des);       // O(logV) (with PQ this will be O(logE), coz in worst case, this will be done for all edges)
					// for printing the while path from source to a node,
					// we can also keep track of a parent array, and update
					// the parent[e.des] = n;
				}
			}
		}

		for(Node n : g.graph.keySet()) {
			System.out.println(n.id + " --> " + n.key);
		}

	}
}
